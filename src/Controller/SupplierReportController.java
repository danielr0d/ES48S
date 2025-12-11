package Controller;

import View.SupplierReportView;
import Report.ReportGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller para relatório de Fornecedores
 */
public class SupplierReportController {
    private SupplierReportView view;

    public SupplierReportController(SupplierReportView view) {
        this.view = view;

        // Listener para gerar relatório
        this.view.addGenerateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        // Listener para limpar formulário
        this.view.addClearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearForm();
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
            Integer supplierId = view.getSupplierId();
            ReportGenerator.generateSupplierReport(supplierId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao gerar relatório: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

