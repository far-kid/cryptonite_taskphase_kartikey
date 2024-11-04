import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player extends User {
    public List<Transaction> transactionHistory; // List to store transactions

    public Player(String name) {
        super(name);
        this.transactionHistory = new ArrayList<>(); // Empty transaction history
    }

    // Get the value of individual currency holding
    public double getCurrValue(int index){
        try { // Added ArrayIndexOutOfBoundsException handling
            return portfolio[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Currency index out of bounds. Returning 0 as fallback.");
            return 0;
        }
    }

    // Set value of individual currency holding
    // CAUTION: It directly sets the value, does not add/subtract
    public void setPortfolio(int index, double value) {
        try { // Added ArrayIndexOutOfBoundsException handling
            portfolio[index] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Currency index out of bounds. Cannot set portfolio value.");
        }
    }

    // Overloaded method to display portfolio with date
    @Override
    public void displayPortfolio() {
        System.out.println("YOUR CURRENT PORTFOLIO:");
        System.out.println("INR = " + portfolio[0]);
        System.out.println("USD = " + portfolio[1]);
        System.out.println("EUR = " + portfolio[2]);
        System.out.println("JPY = " + portfolio[3]);
    }

    // Overloaded method to display portfolio with a specific date
    public void displayPortfolio(Date date){
        System.out.println("YOUR CURRENT PORTFOLIO AS OF " + date + ":");
        System.out.println("INR = " + portfolio[0]);
        System.out.println("USD = " + portfolio[1]);
        System.out.println("EUR = " + portfolio[2]);
        System.out.println("JPY = " + portfolio[3]);
    }

    // Add transactions
    public void addTransaction(String soldCurrency, String bCurrency, double soldAmount, double bAmount) {
        Transaction transaction = new Transaction(soldCurrency, bCurrency, soldAmount, bAmount, new Date());
        transactionHistory.add(transaction);
    }

    // Display the transaction history
    public void displayTransactionHistory() {
        System.out.println("TRANSACTION HISTORY:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction); // Customize Transaction's toString method for display
        }
    }
}