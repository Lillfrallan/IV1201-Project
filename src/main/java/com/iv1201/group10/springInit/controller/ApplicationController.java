package com.iv1201.group10.springInit.controller;

import javax.validation.Valid;

import com.iv1201.group10.springInit.Service.RegistrationService;
import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController {

    @Autowired
    private RegistrationService registrationService;


    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        System.out.println("Controller method called!");
        return "register";
    }

    @PostMapping("/register")
    public String retrieveRegisterPage(@ModelAttribute("person") @Valid Person person, BindingResult result) throws UserAlreadyExistException {
        if (result.hasErrors()) {
            return "register";
        }
        else{
            registrationService.saveUser(person);
            return "redirect:/login";
        }
    }

    /**
     * Handles login request.
     * @return The login view.
     */
    @GetMapping("/login")
    public String serveLoginPage() {
        return "login";
    }


    /**
     * The login form has been submitted.
     * Spring security will override what view is returned based of the context.
     * @return Home page URL.
     */
    @PostMapping("/login")
    public String retrieveLoginPage(BindingResult result) {
        if (result.hasErrors())
            return "login";
        return "redirect:/in";
    }

    @GetMapping("/in") //TODO Change path
    public String getInPage() { //TODO Change name
        return "in";
    }
}
