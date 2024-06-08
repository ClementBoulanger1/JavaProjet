package view;

import javax.swing.*;
import java.awt.*;

public class ConnexionInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public ConnexionInterface() {
        setTitle("Connexion");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Se Connecter");
        registerButton = new JButton("S'inscrire");

        add(new JLabel("Nom d'utilisateur:"));
        add(usernameField);
        add(new JLabel("Mot de passe:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
