package solid.abis.challenge.controller;

import org.springframework.web.bind.annotation.*;
import solid.abis.challenge.dto.FighterFoodDTO;
import solid.abis.challenge.service.FighterService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/fighters")
public class FighterController {

    @Resource
    FighterService fighterService;

    @GetMapping("")
    public List<FighterFoodDTO> getFighters() {
        return fighterService.getFoodsFromJson();
    }

    @PostMapping("/fight")
    public List<String> fight(@RequestBody List<FighterFoodDTO> fighters) {
        return fighterService.fight(fighters);
    }
}
