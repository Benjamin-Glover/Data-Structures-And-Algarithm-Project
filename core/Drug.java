package core;

public class Drug {
    public String name;
    public String code;
    public String[] suppliers;
    public String expiryDate;
    public double price;
    public int stock;

    public Drug(String name, String code, String[] suppliers, String expiryDate, double price, int stock) {
        this.name = name;
        this.code = code;
        this.suppliers = suppliers;
        this.expiryDate = expiryDate;
        this.price = price;
        this.stock = stock;
    }

    // Method to print nicely formatted drug info
    public String getFormattedInfo() {
        return "[" + code + "] " + name + 
               " | ₵" + price + 
               " | Stock: " + stock + 
               " | Exp: " + expiryDate;
    }

    // Still keep original print method if needed
    public void printInfo() {
        System.out.println(getFormattedInfo());
    }
}
