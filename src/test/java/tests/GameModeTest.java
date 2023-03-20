package tests;

import graphics.GameFrame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;

public class GameModeTest {

    GameFrame gameFrame;
    Robot robot;

    @Before
    public void setUp() {
        gameFrame = new GameFrame();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDefaultGameModeShouldBeSimple() {
        Assert.assertTrue(gameFrame.getPanel().getGameTypeSimpleRadioButton().isSelected());
        Assert.assertFalse(gameFrame.getPanel().getGameTypeGeneralRadioButton().isSelected());
    }

    @Test
    public void testClickingGeneralButtonShouldDeselectSimple() {
        gameFrame.getPanel().getGameTypeGeneralRadioButton().setSelected(true);
        Assert.assertTrue(gameFrame.getPanel().getGameTypeGeneralRadioButton().isSelected());
        Assert.assertFalse(gameFrame.getPanel().getGameTypeSimpleRadioButton().isSelected());
    }

    @Test
    public void testClickingSimpleButtonShouldDeselectGeneral() {
        gameFrame.getPanel().getGameTypeGeneralRadioButton().setSelected(true);
        gameFrame.getPanel().getGameTypeSimpleRadioButton().setSelected(true);
        Assert.assertTrue(gameFrame.getPanel().getGameTypeSimpleRadioButton().isSelected());
        Assert.assertFalse(gameFrame.getPanel().getGameTypeGeneralRadioButton().isSelected());
    }

    //AC 2.1
    @Test
    public void testClickingGeneralButtonUsingGUIShouldSelectGeneral() throws InterruptedException {
        gameFrame.setVisible(true);
        Point generalButtonPoint = gameFrame.getPanel().getGameTypeGeneralRadioButton().getLocationOnScreen();
        robot.mouseMove(generalButtonPoint.x, generalButtonPoint.y);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(250);

        Assert.assertTrue(gameFrame.getPanel().getGameTypeGeneralRadioButton().isSelected());
    }

    //AC 2.2
    @Test
    public void testClickingSimpleButtonUsingGUIShouldSelectSimple() throws InterruptedException {
        gameFrame.getPanel().getGameTypeGeneralRadioButton().setSelected(true);

        gameFrame.setVisible(true);
        Point simpleButtonPoint = gameFrame.getPanel().getGameTypeSimpleRadioButton().getLocationOnScreen();
        robot.mouseMove(simpleButtonPoint.x, simpleButtonPoint.y);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(250);

        Assert.assertTrue(gameFrame.getPanel().getGameTypeSimpleRadioButton().isSelected());
    }


}
