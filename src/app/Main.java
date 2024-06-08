package app;

import view.ConnexionInterface;
import controller.ConnexionController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConnexionInterface connexionInterface = new ConnexionInterface();
            new ConnexionController(connexionInterface);
        });
    }
}
