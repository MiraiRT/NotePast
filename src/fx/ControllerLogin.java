package fx;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerLogin implements Controller {
    PageController pageController;
    Button btnSignup, btnLogin;
    TextField username;
    PasswordField password;

    public ControllerLogin(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initilize() {
        Scene scene = pageController.getScene("login");

        btnSignup = (Button) scene.lookup("#btnSignup");
        btnLogin = (Button) scene.lookup("#btnLogin");
        username = (TextField) scene.lookup("#fieldUsername");
        password = (PasswordField) scene.lookup("#fieldPassword");

        btnSignup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pageController.active("signup");
            }
        });

        btnLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String loginUsername = username.getText();
                String loginPass = password.getText();
                /*--------------------TA!!!!! send username n password----------------*/

                /*if true -> switch page to today*/
                pageController.active("today");
            }
        });

    }

    @Override
    public void onActive() {
        username.clear();
        password.clear();
    }
}
