package gamelogic;

public enum Player {
    BLUE_PLAYER("Blue Player"),
    RED_PLAYER("Red Player");

    private final String label;
    Player(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
