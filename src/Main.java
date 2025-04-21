import Controller.UserController;
import View.UserView;

public class Main {
    public static void main(String[] args) {
        UserView view = new UserView();
        new UserController(view);
        view.setVisible(true);
    }
}