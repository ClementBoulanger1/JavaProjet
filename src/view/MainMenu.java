package view;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private JButton stockButton;
    private JButton transactionButton;
    private JButton historyButton;
    private JButton changePhotoButton;

    public MainMenu() {
        setTitle("Gestion des Actions");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        stockButton = new JButton("Gestion du Stock");
        transactionButton = new JButton("Gestion des Transactions");
        historyButton = new JButton("Historique");
        changePhotoButton = new JButton("Changer Photo de Profil");

        add(stockButton);
        add(transactionButton);
        add(historyButton);
        add(changePhotoButton);

        setVisible(true);
    }

    public JButton getStockButton() {
        return stockButton;
    }

    public JButton getTransactionButton() {
        return transactionButton;
    }

    public JButton getHistoryButton() {
        return historyButton;
    }

    public JButton getChangePhotoButton() {
        return changePhotoButton;
    }
}
