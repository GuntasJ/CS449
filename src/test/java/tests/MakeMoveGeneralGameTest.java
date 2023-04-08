package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MakeMoveGeneralGameTest {

    SOSGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = SOSGameLogic.newBuilder()
                .setSize(3)
                .setCurrentPlayerColor(Player.PlayerColor.RED_PLAYER)
                .setGameMode(GameMode.GENERAL)
                .setPlayerTypeMode(SOSGameLogic.PlayerTypeMode.ALL_HUMAN)
                .build();
    }

    //AC 6.1
    @Test
    public void testSuccessfulMoveInGeneralGame() {
        String selection = "S";
        gameLogic.getCurrentPlayer().setPlayerChoice(selection);
        gameLogic.makeMove(1, 1);
        Assert.assertEquals("S", gameLogic.getGameBoard()[1][1].getSelection());
    }

    //AC 6.3
    @Test
    public void testInvalidMoveOnNonEmptyTileInGeneralGame() {
        String selection = "S";
        gameLogic.getCurrentPlayer().setPlayerChoice(selection);
        gameLogic.makeMove(1, 1);
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Choice must be placed on empty square.", e.getMessage());
        }
    }

    //AC 6.4
    @Test
    public void testInvalidMoveWithNoSelectionOnValidTileInGeneralGame() {
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Selection cannot be null.", e.getMessage());
        }
    }
    //AC 6.5
    @Test
    public void testInvalidMoveWithNoSelectionOnNonEmptyTileInGeneralGame() {
        testSuccessfulMoveInGeneralGame();
        try {
            gameLogic.getCurrentPlayer().setPlayerChoice(" ");
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Current choice must be S or O", e.getMessage());
        }
    }

    @Test
    public void testShouldBeRedPlayerTurnAfterMakingSOSCombination() {
        gameLogic.getRedPlayer().setPlayerChoice("S");
        gameLogic.getBluePlayer().setPlayerChoice("O");

        gameLogic.makeMove(0, 0);
        gameLogic.makeMove(1, 1);
        gameLogic.makeMove(2, 2);

        Assert.assertEquals(gameLogic.getRedPlayer(), gameLogic.getCurrentPlayer());
    }

    @Test
    public void testGameShouldEndWhenAllSquaresAreFilledWithRedPlayerWinningWithMoreCombinations() {
        gameLogic.getRedPlayer().setPlayerChoice("S");
        gameLogic.getBluePlayer().setPlayerChoice("O");

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                gameLogic.makeMove(i, j);
            }
        }
        Assert.assertNotSame(gameLogic.getGameState(), SOSGameLogic.GameState.GAME_NOT_OVER);
        Assert.assertEquals(SOSGameLogic.GameState.RED_WON, gameLogic.getGameState());
    }

}