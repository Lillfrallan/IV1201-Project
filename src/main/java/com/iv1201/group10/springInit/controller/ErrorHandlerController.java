package com.iv1201.group10.springInit.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ControllerAdvice
public class ErrorHandlerController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerController.class);

    @GetMapping("/error")
    public String errorHandler(HttpServletRequest request, Model model) {
        // Get status code and message from request attributes
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");

        // Log error details
        logger.error("Error occurred: Status Code={}, Message={}", statusCode, errorMessage);

        // Add status code and message to the model if they are not null
        if (statusCode != null) {
            model.addAttribute("status", statusCode.toString());
        }
        if (errorMessage != null) {
            model.addAttribute("message", errorMessage);
        }

        // Return the error template
        return "error";
    }
}