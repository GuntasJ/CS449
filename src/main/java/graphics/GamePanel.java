package graphics;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    private JRadioButton radioButton;
    private JCheckBox checkBox;
    private JLabel label;

    GamePanel() {
        radioButton = new JRadioButton();
        checkBox = new JCheckBox();
        label = new JLabel("testing");
        label.setForeground(Color.WHITE);
        label.setSize(300, 300);
        setPreferredSize(GraphicConstants.PANEL_BOUNDS);
        setBackground(Color.BLACK);
        add(radioButton);
        add(checkBox);
        add(label);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawLine(50, 50, 100, 100);
        g.drawLine(100, 50, 150, 100);
        g.drawLine(150, 50, 200, 100);
    }
}
