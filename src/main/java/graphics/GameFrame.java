package graphics;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private final GamePanel panel;

    public GameFrame() {
        panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    public GamePanel getPanel() {
        return panel;
    }
}
