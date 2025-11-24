package Controller;

import Model.UserModel;
import View.UserView;
import DAO.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private UserView view;
    private UserDAO userDAO;
    private int selectedUserId = -1;

    public UserController(UserView view) {
        this.view = view;
        this.userDAO = new UserDAO();

        loadFromDatabase();
        refreshList();

        this.view.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        this.view.addUpdateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        this.view.addDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        this.view.addConsultListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultUser();
            }
        });

        this.view.addClearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearForm();
                selectedUserId = -1;
            }
        });
    }

    public UserController() {
        this.view = null;
        this.userDAO = new UserDAO();
        loadFromDatabase();
    }

    public void addUserModel(UserModel u) {
        if (userDAO.insert(u)) {
            refreshList();
        }
    }

    public List<UserModel> getAllUsers() {
        return userDAO.findAll();
    }

    private void addUser() {
        String username = view.getUsername().trim();
        String email = view.getEmail().trim();
        if (username.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos os campos!");
            return;
        }

        UserModel u = new UserModel(username, email);
        if (userDAO.insert(u)) {
            refreshList();
            view.clearForm();
            selectedUserId = -1;
            JOptionPane.showMessageDialog(view, "Cliente incluído com sucesso.");
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao incluir cliente no banco de dados.");
        }
    }

    private void updateUser() {
        if (selectedUserId < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na lista para alterar.");
            return;
        }

        String username = view.getUsername().trim();
        String email = view.getEmail().trim();
        if (username.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos os campos!");
            return;
        }

        UserModel u = new UserModel(selectedUserId, username, email);
        if (userDAO.update(u)) {
            refreshList();
            JOptionPane.showMessageDialog(view, "Cliente alterado com sucesso.");
            selectedUserId = -1;
            view.clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao alterar cliente no banco de dados.");
        }
    }

    private void deleteUser() {
        if (selectedUserId < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na lista para excluir.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        if (userDAO.delete(selectedUserId)) {
            refreshList();
            view.clearForm();
            selectedUserId = -1;
            JOptionPane.showMessageDialog(view, "Cliente excluído com sucesso.");
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao excluir cliente do banco de dados.");
        }
    }

    private void consultUser() {
        int idx = view.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na lista para consultar.");
            return;
        }

        List<UserModel> users = userDAO.findAll();
        if (idx < users.size()) {
            UserModel u = users.get(idx);
            selectedUserId = u.getId();
            view.setUsername(u.getUsername());
            view.setEmail(u.getEmail());
        }
    }

    private void refreshList() {
        List<UserModel> users = userDAO.findAll();
        List<String> display = new ArrayList<>();
        for (UserModel u : users) {
            display.add(u.getId() + " - " + u.getUsername() + " - " + u.getEmail());
        }
        if (view != null) view.setListData(display);
    }

    private void loadFromDatabase() {
        // Dados carregados diretamente do DAO quando necessário
    }
}