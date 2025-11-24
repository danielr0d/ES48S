package Controller;

import Model.SupplierModel;
import View.SupplierView;
import DAO.SupplierDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {
    private SupplierView view;
    private SupplierDAO supplierDAO;
    private int selectedSupplierId = -1;

    public SupplierController(SupplierView view) {
        this.view = view;
        this.supplierDAO = new SupplierDAO();
        loadFromDatabase();
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
            public void actionPerformed(ActionEvent e) {
                view.clearForm();
                selectedSupplierId = -1;
            }
        });
    }

    public SupplierController() {
        this.view = null;
        this.supplierDAO = new SupplierDAO();
        loadFromDatabase();
    }

    public void addSupplierModel(SupplierModel s) {
        if (supplierDAO.insert(s)) {
            refreshList();
        }
    }

    public List<SupplierModel> getAllSuppliers() {
        return supplierDAO.findAll();
    }

    private void addSupplier() {
        String name = view.getSupplierName().trim();
        String phone = view.getPhone().trim();
        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos os campos!");
            return;
        }
        SupplierModel s = new SupplierModel(name, phone);
        if (supplierDAO.insert(s)) {
            refreshList();
            view.clearForm();
            selectedSupplierId = -1;
            JOptionPane.showMessageDialog(view, "Fornecedor incluído com sucesso.");
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao incluir fornecedor no banco de dados.");
        }
    }

    private void updateSupplier() {
        if (selectedSupplierId < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um fornecedor para alterar.");
            return;
        }
        String name = view.getSupplierName().trim();
        String phone = view.getPhone().trim();
        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos os campos!");
            return;
        }
        SupplierModel s = new SupplierModel(selectedSupplierId, name, phone);
        if (supplierDAO.update(s)) {
            refreshList();
            JOptionPane.showMessageDialog(view, "Fornecedor alterado com sucesso.");
            selectedSupplierId = -1;
            view.clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao alterar fornecedor no banco de dados.");
        }
    }

    private void deleteSupplier() {
        if (selectedSupplierId < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um fornecedor para excluir.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        if (supplierDAO.delete(selectedSupplierId)) {
            refreshList();
            view.clearForm();
            selectedSupplierId = -1;
            JOptionPane.showMessageDialog(view, "Fornecedor excluído com sucesso.");
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao excluir fornecedor do banco de dados.");
        }
    }

    private void consultSupplier() {
        int idx = view.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um fornecedor para consultar.");
            return;
        }
        List<SupplierModel> suppliers = supplierDAO.findAll();
        if (idx < suppliers.size()) {
            SupplierModel s = suppliers.get(idx);
            selectedSupplierId = s.getId();
            view.setSupplierName(s.getName());
            view.setPhone(s.getPhone());
        }
    }

    private void refreshList() {
        List<SupplierModel> suppliers = supplierDAO.findAll();
        List<String> display = new ArrayList<>();
        for (SupplierModel s : suppliers) display.add(s.getId() + " - " + s.getName() + " - " + s.getPhone());
        if (view != null) view.setListData(display);
    }

    private void loadFromDatabase() {
        // Dados carregados diretamente do DAO quando necessário
    }
}
