import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;

public class Main extends Application {

    private Broker broker;
    private Player player;
    private Label portfolioLabel;
    private TextArea transactionArea;
    private TextArea exchangeRateArea;
    private int currentBiweek = 0;
    private final String[] currency = {"INR", "USD", "EUR", "YEN"};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Forex Simulator");

        // Initialize UI Components
        Label nameLabel = new Label("Enter Player Name:");
        TextField nameField = new TextField();
        Button startButton = new Button("Start Simulation");

        // Layout for player name input
        VBox nameLayout = new VBox(10);
        nameLayout.setPadding(new Insets(10));
        nameLayout.getChildren().addAll(nameLabel, nameField, startButton);

        // Portfolio Display
        portfolioLabel = new Label("Your Portfolio will appear here.");
        Button refreshPortfolio = new Button("Refresh Portfolio");

        // Transaction History
        transactionArea = new TextArea();
        transactionArea.setEditable(false);
        transactionArea.setPrefHeight(150);
        Button refreshTransactions = new Button("Refresh Transactions");

        // Exchange Rates Display
        exchangeRateArea = new TextArea();
        exchangeRateArea.setEditable(false);
        exchangeRateArea.setPrefHeight(150);
        Button refreshExchangeRates = new Button("Refresh Exchange Rates");

        // Buy Currency Button
        Button buyCurrencyButton = new Button("Buy/Exchange Currency");

        // Next Biweek Button
        Button nextBiweekButton = new Button("Next Biweek");

        // Layout for simulation controls
        GridPane simulationLayout = new GridPane();
        simulationLayout.setPadding(new Insets(10));
        simulationLayout.setVgap(10);
        simulationLayout.setHgap(10);
        simulationLayout.add(portfolioLabel, 0, 0);
        simulationLayout.add(refreshPortfolio, 1, 0);
        simulationLayout.add(new Label("Transaction History:"), 0, 1);
        simulationLayout.add(transactionArea, 0, 2, 2, 1);
        simulationLayout.add(refreshTransactions, 2, 2);
        simulationLayout.add(new Label("Exchange Rates:"), 0, 3);
        simulationLayout.add(exchangeRateArea, 0, 4, 2, 1);
        simulationLayout.add(refreshExchangeRates, 2, 4);
        simulationLayout.add(buyCurrencyButton, 0, 5);
        simulationLayout.add(nextBiweekButton, 1, 5);

        // Initially hide simulation layout
        simulationLayout.setVisible(false);

        // Main Layout
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(nameLayout, simulationLayout);

        // Start Button Action
        startButton.setOnAction(e -> {
            String playerName = nameField.getText().trim();
            if (playerName.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Player name cannot be empty.");
                return;
            }
            player = new Player(playerName);
            broker = new Broker(player);
            simulationLayout.setVisible(true);
            nameLayout.setVisible(false);
            portfolioLabel.setText("Hello " + playerName + ". Be ready to be rich by trading.\nYour current Demo Portfolio is:");
            player.displayPortfolio();
            updatePortfolioDisplay();
            loadExchangeRates(); // Load exchange rates in a separate thread
        });

        // Refresh Portfolio Button Action
        refreshPortfolio.setOnAction(e -> updatePortfolioDisplay());

        // Refresh Transactions Button Action
        refreshTransactions.setOnAction(e -> updateTransactionDisplay());

        // Refresh Exchange Rates Button Action
        refreshExchangeRates.setOnAction(e -> updateExchangeRateDisplay());

        // Buy Currency Button Action
        buyCurrencyButton.setOnAction(e -> showBuyCurrencyDialog());

        // Next Biweek Button Action
        nextBiweekButton.setOnAction(e -> {
            if (currentBiweek < 23) {
                currentBiweek++;
                portfolioLabel.setText("Biweek " + (currentBiweek + 1) + " - Your Portfolio:");
                updatePortfolioDisplay();
                showAlert(Alert.AlertType.INFORMATION, "Biweek Progressed", "Moved to Biweek " + (currentBiweek + 1));
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Simulation Ended", "You have completed all biweeks.");
            }
        });

        // Set Scene and Show
        Scene scene = new Scene(mainLayout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /// PLACE WHERE FILEPATH
    // Method to load exchange rates using multithreading
    private void loadExchangeRates() {
        Task<Void> loadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                broker.populateExchangeRatesFromCSV("Z:\\rates.csv"); // Replace with your actual file path
                return null;
            }
        };

