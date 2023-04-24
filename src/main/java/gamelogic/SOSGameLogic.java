package gamelogic;

import java.util.Arrays;

public abstract class SOSGameLogic {

    protected Player redPlayer;
    protected Player bluePlayer;
    protected Player currentPlayer;
    protected Tile[][] gameBoard;
    protected GameMode gameMode;
    protected GameState gameState;

    private int size;

    protected SOSGameLogic(int size, GameMode gameMode, PlayerTypeMode playerTypeMode, Player.PlayerColor currentPlayer) {
        this.size = size;
        this.gameMode = gameMode;
        this.gameState = GameState.GAME_NOT_OVER;

        setSize(size);
        clearBoard();

        determinePlayerConfiguration(playerTypeMode);
        this.currentPlayer = currentPlayer == Player.PlayerColor.RED_PLAYER ? redPlayer : bluePlayer;
    }


    //Builder

    public static SOSGameLogicBuilder newBuilder() {
        return new SOSGameLogicBuilder();
    }
    public static class SOSGameLogicBuilder {

        private int size = 3;
        private GameMode gameMode = GameMode.SIMPLE;
        private PlayerTypeMode playerTypeMode = PlayerTypeMode.ALL_HUMAN;
        private Player.PlayerColor currentPlayer = Player.PlayerColor.RED_PLAYER;

        public SOSGameLogic build() {
            if(gameMode == GameMode.SIMPLE) {
                return new SimpleSOSGameLogic(size, playerTypeMode, currentPlayer);
            }
            return new GeneralSOSGameLogic(size, playerTypeMode, currentPlayer);
        }

        public SOSGameLogicBuilder setSize(int size) {
            this.size = size;
            return this;
        }

        public SOSGameLogicBuilder setGameMode(GameMode gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public SOSGameLogicBuilder setPlayerTypeMode(PlayerTypeMode playerTypeMode) {
            this.playerTypeMode = playerTypeMode;
            return this;
        }

        public SOSGameLogicBuilder setStartingPlayer(Player.PlayerColor currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }
    }

    public abstract GameState checkForWinner();

    public void determinePlayerConfiguration(PlayerTypeMode playerTypeMode) {
        switch(playerTypeMode) {
            case ALL_HUMAN -> {
                redPlayer =
                        new HumanPlayer(Player.PlayerColor.RED_PLAYER, "", gameBoard);
                bluePlayer =
                        new HumanPlayer(Player.PlayerColor.BLUE_PLAYER, "", gameBoard);
            }
            case ALL_COMPUTER -> {
                redPlayer =
                        new ComputerPlayer(Player.PlayerColor.RED_PLAYER, "", gameBoard);
                bluePlayer =
                        new ComputerPlayer(Player.PlayerColor.BLUE_PLAYER, "", gameBoard);
            }
            case RED_HUMAN_BLUE_COMPUTER -> {
                redPlayer =
                        new HumanPlayer(Player.PlayerColor.RED_PLAYER, "", gameBoard);
                bluePlayer =
                        new ComputerPlayer(Player.PlayerColor.BLUE_PLAYER, "", gameBoard);
            }
            case RED_COMPUTER_BLUE_HUMAN -> {
                redPlayer =
                        new ComputerPlayer(Player.PlayerColor.RED_PLAYER, "", gameBoard);
                bluePlayer =
                        new HumanPlayer(Player.PlayerColor.BLUE_PLAYER, "", gameBoard);
            }
        }
    }

    public void clearBoard() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                gameBoard[i][j] = new Tile();
            }
        }
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        if(size <= 2) {
            throw new IllegalArgumentException("Size must be greater than 2.");
        }
        this.size = size;
        gameBoard = new Tile[size][size];
    }

    protected void executeMove(Move move) {
        gameBoard[move.x()][move.y()].setSelection(move.choice());
    }

    //public abstract void makeMove(int row, int col);
    public abstract void makeComputerMove();
    public abstract void makeHumanMove(int row, int col);

    protected void checkForValidMove(int row, int col) {
        if (row < 0 || row > gameBoard.length || col < 0 || col > gameBoard[0].length) {
            throw new IllegalArgumentException("x and y must fall within game board.");
        }
        if (!gameBoard[row][col].getSelection().equals(" ")) {
            throw new IllegalArgumentException("Choice must be placed on empty square.");
        }
        if (gameMode == null) {
            throw new IllegalArgumentException("Game mode cannot be null.");
        }
        if (currentPlayer.getPlayerChoice() == null) {
            throw new IllegalArgumentException("Selection cannot be null.");
        }
    }


    public void switchPlayer() {
        if(currentPlayer == redPlayer) {
            currentPlayer = bluePlayer;
        }
        else if(currentPlayer == bluePlayer) {
            currentPlayer = redPlayer;
        }
        else {
            throw new IllegalArgumentException("Invalid Player");
        }
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Tile[][] getGameBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getBluePlayer() {
        return bluePlayer;
    }

    public enum PlayerTypeMode {
        ALL_HUMAN,
        ALL_COMPUTER,
        RED_HUMAN_BLUE_COMPUTER,
        RED_COMPUTER_BLUE_HUMAN
    }

    public enum GameState {
        TIE,
        BLUE_WON,
        RED_WON,
        GAME_NOT_OVER,
    }

    @Override
    public String toString() {
        return "SOSGameLogic{" +
                "redPlayer=" + redPlayer +
                ", bluePlayer=" + bluePlayer +
                ", currentPlayer=" + currentPlayer +
                ", gameBoard=" + Arrays.toString(gameBoard) +
                ", gameMode=" + gameMode +
                ", size=" + size +
                '}';
    }
}
