package driver;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import graphics.GameFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class Main {

    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Mac OS X")) {
            System.setProperty("apple.awt.application.appearance", "system");
            System.setProperty("apple.awt.application.name", "SOS Game");
        }
        FlatMaterialDarkerContrastIJTheme.setup();

        UIManager.put("Button.arc", 0);

        SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true));
    }

}
