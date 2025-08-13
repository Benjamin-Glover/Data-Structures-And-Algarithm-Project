package structures;
import core.Colors;
import core.Drug;

public class LinkedList {
    public Node head; // Make public for access
    private int size;

    // Node inner class
    public static class Node { // Make public static
        public Drug drug; // Make public
        public Node next; // Make public

        public Node(Drug drug) {
            this.drug = drug;
            this.next = null;
        }
    }

    public LinkedList() {
        this.head = null;
    }

    // Add drug to list
    public void add(Drug drug) {
        Node newNode = new Node(drug);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Check if list is empty
    public boolean isEmpty() {
        return size == 0; // ✅ Fix for your error
    }

    // List all drugs with colors
    public void listAll() {
        if (isEmpty()) {
            System.out.println(Colors.RED + "❌ No drugs in inventory." + Colors.RESET);
            return;
        }

        Node current = head;
        while (current != null) {
            if (current.drug.stock > 0) {
                System.out.println(Colors.GREEN + current.drug.getFormattedInfo() + Colors.RESET);
            } else {
                System.out.println(Colors.RED + current.drug.getFormattedInfo() + Colors.RESET);
            }
            current = current.next;
        }
    }

    // Search by code
    public Drug searchByCode(String code) {
        Node current = head;
        while (current != null) {
            if (current.drug.code.equalsIgnoreCase(code)) {
                return current.drug;
            }
            current = current.next;
        }
        return null;
    }

    // NEW: Search by name algorithm
    public Drug searchByName(String name) {
        Node current = head;
        while (current != null) {
            if (current.drug.name.toLowerCase().contains(name.toLowerCase())) {
                return current.drug;
            }
            current = current.next;
        }
        return null;
    }

    // NEW: Search by supplier algorithm
    public void searchBySupplier(String supplierName) {
        Node current = head;
        boolean found = false;
        System.out.println("Drugs from supplier: " + supplierName);
        
        while (current != null) {
            for (String supplier : current.drug.suppliers) {
                if (supplier.toLowerCase().contains(supplierName.toLowerCase())) {
                    current.drug.printInfo();
                    System.out.println("─────────────────");
                    found = true;
                    break;
                }
            }
            current = current.next;
        }
        
        if (!found) {
            System.out.println("No drugs found from supplier: " + supplierName);
        }
    }

    // Delete by code
    public boolean deleteByCode(String code) {
        if (head == null) return false;

        if (head.drug.code.equalsIgnoreCase(code)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.drug.code.equalsIgnoreCase(code)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Bubble sort by price algorithm
    public void sortByPrice() {
        if (head == null || head.next == null) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            
            while (current.next != null) {
                if (current.drug.price > current.next.drug.price) {
                    // Swap the drugs
                    Drug temp = current.drug;
                    current.drug = current.next.drug;
                    current.next.drug = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    // NEW: Bubble sort alphabetically by name algorithm
    public void sortAlphabetically() {
        if (head == null || head.next == null) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            
            while (current.next != null) {
                if (current.drug.name.compareToIgnoreCase(current.next.drug.name) > 0) {
                    // Swap the drugs
                    Drug temp = current.drug;
                    current.drug = current.next.drug;
                    current.next.drug = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void printAll() {
        if (head == null) {
            System.out.println("No drugs in inventory.");
            return;
        }

        Node current = head;
        while (current != null) {
            current.drug.printInfo();
            System.out.println("─────────────────");
            current = current.next;
        }
    }

    // NEW: Get count of drugs
    public int getCount() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Add this method to your LinkedList class
    public Node getHead() {
        return head;
    }
}

