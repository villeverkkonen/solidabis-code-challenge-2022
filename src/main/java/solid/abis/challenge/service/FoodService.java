package solid.abis.challenge.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import solid.abis.challenge.dto.FoodDTO;
import solid.abis.challenge.mapper.FoodMapper;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
public class FoodService {

    @Resource
    private FoodMapper foodMapper;

    private static final String FOOD_JSON = "json/foods.json";

    public List<FoodDTO> getFoodsFromJson() {
        try (FileReader reader = new FileReader(this.getFileFromResource())) {
            Object obj = new JSONParser().parse(reader);
            JSONArray foods = (JSONArray) obj;
            return foodMapper.jsonArrayToDtoList(foods);
        } catch (IOException | ParseException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFileFromResource() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(FoodService.FOOD_JSON);
        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + FoodService.FOOD_JSON);
        } else {
            return new File(resource.toURI());
        }
    }
}
