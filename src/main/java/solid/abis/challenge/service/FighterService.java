package solid.abis.challenge.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import solid.abis.challenge.dto.FighterFoodDTO;
import solid.abis.challenge.dto.FoodDTO;
import solid.abis.challenge.mapper.FoodMapper;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class FighterService {

    @Resource
    private FoodMapper foodMapper;

    private static final String FOOD_JSON = "json/foods.json";

    public List<FighterFoodDTO> getFoodsFromJson() {
        try (FileReader reader = new FileReader(this.getFileFromResource())) {
            Object obj = new JSONParser().parse(reader);
            JSONArray foods = (JSONArray) obj;
            List<FoodDTO> foodDTOList = foodMapper.jsonArrayToFoodDTOList(foods);
            List<FighterFoodDTO> fighterFoodDTOList = foodDTOList.stream().map(foodMapper::foodToFighterFood).collect(Collectors.toList());
            fighterFoodDTOList.sort(Comparator.comparing(FighterFoodDTO::getId));
            return fighterFoodDTOList;
        } catch (IOException | ParseException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, List<String>>> fight(final List<FighterFoodDTO> fighters) {
        List<Map<String, List<String>>> fightLog = new ArrayList<>();

        if (fighters.get(0) == null || fighters.get(1) == null) {
            fightLog.add(Collections.singletonMap("invalidFighters", List.of("Two fighters are required for a fair brawl.")));
            return fightLog;
        }

        FighterFoodDTO fighter1 = fighters.get(0);
        FighterFoodDTO fighter2 = fighters.get(1);

        fightLog.add(Collections.singletonMap("startGame", List.of(fighter1.getName(), " VS ", fighter2.getName())));

        int fighterOneRound = 1;
        int fighterTwoRound = 1;
        while (true) {
            double fighterOneDelay = fighter1.getDelay() * fighterOneRound;
            double fighterTwoDelay = fighter2.getDelay() * fighterTwoRound;
            // Decide who hits who
            if (fighterOneDelay < fighterTwoDelay) {
                hit(fighter1, fighter2, fighterOneRound, fightLog, "playerOne");
                fighterOneRound++;
            } else {
                hit(fighter2, fighter1, fighterTwoRound, fightLog, "playerTwo");
                fighterTwoRound++;
            }

            // End game
            if (fighter1.getHealth() <= 0) {
                fightLog.add(Collections.singletonMap("endGame", List.of("playerTwo", fighter2.getName() + " wins the battle!")));
                break;
            }
            if (fighter2.getHealth() <= 0) {
                fightLog.add(Collections.singletonMap("endGame", List.of("playerOne", fighter1.getName() + " wins the battle!")));
                break;
            }
        }
        return fightLog;
    }

    private void hit(final FighterFoodDTO hitter, FighterFoodDTO target, final int round, List<Map<String, List<String>>> fightLog, final String player) {
        double time = roundToOneDecimal(hitter.getDelay() * round);
        // Damage is randomized between 0 and attack
        double damage = roundToOneDecimal(ThreadLocalRandom.current().nextDouble(0, (hitter.getAttack() - target.getDefence())));
        target.setHealth(roundToOneDecimal(target.getHealth() - damage));
        fightLog.add(Collections.singletonMap("row", List.of(player, time + "s", hitter.getName() + " hits " + damage + " damage.", target.getName() + " has " + target.getHealth() + " Health.")));
    }

    private static double roundToOneDecimal(final double value) {
        int scale = (int) Math.pow(10, 1);
        return (double) Math.round(value * scale) / scale;
    }

    private File getFileFromResource() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(FighterService.FOOD_JSON);
        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + FighterService.FOOD_JSON);
        } else {
            return new File(resource.toURI());
        }
    }
}
