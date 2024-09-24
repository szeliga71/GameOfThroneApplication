package pl.wp.gameofthroneapplication.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class LogoutController {
    @GetMapping("/logoutPage")
    public String logoutPage(@RequestParam(value = "user", required = false) String user, Model model) {
        if (user != null) {
            model.addAttribute("message", "Użytkownik " + user + " został pomyślnie wylogowany.");
        } else {
            model.addAttribute("message", "Zostałeś pomyślnie wylogowany.");
        }
        return "logoutPage";
    }
}
