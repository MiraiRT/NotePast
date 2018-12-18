package fx;

import NotePast.EntityDiary;
import NotePast.User;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerSignup implements Controller {
    PageController pageController;
    Button btnRegSubmit, btnRegCancel;
    TextField regUsername;
    PasswordField regPassword, rePassword;
    private EntityDiary entity;
    Label signUpInvalid;

    public ControllerSignup(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        Scene scene = pageController.getScene("signup");

        btnRegSubmit = (Button) scene.lookup("#btnRegSubmit");
        btnRegCancel = (Button) scene.lookup("#btnRegCancel");
        regUsername = (TextField) scene.lookup("#fieldRegUsername");
        regPassword = (PasswordField) scene.lookup("#fieldRegPass");
        rePassword = (PasswordField) scene.lookup("#fieldRegRepass");
        signUpInvalid = (Label) scene.lookup("#signUpInvalid");

        btnRegCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pageController.active("login");
            }
        });

        btnRegSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String username = regUsername.getText();
                String password1 = regPassword.getText();
                String password2 = rePassword.getText();
                System.out.println(password1 + " " + password2);



                if (password1.equals(password2) & (!username.isEmpty() | !password1.isEmpty() | !password2.isEmpty())) {
                    System.out.println("Password Re-Type Correct");
                    boolean isSuccess = User.signup(entity, username, password1);
                    if (isSuccess) {
                        pageController.active("login");
                    }
                } else {
                    signUpInvalid.setVisible(true);
                }
                System.out.println("Submit Clicked");
            }
        });
    }

    public void onActive() {
        regUsername.clear();
        regPassword.clear();
        rePassword.clear();
        signUpInvalid.setVisible(false);
        entity = new EntityDiary();
    }
}
