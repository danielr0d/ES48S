package Model;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;

    public ProductModel(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }
}
