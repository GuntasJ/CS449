package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameOverGeneralTest {

    SOSGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = SOSGameLogic.newBuilder()
                .setGameMode(GameMode.GENERAL)
                .setSize(3)
                .setStartingPlayer(Player.PlayerColor.RED_PLAYER)
                .setPlayerTypeMode(SOSGameLogic.PlayerTypeMode.ALL_HUMAN)
                .build();
    }

    //AC 7.1
    @Test
    public void testGameShouldEndWhenAllSquaresAreFilledWithRedPlayerWinningWithMoreCombinations() {
        gameLogic.getRedPlayer().setPlayerChoice("S");
        gameLogic.getBluePlayer().setPlayerChoice("O");

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                gameLogic.makeHumanMove(i, j);
            }
        }
        Assert.assertNotSame(gameLogic.getGameState(), SOSGameLogic.GameState.GAME_NOT_OVER);
        Assert.assertEquals(SOSGameLogic.GameState.RED_WON, gameLogic.getGameState());
    }

    //AC 7.2
    @Test
    public void testGameShouldEndInStalemateAfterAllSquaresAreFilledAndEachPlayerHasEqualCombinations() {
        gameLogic.getRedPlayer().setPlayerChoice("S");
        gameLogic.getBluePlayer().setPlayerChoice("O");

        for(int i = 0; i < 3; i++) {
            gameLogic.makeHumanMove(0, i);
        }

        gameLogic.makeHumanMove(1, 0); // red
        gameLogic.makeHumanMove(1, 1); // blue
        gameLogic.getCurrentPlayer().setPlayerChoice("O"); //red
        gameLogic.makeHumanMove(2, 0); //red
        gameLogic.getCurrentPlayer().setPlayerChoice("S"); //blue
        gameLogic.makeHumanMove(1, 2); //blue
        gameLogic.makeHumanMove(2, 1); //blue
        gameLogic.makeHumanMove(2, 2); //red

        Assert.assertEquals(SOSGameLogic.GameState.TIE, gameLogic.getGameState());
        /*
        s o s
        s o s
        o s o
         */
    }


}
