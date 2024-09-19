package pl.wp.gameofthroneapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wp.gameofthroneapplication.databaseutils.CustomUserDao;
import pl.wp.gameofthroneapplication.model.CustomUser;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String getLoginPage() {
        return"security/login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("customUser", new CustomUser());
        return"security/register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("customUser") CustomUser user, BindingResult result) {

        CustomUserDao userDao = new CustomUserDao();
        if (userDao.findUserByEmail(user.getEmail()) != null) {
            result.rejectValue("confirmedPassword", "error.customUser", "Email is already in use");
            return "security/register";
        }
        if (user.getPassword() == null || user.getConfirmedPassword() == null ||
                !user.getPassword().equals(user.getConfirmedPassword())) {
            result.rejectValue("confirmedPassword", "error.customUser", "Passwords do not match");
            return "security/register";
        }
        if (result.hasErrors()) {
            return "security/register";
        }

        userDao.saveUser(user);
        return "security/login";
    }
}