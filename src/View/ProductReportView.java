package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View para relatório de Produtos com agrupamento por faixa de preço
 */
public class ProductReportView extends JFrame {
    private JButton generateButton;
    private JButton closeButton;

    public ProductReportView() {
        setTitle("Relatório de Produtos");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de informações
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel infoLabel = new JLabel("Este relatório exibe todos os produtos agrupados por faixa de preço.");
        infoPanel.add(infoLabel, BorderLayout.CENTER);

        add(infoPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        generateButton = new JButton("Gerar Relatório");
        closeButton = new JButton("Fechar");

        buttonPanel.add(generateButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addGenerateListener(ActionListener listener) {
        generateButton.addActionListener(listener);
    }

    public void addCloseListener(ActionListener listener) {
        closeButton.addActionListener(listener);
    }
}

