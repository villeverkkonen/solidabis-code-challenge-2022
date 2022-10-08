package solid.abis.challenge.util;

import org.springframework.stereotype.Component;
import solid.abis.challenge.dto.FoodDTO;

@Component
public class TestMaterial {

    public FoodDTO buildPineapple() {
        return FoodDTO.builder()
                .name("Ananas")
                .carbohydrate(11.2)
                .protein(0.5)
                .energy(232.7)
                .fat(0.4)
                .build();
    }

    public FoodDTO buildBanana() {
        return FoodDTO.builder()
                .name("Banaani")
                .carbohydrate(18.3)
                .protein(1.2)
                .energy(366.4)
                .fat(0.4)
                .build();
    }

    public FoodDTO buildApple() {
        return FoodDTO.builder()
                .name("Omena")
                .carbohydrate(8.3)
                .protein(0.2)
                .energy(169.6)
                .fat(0.1)
                .build();
    }

    public boolean twoFoodDTOsEquals(FoodDTO a, FoodDTO b) {
        return a.getName().equals(b.getName()) &&
                a.getCarbohydrate() == b.getCarbohydrate() &&
                a.getProtein() == b.getProtein() &&
                a.getEnergy() == b.getEnergy() &&
                a.getFat() == b.getFat();
    }
}
