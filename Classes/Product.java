package Classes;

import java.util.ArrayList;

public class Product {
    private String brand;
    private ArrayList<String> size = new ArrayList<>();
    private double price;
    private ArrayList<Integer> avail = new ArrayList<>();
    private String type;

    //used to create an object of product class
    public Product(String brand, String type, ArrayList<String> sizes, double price, ArrayList<Integer> avail) {
        this.type = type;
        this.price = price;
        this.size.addAll(sizes);
        this.avail.addAll(avail);
        this.brand = brand;
    }

    //used in creating object of class CartItem
    public Product(String brand, String type, String size, double price) {
        this.type = type;
        this.price = price;
        this.size.add(size);
        this.brand = brand;
    }

    public void setAvail(ArrayList<Integer> avail,int index, int data) {     
        avail.set(index, data);
    }

    public String getType() {
        return this.type;
    }

    public ArrayList<String> getSize() {
        return this.size;
    }

    public double getPrice() {
        return this.price;
    }

    public ArrayList<Integer> getAvail() {
        return this.avail;
    }

    public String getBrand() {
        return this.brand;
    }
}
