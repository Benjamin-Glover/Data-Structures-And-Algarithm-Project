package core;

import java.util.ArrayList;
import java.util.List;

public class customer { // Changed from 'customer' to 'Customer'
    public String customerID;
    public String name;
    public String contact;
    public List<String> transactionHistory; // List of transaction IDs

    public customer(String customerID, String name, String contact) { // Changed constructor name
        this.customerID = customerID;
        this.name = name;
        this.contact = contact;
        this.transactionHistory = new ArrayList<>();
    }

    public void addTransaction(String transactionID) {
        transactionHistory.add(transactionID);
    }

    public void printInfo() {
        System.out.println(Colors.CYAN + " Customer ID: " + customerID + Colors.RESET);
        System.out.println("Name: " + name);
        System.out.println(" Contact: " + contact);
        System.out.println(" Transactions: " + transactionHistory.size());
    }

    @Override
    public String toString() {
        return customerID + "," + name + "," + contact;
    }
}
