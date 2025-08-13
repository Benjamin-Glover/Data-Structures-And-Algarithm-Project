package core;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProfileManager {
    private Map<String, customer> customers; // Hash table for customers
    private Map<String, Supplier> suppliers; // Hash table for suppliers
    private Scanner sc;
    private static final String CUSTOMERS_FILE = "data/customers.txt";
    private static final String SUPPLIERS_FILE = "data/suppliers.txt";

    public ProfileManager(Scanner sc) {
        this.sc = sc;
        this.customers = new HashMap<>();
        this.suppliers = new HashMap<>();
        loadCustomers();
        loadSuppliers();
    }

    // Customer Management
    public void addCustomer() {
        System.out.print(Colors.YELLOW + "→ Customer ID: " + Colors.RESET);
        String id = sc.nextLine().trim();
        
        if (customers.containsKey(id)) {
            System.out.println(Colors.RED + "❌ Customer already exists." + Colors.RESET);
            return;
        }

        System.out.print(Colors.YELLOW + "→ Name: " + Colors.RESET);
        String name = sc.nextLine().trim();
        
        System.out.print(Colors.YELLOW + "→ Contact: " + Colors.RESET);
        String contact = sc.nextLine().trim();

        customer customer = new customer(id, name, contact);
        customers.put(id, customer);
        saveCustomers();
        System.out.println(Colors.GREEN + "✅ Customer added successfully." + Colors.RESET);
    }

    public void viewCustomer() {
        System.out.print(Colors.YELLOW + "→ Customer ID: " + Colors.RESET);
        String id = sc.nextLine().trim();
        
        customer customer = customers.get(id);
        if (customer != null) {
            customer.printInfo();
        } else {
            System.out.println(Colors.RED + "❌ Customer not found." + Colors.RESET);
        }
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println(Colors.RED + "❌ No customers registered." + Colors.RESET);
            return;
        }

        System.out.println(Colors.CYAN + "👥 Customer List:" + Colors.RESET);
        for (customer customer : customers.values()) {
            customer.printInfo();
            System.out.println("─────────────────");
        }
    }

    // Supplier Management
    public void addSupplier() {
        System.out.print(Colors.YELLOW + "→ Supplier ID: " + Colors.RESET);
        String id = sc.nextLine().trim();
        
        if (suppliers.containsKey(id)) {
            System.out.println(Colors.RED + "❌ Supplier already exists." + Colors.RESET);
            return;
        }

        System.out.print(Colors.YELLOW + "→ Name: " + Colors.RESET);
        String name = sc.nextLine().trim();
        
        System.out.print(Colors.YELLOW + "→ Location: " + Colors.RESET);
        String location = sc.nextLine().trim();
        
        System.out.print(Colors.YELLOW + "→ Contact: " + Colors.RESET);
        String contact = sc.nextLine().trim();
        
        System.out.print(Colors.YELLOW + "→ Delivery Time (days): " + Colors.RESET);
        int deliveryTime = sc.nextInt();
        sc.nextLine();

        Supplier supplier = new Supplier(id, name, location, contact, deliveryTime);
        suppliers.put(id, supplier);
        saveSuppliers();
        System.out.println(Colors.GREEN + "✅ Supplier added successfully." + Colors.RESET);
    }

    public void viewSupplier() {
        System.out.print(Colors.YELLOW + "→ Supplier ID: " + Colors.RESET);
        String id = sc.nextLine().trim();
        
        Supplier supplier = suppliers.get(id);
        if (supplier != null) {
            supplier.printInfo();
        } else {
            System.out.println(Colors.RED + "❌ Supplier not found." + Colors.RESET);
        }
    }

    public void listSuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println(Colors.RED + "❌ No suppliers registered." + Colors.RESET);
            return;
        }

        System.out.println(Colors.GREEN + "🏢 Supplier List:" + Colors.RESET);
        for (Supplier supplier : suppliers.values()) {
            supplier.printInfo();
            System.out.println("─────────────────");
        }
    }

    // File Operations
    private void loadCustomers() {
        File file = new File(CUSTOMERS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    customer customer = new customer(parts[0], parts[1], parts[2]);
                    customers.put(parts[0], customer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }

    private void saveCustomers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (customer customer : customers.values()) {
                writer.println(customer.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    private void loadSuppliers() {
        File file = new File(SUPPLIERS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Supplier supplier = new Supplier(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
                    suppliers.put(parts[0], supplier);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading suppliers: " + e.getMessage());
        }
    }

    private void saveSuppliers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SUPPLIERS_FILE))) {
            for (Supplier supplier : suppliers.values()) {
                writer.println(supplier.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving suppliers: " + e.getMessage());
        }
    }
}
git 