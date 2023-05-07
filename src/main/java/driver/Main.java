package driver;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;
import gamelogic.GeneralSOSGameLogic;
import gamelogic.SOSGameLogic;
import gamelogic.SOSGameRecorder;
import gamelogic.SimpleSOSGameLogic;
import graphics.GameFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) throws IOException {
        if(System.getProperty("os.name").equals("Mac OS X")) {
            macOSSetUp();
        }

        setUpOSAgnosticDecorations();

        SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true));
    }

    private static void macOSSetUp() {
        System.setProperty("apple.awt.application.appearance", "system");
        System.setProperty("apple.awt.application.name", "SOS Game");
    }
    private static void setUpOSAgnosticDecorations() {
        FlatMaterialDarkerContrastIJTheme.setup();
        UIManager.put("Button.arc", 0);
    }

}
