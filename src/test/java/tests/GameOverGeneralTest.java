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
                gameLogic.makeMove(i, j);
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
            gameLogic.makeMove(0, i);
        }

        gameLogic.makeMove(1, 0); // red
        gameLogic.makeMove(1, 1); // blue
        gameLogic.getCurrentPlayer().setPlayerChoice("O"); //red
        gameLogic.makeMove(2, 0); //red
        gameLogic.getCurrentPlayer().setPlayerChoice("S"); //blue
        gameLogic.makeMove(1, 2); //blue
        gameLogic.makeMove(2, 1); //blue
        gameLogic.makeMove(2, 2); //red

        Assert.assertEquals(SOSGameLogic.GameState.TIE, gameLogic.getGameState());
        /*
        s o s
        s o s
        o s o
         */
    }


}
