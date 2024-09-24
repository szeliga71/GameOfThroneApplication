package pl.wp.gameofthroneapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wp.gameofthroneapplication.databaseutils.CustomUserDao;
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
        return "admin/deleteUser";
    }

    //==========================================
    @PostMapping("/changeRole")
    public String changeUserRole(@RequestParam("email") String email, @RequestParam("role") String role, Model model) {
        CustomUser user = customUserDao.findUserByEmail(email);
        if (user != null) {
            user.setRole(role);
            customUserDao.saveUser(user);  // Zapisanie zmian w bazie danych
            model.addAttribute("message", "Rola użytkownika o adresie " + email + " została zmieniona na " + role + ".");
        } else {
            model.addAttribute("error", "Nie znaleziono użytkownika o adresie " + email);
        }
        return "redirect:/admin/userList";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam("email") String email, @RequestParam("password") String password,@RequestParam("password") String confirmedPassword,
                          @RequestParam("role") String role, Model model) {
        CustomUser existingUser = customUserDao.findUserByEmail(email);

        if (existingUser == null) {
            CustomUser newUser = new CustomUser();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setRole(role);
            customUserDao.saveUser(newUser);
            model.addAttribute("message", "Nowy użytkownik " + email + " został dodany.");
        } else {
            model.addAttribute("error", "Użytkownik o adresie " + email + " już istnieje.");
        }
        return "redirect:/admin/userList";
    }
}