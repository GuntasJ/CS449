package gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private String selection;
    private final List<SOSCombination> combinations;

    public Tile(String selection) {
        this.selection = selection;
        combinations = new ArrayList<>();
    }

    public Tile() {
        this(" ");
    }


    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public void addSOSCombination(Player player, Direction direction) {
        combinations.add(new SOSCombination(player, direction));
    }

    public boolean isSelectionEmpty() {
        return selection.equals(" ");
    }

    public List<SOSCombination> getCombinations() {
        return combinations;
    }

    @Override
    public String toString() {
        return selection + " ";
    }
}
