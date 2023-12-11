package kq.practice.assessmentworkshop.config;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Configuration
public class UtilisConfig {

    public static String apiKey = "1b64454e63364be681134eeb68ed71aa";

    public static String newsURL = "https://newsapi.org/v2/top-headlines?";
    public static String countryURL = "https://restcountries.com/v3.1/alpha/";

    private static List<String> codes;
    private static List<String> categories;
    private static Map<String, String> countryCodes;

    public static void setCodes() {

        List<String> list = new ArrayList<>();
        list.add("ae");
        list.add("ar");
        list.add("at");
        list.add("au");
        list.add("be");
        list.add("bg");
        list.add("br");
        list.add("ca");
        list.add("ch");
        list.add("cn");
        list.add("co");
        list.add("cu");
        list.add("cz");
        list.add("de");
        list.add("eg");
        list.add("fr");
        list.add("gb");
        list.add("gr");
        list.add("hk");
        list.add("hu");
        list.add("id");
        list.add("ie");
        list.add("il");
        list.add("in");
        list.add("it");
        list.add("jp");
        list.add("kr");
        list.add("lt");
        list.add("lv");
        list.add("ma");
        list.add("mx");
        list.add("my");
        list.add("ng");
        list.add("nl");
        list.add("no");
        list.add("nz");
        list.add("ph");
        list.add("pl");
        list.add("pt");
        list.add("ro");
        list.add("rs");
        list.add("ru");
        list.add("sa");
        list.add("se");
        list.add("sg");
        list.add("si");
        list.add("sk");
        list.add("th");
        list.add("tr");
        list.add("tw");
        list.add("ua");
        list.add("us");
        list.add("ve");
        list.add("za");

        codes = list;
    }

    public static List<String> getCategories() {

        if (categories == null) {
            setCategories();
        }

        return categories;
    }

    public static void setCategories() {

        List<String> list = new ArrayList<>();

        list.add("Business");
        list.add("Entertainment");
        list.add("General");
        list.add("Health");
        list.add("Science");
        list.add("Sports");
        list.add("Technology");

        categories = list;
    }

    public static String getCountry(String code) {

        RestTemplate template = new RestTemplate();

        String url = countryURL + code;
        String result = template.getForObject(url, String.class);
        JsonReader jReader = Json.createReader(new StringReader(result));
        JsonArray jArray = jReader.readArray();
        JsonObject jObject = jArray.getJsonObject(0).get("name").asJsonObject();

        return jObject.getString("common");
    }

    public static Map<String, String> getCountryCodes() {

        if (countryCodes == null) {
            setCodes();
            setCountryCodes();
        }

        return countryCodes;
    }

    public static void setCountryCodes() {

        Map<String, String> map = new HashMap<>();

        for (String country : codes) {

            map.put(getCountry(country), country);
        }

        Map<String, String> sorted = new TreeMap<>();
        sorted.putAll(map);
        countryCodes = sorted;
    }

}
