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
        Pane pane = new Pane();
        Pane pane2 = new Pane();
        Scene today = new Scene(pane,640,480);
        Scene diary = new Scene(pane2,640,480);


      //  VBox1.setManaged(false);

        Parent root = FXMLLoader.load(getClass().getResource("today.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }


//    public static void main(String[] args) {
//        launch(args);
//    }

}
