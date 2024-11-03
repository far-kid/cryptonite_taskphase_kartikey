import java.util.Date;

public class Transaction {

    private String soldCurrency; // Currency sold
    private String bCurrency; // Currency bought
    private double soldAmount; // Quantity sold
    private double bAmount; // Quantity bought
    private Date timestamp; // Timestamp of the transaction

    // Constructor with correct timestamp assignment
    public Transaction(String soldCurrency, String bCurrency, double soldAmount, double bAmount, Date timestamp) {
        this.soldCurrency = soldCurrency;
        this.bCurrency = bCurrency;
        this.soldAmount = soldAmount;
        this.bAmount = bAmount;
        this.timestamp = timestamp; // Assign the passed timestamp
    }

    // toString method for displaying transaction details
    @Override
    public String toString() {
        return "Transaction{" +
                "soldCurrency='" + soldCurrency + '\'' +
                ", bCurrency='" + bCurrency + '\'' +
                ", soldAmount=" + soldAmount +
                ", bAmount=" + bAmount +
                ", timestamp=" + timestamp +
                '}';
    }
}