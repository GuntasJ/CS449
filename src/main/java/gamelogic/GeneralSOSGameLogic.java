package gamelogic;

public class GeneralSOSGameLogic extends SOSGameLogic {

    protected GeneralSOSGameLogic(int size, PlayerTypeMode playerTypeMode, Player.PlayerColor currentPlayer) {
        super(size, GameMode.GENERAL, playerTypeMode, currentPlayer);
    }

    @Override
    public GameState checkForWinner() {
        for(Tile[] tiles : gameBoard) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                if(tiles[j].getSelection().equals(" ")) {
                    return GameState.GAME_NOT_OVER;
                }
            }
        }
        if(redPlayer.getTotalSOSCombinations() > bluePlayer.getTotalSOSCombinations()) {
            return GameState.RED_WON;
        }
        else if(bluePlayer.getTotalSOSCombinations() > redPlayer.getTotalSOSCombinations()) {
            return GameState.BLUE_WON;
        }
        return GameState.TIE;
    }

    @Override
    public void makeComputerMove() {
        int previousCombinationNumber = currentPlayer.getTotalSOSCombinations();
        Move move = currentPlayer.makeMove(0, 0);
        executeMove(move);
        moveList.add(move);
        SOSGameUtils.checkForAndMarkCombination(gameBoard, currentPlayer);
        gameState = checkForWinner();
        if(currentPlayer.getTotalSOSCombinations() == previousCombinationNumber) {
            switchPlayer();
        }
    }

    @Override
    public void makeHumanMove(int row, int col) {
        checkForValidMove(row, col);
        int previousCombinationNumber = currentPlayer.getTotalSOSCombinations();
        Move move = currentPlayer.makeMove(row, col);
        executeMove(move);
        moveList.add(move);
        SOSGameUtils.checkForAndMarkCombination(gameBoard, currentPlayer);
        gameState = checkForWinner();
        if(currentPlayer.getTotalSOSCombinations() == previousCombinationNumber) {
            switchPlayer();
        }
    }
}
