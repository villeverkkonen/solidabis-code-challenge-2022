package solid.abis.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class FighterFoodDTO {
    private String name;
    private double health;
    private double attack;

    private double defence;
    private double delay;

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefence() {
        return defence;
    }

    public double getDelay() {
        return delay;
    }
}
