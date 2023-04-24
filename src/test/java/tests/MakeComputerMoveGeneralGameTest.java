package tests;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import gamelogic.Tile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MakeComputerMoveGeneralGameTest {
    SOSGameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = SOSGameLogic.newBuilder()
                .setSize(3)
                .setGameMode(GameMode.GENERAL)
                .setStartingPlayer(Player.PlayerColor.RED_PLAYER)
                .setPlayerTypeMode(SOSGameLogic.PlayerTypeMode.RED_HUMAN_BLUE_COMPUTER)
                .build();
    }

    //AC 9.1
    @Test
    public void testComputerIsAbleToMakeMoveInGeneralGame() {
        gameLogic.getCurrentPlayer().setPlayerChoice("O");
        gameLogic.makeHumanMove(0, 0);
        gameLogic.makeComputerMove();

        int movesMade = 0;

        for(Tile[] row : gameLogic.getGameBoard()) {
            for(Tile tile : row) {
                if (!tile.isSelectionEmpty()) {
                    movesMade++;
                }
            }
        }

        Assert.assertEquals(2, movesMade);
    }

    //AC 9.2
    @Test
    public void testComputerIsAbleToMakeDefensiveMoveInGeneralGame() {
        gameLogic.getCurrentPlayer().setPlayerChoice("S");
        gameLogic.makeHumanMove(0, 0);

        //game logic should not have made a move that could cause an opponent victory
        //S was placed in 0,0
        //An S move at 0, 2 or an O move at 0, 1 would cause an opponent victory
        //will be testing 1,000,000 times checking in top row to see if such move was made
        for(int i = 0; i < 1_000_000; i++) {
            gameLogic.makeComputerMove();
            boolean computerMadeDetrimentalMove = gameLogic.getGameBoard()[0][1].getSelection().equals("O")
                    || gameLogic.getGameBoard()[0][2].getSelection().equals("S");

            Assert.assertFalse(computerMadeDetrimentalMove);

            //delete the computer move
            for(int j = 0; j < gameLogic.getSize(); j++) {
                for(int k = 0; k < gameLogic.getSize(); k++) {
                    if(j == 0 && k == 0) continue;
                    if(!gameLogic.getGameBoard()[j][k].isSelectionEmpty()) {
                        gameLogic.getGameBoard()[j][k].setSelection(" ");
                    }
                }
            }
            //switch the player once more so that it is the computers turn.
            gameLogic.switchPlayer();
        }
    }

    //AC 9.3
    @Test
    public void testComputerIsAbleToMakeOffensiveMoveInGeneralGame() {
        gameLogic.getCurrentPlayer().setPlayerChoice("S");
        gameLogic.makeHumanMove(0, 0);

        //switch player again so that the same player is now playing again.
        gameLogic.switchPlayer();
        Assert.assertEquals(gameLogic.getCurrentPlayer(), gameLogic.getRedPlayer());

        gameLogic.makeHumanMove(0, 2);

        gameLogic.makeComputerMove();

        Assert.assertEquals("O", gameLogic.getGameBoard()[0][1].getSelection());
    }

    //9.4
    @Test
    public void testItIsComputersTurnAfterComputerCompletesAnSOSComboInGeneralGame() {
        gameLogic.getCurrentPlayer().setPlayerChoice("S");
        gameLogic.makeHumanMove(0, 0);

        gameLogic.switchPlayer();
        gameLogic.makeHumanMove(0, 2);

        gameLogic.makeComputerMove();
        Assert.assertEquals(gameLogic.getBluePlayer(), gameLogic.getCurrentPlayer());
    }
}
