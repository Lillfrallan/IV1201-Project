package com.iv1201.group10.springInit.controller;


import com.iv1201.group10.springInit.Service.RecruitmentService;
import com.iv1201.group10.springInit.Service.RegistrationService;
import com.iv1201.group10.springInit.entity.Competence;

import com.iv1201.group10.springInit.entity.Competence;
import com.iv1201.group10.springInit.Service.CompetenceService;
import com.iv1201.group10.springInit.Service.CompetenceProfileService;

import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.exceptions.UserAlreadyExistException;
import com.iv1201.group10.springInit.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class ApplicationController {

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private CompetenceProfileService competenceProfileService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("/register")
    public String retrieveRegisterPage(@ModelAttribute("person") @Valid Person person, BindingResult result) throws UserAlreadyExistException {
        if (result.hasErrors()) {
            return "register";
        } else {
            registrationService.saveUser(person);
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String serveLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String retrieveLoginPage(BindingResult result) {
        if (result.hasErrors())
            return "login";
        return "redirect:/in";
    }

    @GetMapping("/in")
    public String getInPage() {
        return "in";
    }


    @GetMapping("/admin") //TODO Change path
    public String getAdminPage() { //TODO Change name
        return "admin";
    }


    @Autowired
    private RecruitmentService recruitmentService;

    /**
     * Handles the GET request to display the recruitment page with filters for competence and years of experience.
     * Retrieves a list of competences and competence profiles based on the provided filters (if any) and adds them to the model.
     *
     * @param competenceId The ID of the selected competence (optional).
     * @param years        The years of experience (optional).
     * @param model        The model to which attributes will be added for rendering in the view.
     * @return The name of the Thymeleaf template to be rendered for the recruitment page.
     */
    @GetMapping("/recruiter")
    public String showRecruitmentPage(@RequestParam(name = "competenceId", required = false) Integer competenceId,
                                      @RequestParam(name = "years", required = false) Integer years,
                                      Model model) {
        // Retrieve all competences
        List<Competence> competences = recruitmentService.getAllCompetences();
        // Add competences to the model
        model.addAttribute("competences", competences);

        // Declare a list to store retrieved profiles
        List<CompetenceProfile> profiles;

        // Check if years of experience filter is provided
        if (years != null) {
            // Check if competence filter is also provided
            if (competenceId != null) {
                // Retrieve profiles filtered by both competence ID and years of experience
                profiles = recruitmentService.getProfilesByCompetenceIdAndYears(competenceId, years);
                // Add selected years and competence ID to the model
                model.addAttribute("selectedYears", years);
                model.addAttribute("selectedCompetenceId", competenceId);
            } else {
                // Retrieve profiles filtered by years of experience only
                profiles = recruitmentService.getProfilesByYearsOfExperience(years);
                // Add selected years to the model
                model.addAttribute("selectedYears", years);
                // Add null competence ID to the model (no competence selected)
                model.addAttribute("selectedCompetenceId", null);
            }
        } else if (competenceId != null) {
            // Retrieve profiles filtered by competence ID only
            profiles = recruitmentService.getProfilesByCompetenceProfileId(competenceId);
            // Add selected competence ID to the model
            model.addAttribute("selectedCompetenceId", competenceId);
        } else {
            // No filters provided, retrieve all competence profiles
            profiles = recruitmentService.getAllCompetenceId();
            // Add null competence ID to the model (no competence selected)
            model.addAttribute("selectedCompetenceId", null);
        }

        // Add retrieved profiles to the model
        model.addAttribute("profiles", profiles);
        // Return the name of the Thymeleaf template for rendering
        return "recruiter";
    }


    @GetMapping("/competence")
    public String showCompetenceForm(Model model) {
        model.addAttribute("competence", new Competence());
        return "competence";
    }
    @PostMapping("/competence")
    public String saveCompetencies(@RequestParam("name") String competencyName, @RequestParam("experience") int yearsOfExperience) {
        try {
            // Validate input parameters
            if (competencyName == null || competencyName.isEmpty() || yearsOfExperience <= 0) {
                // Handle invalid input
                return "redirect:/error";
            }

            // Log the received competency name and years of experience
            System.out.println("Received competency name: " + competencyName);
            System.out.println("Received years of experience: " + yearsOfExperience);

            // Find the Competence object by name
            Competence competence = competenceService.getCompetenceByName(competencyName)
                    .orElse(null);

            // Check if the Competence object exists
            if (competence != null) {
                // Log the found competence
                System.out.println("Found competence: " + competence.getName());


                // Create a new CompetenceProfile object
                CompetenceProfile competenceProfile = new CompetenceProfile();

                // Set the Competence object, years of experience, and Person object in the CompetenceProfile object
                competenceProfile.setCompetence(competence);
                competenceProfile.setYearsOfExperience(yearsOfExperience);


                // Log the CompetenceProfile object before saving
                System.out.println("CompetenceProfile before saving: " + competenceProfile);

                // Save the CompetenceProfile object
                competenceProfileService.saveCompetenceProfile(competenceProfile);

                // Log a success message
                System.out.println("CompetenceProfile saved successfully");

                return "redirect:/in"; // Redirect to some page after saving competencies
            } else {
                // Handle the case where the Competence object is not found
                // You can redirect to an error page or do something else
                System.out.println("Competence not found");
                return "redirect:/error"; // Redirect to an error page
            }
        } catch (Exception ex) {
            // Handle unexpected exceptions
            ex.printStackTrace();
            return "redirect:/error"; // Redirect to an error page
        }

    }








}
