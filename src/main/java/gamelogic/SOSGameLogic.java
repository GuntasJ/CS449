package gamelogic;

import java.util.Arrays;

public class SOSGameLogic {

    private Player redPlayer;
    private Player bluePlayer;
    private Player currentPlayer;
    private Tile[][] gameBoard;
    private GameMode gameMode;

    private int size;

    private SOSGameLogic(int size, GameMode gameMode, PlayerTypeMode playerTypeMode, Player.PlayerColor currentPlayer) {
        this.size = size;
        this.gameMode = gameMode;

        determinePlayerConfiguration(playerTypeMode);

        this.currentPlayer = currentPlayer == Player.PlayerColor.RED_PLAYER ? redPlayer : bluePlayer;

        setSize(size);
        clearBoard();
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
            return new SOSGameLogic(size, gameMode, playerTypeMode, currentPlayer);
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

        public SOSGameLogicBuilder setCurrentPlayerColor(Player.PlayerColor currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }
    }









    public void determinePlayerConfiguration(PlayerTypeMode playerTypeMode) {
        switch(playerTypeMode) {
            case ALL_HUMAN -> {
                redPlayer =
                        new Player(Player.PlayerColor.RED_PLAYER, Player.PlayerType.HUMAN_PLAYER, "");
                bluePlayer =
                        new Player(Player.PlayerColor.BLUE_PLAYER, Player.PlayerType.HUMAN_PLAYER, "");
            }
            case ALL_COMPUTER -> {
                redPlayer =
                        new Player(Player.PlayerColor.RED_PLAYER, Player.PlayerType.COMPUTER_PLAYER, "");
                bluePlayer =
                        new Player(Player.PlayerColor.BLUE_PLAYER, Player.PlayerType.COMPUTER_PLAYER, "");
            }
            case RED_HUMAN_BLUE_COMPUTER -> {
                redPlayer =
                        new Player(Player.PlayerColor.RED_PLAYER, Player.PlayerType.HUMAN_PLAYER, "");
                bluePlayer =
                        new Player(Player.PlayerColor.BLUE_PLAYER, Player.PlayerType.COMPUTER_PLAYER, "");
            }
            case RED_COMPUTER_BLUE_HUMAN -> {
                redPlayer =
                        new Player(Player.PlayerColor.RED_PLAYER, Player.PlayerType.COMPUTER_PLAYER, "");
                bluePlayer =
                        new Player(Player.PlayerColor.BLUE_PLAYER, Player.PlayerType.HUMAN_PLAYER, "");
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

    private void executeMove(Move move) {
        gameBoard[move.x()][move.y()].setSelection(move.choice());
    }

    public void makeMove(int x, int y) {
        if(x < 0 || x > gameBoard.length || y < 0 || y > gameBoard[0].length) {
            throw new IllegalArgumentException("x and y must fall within game board.");
        }
        if(!gameBoard[x][y].getSelection().equals(" ")) {
            throw new IllegalArgumentException("Choice must be placed on empty square.");
        }
        if(gameMode == null) {
            throw new IllegalArgumentException("Game mode cannot be null.");
        }
        if(currentPlayer.getPlayerChoice() == null) {
            throw new IllegalArgumentException("Selection cannot be null.");
        }
        executeMove(currentPlayer.makeMove(x, y));
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

    public GameMode getGameMode() {
        return gameMode;
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
