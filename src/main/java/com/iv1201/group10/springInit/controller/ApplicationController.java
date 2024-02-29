package com.iv1201.group10.springInit.controller;

import com.iv1201.group10.springInit.Service.ApplyService;
import com.iv1201.group10.springInit.Service.CompetenceProfileService;
import com.iv1201.group10.springInit.Service.RecruitmentService;
import com.iv1201.group10.springInit.Service.RegistrationService;
import com.iv1201.group10.springInit.Service.interfaces.CompetenceService;
import com.iv1201.group10.springInit.entity.Competence;
import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.exceptions.UserAlreadyExistException;
import com.iv1201.group10.springInit.security.PersonPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
     * @param yearsStr        The years of experience (optional).
     * @param model        The model to which attributes will be added for rendering in the view.
     * @return The name of the Thymeleaf template to be rendered for the recruitment page.
     */
    @GetMapping("/recruiter")
    public String showRecruitmentPage(@RequestParam(name = "competenceId", required = false) Integer competenceId,
                                      @RequestParam(name = "years", required = false) String yearsStr,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        // Retrieve all competences
        List<Competence> competences = recruitmentService.getAllCompetences();
        // Add competences to the model
        model.addAttribute("competences", competences);

        // Declare a list to store retrieved profiles
        List<CompetenceProfile> profiles;

        Double years = null;

        // Validate input format
        if (yearsStr != null && !yearsStr.isEmpty()) {
            // Check if the input contains a decimal point
            if (!yearsStr.contains(".")) {
                // Append ".0" to the input string
                yearsStr += ".0";
            }
            // Check if the input matches the format #.1f
            if (!yearsStr.matches("\\d*\\.\\d{1}")) {
                redirectAttributes.addFlashAttribute("failedmessage", "Enter a decimal, for example, 0.7!");
                return "redirect:/recruiter"; // Redirect back to the recruiter page
            }
            try {
                years = Double.parseDouble(yearsStr);
            } catch (NumberFormatException e) {
                // Handle invalid input
                // For example, you can log an error or provide a default value
                // In this case, we'll ignore the input and proceed without filtering by years
            }
        }

        // Filter profiles based on competence ID and years of experience if filters are provided
        if (competenceId != null || years != null) {
            if (competenceId != null) {
                // If competenceId is not null, retrieve profiles filtered by competenceId only
                profiles = recruitmentService.getProfilesByCompetenceProfileId(competenceId);
            } else {
                // If competenceId is null, retrieve profiles filtered by years of experience only
                profiles = recruitmentService.getProfilesByYearsOfExperience(years);
            }
        } else {
            // If both competenceId and years are null, retrieve all competence profiles
            profiles = recruitmentService.getAllCompetenceId();
        }

        // Add selected years and competence ID to the model
        model.addAttribute("selectedYears", years);
        model.addAttribute("selectedCompetenceId", competenceId);

        // Add retrieved profiles to the model
        model.addAttribute("profiles", profiles);

        // Return the name of the Thymeleaf template for rendering
        return "recruiter";
    }




    /**
     * Retrieves the competence profile with the specified profile ID and prepares the update status page.
     *
     * @param profileId The ID of the competence profile to update.
     * @param model     The model to add attributes to for rendering the view.
     * @return The name of the Thymeleaf template for the update status page.
     */
    @GetMapping("/updateStatus/{profileId}")
    public String showUpdateStatusPage(@PathVariable("profileId") Integer profileId, Model model) {
        // Retrieve the competence profile by its ID
        CompetenceProfile competenceProfile = recruitmentService.getCompetenceProfileById(profileId);

        // Add the competence profile and its status to the model
        model.addAttribute("competenceProfile", competenceProfile);
        model.addAttribute("status", competenceProfile.getStatus());

        return "updateStatus";
    }



    /**
     * Updates the status of a competence profile with the specified profile ID.
     *
     * @param profileId The ID of the competence profile to update.
     * @param status    The new status to set for the competence profile.
     * @param redirectAttributes The redirect attributes object to add flash attributes
     * @return A redirect to the recruiter page after updating the status.
     */
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("profileId") Integer profileId,
                               @RequestParam("status") String status,
                               RedirectAttributes redirectAttributes) {
        recruitmentService.updateStatus(profileId, status);
        // Add success message as flash attribute
        redirectAttributes.addFlashAttribute("successMessage", "Status saved successfully!");
        return "redirect:/recruiter"; // Redirect back to the recruiter page after updating status
    }
    /**
     * Handles the GET request for displaying the status page.
     * Retrieves competence profiles filtered by status and populates the model with necessary data.
     *
     * @param model        the model to be populated with data for rendering the view
     * @param status       the status to filter competence profiles by, can be null if not specified
     * @param competenceId the ID of the competence to filter by, can be null if not specified
     * @return the name of the Thymeleaf template for the status page
     */
    @GetMapping("/status")
    public String showStatusPage(Model model,
                                 @RequestParam(name = "status", required = false) String status,
                                 @RequestParam(name = "competenceId", required = false) Integer competenceId) {
        // Retrieve competence profiles by status
        List<CompetenceProfile> profiles = recruitmentService.getProfilesByStatus(status);

        // Retrieve all competences
        List<Competence> competences = recruitmentService.getAllCompetences();

        // Add retrieved data to the model
        model.addAttribute("profiles", profiles);
        model.addAttribute("competences", competences);
        model.addAttribute("selectedCompetenceId", competenceId);
        model.addAttribute("status", status); // Add the 'status' attribute to the model

        return "status"; // Return the name of your Thymeleaf template for the status page
    }

    /**
     * Displays the availability page.
     *
     * @return The name of the Thymeleaf template to be rendered for availability.
     */
    @GetMapping("/availability")
    public String showAvailabilityPage(Model model) {
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
    public String serveAvailabilityPage(@RequestParam Date fromDate,
                                        @RequestParam Date toDate,
                                        Model model) {

        if (toDate.before(fromDate)) {
            model.addAttribute("error", "To Date cannot be earlier than From Date");
        } else {
            applyService.saveAvailability(fromDate, toDate);
            model.addAttribute("success", "Dates added successfully!");
        }

        return showAvailabilityPage(model);
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
                                   @RequestParam("year_experience") Integer yearsOfExperience,
                                   @RequestParam("month_experience") Integer monthOfExperience,
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
                competenceProfile.setYearsOfExperience(competenceService.combineExperience(yearsOfExperience, monthOfExperience));
                competenceProfile.setStatus("unhandled");

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