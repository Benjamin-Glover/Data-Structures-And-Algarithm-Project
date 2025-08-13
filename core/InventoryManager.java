package core;

import java.util.Scanner;
import structures.LinkedList;

public class InventoryManager {
    private LinkedList inventory;
    private Scanner sc;

    public InventoryManager(Scanner sc) {
        this.sc = sc;
        this.inventory = new LinkedList();
    }

    public void addDrug() {
        System.out.print(Colors.YELLOW + "→ Name: " + Colors.RESET);
        String name = sc.nextLine();
        System.out.print(Colors.YELLOW + "→ Code: " + Colors.RESET);
        String code = sc.nextLine();
        System.out.print(Colors.YELLOW + "→ Suppliers (comma separated): " + Colors.RESET);
        String[] suppliers = sc.nextLine().split(",");
        System.out.print(Colors.YELLOW + "→ Expiry Date (YYYY-MM-DD): " + Colors.RESET);
        String expiry = sc.nextLine();
        System.out.print(Colors.YELLOW + "→ Price: " + Colors.RESET);
        double price = sc.nextDouble();
        System.out.print(Colors.YELLOW + "→ Stock: " + Colors.RESET);
        int stock = sc.nextInt();
        sc.nextLine(); // clear newline

        Drug drug = new Drug(name, code, suppliers, expiry, price, stock);
        inventory.add(drug);
        System.out.println(Colors.GREEN + " Drug added successfully." + Colors.RESET);
    }

    public void listDrugs() {
        if (inventory.isEmpty()) {
            System.out.println(Colors.RED + " No drugs available." + Colors.RESET);
        } else {
            System.out.println(Colors.CYAN + " Drug List:" + Colors.RESET);
            inventory.listAll();
        }
    }

    public void searchDrug() {
        System.out.println("Search by:");
        System.out.println("1. Drug Code");
        System.out.println("2. Drug Name");
        System.out.println("3. Supplier");
        System.out.print("→ Choose option: ");
        
        String option = sc.nextLine().trim();
        
        switch (option) {
            case "1":
                System.out.print(Colors.YELLOW + "→ Enter drug code: " + Colors.RESET);
                String code = sc.nextLine().trim();
                Drug found = inventory.searchByCode(code);
                if (found != null) {
                    System.out.println(Colors.GREEN + " Drug found:" + Colors.RESET);
                    found.printInfo();
                } else {
                    System.out.println(Colors.RED + " Drug not found." + Colors.RESET);
                }
                break;
                
            case "2":
                System.out.print(Colors.YELLOW + "→ Enter drug name: " + Colors.RESET);
                String name = sc.nextLine().trim();
                Drug foundByName = inventory.searchByName(name);
                if (foundByName != null) {
                    System.out.println(Colors.GREEN + " Drug found:" + Colors.RESET);
                    foundByName.printInfo();
                } else {
                    System.out.println(Colors.RED + " Drug not found." + Colors.RESET);
                }
                break;
                
            case "3":
                System.out.print(Colors.YELLOW + "→ Enter supplier name: " + Colors.RESET);
                String supplier = sc.nextLine().trim();
                inventory.searchBySupplier(supplier);
                break;
                
            default:
                System.out.println(Colors.RED + "Invalid option." + Colors.RESET);
        }
    }

    public void deleteDrug() {
        System.out.print(Colors.YELLOW + "→ Enter drug code to delete: " + Colors.RESET);
        String code = sc.nextLine();
        boolean removed = inventory.deleteByCode(code);
        if (removed) {
            System.out.println(Colors.GREEN + " Drug deleted successfully." + Colors.RESET);
        } else {
            System.out.println(Colors.RED + " Drug not found." + Colors.RESET);
        }
    }

    public void sortDrugsByPrice() {
        inventory.sortByPrice();
        System.out.println(Colors.BLUE + " Drugs sorted by price." + Colors.RESET);
    }

    public void recordSale(SalesManager salesManager, String salesID) {
    System.out.print(Colors.YELLOW + "→ Enter drug code: " + Colors.RESET);
    String code = sc.nextLine();
    Drug drug = inventory.searchByCode(code);

    if (drug == null) {
        System.out.println(Colors.RED + " Drug not found." + Colors.RESET);
        return;
    }

    System.out.print(Colors.YELLOW + "→ Quantity: " + Colors.RESET);
    int qty = sc.nextInt();
    sc.nextLine();

    if (qty <= 0 || qty > drug.stock) {
        System.out.println(Colors.RED + " Invalid quantity." + Colors.RESET);
        return;
    }

    double total = drug.price * qty;
    drug.stock -= qty;

    Sale sale = new Sale(drug.code, salesID, qty, total);
    salesManager.recordSale(sale);

    System.out.println(Colors.GREEN + "💰 Total cost: ₵" + total + Colors.RESET);
}

