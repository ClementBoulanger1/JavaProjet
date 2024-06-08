package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StockInterface extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> typeComboBox;
    private JButton addButton;
    private JButton removeButton;

    public StockInterface() {
        setTitle("Gestion de Stock");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Type", "Nom", "Note", "Plateforme/Jeu de Référence", "Année de sortie", "Prix", "Nombre"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        typeComboBox = new JComboBox<>(new String[]{"Tous", "JeuxVideo", "Goodies"});
        addButton = new JButton("Ajouter");
        removeButton = new JButton("Retirer");
        controlPanel.add(typeComboBox);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        panel.add(controlPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }
}
