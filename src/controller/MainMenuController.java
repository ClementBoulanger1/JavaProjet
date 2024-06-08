package controller;

import view.MainMenu;
import view.StockInterface;

import javax.swing.*;

public class MainMenuController {
    private MainMenu mainMenu;

    public MainMenuController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        initController();
    }

    private void initController() {
        mainMenu.getStockButton().addActionListener(e -> openStockInterface());
        mainMenu.getTransactionButton().addActionListener(e -> openTransactionInterface());
        mainMenu.getHistoryButton().addActionListener(e -> openHistoryInterface());
        mainMenu.getChangePhotoButton().addActionListener(e -> changePhoto());
    }

    private void openStockInterface() {
        SwingUtilities.invokeLater(() -> {
            StockInterface stockInterface = new StockInterface();
            new StockController(stockInterface);
        });
    }

    private void openTransactionInterface() {
        // Placeholder for opening transaction interface
    }

    private void openHistoryInterface() {
        // Placeholder for opening history interface
    }

    private void changePhoto() {
        // Placeholder for changing photo functionality
    }
}
