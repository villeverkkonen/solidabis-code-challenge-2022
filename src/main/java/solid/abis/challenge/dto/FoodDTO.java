package solid.abis.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class FoodDTO {
    private String name;
    private double energy;
    private double carbohydrate;
    private double protein;
    private double fat;

    public String getName() {
        return name;
    }

    public double getEnergy() {
        return energy;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }
}
