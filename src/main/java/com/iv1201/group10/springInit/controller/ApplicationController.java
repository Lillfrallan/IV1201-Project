package com.iv1201.group10.springInit.controller;

import javax.validation.Valid;

//import com.iv1201.group10.springInit.Service.LoginService;
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
  //  @Autowired
   // private LoginService loginService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("person", new Person());
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
    public String retriveLoginPage() {
        return "redirect:/index";
    }

   /** @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("person", new Person());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("person") @Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        } else {
            // Call loginService to handle login logic
            boolean loginSuccessful = loginService.authenticateUser(person.getUsername(), person.getPassword());
            if (loginSuccessful) {
                return "redirect:/home"; // Redirect to the dashboard or home page
            } else {
                result.rejectValue("username", "error.loginForm", "Invalid username or password");
                return "login";
            }
        }
    }*/
}
