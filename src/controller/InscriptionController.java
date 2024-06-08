package controller;

import view.InscriptionInterface;
import javax.swing.*;
import java.util.Map;

public class InscriptionController {
    private InscriptionInterface inscriptionInterface;
    private Map<String, String> users;

    public InscriptionController(InscriptionInterface inscriptionInterface, Map<String, String> users) {
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
            users.put(username, password);
            JOptionPane.showMessageDialog(null, "Inscription réussie!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            inscriptionInterface.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Nom d'utilisateur déjà pris.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
