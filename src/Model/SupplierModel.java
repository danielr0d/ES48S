package Model;

import java.io.Serializable;

public class SupplierModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String phone;

    public SupplierModel() {
    }

    public SupplierModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public SupplierModel(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return name + " - " + phone;
    }
}
