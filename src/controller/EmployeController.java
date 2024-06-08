package controller;

import model.Employe;
import view.EmployeInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

public class EmployeController {
    private EmployeInterface employeInterface;
    private static final String FILE_PATH = "employe.properties";

    public EmployeController(EmployeInterface employeInterface) {
        this.employeInterface = employeInterface;
        initController();
    }

    private void initController() {
        employeInterface.getSaveButton().addActionListener(e -> saveEmploye());
        employeInterface.getLoadButton().addActionListener(e -> loadEmploye());
        employeInterface.getChangePhotoButton().addActionListener(e -> changePhoto());
    }

    private void saveEmploye() {
        String nom = employeInterface.getNomField().getText();
        String codeAdresse = employeInterface.getCodeAdresseField().getText();
        String matricule = employeInterface.getMatriculeField().getText();
        String photoPath = employeInterface.getPhotoLabel().getIcon() != null ? ((ImageIcon) employeInterface.getPhotoLabel().getIcon()).getDescription() : "";

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            Properties properties = new Properties();
            properties.setProperty("nom", nom);
            properties.setProperty("codeAdresse", codeAdresse);
            properties.setProperty("matricule", matricule);
            properties.setProperty("photoPath", photoPath);
            properties.store(fos, null);
            JOptionPane.showMessageDialog(null, "Employé sauvegardé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde de l'employé.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadEmploye() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(fis);
            employeInterface.getNomField().setText(properties.getProperty("nom"));
            employeInterface.getCodeAdresseField().setText(properties.getProperty("codeAdresse"));
            employeInterface.getMatriculeField().setText(properties.getProperty("matricule"));
            String photoPath = properties.getProperty("photoPath", "");
            if (!photoPath.isEmpty()) {
                ImageIcon photoIcon = new ImageIcon(photoPath);
                photoIcon.setDescription(photoPath);
                employeInterface.getPhotoLabel().setIcon(new ImageIcon(photoIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
            } else {
                employeInterface.getPhotoLabel().setIcon(null);
            }
            JOptionPane.showMessageDialog(null, "Employé chargé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement de l'employé.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changePhoto() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(employeInterface);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon photoIcon = new ImageIcon(selectedFile.getAbsolutePath());
            photoIcon.setDescription(selectedFile.getAbsolutePath());
            employeInterface.getPhotoLabel().setIcon(new ImageIcon(photoIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        }
    }
}
