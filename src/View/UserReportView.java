package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View para relatório de Usuários com filtro por nome
 */
public class UserReportView extends JFrame {
    private JTextField usernameFilterField;
    private JButton generateButton;
    private JButton clearButton;
    private JButton closeButton;

    public UserReportView() {
        setTitle("Relatório de Usuários");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de filtros
        JPanel filterPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel filterLabel = new JLabel("Filtrar por nome de usuário (deixe em branco para todos):");
        usernameFilterField = new JTextField();

        filterPanel.add(filterLabel);
        filterPanel.add(usernameFilterField);

        add(filterPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        generateButton = new JButton("Gerar Relatório");
        clearButton = new JButton("Limpar");
        closeButton = new JButton("Fechar");

        buttonPanel.add(generateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getUsernameFilter() {
        return usernameFilterField.getText().trim();
    }

    public void setUsernameFilter(String username) {
        usernameFilterField.setText(username);
    }

    public void addGenerateListener(ActionListener listener) {
        generateButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    public void addCloseListener(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    public void clearForm() {
        usernameFilterField.setText("");
    }
}

