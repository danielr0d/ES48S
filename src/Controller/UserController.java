package Controller;

import Model.UserModel;
import View.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class UserController {
    private UserView view;

    public UserController(UserView view) {
        this.view = view;

        loadLastUser();

        this.view.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String email = view.getEmail();

                if (!username.isEmpty() && !email.isEmpty()) {
                    UserModel user = new UserModel(username, email);
                    saveUserToFile(user);
                    JOptionPane.showMessageDialog(view, "Cadastro de cliente efetuado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(view, "Complete todos os campos!");
                }
            }
        });
    }

    private void saveUserToFile(UserModel user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            writer.write("Nome do cliente: " + user.getUsername() + ", Email do cliente: " + user.getEmail());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar cliente: " + e.getMessage());
        }
    }

    private void loadLastUser() {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String lastLine = null;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine;
            }

            if (lastLine != null) {
                String[] parts = lastLine.split(", ");
                if (parts.length == 2) {
                    String username = parts[0].replace("Nome do cliente: ", "").trim();
                    String email = parts[1].replace("Email do cliente: ", "").trim();

                    view.setUsername(username);
                    view.setEmail(email);
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum cliente salvo anteriormente.");
        }
    }
}