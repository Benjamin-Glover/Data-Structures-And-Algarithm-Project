package core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sale {
    public String drugCode;
    public String buyerID;
    public int quantity;
    public double totalCost;
    public String timestamp;

    public Sale(String drugCode, String buyerID, int quantity, double totalCost) {
        this.drugCode = drugCode;
        this.buyerID = buyerID;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getFormattedSale() {
        return "[" + timestamp + "] Drug: " + drugCode +
               " | Buyer: " + buyerID +
               " | Qty: " + quantity +
               " | Total: ₵" + totalCost;
    }

    public String toFileString() {
        return timestamp + "," + drugCode + "," + buyerID + "," + quantity + "," + totalCost;
    }
}
