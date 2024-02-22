package com.iv1201.group10.springInit.controller;

import com.iv1201.group10.springInit.Service.*;
import com.iv1201.group10.springInit.Service.interfaces.CompetenceService;
import com.iv1201.group10.springInit.entity.Competence;
import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.security.PersonPrincipal;
import com.iv1201.group10.springInit.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

/**
 * Controller handling various actions related to application processes such as
 * registration, login, recruitment and competence management.
 */
@Controller
public class ApplicationController {

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private CompetenceProfileService competenceProfileService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private ApplyService applyService;

    /**
     * Displays the registration form.
     *
     * @param model The model to be populated with data for rendering.
     * @return The name of the Thymeleaf template to be rendered for registration.
     */
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    /**
     * Processes the registration form submission.
     *
     * @param person The person object representing the user registration.
     * @param result The binding result for validation.
     * @return The redirection URL after successful registration or the registration form if there are errors.
     * @throws UserAlreadyExistException if the user already exists.
     */
    @PostMapping("/register")
    public String retrieveRegisterPage(@ModelAttribute("person") @Valid Person person, BindingResult result) throws UserAlreadyExistException {
        if (result.hasErrors()) {
            return "register";
        } else {
            registrationService.saveUser(person);
            return "redirect:/login";
        }
    }

    /**
     * Displays the login page.
     *
     * @return The name of the Thymeleaf template to be rendered for login.
     */
    @GetMapping("/login")
    public String serveLoginPage() {
        return "login";
    }


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

    /**
     * Displays the availability page.
     *
     * @return The name of the Thymeleaf template to be rendered for availability.
     */
    @GetMapping("/availability")
    public String showAvailabilityPage() {
        return "availability";
    }

    /**
     * Processes the availability form submission.
     *
     * @param fromDate The start date of availability.
     * @param toDate   The end date of availability.
     * @return The name of the Thymeleaf template to be rendered for availability.
     */
    @PostMapping("/availability")
    public String serveAvailabilityPage(@RequestParam Date fromDate, @RequestParam Date toDate) {
        applyService.saveAvailability(fromDate, toDate);
        return "availability";
    }

    /**
     * Displays the competence form.
     *
     * @param model The model to be populated with data for rendering.
     * @return The name of the Thymeleaf template to be rendered for competence.
     */
    @GetMapping("/competence")
    public String showCompetenceForm(Model model) {
        model.addAttribute("competence", new Competence());
        return "competence";
    }

    /**
     * Processes the submission of competence form.
     *
     * @param competencyName        The name of the competence.
     * @param yearsOfExperience     The years of experience in the competence.
     * @param redirectAttributes    Attributes for redirection.
     * @return The redirection URL after saving competence or error page if an exception occurs.
     */
    @PostMapping("/competence")
    public String saveCompetencies(@RequestParam("name") String competencyName,
                                   @RequestParam("experience") int yearsOfExperience,
                                   RedirectAttributes redirectAttributes) {
        try {
            // Retrieve the currently authenticated user's PersonPrincipal
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof PersonPrincipal principal) {
                // Find the Competence object by name
                Competence competence = competenceService.getCompetenceByName(competencyName).orElse(null);

                // Create a new CompetenceProfile object
                CompetenceProfile competenceProfile = new CompetenceProfile();
                competenceProfile.setPerson(principal.getPerson());
                competenceProfile.setCompetence(competence);
                competenceProfile.setYearsOfExperience(yearsOfExperience);

                // Save the CompetenceProfile object
                competenceProfileService.saveCompetenceProfile(competenceProfile);

                redirectAttributes.addFlashAttribute("successMessage"
                        , "Competencies saved successfully!");
                // Redirect to a success page or another appropriate location
                return "redirect:/competence";
            } else {
                // Handle the case where the user is not authenticated or the principal is not a PersonPrincipal
                return "redirect:/error";
            }
        } catch (Exception ex) {
            // Handle unexpected exceptions
            ex.printStackTrace();
            return "redirect:/error";
        }
    }
}