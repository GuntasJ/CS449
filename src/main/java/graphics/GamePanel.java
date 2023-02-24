package graphics;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel {

    private final JPanel northPanel;
    private final JPanel westPanel;
    private final JPanel eastPanel;
    private final JPanel centerPanel;
    private final JPanel southPanel;

    private final ButtonGroup topButtons;
    private final JRadioButton gameTypeSimpleRadioButton;
    private final JRadioButton gameTypeGeneralRadioButton;

    private final JLabel buttonGroupLabel;
    private final JLabel boardSizeLabel;
    private final JLabel redPlayerLabel;
    private final JLabel bluePlayerLabel;

    private final JTextField boardSizeTextField;

    private final JButton newGameButton;

    private final ActionListener makeSOSTiles;

    private int gameSize = 3;


    GamePanel() {

        northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 13));
        westPanel = new JPanel();
        eastPanel = new JPanel();
        centerPanel = new JPanel(new GridLayout(3, 3));
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        buttonGroupLabel = new JLabel();
        boardSizeLabel = new JLabel("Board Size: ");
        redPlayerLabel = new JLabel("Red Player");
        bluePlayerLabel = new JLabel("Blue Player");

        topButtons = new ButtonGroup();
        gameTypeGeneralRadioButton = new JRadioButton("General Game");
        gameTypeSimpleRadioButton = new JRadioButton("Simple Game");

        boardSizeTextField = new JTextField();

        newGameButton = new JButton("New Game");

        makeSOSTiles = e -> {
            gameSize = Integer.parseInt(boardSizeTextField.getText());
            centerPanel.removeAll();
            centerPanel.setLayout(new GridLayout(gameSize, gameSize));
            for(int i = 0; i < Math.pow(gameSize, 2); i++) {
                centerPanel.add(new SOSGameTile(i));
            }
            centerPanel.revalidate();
        };

        setLayout(new BorderLayout());
        setPreferredSize(GraphicConstants.GAME_PANEL_BOUNDS);
        setBackground(Color.BLACK);

        setUpNorthPanelAndComponents();
        setUpEastPanelAndComponents();
        setUpWestPanelAndComponents();
        setUpCenterPanelAndComponents();
        setUpSouthPanelAndComponents();

    }

    private void setUpNorthPanelAndComponents() {
        //top panel
        northPanel.setPreferredSize(GraphicConstants.NORTH_PANEL_BOUNDS);

        //labels
        buttonGroupLabel.setText("SOS: ");
        buttonGroupLabel.setPreferredSize(GraphicConstants.BUTTON_GROUP_LABEL_BOUNDS);
        boardSizeLabel.setPreferredSize(GraphicConstants.BOARD_SIZE_LABEL_BOUNDS);


        //radio buttons
        gameTypeSimpleRadioButton.setPreferredSize(GraphicConstants.RADIO_BUTTON_GAME_TYPE_BOUNDS);
        gameTypeGeneralRadioButton.setPreferredSize(GraphicConstants.RADIO_BUTTON_GAME_TYPE_BOUNDS);
        gameTypeSimpleRadioButton.setFocusPainted(false);
        gameTypeGeneralRadioButton.setFocusPainted(false);

        //Text field
        boardSizeTextField.setPreferredSize(GraphicConstants.TEXT_FIELD_BOUNDS);
        boardSizeTextField.setEditable(true);
        boardSizeTextField.addActionListener(makeSOSTiles);


        //adding them


        northPanel.add(buttonGroupLabel);

        topButtons.add(gameTypeSimpleRadioButton);
        topButtons.add(gameTypeGeneralRadioButton);
        northPanel.add(gameTypeSimpleRadioButton);
        northPanel.add(gameTypeGeneralRadioButton);

        northPanel.add(Box.createRigidArea(new Dimension(250, 0)));

        northPanel.add(boardSizeLabel);

        northPanel.add(boardSizeTextField);


        add(northPanel, BorderLayout.NORTH);
    }

    private void setUpEastPanelAndComponents() {
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setPreferredSize(new Dimension(150, 200));

        redPlayerLabel.setAlignmentX(CENTER_ALIGNMENT);

        eastPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        eastPanel.add(redPlayerLabel);

        add(eastPanel, BorderLayout.EAST);
    }
    private void setUpWestPanelAndComponents() {
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setPreferredSize(new Dimension(150, 200));

        bluePlayerLabel.setAlignmentX(CENTER_ALIGNMENT);
        bluePlayerLabel.setFont(bluePlayerLabel.getFont().deriveFont(20f));

        westPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        westPanel.add(bluePlayerLabel);

        add(westPanel, BorderLayout.WEST);

    }
    private void setUpSouthPanelAndComponents() {
        newGameButton.setPreferredSize(GraphicConstants.NEW_GAME_BUTTON_BOUNDS);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(makeSOSTiles);
        southPanel.add(newGameButton);


        //southPanel.setBackground(Color.DARK_GRAY);
        southPanel.setPreferredSize(GraphicConstants.SOUTH_PANEL_BOUNDS);
        add(southPanel, BorderLayout.SOUTH);

    }
    private void setUpCenterPanelAndComponents() {
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setPreferredSize(GraphicConstants.CENTER_PANEL_BOUNDS);
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));


        add(centerPanel, BorderLayout.CENTER);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class SOSGameTile extends JPanel {

        private final int index;

        public SOSGameTile(int index) {
            this.index = index;
            setBackground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        }

        public int getIndex() {
            return index;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = getGraphicsForDrawingChoice(g);
            if(index % 2 == 0) {
                drawX(graphics2D);
            }
            else {
                drawO(graphics2D);
            }
        }

        private void drawX(Graphics2D g) {
            int offset = (int)(getWidth() * 0.25);
            g.drawLine(offset, offset, getWidth() - offset, getHeight() - offset);
            g.drawLine(offset, getHeight() - offset, getWidth() - offset, offset);
        }

        private void drawO(Graphics2D g) {
            int offset = (int)(getWidth() * 0.25);
            int circleDiameter = getWidth() - 2 * offset;
            g.drawOval((getWidth() - circleDiameter) / 2, (getHeight() - circleDiameter) / 2,
                    circleDiameter, circleDiameter);
        }

        private Graphics2D getGraphicsForDrawingChoice(Graphics g) {
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.setColor(Color.WHITE);
            int strokeSize = (int)(GraphicConstants.STROKE_SIZE_RATIO * getWidth());
            graphics2D.setStroke(new BasicStroke(strokeSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            return graphics2D;
        }
    }

}
