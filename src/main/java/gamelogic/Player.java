package gamelogic;

public class Player {

    private final PlayerColor playerColor;
    private final PlayerType playerType;
    private String playerChoice;
    private int totalSOSCombinations;

    public Player(PlayerColor playerColor, PlayerType playerType, String playerChoice) {
        this.playerColor = playerColor;
        this.playerType = playerType;
        this.playerChoice = playerChoice;
        totalSOSCombinations = 0;
    }

    public Move makeMove(int x, int y) {
        return new Move(x, y, playerChoice, playerColor, playerType);
    }

    private void validatePlayerChoiceIsValid(String playerChoice) {
        if(!playerChoice.equals("O") && !playerChoice.equals("S")) {
            throw new IllegalArgumentException("Current choice must be S or O");
        }
    }

    public void setPlayerChoice(String playerChoice) {
        validatePlayerChoiceIsValid(playerChoice);
        this.playerChoice = playerChoice;
    }

    public String getPlayerChoice() {
        return playerChoice;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public int getTotalSOSCombinations() {
        return totalSOSCombinations;
    }

    public void incrementTotalSOSCombinations() {
        totalSOSCombinations++;
    }

    @Override
    public String toString() {
        return playerColor.toString();
    }

    public enum PlayerColor {
        BLUE_PLAYER("Blue Player"),
        RED_PLAYER("Red Player");

        private final String label;
        PlayerColor(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public enum PlayerType {
        HUMAN_PLAYER,
        COMPUTER_PLAYER
    }
}
