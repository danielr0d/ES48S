package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JButton registerButton;

    public UserView() {
        setTitle("Cadastro de Clientes");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Nome do cliente:");
        usernameField = new JTextField();
        add(usernameField);

        JLabel emailLabel = new JLabel("Email do cliente:");
        emailField = new JTextField();
        add(emailField);

        registerButton = new JButton("Cadastrar");
        add(registerButton);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public void setUsername(String username) {
        usernameField.setText(username);
    }

    public void setEmail(String email) {
        emailField.setText(email);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }
}