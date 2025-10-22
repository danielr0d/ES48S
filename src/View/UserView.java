package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton consultButton;
    private JButton clearButton;
    private DefaultListModel<String> listModel;
    private JList<String> userList;

    public UserView() {
        setTitle("Cadastro de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        form.add(new JLabel("Nome do cliente:"));
        usernameField = new JTextField();
        form.add(usernameField);

        form.add(new JLabel("Email do cliente:"));
        emailField = new JTextField();
        form.add(emailField);

        add(form, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        JScrollPane scroll = new JScrollPane(userList);
        add(scroll, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(1, 5, 5, 5));
        addButton = new JButton("Incluir");
        updateButton = new JButton("Alterar");
        deleteButton = new JButton("Excluir");
        consultButton = new JButton("Consultar");
        clearButton = new JButton("Limpar");

        buttons.add(addButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(consultButton);
        buttons.add(clearButton);

        add(buttons, BorderLayout.SOUTH);
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

    public void addAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addConsultListener(ActionListener listener) {
        consultButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    public void setListData(java.util.List<String> items) {
        listModel.clear();
        for (String s : items) listModel.addElement(s);
    }

    public int getSelectedIndex() {
        return userList.getSelectedIndex();
    }

    public void clearForm() {
        usernameField.setText("");
        emailField.setText("");
    }
}