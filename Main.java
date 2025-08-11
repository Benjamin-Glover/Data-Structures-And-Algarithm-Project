import core.InventoryManager;
import core.SalesManager;
import core.Colors;
import java.io.*;
import java.util.*;

public class Main {
    private static final String DATA_DIR = "data";
    private static final String SALES_IDS_FILE = DATA_DIR + File.separator + "sales_ids.txt";
    private static final Scanner SC = new Scanner(System.in);
    private static String salesID = ""; // Store logged-in Sales ID for recording sales

    public static void main(String[] args) {
        // Ensure required folders/files exist
        ensureDataFiles();

        // Load sales IDs
        Set<String> salesIDs = loadSalesIDs(SALES_IDS_FILE);

        // Authenticate user
        if (!authenticateSalesID(salesIDs)) return;

        // Create managers
        InventoryManager inv = new InventoryManager(SC);
        SalesManager salesManager = new SalesManager();

        // Main menu loop
        while (true) {
            System.out.println("\n" + Colors.BOLD + Colors.BLUE + "--- Atinka Meds Pharmacy System ---" + Colors.RESET);
            System.out.println(Colors.BOLD + "1. Add Drug" + Colors.RESET);
            System.out.println(Colors.BOLD + "2. List Drugs" + Colors.RESET);
            System.out.println(Colors.BOLD + "3. Search Drug" + Colors.RESET);
            System.out.println(Colors.BOLD + "4. Delete Drug" + Colors.RESET);
            System.out.println(Colors.BOLD + "5. Record Sale" + Colors.RESET);
            System.out.println(Colors.BOLD + "6. View Sales" + Colors.RESET);
            System.out.println(Colors.BOLD + "0. Exit" + Colors.RESET);
            System.out.print(Colors.YELLOW + "→ Enter choice: " + Colors.RESET);

            String choice = SC.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println(Colors.GREEN + "=== Adding a new drug ===" + Colors.RESET);
                    inv.addDrug();
                    break;
                case "2":
                    System.out.println(Colors.CYAN + "=== Listing all drugs ===" + Colors.RESET);
                    inv.listDrugs();
                    break;
                case "3":
                    System.out.println(Colors.YELLOW + "=== Searching for a drug ===" + Colors.RESET);
                    inv.searchDrug();
                    break;
                case "4":
                    System.out.println(Colors.RED + "=== Deleting a drug ===" + Colors.RESET);
                    inv.deleteDrug();
                    break;
                case "5":
                    System.out.println(Colors.PURPLE + "=== Recording a sale ===" + Colors.RESET);
                    inv.recordSale(salesManager, salesID);
                    break;
                case "6":
                    System.out.println(Colors.CYAN + "=== Viewing recent sales ===" + Colors.RESET);
                    salesManager.viewRecentSales(5);
                    break;
                case "0":
                    System.out.println(Colors.GREEN + "Goodbye!" + Colors.RESET);
                    return;
                default:
                    System.out.println(Colors.RED + "Invalid option. Try again." + Colors.RESET);
            }
        }
    }

    // Create data directory and default Sales ID file
    static void ensureDataFiles() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) dir.mkdirs();

        File salesFile = new File(SALES_IDS_FILE);
        if (!salesFile.exists()) {
            try (PrintWriter out = new PrintWriter(new FileWriter(salesFile))) {
                out.println("BEN");
                out.println("LIQUID");
                out.println("CINDY");
                out.println("SHIRLEY");
                out.println("MIMI");
            } catch (IOException e) {
                System.out.println("Error creating sales IDs file: " + e.getMessage());
            }
        }
    }

    // Read Sales IDs from file
    static Set<String> loadSalesIDs(String path) {
        Set<String> ids = new HashSet<>();
        File f = new File(path);
        if (!f.exists()) {
            System.out.println("Sales ID file not found: " + path);
            return ids;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) ids.add(line.toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("Error reading sales IDs: " + e.getMessage());
        }
        return ids;
    }

    // Authenticate Sales ID with 3 attempts
    static boolean authenticateSalesID(Set<String> validIDs) {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter Sales ID: ");
            String input = SC.nextLine().trim().toUpperCase();
            if (validIDs.contains(input)) {
                salesID = input; // Store for sales recording
                System.out.println("Access granted. Welcome " + input);
                return true;
            } else {
                attempts--;
                System.out.println("Invalid ID. Attempts left: " + attempts);
            }
        }
        System.out.println("Too many failed attempts. Exiting...");
        return false;
    }
}
