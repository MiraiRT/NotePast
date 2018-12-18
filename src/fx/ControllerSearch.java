package fx;

import NotePast.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class ControllerSearch  implements Controller {
    Scene scene;
    private PageController pageController;
    private ScrollPane scp;
    private Pane searchBox;
    private TextField fieldSearch;

    public static String selectedDayStoryDate;
    public static DayStory selectedDayStory;

    EntityDiary entity = Main.entity;

    User activeAcc;
    Diary activeDiary;

    public ControllerSearch(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        scene = pageController.getScene("search");
        scp = (ScrollPane) scene.lookup("#scp");
        fieldSearch = (TextField) scene.lookup("#fieldSearch");

    }

    @Override
    public void onActive() {
        scene = pageController.getScene("search");

        activeAcc = ControllerLogin.activeAcc;
        activeDiary = ControllerLogin.activeDiary;
        System.out.println(ControllerDiary.searchInput);
        fieldSearch.setText(ControllerDiary.searchInput);

        List<Note> result = Tag.searchTag(ControllerDiary.searchInput, activeDiary);

        System.out.println(result);
        VBox VBoxNote = new VBox();
        VBoxNote.setPrefWidth(396);

        for (int i = 0; i < result.size(); i++) {
            searchBox = new Pane();
            searchBox.setPrefSize(396, 55.0);
            searchBox.setStyle("-fx-background-color:white; -fx-background-radius: 0; -fx-border-color: #388ABD; -fx-border-width: 1");

            String strDate = result.get(i).getDayStr() + " " + result.get(i).getTimeStr();
            Label date = new Label(strDate);
            date.setLayoutX(14);
            date.setLayoutY(10);
            date.setStyle("-fx-text-Fill:#1d1f1f");
            date.setFont(new Font("Segoe UI bold", 15));

            Label strNote = new Label(result.get(i).getNoteText());
            strNote.setLayoutX(14);
            strNote.setLayoutY(30);
            strNote.setStyle("-fx-text-Fill:#1d1f1f");
            strNote.setFont(new Font("Segoe UI", 10));
            searchBox.getChildren().addAll(date, strNote);

            VBoxNote.getChildren().add(searchBox);

            String targetDay = result.get(i).getDayStr();

            searchBox.setOnMouseClicked(mouseEvent -> {
                System.out.println("click");
                DayStory selectedDay = null;
                for (DayStory day : activeDiary.getListOfDayStory()) {
                    if (day.getDayStr().equals(targetDay)) {
                        selectedDay = day;
                        break;
                    }
                }
                selectedDayStoryDate = selectedDay.getDayStr();
                selectedDayStory = selectedDay;

                pageController.active("summary");
            });
        }
        scp.setContent(VBoxNote);

    }
}
