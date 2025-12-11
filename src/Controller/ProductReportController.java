package Controller;

import View.ProductReportView;
import Report.ReportGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller para relatório de Produtos
 */
public class ProductReportController {
    private ProductReportView view;

    public ProductReportController(ProductReportView view) {
        this.view = view;

        // Listener para gerar relatório
        this.view.addGenerateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        // Listener para fechar janela
        this.view.addCloseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void generateReport() {
        try {
            ReportGenerator.generateProductReport();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao gerar relatório: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

