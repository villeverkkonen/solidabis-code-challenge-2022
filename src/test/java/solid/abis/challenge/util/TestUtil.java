package solid.abis.challenge.util;

import org.springframework.stereotype.Component;
import solid.abis.challenge.dto.FighterFoodDTO;
import solid.abis.challenge.dto.FoodDTO;

@Component
public class TestUtil {

    public FighterFoodDTO buildFighterApple() {
        return FighterFoodDTO.builder()
                .id(1L)
                .name("Apple")
                .attack(8.3)
                .defence(0.2)
                .health(27)
                .delay(0.9)
                .build();
    }

    public FighterFoodDTO buildFighterBanana() {
        return FighterFoodDTO.builder()
                .id(2L)
                .name("Banana")
                .attack(18.3)
                .defence(1.2)
                .health(46.6)
                .delay(2)
                .build();
    }

    public FighterFoodDTO buildFighterPineapple() {
        return FighterFoodDTO.builder()
                .id(3L)
                .name("Pineapple")
                .attack(11.2)
                .defence(0.5)
                .health(33.3)
                .delay(1.2)
                .build();
    }

    public FoodDTO buildApple() {
        return FoodDTO.builder()
                .id(1L)
                .name("Apple")
                .carbohydrate(8.3)
                .protein(0.2)
                .energy(27)
                .fat(0.1)
                .build();
    }

    public boolean twoFighterFoodDTOsEquals(FighterFoodDTO a, FighterFoodDTO b) {
        return a.getName().equals(b.getName()) &&
                a.getAttack() == b.getAttack() &&
                a.getDefence() == b.getDefence() &&
                a.getHealth() == b.getHealth() &&
                a.getDelay() == b.getDelay();
    }
}
