package kq.practice.assessmentworkshop.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import kq.practice.assessmentworkshop.config.UtilisConfig;
import kq.practice.assessmentworkshop.model.Article;
import kq.practice.assessmentworkshop.model.Request;
import kq.practice.assessmentworkshop.repository.SearchRepo;

@Service
public class SearchService {

    @Autowired
    SearchRepo repo;

    RestTemplate template = new RestTemplate();

    public String[] getNews(Request request) {

        if (repo.exists(request)) {

            String[] arr = { "true", repo.getNews(request) };
            return arr;
        }

        String url = "%scountry=%s&category=%s&apiKey=%s".formatted(UtilisConfig.newsURL, request.getCode(),
                request.getCategory(), UtilisConfig.apiKey);

        String result = template.getForObject(url, String.class);
        repo.save(request, result);

        String[] arr = { "false", repo.getNews(request) };
        return arr;

    }

    public List<Article> getArticles(JsonArray articles) {

        List<JsonObject> list = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {
            JsonReader jReader = Json.createReader(new StringReader(articles.get(i).toString()));
            list.add(jReader.readObject());
        }

        List<Article> articleObj = new ArrayList<>();

        for (JsonObject jO : list) {
            articleObj.add(new Article(
                    jO.get("title").toString().substring(1, jO.get("title").toString().length() - 1),
                    jO.get("urlToImage").toString().substring(1, jO.get("urlToImage").toString().length() - 1),
                    jO.get("author").toString().substring(1, jO.get("author").toString().length() - 1),
                    jO.get("description").toString().substring(1, jO.get("description").toString().length() - 1),
                    jO.get("publishedAt").toString().substring(1, jO.get("publishedAt").toString().length() - 1),
                    jO.get("url").toString().substring(1, jO.get("url").toString().length() - 1)));
        }

        return articleObj;
    }
}
