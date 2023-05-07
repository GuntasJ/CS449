package gamelogic;

public class SimpleSOSGameLogic extends SOSGameLogic {
    protected SimpleSOSGameLogic(int size, PlayerTypeMode playerTypeMode, Player.PlayerColor currentPlayer) {
        super(size, GameMode.SIMPLE, playerTypeMode, currentPlayer);
    }

    @Override
    public GameState checkForWinner() {
        for (Tile[] tiles : gameBoard) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (!tiles[j].getCombinations().isEmpty()) {
                    if (tiles[j].getCombinations().get(0).playerOfCombination() == redPlayer) {
                        return GameState.RED_WON;
                    }
                    return GameState.BLUE_WON;
                }
            }
        }
        for(Tile[] tiles : gameBoard) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                if (tiles[j].getCombinations().isEmpty() && !tiles[j].getSelection().equals(" ")) {
                    continue;
                }
                return GameState.GAME_NOT_OVER;
            }
        }
        return GameState.TIE;
    }

    @Override
    public void makeComputerMove() {
        Move move = currentPlayer.makeMove(0, 0);
        executeMove(move);
        moveList.add(move);
        SOSGameUtils.checkForAndMarkCombination(gameBoard, currentPlayer);
        gameState = checkForWinner();
        switchPlayer();
    }

    @Override
    public void makeHumanMove(int row, int col) {
        checkForValidMove(row, col);
        Move move = currentPlayer.makeMove(row, col);
        executeMove(move);
        moveList.add(move);
        SOSGameUtils.checkForAndMarkCombination(gameBoard, currentPlayer);
        gameState = checkForWinner();
        switchPlayer();
    }
}
