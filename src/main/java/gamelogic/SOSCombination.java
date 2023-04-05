package gamelogic;

import java.util.Objects;

public class SOSCombination {

    private Player playerOfCombination;
    private Direction combinationDirection;
    private int id;

    public SOSCombination(Player playerOfCombination, Direction combinationDirection, int id) {
        this.playerOfCombination = playerOfCombination;
        this.combinationDirection = combinationDirection;
        this.id = id;
    }

    public SOSCombination() {
        this(null, null, -1);
    }

    public Player getPlayerOfCombination() {
        return playerOfCombination;
    }

    public Direction getCombinationDirection() {
        return combinationDirection;
    }

    public void setPlayerOfCombination(Player playerOfCombination) {
        this.playerOfCombination = playerOfCombination;
    }

    public void setCombinationDirection(Direction combinationDirection) {
        this.combinationDirection = combinationDirection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
