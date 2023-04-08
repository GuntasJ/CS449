package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MakeMoveSimpleGameTest {

    SOSGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = SOSGameLogic.newBuilder()
                .setSize(3)
                .setGameMode(GameMode.SIMPLE)
                .setPlayerTypeMode(SOSGameLogic.PlayerTypeMode.ALL_HUMAN)
                .setCurrentPlayerColor(Player.PlayerColor.RED_PLAYER)
                .build();
    }

    //AC 4.1
    @Test
    public void testSuccessfulMoveInSimpleGame() {
        String selection = "S";
        gameLogic.getCurrentPlayer().setPlayerChoice(selection);
        gameLogic.makeMove(1, 1);
        Assert.assertEquals("S", gameLogic.getGameBoard()[1][1].getSelection());
    }

    //AC 4.2
    @Test
    public void testInvalidMoveOnNonEmptyTileInSimpleGame() {
        String selection = "S";
        gameLogic.getCurrentPlayer().setPlayerChoice(selection);
        gameLogic.makeMove(1, 1);
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Choice must be placed on empty square.", e.getMessage());
        }
    }

    //AC 4.3
    @Test
    public void testInvalidMoveWithNoSelectionOnValidTileInSimpleGame() {
        try {
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Selection cannot be null.", e.getMessage());
        }
    }
    //AC 4.4
    @Test
    public void testInvalidMoveWithNoSelectionOnNonEmptyTileInSimpleGame() {
        testSuccessfulMoveInSimpleGame();
        try {
            gameLogic.getCurrentPlayer().setPlayerChoice(" ");
            gameLogic.makeMove(1, 1);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Current choice must be S or O", e.getMessage());
        }
    }

    @Test
    public void testGameEndsAfterAnSOSCombinationAndPlayerWithCombinationWins() {
        gameLogic.getRedPlayer().setPlayerChoice("S");
        gameLogic.getBluePlayer().setPlayerChoice("O");

        gameLogic.makeMove(0,0);
        gameLogic.makeMove(0, 1);
        gameLogic.makeMove(0, 2);

        Assert.assertNotSame(SOSGameLogic.GameState.GAME_NOT_OVER, gameLogic.getGameState());
        Assert.assertEquals(SOSGameLogic.GameState.RED_WON, gameLogic.getGameState());
    }
}
