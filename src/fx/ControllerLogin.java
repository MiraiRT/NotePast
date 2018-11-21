package fx;

import NotePast.Day;
import NotePast.Diary;
import NotePast.EntityDiary;
import NotePast.User;
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
    EntityDiary entity;
    static User activeAcc;
    static Diary activeDiary;

    public ControllerLogin(PageController pageController) {
        this.pageController = pageController;
        this.entity = new EntityDiary();
    }

    @Override
    public void initialize() {
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
                if (User.authen(entity, loginUsername, loginPass)) {
                    activeAcc = User.getAccount(entity, loginUsername);
                    activeDiary = activeAcc.getDiary();
                    Day.addDay(entity,activeDiary);
                    entity.closeConnection();
                    pageController.active("today");
                }
                System.out.println("Login Clicked");
            }
        });

    }

    @Override
    public void onActive() {
        username.clear();
        password.clear();
    }
}
