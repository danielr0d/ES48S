package Report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import Util.JDBCUtil;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por gerar e exibir relatórios usando JasperReports
 */
public class ReportGenerator {

    /**
     * Gera e exibe o relatório de Usuários com parâmetro de filtro
     * @param username Parâmetro de filtro (opcional, null para todos)
     */
    public static void generateUserReport(String username) {
        try {
            String reportPath = "reports/UserReport.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            Map<String, Object> parameters = new HashMap<>();
            if (username != null && !username.isEmpty()) {
                parameters.put("usernameFilter", "%" + username + "%");
            } else {
                parameters.put("usernameFilter", "%");
            }

            Connection connection = JDBCUtil.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            JasperViewer.viewReport(jasperPrint, false);

            connection.close();
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório de usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gera e exibe o relatório de Produtos com agrupamento por faixa de preço
     */
    public static void generateProductReport() {
        try {
            String reportPath = "reports/ProductReport.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            Map<String, Object> parameters = new HashMap<>();

            Connection connection = JDBCUtil.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            JasperViewer.viewReport(jasperPrint, false);

            connection.close();
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório de produtos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gera e exibe o relatório de Fornecedores
     * @param supplierId ID do fornecedor (opcional, null para todos)
     */
    public static void generateSupplierReport(Integer supplierId) {
        try {
            String reportPath = "reports/SupplierReport.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            Map<String, Object> parameters = new HashMap<>();
            if (supplierId != null && supplierId > 0) {
                parameters.put("supplierId", supplierId);
            } else {
                parameters.put("supplierId", -1);
            }

            Connection connection = JDBCUtil.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            JasperViewer.viewReport(jasperPrint, false);

            connection.close();
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório de fornecedores: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

