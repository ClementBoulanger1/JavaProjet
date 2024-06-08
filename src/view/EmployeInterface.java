package view;

import javax.swing.*;
import java.awt.*;

public class EmployeInterface extends JFrame {
    private JTextField nomField;
    private JTextField codeAdresseField;
    private JTextField matriculeField;
    private JLabel photoLabel; // Ajout du label pour la photo de profil
    private JButton saveButton;
    private JButton loadButton;
    private JButton changePhotoButton; // Ajout du bouton pour changer la photo de profil

    public EmployeInterface() {
        setTitle("Gestion des Employés");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        nomField = new JTextField();
        codeAdresseField = new JTextField();
        matriculeField = new JTextField();
        photoLabel = new JLabel(); // Initialisation du label pour la photo de profil
        photoLabel.setHorizontalAlignment(JLabel.CENTER);
        photoLabel.setPreferredSize(new Dimension(100, 100));
        saveButton = new JButton("Sauvegarder");
        loadButton = new JButton("Charger");
        changePhotoButton = new JButton("Changer Photo"); // Initialisation du bouton pour changer la photo de profil

        add(new JLabel("Nom:"));
        add(nomField);
        add(new JLabel("Code Adresse:"));
        add(codeAdresseField);
        add(new JLabel("Matricule:"));
        add(matriculeField);
        add(photoLabel); // Ajout du label pour la photo de profil à l'interface
        add(changePhotoButton); // Ajout du bouton pour changer la photo de profil
        add(saveButton);
        add(loadButton);

        setVisible(true);
    }

    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getCodeAdresseField() {
        return codeAdresseField;
    }

    public JTextField getMatriculeField() {
        return matriculeField;
    }

    public JLabel getPhotoLabel() {
        return photoLabel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getChangePhotoButton() {
        return changePhotoButton;
    }
}
