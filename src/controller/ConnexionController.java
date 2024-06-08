package controller;

import view.ConnexionInterface;
import view.InscriptionInterface;
import view.MainMenu;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnexionController {
    private ConnexionInterface connexionInterface;
    private Properties users;
    private static final String FILE_PATH = "users.properties";

    public ConnexionController(ConnexionInterface connexionInterface) {
        this.connexionInterface = connexionInterface;
        this.users = new Properties();
        loadUsers();
        initController();
    }

    private void initController() {
        connexionInterface.getLoginButton().addActionListener(e -> login());
        connexionInterface.getRegisterButton().addActionListener(e -> openRegisterInterface());
    }

    private void loadUsers() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            users.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsers() {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            users.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        String username = connexionInterface.getUsernameField().getText();
        String password = new String(connexionInterface.getPasswordField().getPassword());
        if (users.containsKey(username) && users.getProperty(username).equals(password)) {
            SwingUtilities.invokeLater(() -> {
                MainMenu mainMenu = new MainMenu();
                new MainMenuController(mainMenu);
            });
            connexionInterface.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegisterInterface() {
        SwingUtilities.invokeLater(() -> {
            InscriptionInterface inscriptionInterface = new InscriptionInterface();
            new InscriptionController(inscriptionInterface, users);
        });
    }

    private class InscriptionController {
        private InscriptionInterface inscriptionInterface;
        private Properties users;

        public InscriptionController(InscriptionInterface inscriptionInterface, Properties users) {
            this.inscriptionInterface = inscriptionInterface;
            this.users = users;
            initController();
        }

        private void initController() {
            inscriptionInterface.getRegisterButton().addActionListener(e -> register());
        }

        private void register() {
            String username = inscriptionInterface.getUsernameField().getText();
            String password = new String(inscriptionInterface.getPasswordField().getPassword());
            if (!users.containsKey(username)) {
                users.setProperty(username, password);
                saveUsers();
                JOptionPane.showMessageDialog(null, "Inscription réussie!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                inscriptionInterface.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Nom d'utilisateur déjà pris.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
