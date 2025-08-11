package structures;

import core.Colors;
import core.Drug;

public class LinkedList {
    private Node head;
    private int size;

    // Node inner class
    private static class Node {
        Drug drug;
        Node next;
        Node(Drug drug) {
            this.drug = drug;
            this.next = null;
        }
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

    // Delete by code
    public boolean deleteByCode(String code) {
        if (isEmpty()) return false;

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

    // Sort by price (simple bubble sort)
    public void sortByPrice() {
        if (isEmpty()) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.drug.price > current.next.drug.price) {
                    Drug temp = current.drug;
                    current.drug = current.next.drug;
                    current.next.drug = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
    
}