    // UPDATE DRUG METHOD
    public void updateDrug() {
        System.out.print(Colors.YELLOW + "→ Enter drug code to update: " + Colors.RESET);
        String code = sc.nextLine().trim();
        Drug drug = inventory.searchByCode(code);

        if (drug == null) {
            System.out.println(Colors.RED + " Drug not found." + Colors.RESET);
            return;
        }

        System.out.println(Colors.CYAN + "Current details:" + Colors.RESET);
        drug.printInfo();

        System.out.print("New name (Enter to keep '" + drug.name + "'): ");
        String name = sc.nextLine().trim();
        if (!name.isEmpty()) drug.name = name;

        System.out.print("New suppliers (comma separated, Enter to keep current): ");
        String suppliersInput = sc.nextLine().trim();
        if (!suppliersInput.isEmpty()) {
            drug.suppliers = suppliersInput.split(",");
            for (int i = 0; i < drug.suppliers.length; i++) {
                drug.suppliers[i] = drug.suppliers[i].trim();
            }
        }

        System.out.print("New expiry date (Enter to keep '" + drug.expiryDate + "'): ");
        String expiry = sc.nextLine().trim();
        if (!expiry.isEmpty()) drug.expiryDate = expiry;

        System.out.print("New price (Enter to keep '" + drug.price + "'): ");
        String priceInput = sc.nextLine().trim();
        if (!priceInput.isEmpty()) {
            try {
                drug.price = Double.parseDouble(priceInput);
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + "Invalid price. Keeping old value." + Colors.RESET);
            }
        }

        System.out.print("New stock (Enter to keep '" + drug.stock + "'): ");
        String stockInput = sc.nextLine().trim();
        if (!stockInput.isEmpty()) {
            try {
                drug.stock = Integer.parseInt(stockInput);
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + "Invalid stock. Keeping old value." + Colors.RESET);
            }
        }

        System.out.println(Colors.GREEN + " Drug updated successfully." + Colors.RESET);
    }

    // SORTING OPTIONS METHOD
    public void sortingOptions() {
        System.out.println("Sort by:");
        System.out.println("1. Price");
        System.out.println("2. Name (Alphabetically)");
        System.out.print("→ Choose option: ");
        
        String option = sc.nextLine().trim();
        
        switch (option) {
            case "1":
                inventory.sortByPrice();
                System.out.println(Colors.BLUE + "✅ Drugs sorted by price." + Colors.RESET);
                break;
            case "2":
                inventory.sortAlphabetically();
                System.out.println(Colors.BLUE + "✅ Drugs sorted alphabetically." + Colors.RESET);
                break;
            default:
                System.out.println(Colors.RED + "Invalid option." + Colors.RESET);
                return;
        }
        
        // Show sorted list
        System.out.println(Colors.CYAN + "=== Sorted Drug List ===" + Colors.RESET);
        inventory.printAll();
    }

    // LOW STOCK DRUGS USING MIN-HEAP
    public void showLowStockDrugs() {
        // Count drugs
        int count = 0;
        structures.LinkedList.Node current = inventory.getHead(); // Use getter
        while (current != null) {
            count++;
            current = current.next;
        }
        
        if (count == 0) {
            System.out.println(Colors.RED + " No drugs in inventory." + Colors.RESET);
            return;
        }

        // Build heap
        structures.MinHeap heap = new structures.MinHeap(count);
        current = inventory.getHead(); // Use getter
        while (current != null) {
            heap.insert(current.drug);
            current = current.next;
        }

        System.out.println(Colors.PURPLE + " Drugs with lowest stock:" + Colors.RESET);
        int shown = 0;
        while (!heap.isEmpty() && shown < 5) {
            Drug d = heap.extractMin();
            System.out.println(Colors.YELLOW + d.getFormattedInfo() + Colors.RESET);
            shown++;
        }
    }

    // STOCK ALERT SYSTEM
    public void checkStockAlerts() {
        System.out.print("Enter minimum stock threshold: ");
        int threshold = sc.nextInt();
        sc.nextLine(); // consume newline
        
        structures.LinkedList.Node current = inventory.head;
        boolean foundLowStock = false;
        
        System.out.println(Colors.RED + "  LOW STOCK ALERTS:" + Colors.RESET);
        
        while (current != null) {
            if (current.drug.stock <= threshold) {
                System.out.println(Colors.YELLOW + " " + current.drug.name + 
                                 " (" + current.drug.code + ") - Stock: " + 
                                 current.drug.stock + Colors.RESET);
                foundLowStock = true;
            }
            current = current.next;
        }
        
        if (!foundLowStock) {
            System.out.println(Colors.GREEN + " All drugs have sufficient stock!" + Colors.RESET);
        }
    }
}
