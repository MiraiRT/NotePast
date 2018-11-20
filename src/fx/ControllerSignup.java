package fx;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerSignup implements Controller {
    PageController pageController;
    Button btnRegSubmin, btnRegCancel;
    TextField regUsername;
    PasswordField regPassword, rePassword;

    public ControllerSignup(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        Scene scene = pageController.getScene("signup");
        btnRegSubmin = (Button) scene.lookup("#btnRegSubmin");
        btnRegCancel = (Button) scene.lookup("#btnRegCancel");
        regUsername = (TextField) scene.lookup("#fieldRegUsername");
        regPassword = (PasswordField) scene.lookup("#fieldRegPass");
        rePassword = (PasswordField) scene.lookup("#fieldRegRepass");

        btnRegCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pageController.active("login");
            }
        });

        btnRegSubmin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                /*------------------- send everything to DB ------------------------*/

                pageController.active("login");
            }
        });
    }
    public void onActive() {
        regUsername.clear();
        regPassword.clear();
        rePassword.clear();
    }
}
