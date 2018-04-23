package ro.siit.mytripsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }
}