package gamelogic;

import java.util.Arrays;

public class SOSGameLogic {

    private String currentChoiceBlue;
    private String currentChoiceRed;
    private String[][] gameBoard;
    private Player currentPlayer;
    private GameMode gameMode;

    private int size;

    public SOSGameLogic(int size, Player currentPlayerPlayer, GameMode gameMode) {
        this.size = size;
        this.currentPlayer = currentPlayerPlayer;
        this.gameMode = gameMode;
        setSize(size);
        clearBoard();
    }

    public SOSGameLogic(Player startingPlayer) {
        size = -1;
        currentPlayer = startingPlayer;
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
        if(currentPlayer == Player.RED_PLAYER) {
            return currentChoiceRed;
        }
        else if(currentPlayer == Player.BLUE_PLAYER) {
            return currentChoiceBlue;
        }
        else {
            return null;
        }
    }

    public void makeMove(int x, int y) {
        if(x < 0 || x > gameBoard.length || y < 0 || y > gameBoard[0].length) {
            throw new IllegalArgumentException("x and y must fall within game board.");
        }
        if(!gameBoard[x][y].equals(" ")) {
            throw new IllegalArgumentException("Choice must be placed on empty square.");
        }
        gameBoard[x][y] = getCurrentChoice();

    }
    public void setCurrentChoice(String currentChoice) {
        if(currentChoice.equals("S") || currentChoice.equals("O")) {
            if(currentPlayer == Player.BLUE_PLAYER) {
                currentChoiceBlue = currentChoice;
            }
            else if(currentPlayer == Player.RED_PLAYER) {
                currentChoiceRed = currentChoice;
            }
            else {
                throw new IllegalArgumentException("Invalid Player");
            }
        }
        else {
            throw new IllegalArgumentException("Current choice must be S or O");
        }
    }

    public void switchPlayer() {
        if(currentPlayer == Player.RED_PLAYER) {
            currentPlayer = Player.BLUE_PLAYER;
        }
        else if(currentPlayer == Player.BLUE_PLAYER){
            currentPlayer = Player.RED_PLAYER;
        }
        else {
            throw new IllegalArgumentException("Invalid Player");
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setCurrentChoiceBlue(String currentChoiceBlue) {
        this.currentChoiceBlue = currentChoiceBlue;
    }

    public void setCurrentChoiceRed(String currentChoiceRed) {
        this.currentChoiceRed = currentChoiceRed;
    }

    @Override
    public String toString() {
        return "SOSGameLogic{" +
                "currentChoice='" + getCurrentChoice() + '\'' +
                ", gameBoard=" + Arrays.deepToString(gameBoard) +
                ", size=" + size +
                '}';
    }


}
