package gamelogic;

public class HumanPlayer extends Player {
    public HumanPlayer(PlayerColor playerColor, String playerChoice, Tile[][] gameBoard) {
        super(playerColor, PlayerType.HUMAN_PLAYER, playerChoice, gameBoard);
    }

    @Override
    public Move makeMove(int x, int y) {
        return new Move(x, y, playerChoice, playerColor, playerType);
    }
}
