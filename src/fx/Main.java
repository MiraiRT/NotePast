package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("NotePast");

        Pane diaryPane = FXMLLoader.load(getClass().getResource("diary.fxml"));
        Pane loginPane = FXMLLoader.load(getClass().getResource("login.fxml"));
        Pane searchPane = FXMLLoader.load(getClass().getResource("search.fxml"));
        Pane signupPane = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Pane summaryPane = FXMLLoader.load(getClass().getResource("summary.fxml"));
        Pane todayPane = FXMLLoader.load(getClass().getResource("today.fxml"));
        Pane yearsagoPane = FXMLLoader.load(getClass().getResource("yearsago.fxml"));

        Scene scene = new Scene(loginPane, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        PageController pageController = new PageController(scene);

        /*--------------------------------------------------------------------------*/
        ControllerLogin controllerLogin = new ControllerLogin(pageController);
        ControllerSignup controllerSignup = new ControllerSignup(pageController);
        ControllerToday controllerToday = new ControllerToday(pageController);
        ControllerDiary controllerDiary = new ControllerDiary(pageController);
      //  ControllerYearsago controllerYearsago = new ControllerToday(pageController);
        ControllerSummary controllerSummary = new ControllerSummary(pageController);

        /*--------------------------------------------------------------------------*/
        pageController.addPage("login", loginPane, controllerLogin);
        pageController.addPage("signup", signupPane, controllerSignup);
        pageController.addPage("today", todayPane, controllerToday);
        pageController.addPage("diary", diaryPane, controllerDiary);
      //  pageController.addPage("yearsago", yearsagoPane, controllerYearsago);
        pageController.addPage("summary", summaryPane, controllerSummary);

        pageController.active("today");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
