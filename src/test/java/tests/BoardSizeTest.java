package tests;

import gamelogic.SOSGameLogic;
import graphics.GameFrame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BoardSizeTest {

    SOSGameLogic gameLogic;
    GameFrame gameFrame;
    Robot robot;

    @Before
    public void setUp() throws AWTException {
        gameLogic = SOSGameLogic.newBuilder().build();
        gameFrame = new GameFrame();
        robot = new Robot();
    }

    //AC 1.1
    @Test
    public void testSizeThatIsValid() {
        int size = 5;
        gameLogic.setSize(size);
        Assert.assertEquals(5, gameLogic.getSize());
    }
    //AC 1.2
    @Test
    public void testSizeThatIsLessThanOrEqualToTwo() {
        int size = 2;
        try {
            gameLogic.setSize(size);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Size must be greater than 2.", e.getMessage());
        }
    }
    //AC 1.3
    @Test
    public void testSizeThatIsInvalidDataType() throws InterruptedException {
        gameFrame.setVisible(true);
        final boolean[] errorCaught = {false};
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> errorCaught[0] = true);
        JTextField textField = gameFrame.getPanel().getBoardSizeTextField();
        textField.setText("sdfsdf");
        textField.requestFocusInWindow();

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Assert.assertTrue(errorCaught[0]);

    }

}
