package solid.abis.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private String name;
    private double carbohydrate;
    private double protein;
    private double energy;
    private double fat;

    public String getName() {
        return name;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getProtein() {
        return protein;
    }

    public double getEnergy() {
        return energy;
    }

    public double getFat() {
        return fat;
    }
}
