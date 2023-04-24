package gamelogic;

public abstract class Player {

    protected final PlayerColor playerColor;
    protected final PlayerType playerType;
    protected String playerChoice;
    protected Tile[][] gameBoard;
    private int totalSOSCombinations;

    protected Player(PlayerColor playerColor, PlayerType playerType, String playerChoice, Tile[][] gameBoard) {
        this.playerColor = playerColor;
        this.playerType = playerType;
        this.playerChoice = playerChoice;
        this.gameBoard = gameBoard;
        totalSOSCombinations = 0;
    }

    public abstract Move makeMove(int x, int y);

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

    public PlayerType getPlayerType() {
        return playerType;
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
}
