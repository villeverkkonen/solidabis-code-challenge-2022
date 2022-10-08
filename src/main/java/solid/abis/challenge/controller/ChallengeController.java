package solid.abis.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    @GetMapping("/hello")
    public String hello() {
        return "Mau!";
    }
}
