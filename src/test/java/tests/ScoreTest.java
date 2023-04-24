package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScoreTest {

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

    @Test
    public void testScoreIncreasesWhenRedFormsACombo() {
        gameLogic.getCurrentPlayer().setPlayerChoice("S");
        gameLogic.makeHumanMove(0, 0);

        gameLogic.getBluePlayer().setPlayerChoice("O");
        gameLogic.makeHumanMove(0, 1);

        gameLogic.makeHumanMove(0, 2);

        Assert.assertEquals(1, gameLogic.getRedPlayer().getTotalSOSCombinations());
    }

    @Test
    public void testScoreIncreasesWhenBlueFormsACombo() {
        gameLogic.getCurrentPlayer().setPlayerChoice("S");
        gameLogic.makeHumanMove(0, 0);

        gameLogic.getCurrentPlayer().setPlayerChoice("O");
        gameLogic.makeHumanMove(0, 1);

        gameLogic.makeHumanMove(2, 2);

        gameLogic.getCurrentPlayer().setPlayerChoice("S");
        gameLogic.makeHumanMove(0, 2);

        Assert.assertEquals(1, gameLogic.getBluePlayer().getTotalSOSCombinations());
    }

}
