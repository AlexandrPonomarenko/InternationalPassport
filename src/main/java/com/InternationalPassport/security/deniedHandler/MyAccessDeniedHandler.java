package com.InternationalPassport.security.deniedHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private final Logger logger = LogManager.getLogger(MyAccessDeniedHandler.class);


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        logger.debug(DispatcherServlet.DEFAULT_CONTEXT_CLASS + "DEBUG from MyAccessDeniedHandler --> " + e.getMessage());
//        response.sendRedirect( request.getContextPath() + "/error");

        logger.debug("contexPath" +  request.getContextPath());
        logger.debug("getRequestURL()" +  request.getRequestURL());
        logger.debug("getRequestURI()" +  request.getRequestURI());
        request.setAttribute("directQuery", true);
        request.setAttribute("user" , "Sorry " + request.getUserPrincipal().getName() + " but");
        request.setAttribute("accessDenied", e.getMessage());
        request.getRequestDispatcher("error").forward(request, response);
    }

//    private ModelAndView forwardToErrorPage(HttpServletRequest httpServletRequest) {
//        ModelAndView modelAndView = new ModelAndView();
//        logger.debug("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        modelAndView.setViewName(httpServletRequest.getContextPath() + "/error");
//        return modelAndView;
//    }


}
