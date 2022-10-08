package solid.abis.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solid.abis.challenge.dto.FoodDTO;
import solid.abis.challenge.mapper.FoodMapper;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Resource
    private FoodMapper foodMapper;

    private static final String FOOD_JSON = "json/foods.json";

    @GetMapping("")
    public List<FoodDTO> getFoods() {
        try (FileReader reader = new FileReader(this.getFileFromResource(FOOD_JSON))) {
            Object obj = new JSONParser().parse(reader);
            JSONArray foods = (JSONArray) obj;
            return foodMapper.jsonArrayToDtoList(foods);
        } catch (IOException | ParseException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFileFromResource(final String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }
}
