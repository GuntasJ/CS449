package driver;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;
import graphics.GameFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Main {

    public static void main(String[] args) {
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
