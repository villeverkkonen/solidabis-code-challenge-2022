package solid.abis.challenge.mapper;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import solid.abis.challenge.dto.FoodDTO;

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

    @Test
    public void shouldReturnListOfDTOs() throws URISyntaxException, IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("json/foods.json");
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object obj = new JSONParser().parse(reader);
        JSONArray foods = (JSONArray) obj;

        List<FoodDTO> foodDTOList = foodMapper.jsonArrayToDtoList(foods);
        foodDTOList.sort(Comparator.comparing(FoodDTO::getName));

        FoodDTO expectedOne = FoodDTO.builder()
                .name("Ananas")
                .carbohydrate(11.2)
                .protein(0.5)
                .energy(232.7)
                .fat(0.4)
                .build();

        FoodDTO expectedTwo = FoodDTO.builder()
                .name("Banaani")
                .carbohydrate(18.3)
                .protein(1.2)
                .energy(366.4)
                .fat(0.4)
                .build();

        FoodDTO expectedThree = FoodDTO.builder()
                .name("Omena")
                .carbohydrate(8.3)
                .protein(0.2)
                .energy(169.6)
                .fat(0.1)
                .build();

        assertTrue(twoFoodDTOsEquals(expectedOne, foodDTOList.get(0)));
        assertTrue(twoFoodDTOsEquals(expectedTwo, foodDTOList.get(1)));
        assertTrue(twoFoodDTOsEquals(expectedThree, foodDTOList.get(2)));
    }
    
    private boolean twoFoodDTOsEquals(FoodDTO a, FoodDTO b) {
        return a.getName().equals(b.getName()) &&
                a.getCarbohydrate() == b.getCarbohydrate() &&
                a.getProtein() == b.getProtein() &&
                a.getEnergy() == b.getEnergy() &&
                a.getFat() == b.getFat();
    }
}
