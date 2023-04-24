package gamelogic;

import java.util.function.Predicate;

public class SOSGameUtils {

    public static int[] convertOneDIndexToTwoD(int index, int arraySize) {
        return new int[] {index / arraySize, index % arraySize};
    }

    /*
    00, 01, 02, 03, 04,
    05, 06, 07, 08, 09,
    10, 11, 12, 13, 14,
    15, 16, 17, 18, 19,
    20, 21, 22, 23, 24
     */




    public static void checkForAndMarkCombination(Tile[][] gameBoard, Player player) {
        checkAndMarkThreeInRow(gameBoard, player);
        checkAndMarkThreeInCol(gameBoard, player);
        checkAndMarkThreeInPositiveDiagonal(gameBoard, player);
        checkAndMarkThreeInNegativeDiagonal(gameBoard, player);
    }

    private static void checkAndMarkThreeInRow(Tile[][] gameBoard, Player player) {
        for (Tile[] tiles : gameBoard) {
            for (int j = 0; j < gameBoard[0].length - 2; j++) {
                if (isNewSOSCombination(tiles[j], tiles[j + 1], tiles[j + 2])) {
                    markTiles(tiles[j], tiles[j + 1], tiles[j + 2], Direction.HORIZONTAL, player);
                }
            }
        }
    }

    private static void checkAndMarkThreeInCol(Tile[][] gameBoard, Player player) {
        for(int col = 0; col < gameBoard.length; col++) {
            for(int row = 0; row < gameBoard[0].length - 2; row++) {
                if(isNewSOSCombination(gameBoard[row][col], gameBoard[row + 1][col], gameBoard[row + 2][col])) {
                    markTiles(gameBoard[row][col], gameBoard[row + 1][col], gameBoard[row + 2][col],
                            Direction.VERTICAL, player);
                }
            }
        }
    }

    private static void checkAndMarkThreeInNegativeDiagonal(Tile[][] gameBoard, Player player) {
        for(int i = 2; i < gameBoard.length; i++) {
            checkAndMarkSingleFirstHalfNegativeDiagonal(i, gameBoard, player);
        }
        for(int i = 2; i < gameBoard.length; i++) {
            checkAndMarkSingleSecondHalfNegativeDiagonal(i, gameBoard, player);
        }
    }
    private static void checkAndMarkSingleFirstHalfNegativeDiagonal(int startingRow, Tile[][] gameBoard, Player player) {
        int col = gameBoard.length - 1;
        loopThroughNegativeDiagonal(startingRow, gameBoard, player, startingRow, col);
    }
    private static void checkAndMarkSingleSecondHalfNegativeDiagonal(int startingCol, Tile[][] gameBoard, Player player) {
        int row = gameBoard.length - 1;
        loopThroughNegativeDiagonal(startingCol, gameBoard, player, row, startingCol);
    }

    private static void loopThroughNegativeDiagonal(int startingCol, Tile[][] gameBoard, Player player, int row, int col) {
        int limit = startingCol - 1;

        for(int i = 0; i < limit; i++) {
            if(isNewSOSCombination(gameBoard[row][col], gameBoard[row - 1][col - 1], gameBoard[row - 2][col - 2])) {
                markTiles(gameBoard[row][col], gameBoard[row - 1][col - 1], gameBoard[row - 2][col - 2],
                        Direction.NEGATIVE_DIAGONAL, player);
            }
            row--;
            col--;
        }
    }

    private static void checkAndMarkThreeInPositiveDiagonal(Tile[][] gameBoard, Player player) {
        for(int i = 2; i < gameBoard.length; i++) {
            checkAndMarkSingleFirstHalfPositiveDiagonal(i, gameBoard, player);
        }
        for(int i = gameBoard.length - 3; i >= 0; i--) {
            checkAndMarkSingleSecondHalfPositiveDiagonal(i, gameBoard, player);
        }
    }

    private static void checkAndMarkSingleFirstHalfPositiveDiagonal(int startingRow, Tile[][] gameBoard, Player player) {
        int col = 0;
        int limit = startingRow - 1;

        loopThroughPositiveDiagonal(gameBoard, player, col, startingRow, limit);
    }
    private static void checkAndMarkSingleSecondHalfPositiveDiagonal(int startingCol, Tile[][] gameBoard, Player player) {
        int row = gameBoard.length - 1;
        int limit = gameBoard.length - startingCol - 2;

        loopThroughPositiveDiagonal(gameBoard, player, startingCol, row, limit);

    }

    private static void loopThroughPositiveDiagonal(Tile[][] gameBoard, Player player, int col, int row, int limit) {
        for(int i = 0; i < limit; i++) {
            if(isNewSOSCombination(gameBoard[row][col], gameBoard[row - 1][col + 1],
                    gameBoard[row - 2][col + 2])) {
                markTiles(gameBoard[row][col], gameBoard[row - 1][col + 1], gameBoard[row - 2][col + 2],
                        Direction.POSITIVE_DIAGONAL, player);
            }
            row--;
            col++;
        }
    }

    private static boolean isNewSOSCombination(Tile t1, Tile t2, Tile t3) {
        boolean validCombination =
                t1.getSelection().equals("S") && t2.getSelection().equals("O") && t3.getSelection().equals("S");
        return validCombination && isSameSOSCombinationNotInTiles(t1, t2, t3);
    }

    private static boolean isSameSOSCombinationNotInTiles(Tile t1, Tile t2, Tile t3) {
        return t1.getCombinations().isEmpty() || t2.getCombinations().isEmpty() || t3.getCombinations().isEmpty();
    }

    private static void markTiles(Tile t1, Tile t2, Tile t3, Direction direction, Player player) {
        switch (direction) {
            case VERTICAL -> {
                t1.addSOSCombination(player, direction);
                t2.addSOSCombination(player, direction);
                t3.addSOSCombination(player, direction);

                System.out.println("New SOS combo formed: Vertical");
            }
            case HORIZONTAL -> {
                t1.addSOSCombination(player, direction);
                t2.addSOSCombination(player, direction);
                t3.addSOSCombination(player, direction);

                System.out.println("New SOS combo formed: Horizontal");
            }
            case POSITIVE_DIAGONAL -> {
                t1.addSOSCombination(player, direction);
                t2.addSOSCombination(player, direction);
                t3.addSOSCombination(player, direction);

                System.out.println("New SOS combo formed: Positive Diagonal");
            }
            case NEGATIVE_DIAGONAL -> {
                t1.addSOSCombination(player, direction);
                t2.addSOSCombination(player, direction);
                t3.addSOSCombination(player, direction);

                System.out.println("New SOS combo formed: Negative Diagonal");
            }
        }
        player.incrementTotalSOSCombinations();
    }
}
