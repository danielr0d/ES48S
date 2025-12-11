package View;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * View Principal da Aplicação com menu para acessar relatórios e cadastros
 */
public class MainView extends JFrame {
    private JMenu relatoriosMenu;
    private JMenuItem userReportItem;
    private JMenuItem productReportItem;
    private JMenuItem supplierReportItem;

    public MainView() {
        setTitle("Sistema de Gestão - ES48S");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criar menu bar
        JMenuBar menuBar = new JMenuBar();

        // Menu de Cadastros
        JMenu cadastrosMenu = new JMenu("Cadastros");

        JMenuItem userCadastroItem = new JMenuItem("Usuários/Clientes");
        JMenuItem productCadastroItem = new JMenuItem("Produtos");
        JMenuItem supplierCadastroItem = new JMenuItem("Fornecedores");

        cadastrosMenu.add(userCadastroItem);
        cadastrosMenu.add(productCadastroItem);
        cadastrosMenu.add(supplierCadastroItem);

        // Menu de Relatórios
        relatoriosMenu = new JMenu("Relatórios");

        userReportItem = new JMenuItem("Relatório de Usuários");
        productReportItem = new JMenuItem("Relatório de Produtos");
        supplierReportItem = new JMenuItem("Relatório de Fornecedores");

        relatoriosMenu.add(userReportItem);
        relatoriosMenu.add(productReportItem);
        relatoriosMenu.add(supplierReportItem);

        // Menu Sair
        JMenu sairMenu = new JMenu("Arquivo");
        JMenuItem sairItem = new JMenuItem("Sair");
        sairMenu.add(sairItem);

        menuBar.add(cadastrosMenu);
        menuBar.add(relatoriosMenu);
        menuBar.add(sairMenu);

        setJMenuBar(menuBar);

        // Painel central com instruções
        JPanel panel = new JPanel();
        JLabel label = new JLabel("<html>Sistema de Gestão de Dados<br>" +
                "Use o menu para acessar os cadastros ou relatórios</html>");
        panel.add(label);
        add(panel);
    }

    public void addUserReportListener(ActionListener listener) {
        userReportItem.addActionListener(listener);
    }

    public void addProductReportListener(ActionListener listener) {
        productReportItem.addActionListener(listener);
    }

    public void addSupplierReportListener(ActionListener listener) {
        supplierReportItem.addActionListener(listener);
    }
}

