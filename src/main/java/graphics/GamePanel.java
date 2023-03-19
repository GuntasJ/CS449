package graphics;

import gamelogic.GameMode;
import gamelogic.Player;
import gamelogic.SOSGameLogic;
import gamelogic.SOSGameUtils;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class GamePanel extends JPanel {

    private final JPanel northPanel;
    private final JPanel westPanel;
    private final JPanel eastPanel;
    private final JPanel centerPanel;
    private final JPanel southPanel;

    private final ButtonGroup gameTypeRadioButtonGroup;
    private final JRadioButton gameTypeSimpleRadioButton;
    private final JRadioButton gameTypeGeneralRadioButton;

    private final ButtonGroup redPlayerButtonGroup;
    private final JRadioButton redPlayerSRadioButton;
    private final JRadioButton redPlayerORadioButton;

    private final ButtonGroup bluePlayerButtonGroup;
    private final JRadioButton bluePlayerSRadioButton;
    private final JRadioButton bluePlayerORadioButton;

    private final JLabel buttonGroupLabel;
    private final JLabel boardSizeLabel;
    private final JLabel redPlayerLabel;
    private final JLabel bluePlayerLabel;
    private final JLabel currentPlayerTurnLabel;

    private final JTextField boardSizeTextField;

    private final JButton newGameButton;

    private final ActionListener makeSOSTiles;

    private final SOSGameLogic gameLogic;


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
        currentPlayerTurnLabel = new JLabel("Current Player: ");

        gameTypeRadioButtonGroup = new ButtonGroup();
        gameTypeGeneralRadioButton = new JRadioButton("General Game");
        gameTypeSimpleRadioButton = new JRadioButton("Simple Game");

        redPlayerButtonGroup = new ButtonGroup();
        redPlayerORadioButton = new JRadioButton("O");
        redPlayerSRadioButton = new JRadioButton("S");

        bluePlayerButtonGroup = new ButtonGroup();
        bluePlayerORadioButton = new JRadioButton("O");
        bluePlayerSRadioButton = new JRadioButton("S");

        boardSizeTextField = new JTextField();

        newGameButton = new JButton("New Game");

        gameLogic = new SOSGameLogic(Player.RED_PLAYER);

        makeSOSTiles = e -> {
            gameLogic.setSize(Integer.parseInt(boardSizeTextField.getText()));
            gameLogic.clearBoard();
            centerPanel.removeAll();
            centerPanel.setLayout(new GridLayout(gameLogic.getSize(), gameLogic.getSize()));

            if(redPlayerSRadioButton.isSelected()) {
                gameLogic.setCurrentChoiceRed("S");
            }
            else if(redPlayerORadioButton.isSelected()) {
                gameLogic.setCurrentChoiceRed("O");
            }

            if(bluePlayerSRadioButton.isSelected()) {
                gameLogic.setCurrentChoiceBlue("S");
            }
            else if(bluePlayerORadioButton.isSelected()) {
                gameLogic.setCurrentChoiceBlue("O");
            }

            for(int i = 0; i < Math.pow(gameLogic.getSize(), 2); i++) {
                centerPanel.add(new SOSGameTile(i));
            }

            currentPlayerTurnLabel.setText("Current Player: " + gameLogic.getCurrentPlayer());

            if(gameTypeSimpleRadioButton.isSelected()) {
                gameLogic.setGameMode(GameMode.SIMPLE);
            } else if(gameTypeGeneralRadioButton.isSelected()) {
                gameLogic.setGameMode(GameMode.GENERAL);
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
        gameTypeSimpleRadioButton.addActionListener(e -> gameLogic.setGameMode(GameMode.SIMPLE));
        gameTypeGeneralRadioButton.addActionListener(e -> gameLogic.setGameMode(GameMode.GENERAL));
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

        gameTypeRadioButtonGroup.add(gameTypeSimpleRadioButton);
        gameTypeRadioButtonGroup.add(gameTypeGeneralRadioButton);
        northPanel.add(gameTypeSimpleRadioButton);
        northPanel.add(gameTypeGeneralRadioButton);

        gameTypeSimpleRadioButton.setSelected(true);

        northPanel.add(Box.createRigidArea(new Dimension(250, 0)));

        northPanel.add(boardSizeLabel);

        northPanel.add(boardSizeTextField);


        add(northPanel, BorderLayout.NORTH);
    }

    private void setUpEastPanelAndComponents() {
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setPreferredSize(new Dimension(150, 200));

        redPlayerLabel.setAlignmentX(CENTER_ALIGNMENT);
        redPlayerLabel.setFont(redPlayerLabel.getFont().deriveFont(20f));

        eastPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        eastPanel.add(redPlayerLabel);

        eastPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        redPlayerORadioButton.addActionListener(e -> gameLogic.setCurrentChoice("O"));
        redPlayerSRadioButton.addActionListener(e -> gameLogic.setCurrentChoice("S"));

        redPlayerButtonGroup.add(redPlayerORadioButton);
        redPlayerButtonGroup.add(redPlayerSRadioButton);
        redPlayerSRadioButton.setSelected(true);

        eastPanel.add(redPlayerSRadioButton);
        eastPanel.add(redPlayerORadioButton);

        add(eastPanel, BorderLayout.EAST);
    }
    private void setUpWestPanelAndComponents() {
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setPreferredSize(new Dimension(150, 200));

        bluePlayerLabel.setAlignmentX(CENTER_ALIGNMENT);
        bluePlayerLabel.setFont(bluePlayerLabel.getFont().deriveFont(20f));

        westPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        westPanel.add(bluePlayerLabel);

        westPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        bluePlayerORadioButton.addActionListener(e -> gameLogic.setCurrentChoice("O"));
        bluePlayerSRadioButton.addActionListener(e -> gameLogic.setCurrentChoice("S"));

        bluePlayerButtonGroup.add(bluePlayerORadioButton);
        bluePlayerButtonGroup.add(bluePlayerSRadioButton);
        bluePlayerSRadioButton.setSelected(true);

        westPanel.add(bluePlayerSRadioButton);
        westPanel.add(bluePlayerORadioButton);

        add(westPanel, BorderLayout.WEST);

    }
    private void setUpSouthPanelAndComponents() {
        newGameButton.setPreferredSize(GraphicConstants.NEW_GAME_BUTTON_BOUNDS);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(makeSOSTiles);

        southPanel.add(currentPlayerTurnLabel);

        southPanel.add(Box.createRigidArea(new Dimension(200, 0)));

        southPanel.add(newGameButton);


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
        private String selection;

        public SOSGameTile(int index) {
            this.index = index;
            setBackground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    int[] cords = SOSGameUtils.convertOneDIndexToTwoD(index, gameLogic.getSize());
                    selection = gameLogic.getCurrentChoice();
                    gameLogic.makeMove(cords[0], cords[1]);

                    if(gameLogic.getGameMode() == GameMode.SIMPLE) {
                        gameLogic.switchPlayer();
                    }

                    currentPlayerTurnLabel.setText("Current Player: " + gameLogic.getCurrentPlayer());

                    repaint();

                }
            });
        }

        public int getIndex() {
            return index;
        }

        private void drawS(Graphics2D g) {
            float fontSize = (float) (5 * (GraphicConstants.STROKE_SIZE_RATIO * getWidth()));
            g.setFont(g.getFont().deriveFont(fontSize));
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth("S")) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g.drawString("S", x, y);
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

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = getGraphicsForDrawingChoice(g);
            if(selection == null) {
                return;
            }
            if(selection.equals("S")) {
                drawS(graphics2D);
            }
            else if(selection.equals("O")) {
                drawO(graphics2D);
            }
        }
    }

}
