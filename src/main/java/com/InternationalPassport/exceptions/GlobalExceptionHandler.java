package com.InternationalPassport.exceptions;

import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
    private SearchPassportForm searchPassportForm = new SearchPassportForm();

//    @ExceptionHandler(NoHandlerFoundException.class)
//    private String notFoundPage(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException ex) {
////        int errorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//
////        switch (errorCode) {
////            case 400: {
////                errorMsg = "Http Error Code: 400. Bad Request";
////                break;
////            }
////            case 401: {
////                errorMsg = "Http Error Code: 401. Unauthorized";
////                break;
////            }
////            case 404: {
////                errorMsg = "Http Error Code: 404. Resource not found";
////                break;
////            }
////            case 500: {
////                errorMsg = "Http Error Code: 500. Internal Server Error";
////                break;
////            }
////        }
////        logger.debug(errorMsg + " GlobalExceptionHandler => WAS EXCEPTION " + ex.getHttpMethod());
////        logger.debug(errorMsg + " GlobalExceptionHandler => WAS EXCEPTION2  " + ex.getRequestURL());
////        logger.debug(errorMsg + " GlobalExceptionHandler => WAS EXCEPTION3 " + ex.getHeaders());
//        logger.debug(" GlobalExceptionHandler => WAS EXCEPTION redirect " + ex.getMessage());
//
//        return "redirect:/error";
//    }

    @ExceptionHandler(NoHandlerFoundException.class)
    private ModelAndView notFoundPage(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException ex) throws IOException {
        ModelAndView errorPage = new ModelAndView("errorPage/error");
//        String errorMsg = "";

//        logger.debug(errorMsg + " GlobalExceptionHandler => WAS EXCEPTION " + ex.getHttpMethod());
//        logger.debug(errorMsg + " GlobalExceptionHandler => WAS EXCEPTION2  " + ex.getRequestURL());
//        logger.debug(errorMsg + " GlobalExceptionHandler => WAS EXCEPTION3 " + ex.getHeaders());
        logger.debug(" GlobalExceptionHandler => WAS EXCEPTION twoo " + ex.getMessage());

        errorPage.addObject("searchPassportForm", searchPassportForm);
        request.setAttribute("directQuery", true);
        request.setAttribute("error", ex.getMessage());
        return errorPage;
    }
}
