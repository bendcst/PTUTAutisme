package AutisMono.controller;

import AutisMono.entity.Medecin;
import AutisMono.entity.Patient;
import AutisMono.entity.Utilisateur;
import AutisMono.service.SecurityService;
import AutisMono.service.UserService;
import AutisMono.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginAndRegistrationController {
    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    public LoginAndRegistrationController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registrationpatient")
    public String registrationpatient(Model model) {
        model.addAttribute("userForm", new Patient());

        return "registration";
    }

    @PostMapping("/registrationpatient")
    public String registrationpatient(@Valid @ModelAttribute("userForm") Patient userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.savepatient(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    
    @GetMapping("/registrationmedecin")
    public String registrationmedecin(Model model) {
        model.addAttribute("userForm", new Medecin());

        return "registrationmedecin";
    }

    @PostMapping("/registrationmedecin")
    public String registrationmedecin(@Valid @ModelAttribute("userForm") Medecin userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registrationmedecin";
        }

        userService.savemedecin(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");

        if (logout != null)
            model.addAttribute("message", "Vous avez été déconnecté.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}