        loadTask.setOnSucceeded(e -> showAlert(Alert.AlertType.INFORMATION, "Success", "Exchange rates loaded successfully."));
        loadTask.setOnFailed(e -> showAlert(Alert.AlertType.ERROR, "Error", "Failed to load exchange rates."));

        Thread loadThread = new Thread(loadTask);
        loadThread.setDaemon(true);
        loadThread.start();
    }

    // Method to update portfolio display
    private void updatePortfolioDisplay() {
        Platform.runLater(() -> {
            StringBuilder portfolioText = new StringBuilder();
            double[] portfolio = player.getPortfolio();
            portfolioText.append("INR = ").append(portfolio[0]).append("\n");
            portfolioText.append("USD = ").append(portfolio[1]).append("\n");
            portfolioText.append("EUR = ").append(portfolio[2]).append("\n");
            portfolioText.append("YEN = ").append(portfolio[3]).append("\n");
            portfolioLabel.setText("Your Portfolio:\n" + portfolioText.toString());
        });
    }

    // Method to update transaction history display
    private void updateTransactionDisplay() {
        Platform.runLater(() -> {
            transactionArea.clear();
            for (Transaction transaction : player.transactionHistory) {
                transactionArea.appendText(transaction.toString() + "\n");
            }
        });
    }

    // Method to update exchange rates display
    private void updateExchangeRateDisplay() {
        Platform.runLater(() -> {
            exchangeRateArea.clear();
            double[][][] rates = broker.exchangeRates[currentBiweek];
            for (int from = 0; from < rates.length; from++) {
                for (int to = 0; to < rates[from].length; to++) {
                    if (from != to) { // Skip same currency exchange
                        exchangeRateArea.appendText(currency[from] + " to " + currency[to] + " = " + rates[from][to][0] + "\n");
                    }
                }
            }
        });
    }

    // Method to show Buy Currency Dialog
    private void showBuyCurrencyDialog() {
        // Main class
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Buy/Exchange Currency");

    ButtonType buyButtonType = new ButtonType("Buy", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(buyButtonType, ButtonType.CANCEL);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    ComboBox<String> sellCurrencyBox = new ComboBox<>();
    sellCurrencyBox.getItems().addAll(currency);
    sellCurrencyBox.setValue(currency[0]);

    ComboBox<String> buyCurrencyBox = new ComboBox<>();
    buyCurrencyBox.getItems().addAll(currency);
    buyCurrencyBox.setValue(currency[1]);

    TextField amountField = new TextField();
    amountField.setPromptText("Amount");

    grid.add(new Label("Sell Currency:"), 0, 0);
    grid.add(sellCurrencyBox, 1, 0);
    grid.add(new Label("Buy Currency:"), 0, 1);
    grid.add(buyCurrencyBox, 1, 1);
    grid.add(new Label("Amount:"), 0, 2);
    grid.add(amountField, 1, 2);

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == buyButtonType) {
            String sellCurrency = sellCurrencyBox.getValue();
            String buyCurrency = buyCurrencyBox.getValue();
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid number for amount.");
                return null;
            }

            int sellIndex = getCurrencyIndex(sellCurrency);
            int buyIndex = getCurrencyIndex(buyCurrency);

            Task<Void> buyTask = new Task<Void>() {
                @Override
                protected Void call() {
                    broker.buyCurrency(currentBiweek, sellIndex, amount, buyIndex);
                    return null;
                }
            };

            buyTask.setOnSucceeded(event -> {
                updatePortfolioDisplay();
                updateTransactionDisplay();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Currency exchanged successfully.");
            });

            buyTask.setOnFailed(event -> showAlert(Alert.AlertType.ERROR, "Error", "Failed to exchange currency."));

            Thread buyThread = new Thread(buyTask);
            buyThread.setDaemon(true);
            buyThread.start();
        }
        return null;
    });

    dialog.showAndWait();
    }

    // Helper method to get currency index
    private int getCurrencyIndex(String currencyName) {
        for (int i = 0; i < currency.length; i++) {
            if (currency[i].equalsIgnoreCase(currencyName)) {
                return i;
            }
        }
        return -1; // Not found
    }

    // Helper method to show alerts
    private void showAlert(Alert.AlertType type, String title, String message) {
        Platform.runLater(() -> { // Ensure alerts are run on the JavaFX Application Thread
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}