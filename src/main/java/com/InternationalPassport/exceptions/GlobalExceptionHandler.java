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

    @ExceptionHandler(NoHandlerFoundException.class)
    private ModelAndView notFoundPage(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException ex) throws IOException {
        ModelAndView errorPage = new ModelAndView("errorPage/error");

        errorPage.addObject("searchPassportForm", searchPassportForm);
        request.setAttribute("directQuery", true);
        request.setAttribute("error", ex.getMessage());
        return errorPage;
    }
}
