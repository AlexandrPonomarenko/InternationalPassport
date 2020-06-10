package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Photo;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.FileLoader;
import com.InternationalPassport.security.UserDetailsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    private static final Logger logger = LogManager.getLogger(UploadController.class);
    private FileLoader fl = new FileLoader();

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.GET)
    public String showUploadImage(Model model) {
        return "uploadImage";
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Sorry file not upload");
            return "redirect:uploadImage";
        }

        uploadFile(file);

        if (!fl.isStatusLoad()) {
            redirectAttributes.addFlashAttribute("message", "Sorry file not upload");
            return "redirect:uploadImage";
        }
        redirectAttributes.addFlashAttribute("message", "SGood load bro" + file.getOriginalFilename());
        updateCustomer();
        return "redirect:uploadImage";
    }

    private void uploadFile(MultipartFile file) {
        fl.setDirName(getAuthCustomer().getLogin());
        fl.setMultipartFile(file);
        fl.setFileName(file.getOriginalFilename());
        fl.loadFile();
    }

    private Customer getAuthCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findByIdInitAll((((UserDetailsImpl) authentication.getPrincipal()).getCust()).getId());
        logger.debug(customer.toStringLogin());
        return customer;
    }

    private void updateCustomer() {
        Customer customer = getAuthCustomer();
        Photo photo = null;
        if (customer.getPhotos().size() < 1) {
            photo = new Photo();
            photo.setCustomer(customer);
            photo.setPathFile(fl.getPath().toString());
            photo.setFileName(fl.getFileName());
            photo.setRightPath(fl.getRightPath());
            photo.setStatusLoad(true);
            customer.getPhotos().add(photo);
        } else {
            photo = customer.getPhotos().get(0);
            photo.setPathFile(fl.getPath().toString());
            photo.setFileName(fl.getFileName());
            photo.setRightPath(fl.getRightPath());
        }
        customerService.update(customer);
    }
}
