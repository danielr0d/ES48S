package Controller;

import Model.UserModel;
import View.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private UserView view;
    private List<UserModel> users;
    private final File file = new File("clientes.txt");

    public UserController(UserView view) {
        this.view = view;
        this.users = new ArrayList<>();

        loadFromFile();
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
            }
        });
    }

    public UserController() {
        this.view = null;
        this.users = new ArrayList<>();
        loadFromFile();
    }

    public void addUserModel(UserModel u) {
        users.add(u);
        saveToFile();
        refreshList();
    }

    public List<UserModel> getAllUsers() {
        return new ArrayList<>(users);
    }

    private void addUser() {
        String username = view.getUsername().trim();
        String email = view.getEmail().trim();
        if (username.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos os campos!");
            return;
        }
        UserModel u = new UserModel(username, email);
        users.add(u);
        saveToFile();
        refreshList();
        view.clearForm();
        JOptionPane.showMessageDialog(view, "Cliente incluído com sucesso.");
    }

    private void updateUser() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= users.size()) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na lista para alterar.");
            return;
        }
        String username = view.getUsername().trim();
        String email = view.getEmail().trim();
        if (username.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos os campos!");
            return;
        }
        UserModel u = users.get(idx);
        u.setUsername(username);
        u.setEmail(email);
        saveToFile();
        refreshList();
        JOptionPane.showMessageDialog(view, "Cliente alterado com sucesso.");
    }

    private void deleteUser() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= users.size()) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na lista para excluir.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        users.remove(idx);
        saveToFile();
        refreshList();
        view.clearForm();
        JOptionPane.showMessageDialog(view, "Cliente excluído com sucesso.");
    }

    private void consultUser() {
        int idx = view.getSelectedIndex();
        if (idx < 0 || idx >= users.size()) {
            JOptionPane.showMessageDialog(view, "Selecione um cliente na lista para consultar.");
            return;
        }
        UserModel u = users.get(idx);
        view.setUsername(u.getUsername());
        view.setEmail(u.getEmail());
    }

    private void refreshList() {
        List<String> display = new ArrayList<>();
        for (UserModel u : users) {
            display.add(u.getUsername() + " - " + u.getEmail());
        }
        if (view != null) view.setListData(display);
    }

    private void loadFromFile() {
        users.clear();
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (line.contains("Nome do cliente:")) {
                    String[] parts = line.split(",");
                    String username = "";
                    String email = "";
                    for (String p : parts) {
                        if (p.contains("Nome do cliente:")) username = p.replace("Nome do cliente:", "").trim();
                        if (p.contains("Email do cliente:")) email = p.replace("Email do cliente:", "").trim();
                    }
                    if (!username.isEmpty() || !email.isEmpty()) users.add(new UserModel(username, email));
                } else if (line.contains("|")) {
                    String[] parts = line.split("\\|");
                    String username = parts.length > 0 ? parts[0] : "";
                    String email = parts.length > 1 ? parts[1] : "";
                    users.add(new UserModel(username, email));
                } else {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        users.add(new UserModel(parts[0].trim(), parts[1].trim()));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (UserModel u : users) {
                writer.write(u.getUsername() + "|" + u.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            if (view != null) JOptionPane.showMessageDialog(view, "Erro ao salvar clientes: " + e.getMessage());
            else System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
}