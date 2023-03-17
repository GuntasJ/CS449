package gamelogic;

import java.util.Arrays;
import java.util.Objects;

public class SOSGameLogic {

    private String currentChoice = "S";
    private String[][] gameBoard;
    private Player player;
    private GameMode gameMode;

    private int size;

    public SOSGameLogic(int size, Player startingPlayer, GameMode gameMode) {
        this.size = size;
        this.player = startingPlayer;
        this.gameMode = gameMode;
        setSize(size);
        clearBoard();
    }

    public SOSGameLogic(Player startingPlayer) {
        size = -1;
        player = startingPlayer;
        gameMode = null;
    }

    public void clearBoard() {
        for(String[] row : gameBoard) {
            Arrays.fill(row, " ");
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
        gameBoard = new String[size][size];
    }

    public String getCurrentChoice() {
        return currentChoice;
    }

    public void makeMove(int x, int y) {
        if(x < 0 || x > gameBoard.length || y < 0 || y > gameBoard[0].length) {
            throw new IllegalArgumentException("x and y must fall within game board.");
        }
        if(!gameBoard[x][y].equals(" ")) {
            throw new IllegalArgumentException("Choice must be placed on empty square.");
        }
        gameBoard[x][y] = String.valueOf(currentChoice);

    }
    public void setCurrentChoice(String currentChoice) {
        if(currentChoice.equals("S") || currentChoice.equals("O")) {
            this.currentChoice = currentChoice;
        }
        else {
            throw new IllegalArgumentException("Current choice must be S or O");
        }
    }

    public void switchPlayer() {
        if(player == Player.RED_PLAYER) {
            player = Player.BLUE_PLAYER;
        } else {
            player = Player.RED_PLAYER;
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }



    @Override
    public String toString() {
        return "SOSGameLogic{" +
                "currentChoice='" + currentChoice + '\'' +
                ", gameBoard=" + Arrays.deepToString(gameBoard) +
                ", size=" + size +
                '}';
    }

    public enum Player {
        BLUE_PLAYER,
        RED_PLAYER
    }

    public enum GameMode {
        SIMPLE,
        GENERAL
    }
}
