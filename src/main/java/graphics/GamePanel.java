package graphics;

import gamelogic.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

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

    private final ButtonGroup redPlayerComputerHumanButtonGroup;
    private final JRadioButton redPlayerComputerButton;
    private final JRadioButton redPlayerHumanButton;

    private final ButtonGroup bluePlayerComputerHumanButtonGroup;
    private final JRadioButton bluePlayerComputerButton;
    private final JRadioButton bluePlayerHumanButton;

    private final JCheckBox recordGameCheckBox;

    private final JLabel buttonGroupLabel;
    private final JLabel boardSizeLabel;
    private final JLabel redPlayerLabel;
    private final JLabel bluePlayerLabel;
    private final JLabel currentPlayerTurnLabel;
    private final JLabel redScoreLabel;
    private final JLabel blueScoreLabel;

    private final JTextField boardSizeTextField;

    private final JButton newGameButton;
    private final JButton replayGameButton;

    private final ActionListener makeSOSTiles;

    private volatile SOSGameLogic gameLogic;
    private volatile SOSGameRecorder gameRecorder;

    private Thread computerPlayerThread = new Thread(this);

    private boolean isGameOver;

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
        redScoreLabel = new JLabel("Red Score: 0");
        blueScoreLabel = new JLabel("Blue Score: 0");


        gameTypeRadioButtonGroup = new ButtonGroup();
        gameTypeGeneralRadioButton = new JRadioButton("General Game");
        gameTypeSimpleRadioButton = new JRadioButton("Simple Game");

        redPlayerButtonGroup = new ButtonGroup();
        redPlayerORadioButton = new JRadioButton("O");
        redPlayerSRadioButton = new JRadioButton("S");

        bluePlayerButtonGroup = new ButtonGroup();
        bluePlayerORadioButton = new JRadioButton("O");
        bluePlayerSRadioButton = new JRadioButton("S");

        redPlayerComputerHumanButtonGroup = new ButtonGroup();
        redPlayerComputerButton = new JRadioButton("Computer");
        redPlayerHumanButton = new JRadioButton("Human");

        bluePlayerComputerHumanButtonGroup = new ButtonGroup();
        bluePlayerComputerButton = new JRadioButton("Computer");
        bluePlayerHumanButton = new JRadioButton("Human");

        recordGameCheckBox = new JCheckBox("Record Game");

        boardSizeTextField = new JTextField();

        newGameButton = new JButton("New Game");
        replayGameButton = new JButton("Replay Game");

        makeSOSTiles = e -> {
            computerPlayerThread = new Thread(this);

            SOSGameLogic.PlayerTypeMode playerTypeMode;

            if(redPlayerHumanButton.isSelected() && bluePlayerHumanButton.isSelected()) {
                playerTypeMode = SOSGameLogic.PlayerTypeMode.ALL_HUMAN;
            }
            else if(redPlayerComputerButton.isSelected() && bluePlayerHumanButton.isSelected()) {
                playerTypeMode = SOSGameLogic.PlayerTypeMode.RED_COMPUTER_BLUE_HUMAN;
            }
            else if(redPlayerHumanButton.isSelected() && bluePlayerComputerButton.isSelected()) {
                playerTypeMode = SOSGameLogic.PlayerTypeMode.RED_HUMAN_BLUE_COMPUTER;
            }
            else {
                playerTypeMode = SOSGameLogic.PlayerTypeMode.ALL_COMPUTER;
            }

            gameLogic = SOSGameLogic.newBuilder()
                    .setSize(Integer.parseInt(boardSizeTextField.getText()))
                    .setGameMode(gameTypeSimpleRadioButton.isSelected() ? GameMode.SIMPLE : GameMode.GENERAL)
                    .setPlayerTypeMode(playerTypeMode)
                    .setStartingPlayer(Player.PlayerColor.RED_PLAYER)
                    .build();

            gameLogic.getRedPlayer().setPlayerChoice(redPlayerSRadioButton.isSelected() ? "S" : "O");
            gameLogic.getBluePlayer().setPlayerChoice(bluePlayerSRadioButton.isSelected() ? "S" : "O");


            centerPanel.removeAll();
            centerPanel.setLayout(new GridLayout(gameLogic.getSize(), gameLogic.getSize()));

            for(int i = 0; i < Math.pow(gameLogic.getSize(), 2); i++) {
                int[] indexes = SOSGameUtils.convertOneDIndexToTwoD(i, gameLogic.getSize());
                centerPanel.add(new SOSGameTile(i, gameLogic.getGameBoard()[indexes[0]][indexes[1]]));
            }

            currentPlayerTurnLabel.setText("Current Player: " + gameLogic.getCurrentPlayer());

            isGameOver = false;

            redPlayerHumanButton.setEnabled(false);
            redPlayerComputerButton.setEnabled(false);

            bluePlayerHumanButton.setEnabled(false);
            bluePlayerComputerButton.setEnabled(false);

            gameTypeSimpleRadioButton.setEnabled(false);
            gameTypeGeneralRadioButton.setEnabled(false);

            boardSizeTextField.setEnabled(false);

            recordGameCheckBox.setEnabled(false);

            computerPlayerThread.start();
            if(recordGameCheckBox.isSelected()) {
                gameRecorder = new SOSGameRecorder(gameLogic);
            }
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
        northPanel.setPreferredSize(GraphicConstants.NORTH_PANEL_BOUNDS);

        buttonGroupLabel.setText("SOS: ");
        buttonGroupLabel.setPreferredSize(GraphicConstants.BUTTON_GROUP_LABEL_BOUNDS);
        boardSizeLabel.setPreferredSize(GraphicConstants.BOARD_SIZE_LABEL_BOUNDS);


        gameTypeSimpleRadioButton.setPreferredSize(GraphicConstants.RADIO_BUTTON_GAME_TYPE_BOUNDS);
        gameTypeGeneralRadioButton.setPreferredSize(GraphicConstants.RADIO_BUTTON_GAME_TYPE_BOUNDS);
        gameTypeSimpleRadioButton.setFocusPainted(false);
        gameTypeGeneralRadioButton.setFocusPainted(false);

        boardSizeTextField.setPreferredSize(GraphicConstants.TEXT_FIELD_BOUNDS);
        boardSizeTextField.setEditable(true);
        boardSizeTextField.addActionListener(makeSOSTiles);


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
        helpSetUpEastWestLabelAndPanel(eastPanel, redPlayerLabel, redScoreLabel);

        redPlayerORadioButton.addActionListener(e ->  {
            if(gameLogic != null) gameLogic.getRedPlayer().setPlayerChoice("O");
        });
        redPlayerSRadioButton.addActionListener(e -> {
            if(gameLogic != null) gameLogic.getRedPlayer().setPlayerChoice("S");
        });

        redPlayerComputerButton.addActionListener( e -> {
            redPlayerORadioButton.setEnabled(false);
            redPlayerSRadioButton.setEnabled(false);
        });

        redPlayerHumanButton.addActionListener(e -> {
            redPlayerORadioButton.setEnabled(true);
            redPlayerSRadioButton.setEnabled(true);
        });

        redPlayerButtonGroup.add(redPlayerORadioButton);
        redPlayerButtonGroup.add(redPlayerSRadioButton);
        redPlayerSRadioButton.setSelected(true);

        eastPanel.add(redPlayerSRadioButton);
        eastPanel.add(redPlayerORadioButton);

        redPlayerComputerHumanButtonGroup.add(redPlayerHumanButton);
        redPlayerComputerHumanButtonGroup.add(redPlayerComputerButton);
        redPlayerHumanButton.setSelected(true);

        eastPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        eastPanel.add(redPlayerHumanButton);
        eastPanel.add(redPlayerComputerButton);

        add(eastPanel, BorderLayout.EAST);
    }

    private void helpSetUpEastWestLabelAndPanel(JPanel panel, JLabel playerLabel, JLabel scoreLabel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(150, 200));

        playerLabel.setAlignmentX(CENTER_ALIGNMENT);
        playerLabel.setFont(playerLabel.getFont().deriveFont(20f));

        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        panel.add(playerLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(scoreLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 50)));

    }

    private void setUpWestPanelAndComponents() {
        helpSetUpEastWestLabelAndPanel(westPanel, bluePlayerLabel, blueScoreLabel);

        bluePlayerORadioButton.addActionListener(e -> {
            if(gameLogic != null) gameLogic.getBluePlayer().setPlayerChoice("O");
        });
        bluePlayerSRadioButton.addActionListener(e -> {
            if(gameLogic != null) gameLogic.getBluePlayer().setPlayerChoice("S");
        });

        bluePlayerComputerButton.addActionListener( e -> {
            bluePlayerORadioButton.setEnabled(false);
            bluePlayerSRadioButton.setEnabled(false);
        });

        bluePlayerHumanButton.addActionListener(e -> {
            bluePlayerORadioButton.setEnabled(true);
            bluePlayerSRadioButton.setEnabled(true);
        });

        bluePlayerButtonGroup.add(bluePlayerORadioButton);
        bluePlayerButtonGroup.add(bluePlayerSRadioButton);
        bluePlayerSRadioButton.setSelected(true);

        westPanel.add(bluePlayerSRadioButton);
        westPanel.add(bluePlayerORadioButton);

        bluePlayerComputerHumanButtonGroup.add(bluePlayerHumanButton);
        bluePlayerComputerHumanButtonGroup.add(bluePlayerComputerButton);
        bluePlayerHumanButton.setSelected(true);

        westPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        westPanel.add(bluePlayerHumanButton);
        westPanel.add(bluePlayerComputerButton);

        add(westPanel, BorderLayout.WEST);

    }
    private void setUpSouthPanelAndComponents() {
        newGameButton.setPreferredSize(GraphicConstants.NEW_GAME_BUTTON_BOUNDS);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(makeSOSTiles);

        southPanel.add(recordGameCheckBox);

        southPanel.add(currentPlayerTurnLabel);

        southPanel.add(Box.createRigidArea(new Dimension(100, 0)));

        southPanel.add(newGameButton);

        replayGameButton.addActionListener(e -> {
                    Thread thread = new Thread(() -> {
                        GameFrame gameFrame = new GameFrame();
                        gameFrame.setVisible(true);

                        gameFrame.getPanel().getBoardSizeTextField().setText(String.valueOf(gameLogic.getSize()));
                        if (gameTypeSimpleRadioButton.isSelected()) {
                            gameFrame.getPanel().getGameTypeSimpleRadioButton().setSelected(true);
                        } else {
                            gameFrame.getPanel().getGameTypeGeneralRadioButton().setSelected(true);
                        }

                        gameFrame.getPanel().getNewGameButton().doClick();

                        for(Move move : gameLogic.getMoveList()) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            gameFrame.getPanel().makeReplayPlay(move);
                        }
                    });

                    thread.start();
                });

        southPanel.add(replayGameButton);



        southPanel.setPreferredSize(GraphicConstants.SOUTH_PANEL_BOUNDS);
        add(southPanel, BorderLayout.SOUTH);

    }
    private void setUpCenterPanelAndComponents() {
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setPreferredSize(GraphicConstants.CENTER_PANEL_BOUNDS);
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));


        add(centerPanel, BorderLayout.CENTER);
    }

    public JRadioButton getGameTypeSimpleRadioButton() {
        return gameTypeSimpleRadioButton;
    }

    public JRadioButton getGameTypeGeneralRadioButton() {
        return gameTypeGeneralRadioButton;
    }

    public JTextField getBoardSizeTextField() {
        return boardSizeTextField;
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    private void disposeFrame() {
        GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        gameFrame.dispose();
    }

    private void repaintTiles() {
        for(Component c : centerPanel.getComponents()) {
            c.repaint();
        }
    }


    private void endGame() {
        System.out.println(gameLogic.getGameState());
        isGameOver = true;
        SwingUtilities.invokeLater(
                () -> new MenuFrame(gameLogic.getGameState()).setVisible(true)
        );
        disposeFrame();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void run() {
        boolean isRedComputer = gameLogic.getRedPlayer().getPlayerType() == PlayerType.COMPUTER_PLAYER;
        boolean isBlueComputer = gameLogic.getBluePlayer().getPlayerType() == PlayerType.COMPUTER_PLAYER;

        while(!isGameOver) {
            if (!isRedComputer && isBlueComputer) {
                if (gameLogic.getCurrentPlayer() == gameLogic.getBluePlayer()) {
                    makeComputerPlay();
                }
            }
            if (isRedComputer && !isBlueComputer) {
                if (gameLogic.getCurrentPlayer() == gameLogic.getRedPlayer()) {
                    makeComputerPlay();
                }
            }
            if (isRedComputer && isBlueComputer) {
                makeComputerPlay();
            }
        }
    }

    private void updateVisualGameStatistics() {
        currentPlayerTurnLabel.setText("Current Player: " + gameLogic.getCurrentPlayer());
        redScoreLabel.setText("Red Score: " + gameLogic.getRedPlayer().getTotalSOSCombinations());
        blueScoreLabel.setText("Blue Score: " + gameLogic.getBluePlayer().getTotalSOSCombinations());
    }

    private void makeComputerPlay() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameLogic.makeComputerMove();
        if(recordGameCheckBox.isSelected()) {
            gameRecorder.recordCurrentGameState();
        }
        updateVisualGameStatistics();
        repaintTiles();
        if(gameLogic.getGameState() != SOSGameLogic.GameState.GAME_NOT_OVER) {
            endGame();
            isGameOver = true;
        }
    }

    public void makeReplayPlay(Move move) {
        gameLogic.getCurrentPlayer().setPlayerChoice(move.choice());
        gameLogic.makeHumanMove(move.x(), move.y());
        updateVisualGameStatistics();
        repaintTiles();
    }

    private class SOSGameTile extends JPanel {

        private final Tile tile;

        public SOSGameTile(int index, Tile tile) {
            this.tile = tile;
            setBackground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    updateVisualGameStatistics();
                    int[] cords = SOSGameUtils.convertOneDIndexToTwoD(index, gameLogic.getSize());
                    gameLogic.makeHumanMove(cords[0], cords[1]);
                    if(recordGameCheckBox.isSelected()) {
                        gameRecorder.recordCurrentGameState();
                    }

                    if(gameLogic.getGameState() != SOSGameLogic.GameState.GAME_NOT_OVER) {
                        endGame();
                    }
                    updateVisualGameStatistics();
                    repaint();
                    getParent().repaint();
                }
            });
        }


        private Color convertPlayerToGraphicsColor(Player player) {
            if(player.getPlayerColor() == Player.PlayerColor.RED_PLAYER) {
                return Color.RED;
            }
            return Color.BLUE;
        }

        private void drawLineThroughSOS(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(new BasicStroke(2));

            for(SOSCombination combination : tile.getCombinations()) {
                graphics2D.setColor(convertPlayerToGraphicsColor(combination.playerOfCombination()));

                if (combination.combinationDirection() == Direction.VERTICAL) {
                    graphics2D.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
                }
                if (combination.combinationDirection() == Direction.HORIZONTAL) {
                    graphics2D.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
                }
                if (combination.combinationDirection() == Direction.POSITIVE_DIAGONAL) {
                    graphics2D.drawLine(0, getHeight(), getWidth(), 0);
                }
                if (combination.combinationDirection() == Direction.NEGATIVE_DIAGONAL) {
                    graphics2D.drawLine(0, 0, getWidth(), getHeight());
                }
            }
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
            if(tile.getSelection().equals(" ")) {
                return;
            }
            if(tile.getSelection().equals("S")) {
                drawS(graphics2D);
            }
            else if(tile.getSelection().equals("O")) {
                drawO(graphics2D);
            }

            drawLineThroughSOS(g);
        }
    }

}
