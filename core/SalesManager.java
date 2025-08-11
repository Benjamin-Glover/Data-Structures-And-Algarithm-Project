package core;

import java.io.*;
import java.util.*;

public class SalesManager {
    private static final String SALES_FILE = "data/sales.txt";
    private List<Sale> salesHistory = new ArrayList<>();

    public SalesManager() {
        loadSalesFromFile();
    }

    public void recordSale(Sale sale) {
        salesHistory.add(sale);
        saveSaleToFile(sale);
        System.out.println(Colors.GREEN + "✅ Sale recorded successfully." + Colors.RESET);
    }

    public void viewRecentSales(int count) {
        if (salesHistory.isEmpty()) {
            System.out.println(Colors.RED + "❌ No sales recorded yet." + Colors.RESET);
            return;
        }
        System.out.println(Colors.CYAN + "📜 Recent Sales:" + Colors.RESET);
        int start = Math.max(0, salesHistory.size() - count);
        for (int i = salesHistory.size() - 1; i >= start; i--) {
            System.out.println(Colors.YELLOW + salesHistory.get(i).getFormattedSale() + Colors.RESET);
        }
    }

    private void saveSaleToFile(Sale sale) {
        try (PrintWriter out = new PrintWriter(new FileWriter(SALES_FILE, true))) {
            out.println(sale.toFileString());
        } catch (IOException e) {
            System.out.println(Colors.RED + "❌ Error saving sale: " + e.getMessage() + Colors.RESET);
        }
    }

    private void loadSalesFromFile() {
        File file = new File(SALES_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Sale sale = new Sale(parts[1], parts[2],
                            Integer.parseInt(parts[3]),
                            Double.parseDouble(parts[4]));
                    sale.timestamp = parts[0];
                    salesHistory.add(sale);
                }
            }
        } catch (IOException e) {
            System.out.println(Colors.RED + "❌ Error reading sales file: " + e.getMessage() + Colors.RESET);
        }
    }
}
