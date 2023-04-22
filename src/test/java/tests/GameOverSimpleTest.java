package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameOverSimpleTest {

    SOSGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = SOSGameLogic.newBuilder()
                .setGameMode(GameMode.SIMPLE)
                .setSize(3)
                .setStartingPlayer(Player.PlayerColor.RED_PLAYER)
                .setPlayerTypeMode(SOSGameLogic.PlayerTypeMode.ALL_HUMAN)
                .build();
    }

    //AC 5.1
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

    //AC 5.2
    @Test
    public void testGameEndsInStalemateIfNoSOSCombinationsAreFormedAndNoValidSquaresLeft() {
        gameLogic.getRedPlayer().setPlayerChoice("S");
        gameLogic.getBluePlayer().setPlayerChoice("S");

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                gameLogic.makeMove(i, j);
            }
        }

        Assert.assertEquals(SOSGameLogic.GameState.TIE, gameLogic.getGameState());
    }
}
