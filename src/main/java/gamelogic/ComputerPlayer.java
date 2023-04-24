package gamelogic;

import java.util.*;

public class ComputerPlayer extends Player {

    private static final int THINKING_THRESHOLD = 1_000_000;
    private final Set<Move> badMoves;
    protected ComputerPlayer(PlayerColor playerColor, String playerChoice, Tile[][] gameBoard) {
        super(playerColor, PlayerType.COMPUTER_PLAYER, playerChoice, gameBoard);
        badMoves = new HashSet<>();
    }


    @Override
    public Move makeMove(int x, int y) {
        badMoves.clear();
        findBadMoves();
        Move move;
        if((move = findSOSMakingMove()) != null) {
            System.out.println(move);
            return move;
        }
        else {
            int i = 0;
            while(true) {
                move = makeDefaultRandomMove();
                if(!badMoves.contains(move) || i > THINKING_THRESHOLD) {
                    return move;
                }
                i++;
            }
        }
    }

    private Move findSOSMakingMove() {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if(gameBoard[i][j].getSelection().equals("S") && gameBoard[i][j + 1].getSelection().equals("O")
                        && gameBoard[i][j + 2].isSelectionEmpty()) {
                    return new Move(i, j + 2, "S", playerColor, playerType);
                }

                if(gameBoard[i][j].getSelection().equals("S") && gameBoard[i][j + 1].isSelectionEmpty()
                        && gameBoard[i][j + 2].getSelection().equals("S")) {
                    return new Move(i, j + 1, "O", playerColor, playerType);
                }

                if(gameBoard[i][j].isSelectionEmpty() && gameBoard[i][j + 1].getSelection().equals("O")
                        && gameBoard[i][j + 2].getSelection().equals("S")) {
                    return new Move(i, j, "S", playerColor, playerType);
                }
            }
        }
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if(gameBoard[j][i].getSelection().equals("S") && gameBoard[j + 1][i].getSelection().equals("O")
                        && gameBoard[j + 2][i].isSelectionEmpty()) {
                    return new Move(j + 2, i, "S", playerColor, playerType);
                }

                if(gameBoard[j][i].getSelection().equals("S") && gameBoard[j + 1][i].isSelectionEmpty()
                        && gameBoard[j + 2][i].getSelection().equals("S")) {
                    return new Move(j + 1, i, "O", playerColor, playerType);
                }

                if(gameBoard[j][i].isSelectionEmpty() && gameBoard[j + 1][i].getSelection().equals("O")
                        && gameBoard[j + 2][i].getSelection().equals("S")) {
                    return new Move(j, i, "S", playerColor, playerType);
                }
            }
        }
        return null;
    }
    private void findBadMoves() {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if(gameBoard[i][j].getSelection().equals("S") && gameBoard[i][j + 1].isSelectionEmpty()
                        && gameBoard[i][j + 2].isSelectionEmpty()) {
                    badMoves.add(new Move(i, j + 1, "O", playerColor, playerType));
                    badMoves.add(new Move(i, j + 2, "S", playerColor, playerType));
                }

                else if(gameBoard[i][j].isSelectionEmpty() && gameBoard[i][j + 1].isSelectionEmpty()
                        && gameBoard[i][j + 2].getSelection().equals("S")) {
                    badMoves.add(new Move(i, j, "S", playerColor, playerType));
                    badMoves.add(new Move(i, j + 1, "O", playerColor, playerType));

                }

                else if(gameBoard[i][j].isSelectionEmpty() && gameBoard[i][j + 1].getSelection().equals("O")
                        && gameBoard[i][j + 2].isSelectionEmpty()) {
                    badMoves.add(new Move(i, j, "S", playerColor, playerType));
                    badMoves.add(new Move(i, j + 2, "S", playerColor, playerType));
                }
            }
        }
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if(gameBoard[j][i].getSelection().equals("S") && gameBoard[j + 1][i].isSelectionEmpty()
                        && gameBoard[j + 2][i].isSelectionEmpty()) {
                    badMoves.add(new Move(j + 2, i, "S", playerColor, playerType));
                    badMoves.add(new Move(j + 1, i, "O", playerColor, playerType));
                }

                if(gameBoard[j][i].isSelectionEmpty() && gameBoard[j + 1][i].isSelectionEmpty()
                        && gameBoard[j + 2][i].getSelection().equals("S")) {
                    badMoves.add(new Move(j + 1, i, "O", playerColor, playerType));
                    badMoves.add(new Move(j, i, "S", playerColor, playerType));
                }

                if(gameBoard[j][i].isSelectionEmpty() && gameBoard[j + 1][i].getSelection().equals("O")
                        && gameBoard[j + 2][i].isSelectionEmpty()) {
                    badMoves.add(new Move(j, i, "S", playerColor, playerType));
                    badMoves.add(new Move(j + 2, i, "S", playerColor, playerType));
                }
            }
        }
    }


    //This move is only called when there are no more optimal moves to play. It defaults to a random move
    private Move makeDefaultRandomMove() {
        Random random = new Random();

        int row;
        int col;

        do {
            row = random.nextInt(gameBoard.length);
            col = random.nextInt(gameBoard.length);
        } while (!gameBoard[row][col].isSelectionEmpty());

        playerChoice = random.nextBoolean() ? "S" : "O";
        return new Move(row, col, playerChoice, playerColor, playerType);
    }
}
