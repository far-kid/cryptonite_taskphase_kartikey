import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

// Broker now extends an abstract class (if needed in future for different broker types)
public class Broker {
    private Player player;
    private String currency[] = {"INR", "USD", "EUR" ,"JPY"};
    // INR is 0, USD is 1, EUR is 2, YEN is 3
    public double[][][][] exchangeRates = new double[24][4][4][1]; // [biweek][currencyFrom][currencyTo][exchangeRate]

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
            try {
                double rate = exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][0];
                if (rate == 0) {
                    throw new Exception("Exchange rate not available.");
                }
                double desiredBoughtAmount = soldCurrencyAmount * rate;
    
                if (player.getCurrValue(soldCurrencyIndex) < soldCurrencyAmount) {
                    throw new Exception("Insufficient funds to complete the transaction.");
                }
    
                // Update portfolio
                player.setPortfolio(soldCurrencyIndex, player.getCurrValue(soldCurrencyIndex) - soldCurrencyAmount);
                player.setPortfolio(boughtCurrencyIndex, player.getCurrValue(boughtCurrencyIndex) + desiredBoughtAmount);
    
                // Add transaction
                player.addTransaction(currency[soldCurrencyIndex], currency[boughtCurrencyIndex], soldCurrencyAmount, desiredBoughtAmount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
    }}
    
    }

