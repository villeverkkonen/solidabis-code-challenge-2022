package solid.abis.challenge.mapper;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import solid.abis.challenge.dto.FoodDTO;
import solid.abis.challenge.util.TestMaterial;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FoodMapperTest {

    @Autowired
    FoodMapper foodMapper;
    @Autowired
    TestMaterial testMaterial;

    @Test
    public void shouldReturnListOfDTOs() throws URISyntaxException, IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("json/foods.json");
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object obj = new JSONParser().parse(reader);
        JSONArray foods = (JSONArray) obj;

        List<FoodDTO> result = foodMapper.jsonArrayToDtoList(foods);
        result.sort(Comparator.comparing(FoodDTO::getName));

        assertTrue(testMaterial.twoFoodDTOsEquals(testMaterial.buildPineapple(), result.get(0)));
        assertTrue(testMaterial.twoFoodDTOsEquals(testMaterial.buildBanana(), result.get(1)));
        assertTrue(testMaterial.twoFoodDTOsEquals(testMaterial.buildApple(), result.get(2)));
    }
}
