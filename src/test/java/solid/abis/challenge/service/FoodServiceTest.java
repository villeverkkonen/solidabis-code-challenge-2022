package solid.abis.challenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import solid.abis.challenge.dto.FoodDTO;
import solid.abis.challenge.util.TestMaterial;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;
    @Autowired
    TestMaterial testMaterial;

    @Test
    void shouldGetFoodsFromJson() {
        List<FoodDTO> result = foodService.getFoodsFromJson();
        result.sort(Comparator.comparing(FoodDTO::getName));

        assertTrue(testMaterial.twoFoodDTOsEquals(testMaterial.buildPineapple(), result.get(0)));
        assertTrue(testMaterial.twoFoodDTOsEquals(testMaterial.buildBanana(), result.get(1)));
        assertTrue(testMaterial.twoFoodDTOsEquals(testMaterial.buildApple(), result.get(2)));
    }
}
