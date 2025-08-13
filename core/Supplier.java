package core;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    public String supplierID;
    public String name;
    public String location;
    public String contact;
    public int deliveryTime; // in days
    public List<String> drugCodes; // Drugs they supply

    public Supplier(String supplierID, String name, String location, String contact, int deliveryTime) {
        this.supplierID = supplierID;
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.deliveryTime = deliveryTime;
        this.drugCodes = new ArrayList<>();
    }

    public void addDrug(String drugCode) {
        if (!drugCodes.contains(drugCode)) {
            drugCodes.add(drugCode);
        }
    }

    public void printInfo() {
        System.out.println(Colors.GREEN + " Supplier ID: " + supplierID + Colors.RESET);
        System.out.println(" Name: " + name);
        System.out.println(" Location: " + location);
        System.out.println(" Contact: " + contact);
        System.out.println(" Delivery Time: " + deliveryTime + " days");
        System.out.println(" Drugs Supplied: " + drugCodes.size());
    }

    @Override
    public String toString() {
        return supplierID + "," + name + "," + location + "," + contact + "," + deliveryTime;
    }
}
