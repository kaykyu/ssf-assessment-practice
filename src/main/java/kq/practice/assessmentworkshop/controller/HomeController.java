package kq.practice.assessmentworkshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kq.practice.assessmentworkshop.config.UtilisConfig;
import kq.practice.assessmentworkshop.model.Request;

@Controller
@RequestMapping(path = { "/", "/index" })
public class HomeController {

    @GetMapping
    public String getHome(Model model) {

        model.addAttribute("request", new Request());
        model.addAttribute("category", UtilisConfig.getCategories());
        model.addAttribute("country", UtilisConfig.getCountryCodes());
        return "view1";
    }

}
