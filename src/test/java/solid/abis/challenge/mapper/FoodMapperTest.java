package solid.abis.challenge.mapper;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import solid.abis.challenge.dto.FighterFoodDTO;
import solid.abis.challenge.dto.FoodDTO;
import solid.abis.challenge.util.TestUtil;

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
    TestUtil testUtil;

    @Test
    public void shouldReturnListOfDTOs() throws URISyntaxException, IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("json/foods.json");
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object obj = new JSONParser().parse(reader);
        JSONArray foods = (JSONArray) obj;

        List<FoodDTO> foodDTOList = foodMapper.jsonArrayToFoodDTOList(foods);
        List<FighterFoodDTO> fighterFoodDTOList = foodDTOList.stream().map(foodMapper::foodToFighterFood).sorted(Comparator.comparing(FighterFoodDTO::getName)).toList();

        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterApple(), fighterFoodDTOList.get(0)));
        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterBanana(), fighterFoodDTOList.get(1)));
        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterPineapple(), fighterFoodDTOList.get(2)));
    }

    @Test
    public void shouldMapFoodToFighterFood() {
        FoodDTO apple = testUtil.buildApple();
        FighterFoodDTO expected = testUtil.buildFighterApple();
        FighterFoodDTO result = foodMapper.foodToFighterFood(apple);
        assertTrue(testUtil.twoFighterFoodDTOsEquals(expected, result));
    }
}
