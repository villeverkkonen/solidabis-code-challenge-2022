package solid.abis.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solid.abis.challenge.dto.FoodDTO;
import solid.abis.challenge.service.FoodService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Resource
    FoodService foodService;

    @GetMapping("")
    public List<FoodDTO> getFoods() {
        return foodService.getFoodsFromJson();
    }
}
