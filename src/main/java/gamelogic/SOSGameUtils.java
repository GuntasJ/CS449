package gamelogic;

import java.util.Arrays;

public class SOSGameUtils {

    private static int combinationCount = 0;

    public static int convertTwoDIndexToOneD(int x, int y, int arraySize) {
        return x * arraySize + y;
    }
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
                t1.addSOSCombination(player, direction, combinationCount);
                t2.addSOSCombination(player, direction, combinationCount);
                t3.addSOSCombination(player, direction, combinationCount);

                System.out.println("New SOS combo formed: Vertical");
            }
            case HORIZONTAL -> {
                t1.addSOSCombination(player, direction, combinationCount);
                t2.addSOSCombination(player, direction, combinationCount);
                t3.addSOSCombination(player, direction, combinationCount);

                System.out.println("New SOS combo formed: Horizontal");
            }
            case POSITIVE_DIAGONAL -> {
                t1.addSOSCombination(player, direction,combinationCount);
                t2.addSOSCombination(player, direction,combinationCount);
                t3.addSOSCombination(player, direction,combinationCount);

                System.out.println("New SOS combo formed: Positive Diagonal");
            }
            case NEGATIVE_DIAGONAL -> {
                t1.addSOSCombination(player, direction,combinationCount);
                t2.addSOSCombination(player, direction,combinationCount);
                t3.addSOSCombination(player, direction,combinationCount);

                System.out.println("New SOS combo formed: Negative Diagonal");
            }
        }
        combinationCount++;
    }

    public static void printBoard(Tile[][] gameBoard) {
        for(Tile[] row : gameBoard) {
            for(Tile tile : row) {
                System.out.print(tile);
            }
            System.out.println();
        }
    }

}
