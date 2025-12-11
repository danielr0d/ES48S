package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View para relatório de Fornecedores
 */
public class SupplierReportView extends JFrame {
    private JTextField supplierIdField;
    private JButton generateButton;
    private JButton clearButton;
    private JButton closeButton;

    public SupplierReportView() {
        setTitle("Relatório de Fornecedores");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de filtros
        JPanel filterPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel filterLabel = new JLabel("ID do Fornecedor (deixe em branco para todos):");
        supplierIdField = new JTextField();

        filterPanel.add(filterLabel);
        filterPanel.add(supplierIdField);

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

    public Integer getSupplierId() {
        String text = supplierIdField.getText().trim();
        if (text.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void setSupplierId(String id) {
        supplierIdField.setText(id);
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
        supplierIdField.setText("");
    }
}

