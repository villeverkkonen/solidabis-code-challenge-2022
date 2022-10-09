package solid.abis.challenge.mapper;

import com.fasterxml.jackson.databind.*;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;
import solid.abis.challenge.dto.FighterFoodDTO;
import solid.abis.challenge.dto.FoodDTO;

import java.io.IOException;
import java.util.List;

@Component
public class FoodMapper {

    public List<FoodDTO> jsonArrayToFoodDTOList(JSONArray foods) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, FoodDTO.class);
        return mapper.readValue(foods.toString(), type);
    }

    public FighterFoodDTO foodToFighterFood(final FoodDTO foodDTO) {
        return FighterFoodDTO.builder()
                .id(foodDTO.getId())
                .name(foodDTO.getName())
                .health(foodDTO.getEnergy())
                .attack(foodDTO.getCarbohydrate())
                .defence(foodDTO.getProtein())
                .delay(calculateDelay(foodDTO))
                .build();
    }

    private double calculateDelay(final FoodDTO foodDTO) {
        // Delay affects attack speed
        return roundToOneDecimal((foodDTO.getCarbohydrate() + foodDTO.getProtein() + foodDTO.getFat()) / 10);
    }

    private static double roundToOneDecimal(double value) {
        int scale = (int) Math.pow(10, 1);
        return (double) Math.round(value * scale) / scale;
    }
}
