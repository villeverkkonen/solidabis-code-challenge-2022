package solid.abis.challenge.controller;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private static final String FOOD_JSON = "json/foods.json";

    @GetMapping("")
    public String foods() {
        FoodController app = new FoodController();
        try (FileReader reader = new FileReader(app.getFileFromResource(FOOD_JSON))) {
            Object obj = new JSONParser().parse(reader);
            JSONArray foods = (JSONArray) obj;
            return foods.toString();
        } catch (IOException | ParseException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }
}
