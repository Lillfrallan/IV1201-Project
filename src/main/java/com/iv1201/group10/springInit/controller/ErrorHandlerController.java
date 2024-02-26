package com.iv1201.group10.springInit.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ControllerAdvice
public class ErrorHandlerController implements ErrorController {

    @GetMapping("/error")
    public String errorHandler(HttpServletRequest request, Model model) {
        model.addAttribute("status", request.getAttribute("jakarta.servlet.error.status_code").toString());
        model.addAttribute("message", request.getAttribute("jakarta.servlet.error.message").toString());
        return "error";
    }
}