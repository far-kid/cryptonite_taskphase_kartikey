import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

// Broker now extends an abstract class (if needed in future for different broker types)
public class Broker {
    private Player player;
    private String currency[] = {"INR", "USD", "EUR"};
    // INR is 0, USD is 1, EUR is 2, YEN is 3
    private double[][][][] exchangeRates = new double[24][3][3][1]; // [biweek][currencyFrom][currencyTo][exchangeRate]

    public Broker(Player player) {
        this.player = player;
    }

    // Method to populate the array using multithreading
    public void populateExchangeRatesFromCSV(String filePath) {
        Thread loadThread = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Skip header
                    if (line.startsWith("biweek")) {
                        continue;
                    }
                    String[] values = line.split(",");

                    try {
                        int biweekIndex = Integer.parseInt(values[0]);
                        int soldCurrencyIndex = Integer.parseInt(values[1]);
                        int boughtCurrencyIndex = Integer.parseInt(values[2]);
                        double rate = Double.parseDouble(values[3]);

                        // Populate the exchange rates array (Fixed index from [1] to [0])
                        exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][0] = rate;
                    } catch (NumberFormatException e) { // Handles parsing issues
                        System.out.println("Error parsing numbers in CSV file line: " + line);
                        // e.printStackTrace(); // Uncomment for detailed stack trace
                    } catch (ArrayIndexOutOfBoundsException e) { // Handles invalid indices
                        System.out.println("Array index error while processing exchange rate data from CSV.");
                        // e.printStackTrace(); // Uncomment for detailed stack trace
                    }
                }
                System.out.println("Exchange rates loaded successfully.");
            } catch (IOException e) {
                System.out.println("Failed to read file at path: " + filePath);
                // e.printStackTrace(); // Uncomment for detailed stack trace
            }
        });
        loadThread.start(); // Start the thread
    }

    // Buy currency with method overloading for different parameters
    public void buyCurrency(int biweekIndex, int soldCurrencyIndex, double soldCurrencyAmount, int boughtCurrencyIndex) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        try {
            System.out.println("Press 1 to buy with known bought currency amount");
            System.out.println("Press 2 to buy with known sold currency amount");
            int choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
                return;
            }

            if (choice == 1) {
                // Buy with known bought currency amount
                System.out.println("Enter the amount of currency you want to buy:");
                double desiredBoughtAmount = scanner.nextDouble();
                double rate = exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][0];
                if (rate == 0) {
                    System.out.println("Exchange rate not available.");
                    return;
                }
                double requiredSoldAmount = desiredBoughtAmount / rate;
                if (player.getCurrValue(soldCurrencyIndex) < requiredSoldAmount) {
                    System.out.println("Insufficient funds to complete the transaction.");
                    return;
                }
                // Update portfolio
                player.setPortfolio(soldCurrencyIndex, player.getCurrValue(soldCurrencyIndex) - requiredSoldAmount);
                player.setPortfolio(boughtCurrencyIndex, player.getCurrValue(boughtCurrencyIndex) + desiredBoughtAmount);
                System.out.println("You bought: " + desiredBoughtAmount + " " + currency[boughtCurrencyIndex]);
                // Add transaction
                player.addTransaction(currency[soldCurrencyIndex], currency[boughtCurrencyIndex], requiredSoldAmount, desiredBoughtAmount);
            } else if (choice == 2) {
                // Buy with known sold currency amount
                System.out.println("Enter the amount of currency you want to sell:");
                double soldAmount = scanner.nextDouble();
                double rate = exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][0];
                if (rate == 0) {
                    System.out.println("Exchange rate not available.");
                    return;
                }
                double boughtAmount = soldAmount * rate;
                if (player.getCurrValue(soldCurrencyIndex) < soldAmount) {
                    System.out.println("Insufficient funds to complete the transaction.");
                    return;
                }
                // Update portfolio
                player.setPortfolio(soldCurrencyIndex, player.getCurrValue(soldCurrencyIndex) - soldAmount);
                player.setPortfolio(boughtCurrencyIndex, player.getCurrValue(boughtCurrencyIndex) + boughtAmount);
                System.out.println("You bought: " + boughtAmount + " " + currency[boughtCurrencyIndex]);
                // Add transaction
                player.addTransaction(currency[soldCurrencyIndex], currency[boughtCurrencyIndex], soldAmount, boughtAmount);
            }

            // Display updated portfolio
            player.displayPortfolio();

            // Optionally, add broker transaction cost here
            // Example: Deduct a fixed transaction fee
            double transactionFee = 10; // Fixed fee
            player.setPortfolio(soldCurrencyIndex, player.getCurrValue(soldCurrencyIndex) - transactionFee);
            System.out.println("A transaction fee of " + transactionFee + " " + currency[soldCurrencyIndex] + " has been deducted.");

        } catch (ArrayIndexOutOfBoundsException e) { // Handles invalid indices
            System.out.println("Invalid index for currencies or biweeks.");
            // e.printStackTrace(); // Uncomment for detailed stack trace
        } catch (InputMismatchException e) { // Handles invalid input types
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input from scanner buffer
            // e.printStackTrace(); // Uncomment for detailed stack trace
        }
    }

    // Overloaded buyCurrency method with default parameters (for method overloading demonstration)
    public void buyCurrency() {
        buyCurrency(0, 0, 0, 0);
    }
}