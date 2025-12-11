import Controller.MainController;
import Controller.ProductController;
import Controller.SupplierController;
import Controller.UserController;
import View.MainView;
import View.ProductView;
import View.SupplierView;
import View.UserView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Criar e exibir a janela principal
            MainView mainView = new MainView();
            new MainController(mainView);
            mainView.setVisible(true);

            // Criar e exibir as janelas de cadastro
            UserView userView = new UserView();
            new UserController(userView);
            userView.setVisible(true);

            ProductView productView = new ProductView();
            new ProductController(productView);
            productView.setVisible(true);

            SupplierView supplierView = new SupplierView();
            new SupplierController(supplierView);
            supplierView.setVisible(true);
        });
    }
}