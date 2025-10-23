package Controller;

import Model.ProductModel;
import View.ProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductView view;
    private List<ProductModel> products;
    private final File file = new File("produtos.bin");

    public ProductController(ProductView view) {
        this.view = view;
        this.products = new ArrayList<>();
        loadFromFile();
        refreshList();

        this.view.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { addProduct(); }
        });
        this.view.addUpdateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { updateProduct(); }
        });
        this.view.addDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deleteProduct(); }
        });
        this.view.addConsultListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { consultProduct(); }
        });
        this.view.addClearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { view.clearForm(); }
        });
    }

    public ProductController() {
        this.view = null;
        this.products = new ArrayList<>();
        loadFromFile();
    }

    public void addProductModel(ProductModel p) {
        products.add(p);
        saveToFile();
        refreshList();
    }

    public List<ProductModel> getAllProducts() {
        return new ArrayList<>(products);
    }

    private void addProduct() {
        String name = view.getProductName().trim();
        String priceStr = view.getPrice().trim();
        if (name.isEmpty() || priceStr.isEmpty()) { JOptionPane.showMessageDialog(view, "Complete todos os campos!"); return; }
        double price;
        try { price = Double.parseDouble(priceStr); } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(view, "Preço inválido"); return; }
        products.add(new ProductModel(name, price));
        saveToFile();
        refreshList();
        view.clearForm();
        JOptionPane.showMessageDialog(view, "Produto incluído com sucesso.");
    }

    private void updateProduct() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= products.size()) { JOptionPane.showMessageDialog(view, "Selecione um produto para alterar."); return; }
        String name = view.getProductName().trim();
        String priceStr = view.getPrice().trim();
        if (name.isEmpty() || priceStr.isEmpty()) { JOptionPane.showMessageDialog(view, "Complete todos os campos!"); return; }
        double price;
        try { price = Double.parseDouble(priceStr); } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(view, "Preço inválido"); return; }
        ProductModel p = products.get(idx);
        p.setName(name);
        p.setPrice(price);
        saveToFile();
        refreshList();
        JOptionPane.showMessageDialog(view, "Produto alterado com sucesso.");
    }

    private void deleteProduct() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= products.size()) { JOptionPane.showMessageDialog(view, "Selecione um produto para excluir."); return; }
        int confirm = JOptionPane.showConfirmDialog(view, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        products.remove(idx);
        saveToFile();
        refreshList();
        view.clearForm();
        JOptionPane.showMessageDialog(view, "Produto excluído com sucesso.");
    }

    private void consultProduct() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= products.size()) { JOptionPane.showMessageDialog(view, "Selecione um produto para consultar."); return; }
        ProductModel p = products.get(idx);
        view.setProductName(p.getName());
        view.setPrice(String.valueOf(p.getPrice()));
    }

    private void refreshList() {
        List<String> display = new ArrayList<>();
        for (ProductModel p : products) display.add(p.getName() + " - " + p.getPrice());
        if (view != null) view.setListData(display);
    }

    private void loadFromFile() {
        products.clear();
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            products = (List<ProductModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(products);
        } catch (IOException e) {
            if (view != null) JOptionPane.showMessageDialog(view, "Erro ao salvar produtos: " + e.getMessage());
            else System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }
}
