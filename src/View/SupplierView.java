package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SupplierView extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton consultButton;
    private JButton clearButton;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;

    public SupplierView() {
        setTitle("Cadastro de Fornecedores");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        form.add(new JLabel("Nome do fornecedor:"));
        nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Telefone:"));
        phoneField = new JTextField();
        form.add(phoneField);

        add(form, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        add(new JScrollPane(itemList), BorderLayout.CENTER);

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

    public String getSupplierName() { return nameField.getText(); }
    public String getPhone() { return phoneField.getText(); }
    public void setSupplierName(String v) { nameField.setText(v); }
    public void setPhone(String v) { phoneField.setText(v); }

    public void addAddListener(ActionListener l) { addButton.addActionListener(l); }
    public void addUpdateListener(ActionListener l) { updateButton.addActionListener(l); }
    public void addDeleteListener(ActionListener l) { deleteButton.addActionListener(l); }
    public void addConsultListener(ActionListener l) { consultButton.addActionListener(l); }
    public void addClearListener(ActionListener l) { clearButton.addActionListener(l); }

    public void setListData(List<String> items) {
        listModel.clear();
        for (String s : items) listModel.addElement(s);
    }

    public int getSelectedIndex() { return itemList.getSelectedIndex(); }
    public void clearForm() { nameField.setText(""); phoneField.setText(""); }
}

