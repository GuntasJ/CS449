package graphics;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
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


    GamePanel() {
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 13));
        westPanel = new JPanel();
        eastPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();
        topButtons = new ButtonGroup();
        buttonGroupLabel = new JLabel();
        gameTypeGeneralRadioButton = new JRadioButton("General Game");
        gameTypeSimpleRadioButton = new JRadioButton("Simple Game");
        boardSizeLabel = new JLabel("Board Size: ");
        boardSizeTextField = new JTextField();

        //setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setLayout(new BorderLayout());
        setPreferredSize(GraphicConstants.GAME_PANEL_BOUNDS);
        setBackground(Color.BLACK);
        setUpTopPanelAndComponents();

        westPanel.setBackground(Color.BLUE);
        westPanel.setPreferredSize(new Dimension(200, 200));
        add(westPanel, BorderLayout.WEST);

        eastPanel.setBackground(Color.RED);
        eastPanel.setPreferredSize(new Dimension(200, 200));
        add(eastPanel, BorderLayout.EAST);

        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(200, 200));
        add(centerPanel, BorderLayout.CENTER);

        southPanel.setBackground(Color.GRAY);
        southPanel.setPreferredSize(new Dimension(100, 200));
        add(southPanel, BorderLayout.SOUTH);

    }

    private void setUpTopPanelAndComponents() {
        //top panel
        topPanel.setBackground(Color.GRAY);
        topPanel.setPreferredSize(GraphicConstants.TOP_PANEL_BOUNDS);

        //labels
        buttonGroupLabel.setText("SOS: ");
        buttonGroupLabel.setPreferredSize(GraphicConstants.BUTTON_GROUP_LABEL_BOUNDS);
        boardSizeLabel.setPreferredSize(GraphicConstants.BOARD_SIZE_LABEL_BOUNDS);


        //radio buttons
        gameTypeSimpleRadioButton.setPreferredSize(GraphicConstants.RADIO_BUTTON_GAME_TYPE_BOUNDS);
        gameTypeGeneralRadioButton.setPreferredSize(GraphicConstants.RADIO_BUTTON_GAME_TYPE_BOUNDS);

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

    private void setUpMiddleLeftPanelAndComponents() {

    }
    private void setUpMiddleRightPanelAndComponents() {

    }
    private void setUpSOSPanel() {

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class SOSGameTile extends JPanel {

        public SOSGameTile() {
            this.setBackground(Color.BLACK);
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        }
    }

}
