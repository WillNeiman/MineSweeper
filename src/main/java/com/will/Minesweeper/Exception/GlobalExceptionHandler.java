package com.will.Minesweeper.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception{
        ModelAndView mav = new ModelAndView();

        mav.addObject("message", e.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
