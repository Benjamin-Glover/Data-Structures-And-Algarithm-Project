package core;

import structures.LinkedList;
import java.util.Scanner;

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
        System.out.println(Colors.GREEN + "✅ Drug added successfully." + Colors.RESET);
    }

    public void listDrugs() {
        if (inventory.isEmpty()) {
            System.out.println(Colors.RED + "❌ No drugs available." + Colors.RESET);
        } else {
            System.out.println(Colors.CYAN + "📋 Drug List:" + Colors.RESET);
            inventory.listAll();
        }
    }

    public void searchDrug() {
        System.out.print(Colors.YELLOW + "→ Enter drug code: " + Colors.RESET);
        String code = sc.nextLine();
        Drug found = inventory.searchByCode(code);
        if (found != null) {
            System.out.println(Colors.GREEN + "✅ Drug found:" + Colors.RESET);
            found.printInfo();
        } else {
            System.out.println(Colors.RED + "❌ Drug not found." + Colors.RESET);
        }
    }

    public void deleteDrug() {
        System.out.print(Colors.YELLOW + "→ Enter drug code to delete: " + Colors.RESET);
        String code = sc.nextLine();
        boolean removed = inventory.deleteByCode(code);
        if (removed) {
            System.out.println(Colors.GREEN + "✅ Drug deleted successfully." + Colors.RESET);
        } else {
            System.out.println(Colors.RED + "❌ Drug not found." + Colors.RESET);
        }
    }

    public void sortDrugsByPrice() {
        inventory.sortByPrice();
        System.out.println(Colors.BLUE + "✅ Drugs sorted by price." + Colors.RESET);
    }

    public void recordSale(SalesManager salesManager, String salesID) {
    System.out.print(Colors.YELLOW + "→ Enter drug code: " + Colors.RESET);
    String code = sc.nextLine();
    Drug drug = inventory.searchByCode(code);

    if (drug == null) {
        System.out.println(Colors.RED + "❌ Drug not found." + Colors.RESET);
        return;
    }

    System.out.print(Colors.YELLOW + "→ Quantity: " + Colors.RESET);
    int qty = sc.nextInt();
    sc.nextLine();

    if (qty <= 0 || qty > drug.stock) {
        System.out.println(Colors.RED + "❌ Invalid quantity." + Colors.RESET);
        return;
    }

    double total = drug.price * qty;
    drug.stock -= qty;

    Sale sale = new Sale(drug.code, salesID, qty, total);
    salesManager.recordSale(sale);

    System.out.println(Colors.GREEN + "💰 Total cost: ₵" + total + Colors.RESET);
}



}
