package kq.practice.assessmentworkshop.controller;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import kq.practice.assessmentworkshop.config.UtilisConfig;
import kq.practice.assessmentworkshop.model.Request;
import kq.practice.assessmentworkshop.service.SearchService;

@RestController
@RequestMapping(path = "/search")
public class SearchController {

    @Autowired
    SearchService svc;

    @PostMapping(path = "/news")
    public ModelAndView postSearchNews(@ModelAttribute Request request) {

        ModelAndView mav = new ModelAndView("view2");

        String[] result = svc.getNews(request);

        switch (result[0]) {
            case "true": mav.addObject("cached", true);
            break;
            default: mav.addObject("cached", false);
        }

        JsonReader jReader = Json.createReader(new StringReader(result[1]));
        JsonObject jObject = jReader.readObject();

        if (jObject.getInt("totalResults") == 0) {
            mav.addObject("notfound", true);
            mav.addObject("country", UtilisConfig.getCountry(request.getCode()));
            mav.addObject("category", request.getCategory());

            return mav;
            }
            
        JsonArray articles = jObject.get("articles").asJsonArray();
        mav.addObject("articles", svc.getArticles(articles));
        return mav;
    }
}
