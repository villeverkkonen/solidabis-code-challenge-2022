package solid.abis.challenge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import solid.abis.challenge.dto.FighterFoodDTO;
import solid.abis.challenge.util.TestUtil;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FighterControllerTest {

    @Autowired
    private FighterController fighterController;
    @Autowired
    TestUtil testUtil;

    @Test
    void shouldGetFoodsFromJson() {
        List<FighterFoodDTO> result = fighterController.getFighters();
        result.sort(Comparator.comparing(FighterFoodDTO::getName));

        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterApple(), result.get(0)));
        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterBanana(), result.get(1)));
        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterPineapple(), result.get(2)));
    }
}
