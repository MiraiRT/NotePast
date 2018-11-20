package fx;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;

public class ControllerDiary implements Controller {
    PageController pageController;
    private Scene scene;
    private Button btnToday, btnDiary, btnYearsAgo, btnLogout, btnAddEvent, doneAddEvent, closeAddEvent;


    public ControllerDiary(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        Scene scene = pageController.getScene("diary");

        AnchorPane test = (AnchorPane) scene.lookup("#test");
        btnYearsAgo = (Button) scene.lookup("#btnYearsAgo");
        btnToday = (Button) scene.lookup("#btnToday");
        btnDiary = (Button) scene.lookup("#btnDiary");
        btnLogout = (Button) scene.lookup("#btnLogout");

//        Button del = new Button( "Click me!");
//        del.setStyle("-fx-background-image: url('DjbxdMNU0AAhmBG.jpg')");
//
//        final ImageView selectedImage = new ImageView();
//        Image del = new Image("file: DjbxdMNU0AAhmBG.jpg", 16, 16, false, false);
//        selectedImage.setImage(del);
//        selectedImage.setLayoutX(150);
////        selectedImage.setLayoutX(173);
////        selectedImage.setLayoutY(6);
//        selectedImage.setLayoutY(150);
//            test.getChildren().add(del);
//
//        FileInputStream input = new FileInputStream("resources/images/iconmonstr-home-6-48.png");
//        Image image = new Image(input);
//        ImageView imageView = new ImageView(image);
//
//        HBox hbox = new HBox(imageView);

        /* Click menu today */
        btnToday.setOnMouseClicked(mouseEvent -> pageController.active("today"));

        /* Click menu diary */
//        btnDiary.setOnMouseClicked(mouseEvent -> pageController.active("diary"));

        /* Click menu years ago */
        btnYearsAgo.setOnMouseClicked(mouseEvent -> pageController.active("yearsago"));
    }

    @Override
    public void onActive() {

    }
}
