package pl.wp.gameofthroneapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wp.gameofthroneapplication.databaseutils.CustomUserDao;
import pl.wp.gameofthroneapplication.model.CharacterSearchObject;
import pl.wp.gameofthroneapplication.model.CustomUser;
import pl.wp.gameofthroneapplication.model.CustomUserSearchObject;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CustomUserDao customUserDao = new CustomUserDao();


    @GetMapping("/userList")
    public String getUserList(Model model) {
        model.addAttribute("customUserSearchObject" ,new CustomUserSearchObject());
        model.addAttribute("users", customUserDao.getAllUsers());
        return "admin/userList";
    }

    @PostMapping("/userList")
    public String deleteUser(Model model,CustomUserSearchObject customUserSearchObject){
        String email = customUserSearchObject.getEmail();
        CustomUser user= customUserDao.findUserByEmail(email);
        if (user != null) {
            customUserDao.deleteUserByEmail(email);
            model.addAttribute("message", "Użytkownik o adresie email " + email + " został usunięty.");
        } else {
            model.addAttribute("error", "Użytkownik o adresie email " + email + " nie został znaleziony.");
        }
        return "admin/userList";
    }
    /*@PostMapping("/userList")
    public String deleteUser(String email,Model model) {
        model.addAttribute("email", email);
        customUserDao.deleteUserByEmail(email);
        return "admin/deleteUser";
    }*/

}
