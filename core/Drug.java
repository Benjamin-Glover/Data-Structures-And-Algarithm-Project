
package core;

public class Drug {
    public String code;
    public String name;
    public String[] suppliers;
    public String expiryDate;
    public double price;
    public int stock;

    public Drug(String code, String name, String[] suppliers, String expiryDate, double price, int stock) {
        this.code = code;
        this.name = name;
        this.suppliers = suppliers;
        this.expiryDate = expiryDate;
        this.price = price;
        this.stock = stock;
    }

    public void printInfo() {
        System.out.println("💊 Drug Code: " + code);
        System.out.println("📝 Name: " + name);
        System.out.print("🏢 Suppliers: ");
        for (int i = 0; i < suppliers.length; i++) {
            System.out.print(suppliers[i]);
            if (i < suppliers.length - 1) System.out.print(", ");
        }
        System.out.println();
        System.out.println("📅 Expiry Date: " + expiryDate);
        System.out.println("💰 Price: GHS " + price);
        System.out.println("📦 Stock: " + stock);
    }

    public String getFormattedInfo() {
        return "Code: " + code + " | Name: " + name + " | Stock: " + stock + " | Price: GHS " + price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(code).append(",");
        sb.append(name).append(",");
        
        // Join suppliers with semicolon
        for (int i = 0; i < suppliers.length; i++) {
            sb.append(suppliers[i]);
            if (i < suppliers.length - 1) sb.append(";");
        }
        
        sb.append(",").append(expiryDate);
        sb.append(",").append(price);
        sb.append(",").append(stock);
        
        return sb.toString();
    }
}
