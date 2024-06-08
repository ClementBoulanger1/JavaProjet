package view;

import javax.swing.*;
import java.awt.*;

public class InscriptionInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public InscriptionInterface() {
        setTitle("Inscription");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        registerButton = new JButton("S'inscrire");

        add(new JLabel("Nom d'utilisateur:"));
        add(usernameField);
        add(new JLabel("Mot de passe:"));
        add(passwordField);
        add(new JLabel());
        add(registerButton);

        setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
