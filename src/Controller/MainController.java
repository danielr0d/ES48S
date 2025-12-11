package Controller;

import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller Principal da Aplicação
 */
public class MainController {
    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;

        // Listener para abrir relatório de usuários
        this.mainView.addUserReportListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserReportView();
            }
        });

        // Listener para abrir relatório de produtos
        this.mainView.addProductReportListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProductReportView();
            }
        });

        // Listener para abrir relatório de fornecedores
        this.mainView.addSupplierReportListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSupplierReportView();
            }
        });
    }

    private void openUserReportView() {
        UserReportView view = new UserReportView();
        new UserReportController(view);
        view.setVisible(true);
    }

    private void openProductReportView() {
        ProductReportView view = new ProductReportView();
        new ProductReportController(view);
        view.setVisible(true);
    }

    private void openSupplierReportView() {
        SupplierReportView view = new SupplierReportView();
        new SupplierReportController(view);
        view.setVisible(true);
    }
}

