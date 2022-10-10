package solid.abis.challenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import solid.abis.challenge.dto.FighterFoodDTO;
import solid.abis.challenge.util.TestUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FighterServiceTest {

    @Autowired
    private FighterService fighterService;
    @Autowired
    TestUtil testUtil;

    @Test
    void shouldGetFoodsFromJson() {
        List<FighterFoodDTO> result = fighterService.getFoodsFromJson();
        result.sort(Comparator.comparing(FighterFoodDTO::getName));

        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterApple(), result.get(0)));
        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterBanana(), result.get(1)));
        assertTrue(testUtil.twoFighterFoodDTOsEquals(testUtil.buildFighterPineapple(), result.get(2)));
    }

    @Test
    void shouldNotFightWithOnlyOneFighter() {
        List<FighterFoodDTO> fighters = new ArrayList<>();
        fighters.add(null);
        fighters.add(testUtil.buildFighterApple());
        List<Map<String, List<String>>> fightLog = fighterService.fight(fighters);

        assertEquals(1, fightLog.size());
        assertEquals("Two fighters are required for a fair brawl.", fightLog.get(0).get("invalidFighters").get(0));
    }

    @Test
    void shouldFight() {
        List<FighterFoodDTO> fighters = new ArrayList<>();
        fighters.add(testUtil.buildFighterPineapple());
        fighters.add(testUtil.buildFighterBanana());
        List<Map<String, List<String>>> fightLog = fighterService.fight(fighters);

        assertTrue(fightLog.size() > 1);
        assertTrue(fightLog.get(fightLog.size() - 1).get("endGame").get(1).contains("wins the battle!"));
    }
}
