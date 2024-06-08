package controller;

import model.JeuxVideo;
import model.Goodies;
import model.Produit;
import view.StockInterface;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StockController {
    private StockInterface stockInterface;
    private List<Produit> produits;
    private static final String FILE_PATH = "stock.properties";

    public StockController(StockInterface stockInterface) {
        this.stockInterface = stockInterface;
        this.produits = new ArrayList<>();
        initController();
        loadItemsFromFile();
        filterItems();
    }

    private void initController() {
        stockInterface.getAddButton().addActionListener(e -> addItem());
        stockInterface.getRemoveButton().addActionListener(e -> removeItem());
        stockInterface.getTypeComboBox().addActionListener(e -> filterItems());
    }

    private void addItem() {
        String[] options = {"JeuxVideo", "Goodies"};
        String type = (String) JOptionPane.showInputDialog(null, "Sélectionnez le type de produit",
                "Ajouter un produit", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (type != null) {
            JTextField nameField = new JTextField();
            JComboBox<Integer> noteField = new JComboBox<>(createRangeArray(1, 10));
            JComboBox<String> platformField = new JComboBox<>(new String[]{"PC", "PS3", "Xbox One", "Xbox Series X", "PS5", "PS4", "Xbox 360", "Nintendo Switch"});
            JTextField refJeuField = new JTextField();
            JComboBox<Integer> yearField = new JComboBox<>(createRangeArray(1950, 2024));
            JTextField priceField = new JTextField();
            JTextField countField = new JTextField();

            Object[] message;
            if (type.equals("JeuxVideo")) {
                message = new Object[]{
                        "Nom:", nameField,
                        "Note:", noteField,
                        "Plateforme:", platformField,
                        "Année de sortie:", yearField,
                        "Prix:", priceField,
                        "Nombre:", countField
                };
            } else {
                message = new Object[]{
                        "Nom:", nameField,
                        "Note:", noteField,
                        "Jeu de référence:", refJeuField,
                        "Année de sortie:", yearField,
                        "Prix:", priceField,
                        "Nombre:", countField
                };
            }

            int option = JOptionPane.showConfirmDialog(null, message, "Ajouter un produit", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String name = nameField.getText();
                    int note = (int) noteField.getSelectedItem();
                    String refOrPlat = type.equals("JeuxVideo") ? (String) platformField.getSelectedItem() : refJeuField.getText();
                    int year = (int) yearField.getSelectedItem();
                    double price = Double.parseDouble(priceField.getText());
                    int count = Integer.parseInt(countField.getText());

                    Produit produit;
                    if (type.equals("JeuxVideo")) {
                        produit = new JeuxVideo("", UUID.randomUUID().toString(), name, "", refOrPlat, note, Integer.toString(year), price);
                    } else {
                        produit = new Goodies("", UUID.randomUUID().toString(), name, refOrPlat, Integer.toString(year), price);
                    }

                    produits.add(produit);
                    stockInterface.getTableModel().addRow(new Object[]{type, name, note, refOrPlat, year, price, count});
                    saveItemsToFile();
                    filterItems();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void removeItem() {
        int selectedRow = stockInterface.getTable().getSelectedRow();
        if (selectedRow != -1) {
            String idProduit = (String) stockInterface.getTableModel().getValueAt(selectedRow, 1);
            produits.removeIf(produit -> produit.getIdProduit().equals(idProduit));
            stockInterface.getTableModel().removeRow(selectedRow);
            saveItemsToFile();
            filterItems();
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un élément à retirer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadItemsFromFile() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {
                Files.createFile(Paths.get(FILE_PATH));
            }
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
                properties.load(fis);
            }
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                String[] parts = value.split(",");
                String type = parts[0].equals("0") ? "JeuxVideo" : "Goodies";
                Produit produit;
                if (type.equals("JeuxVideo")) {
                    produit = new JeuxVideo(parts[1], parts[2], parts[3], "", parts[4], Integer.parseInt(parts[5]), parts[6], Double.parseDouble(parts[7]));
                } else {
                    produit = new Goodies(parts[1], parts[2], parts[3], parts[4], parts[5], Double.parseDouble(parts[6]));
                }
                produits.add(produit);
                stockInterface.getTableModel().addRow(new Object[]{type, parts[3], Integer.parseInt(parts[5]), parts[4], Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Integer.parseInt(parts[8])});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveItemsToFile() {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            Properties properties = new Properties();
            int i = 0;
            for (Produit produit : produits) {
                if (produit instanceof JeuxVideo) {
                    JeuxVideo jv = (JeuxVideo) produit;
                    properties.setProperty("item" + i, "0," + jv.getCodeBarre() + "," + jv.getIdProduit() + "," + jv.getNom() + "," + jv.getPlateforme() + "," + jv.getNote() + "," + jv.getAnneeDistribution() + "," + jv.getPrix());
                } else if (produit instanceof Goodies) {
                    Goodies gd = (Goodies) produit;
                    properties.setProperty("item" + i, "1," + gd.getCodeBarre() + "," + gd.getIdProduit() + "," + gd.getNom() + "," + gd.getRefJeux() + "," + gd.getAnnee() + "," + gd.getPrix());
                }
                i++;
            }
            properties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void filterItems() {
        String selectedType = (String) stockInterface.getTypeComboBox().getSelectedItem();
        List<Object[]> rows = new ArrayList<>();
        for (Produit produit : produits) {
            if (selectedType.equals("Tous") || (produit instanceof JeuxVideo && selectedType.equals("JeuxVideo")) || (produit instanceof Goodies && selectedType.equals("Goodies"))) {
                Object[] row = produit instanceof JeuxVideo
                        ? new Object[]{"JeuxVideo", produit.getNom(), ((JeuxVideo) produit).getNote(), ((JeuxVideo) produit).getPlateforme(), Integer.parseInt(((JeuxVideo) produit).getAnneeDistribution()), ((JeuxVideo) produit).getPrix(), 1}
                        : new Object[]{"Goodies", produit.getNom(), 0, ((Goodies) produit).getRefJeux(), Integer.parseInt(((Goodies) produit).getAnnee()), ((Goodies) produit).getPrix(), 1};
                rows.add(row);
            }
        }
        rows.sort(Comparator.comparing(o -> ((String) o[1]).toLowerCase()));
        stockInterface.getTableModel().setRowCount(0);  // Clear the table
        for (Object[] row : rows) {
            stockInterface.getTableModel().addRow(row);
        }
    }

    private Integer[] createRangeArray(int start, int end) {
        Integer[] array = new Integer[end - start + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = start + i;
        }
        return array;
    }
}
