package fx;

import NotePast.DayStory;
import NotePast.Diary;
import NotePast.EntityDiary;
import NotePast.User;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerLogin implements Controller {
    PageController pageController;
    Button btnSignup, btnLogin;
    TextField username;
    PasswordField password;
    private EntityDiary entity;
    static User activeAcc;
    static Diary activeDiary;
    static String activeUser;

    public ControllerLogin(PageController pageController) {
        this.pageController = pageController;
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
                    ControllerLogin.activeUser = loginUsername;
                    ControllerLogin.activeAcc = User.getAccount(entity, loginUsername);
                    ControllerLogin.activeDiary = activeAcc.getDiary();

                    if (activeDiary.getListOfDayStory().size() == 0) {
                        DayStory.addDay(entity, activeDiary);
                        System.out.println("New User -> Create Day");
                    }

                    int today = Integer.parseInt(activeDiary.getToday().getDayStr());
                    String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
                    int newDay = Integer.parseInt(dayStr);

                    if (newDay > today) {
                        DayStory.addDay(entity, activeDiary);
                        System.out.println("Create Day");
                    }
                    System.out.println("Authen -> Success!!\n\n");
                    pageController.active("today");
                }
            }
        });

    }

    @Override
    public void onActive() {
        username.clear();
        password.clear();
        entity = new EntityDiary();
        ControllerLogin.activeUser = null;
        ControllerLogin.activeDiary = null;
        ControllerLogin.activeAcc = null;
    }
}
