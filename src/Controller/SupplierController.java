package Controller;

import Model.SupplierModel;
import View.SupplierView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {
    private SupplierView view;
    private List<SupplierModel> suppliers;
    private final File file = new File("fornecedores.bin");

    public SupplierController(SupplierView view) {
        this.view = view;
        this.suppliers = new ArrayList<>();
        loadFromFile();
        refreshList();

        this.view.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { addSupplier(); }
        });
        this.view.addUpdateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { updateSupplier(); }
        });
        this.view.addDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deleteSupplier(); }
        });
        this.view.addConsultListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { consultSupplier(); }
        });
        this.view.addClearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { view.clearForm(); }
        });
    }

    public SupplierController() {
        this.view = null;
        this.suppliers = new ArrayList<>();
        loadFromFile();
    }

    public void addSupplierModel(SupplierModel s) {
        suppliers.add(s);
        saveToFile();
        refreshList();
    }

    public List<SupplierModel> getAllSuppliers() {
        return new ArrayList<>(suppliers);
    }

    private void addSupplier() {
        String name = view.getSupplierName().trim();
        String phone = view.getPhone().trim();
        if (name.isEmpty() || phone.isEmpty()) { JOptionPane.showMessageDialog(view, "Complete todos os campos!"); return; }
        suppliers.add(new SupplierModel(name, phone));
        saveToFile();
        refreshList();
        view.clearForm();
        JOptionPane.showMessageDialog(view, "Fornecedor incluído com sucesso.");
    }

    private void updateSupplier() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= suppliers.size()) { JOptionPane.showMessageDialog(view, "Selecione um fornecedor para alterar."); return; }
        String name = view.getSupplierName().trim();
        String phone = view.getPhone().trim();
        if (name.isEmpty() || phone.isEmpty()) { JOptionPane.showMessageDialog(view, "Complete todos os campos!"); return; }
        SupplierModel s = suppliers.get(idx);
        s.setName(name);
        s.setPhone(phone);
        saveToFile();
        refreshList();
        JOptionPane.showMessageDialog(view, "Fornecedor alterado com sucesso.");
    }

    private void deleteSupplier() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= suppliers.size()) { JOptionPane.showMessageDialog(view, "Selecione um fornecedor para excluir."); return; }
        int confirm = JOptionPane.showConfirmDialog(view, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        suppliers.remove(idx);
        saveToFile();
        refreshList();
        view.clearForm();
        JOptionPane.showMessageDialog(view, "Fornecedor excluído com sucesso.");
    }

    private void consultSupplier() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= suppliers.size()) { JOptionPane.showMessageDialog(view, "Selecione um fornecedor para consultar."); return; }
        SupplierModel s = suppliers.get(idx);
        view.setSupplierName(s.getName());
        view.setPhone(s.getPhone());
    }

    private void refreshList() {
        List<String> display = new ArrayList<>();
        for (SupplierModel s : suppliers) display.add(s.getName() + " - " + s.getPhone());
        if (view != null) view.setListData(display);
    }

    private void loadFromFile() {
        suppliers.clear();
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            suppliers = (List<SupplierModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar fornecedores: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(suppliers);
        } catch (IOException e) {
            if (view != null) JOptionPane.showMessageDialog(view, "Erro ao salvar fornecedores: " + e.getMessage());
            else System.out.println("Erro ao salvar fornecedores: " + e.getMessage());
        }
    }
}
