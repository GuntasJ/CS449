package graphics;

import gamelogic.Player;
import gamelogic.SOSGameLogic;

import javax.swing.*;

public class MenuFrame extends JFrame {

    public MenuFrame(SOSGameLogic.GameState gameState) {
        add(new MenuPanel(gameState));
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


}
