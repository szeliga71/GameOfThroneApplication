package pl.wp.gameofthroneapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wp.gameofthroneapplication.databaseutils.CustomUserDao;
import pl.wp.gameofthroneapplication.model.CustomUser;
import pl.wp.gameofthroneapplication.model.CustomUserSearchObject;

@Controller
public class DeletePageController {

    private CustomUserDao customUserDao = new CustomUserDao();

  /*  @GetMapping("/deletePage")
    public String getDeletePage(Model model){
        model.addAttribute("customUserSearchObject" ,new CustomUserSearchObject());
        model.addAttribute("users",customUserDao.getAllUsers());
        return "deletePage";
    }
    @PostMapping("/deletePage")
    public String deleteUser(Model model,CustomUserSearchObject customUserSearchObject){
        String email = customUserSearchObject.getEmail();
        CustomUser user= customUserDao.findUserByEmail(email);

        if (user != null) {
            customUserDao.deleteUserByEmail(email);  // Usuń użytkownika, jeśli istnieje
            model.addAttribute("message", "Użytkownik o adresie email " + email + " został usunięty.");
        } else {
            model.addAttribute("error", "Użytkownik o adresie email " + email + " nie został znaleziony.");
        }
        return "deletePage";
    }*/

}
