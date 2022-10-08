package solid.abis.challenge.mapper;

import com.fasterxml.jackson.databind.*;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;
import solid.abis.challenge.dto.FoodDTO;

import java.io.IOException;
import java.util.List;

@Component
public class FoodMapper {

    public List<FoodDTO> jsonArrayToDtoList(JSONArray foods) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, FoodDTO.class);
        return mapper.readValue(foods.toString(), type);
    }
}
