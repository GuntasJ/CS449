package graphics;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;


public class GamePanel extends JPanel {

    private final JPanel topPanel;
    private final JPanel westPanel;
    private final JPanel eastPanel;
    private final JPanel centerPanel;
    private final JPanel southPanel;

    private final ButtonGroup topButtons;
    private final JRadioButton gameTypeSimpleRadioButton;
    private final JRadioButton gameTypeGeneralRadioButton;

    private final JLabel buttonGroupLabel;
    private final JLabel boardSizeLabel;

    private final JTextField boardSizeTextField;

    private final JButton newGameButton;

    private int gameSize = 3;


    GamePanel() {
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 13));
        westPanel = new JPanel();
        eastPanel = new JPanel();
        centerPanel = new JPanel(new GridLayout(3, 3));
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        buttonGroupLabel = new JLabel();
        boardSizeLabel = new JLabel("Board Size: ");

        topButtons = new ButtonGroup();
        gameTypeGeneralRadioButton = new JRadioButton("General Game");
        gameTypeSimpleRadioButton = new JRadioButton("Simple Game");

        boardSizeTextField = new JTextField();

        newGameButton = new JButton("New Game");

        //setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
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
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setPreferredSize(GraphicConstants.NORTH_PANEL_BOUNDS);

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


        //adding them

        topPanel.add(buttonGroupLabel);


        topButtons.add(gameTypeSimpleRadioButton);
        topButtons.add(gameTypeGeneralRadioButton);
        topPanel.add(gameTypeSimpleRadioButton);
        topPanel.add(gameTypeGeneralRadioButton);


        topPanel.add(boardSizeLabel);

        topPanel.add(boardSizeTextField);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setUpEastPanelAndComponents() {
        eastPanel.setBackground(Color.RED);
        eastPanel.setPreferredSize(new Dimension(150, 200));
        add(eastPanel, BorderLayout.EAST);
    }
    private void setUpWestPanelAndComponents() {
        westPanel.setBackground(Color.BLUE);
        westPanel.setPreferredSize(new Dimension(150, 200));
        add(westPanel, BorderLayout.WEST);

    }
    private void setUpSouthPanelAndComponents() {
        newGameButton.setPreferredSize(GraphicConstants.NEW_GAME_BUTTON_BOUNDS);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(e -> {
            gameSize = Integer.parseInt(boardSizeTextField.getText());
            centerPanel.removeAll();
            centerPanel.setLayout(new GridLayout(gameSize, gameSize));
            for(int i = 0; i < Math.pow(gameSize, 2); i++) {
                centerPanel.add(new SOSGameTile(i));
            }
            centerPanel.revalidate();
        });
        southPanel.add(newGameButton);


        southPanel.setBackground(Color.DARK_GRAY);
        southPanel.setPreferredSize(GraphicConstants.SOUTH_PANEL_BOUNDS);
        add(southPanel, BorderLayout.SOUTH);

    }
    private void setUpCenterPanelAndComponents() {
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setPreferredSize(GraphicConstants.CENTER_PANEL_BOUNDS);


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
            setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));

        }
    }

}
