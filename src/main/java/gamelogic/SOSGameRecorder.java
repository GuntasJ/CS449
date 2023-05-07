package gamelogic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SOSGameRecorder {

    private final SOSGameLogic gameLogic;
    private final Path gameDataFilePath = Path.of("src","main","resources","game_data_file.txt");

    private final String fileFormatRules =
            """
            Rules:\s
            Each number represents the turn move and the letter represents whose turn it is.
            The letter and number after those will be the move the player makes.\s
            
            For example:\s
                M1,TB---S=>(3,3)
            This means it is move 1 and blue's turn, and they played S at location 3,3 on the board.
            ////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////
            
            
            """;
    public SOSGameRecorder(SOSGameLogic gameLogic) {
        this.gameLogic = gameLogic;
        try {
            Files.deleteIfExists(gameDataFilePath);
            Files.createFile(gameDataFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printToFileFormatRules();
    }

    private void printToFileFormatRules() {
        try {
            Files.write(gameDataFilePath,
                    fileFormatRules.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.WRITE);
            Files.write(gameDataFilePath,
                    (gameLogic.getGameMode().toString() + "\n").getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void recordCurrentGameState() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append('M')
                .append(gameLogic.moveList.size())
                .append(',')
                .append('T')
                .append(gameLogic.moveList.get(gameLogic.moveList.size() - 1).playerColor()
                        == Player.PlayerColor.RED_PLAYER ? 'R' : 'B')
                .append("---")
                .append(gameLogic.moveList.get(gameLogic.moveList.size() - 1).choice())
                .append("=>")
                .append('(')
                .append(gameLogic.moveList.get(gameLogic.moveList.size() - 1).x())
                .append(',')
                .append(gameLogic.moveList.get(gameLogic.moveList.size() - 1).y())
                .append(')')
                .append('\n');

        try {
            Files.write(gameDataFilePath,
                    stringBuilder.toString().getBytes(), StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
