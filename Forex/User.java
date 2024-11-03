public abstract class User {
    protected String name;
    protected double[] portfolio;

    public User(String name) {
        this.name = name;
        this.portfolio = new double[]{100000, 0, 0, 0}; // [INR, USD, EUR, YEN]
    }

    public String getName() {
        return name;
    }

    public double[] getPortfolio() {
        return portfolio;
    }

    // Abstract method to display portfolio, to be implemented by subclasses
    public abstract void displayPortfolio();
}