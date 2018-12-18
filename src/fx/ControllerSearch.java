package fx;

import NotePast.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button btnToday, btnDiary, btnSearch, btnLogout, btnBack;
    private Label noResult;
    private List<Note> result;

    EntityDiary entity;

    public ControllerSearch(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        scene = pageController.getScene("search");
        scp = (ScrollPane) scene.lookup("#scp");
        fieldSearch = (TextField) scene.lookup("#fieldSearch");
        btnToday = (Button) scene.lookup("#btnToday");
        btnDiary = (Button) scene.lookup("#btnDiary");
        btnLogout = (Button) scene.lookup("#btnLogout");
        btnBack = (Button) scene.lookup("#btnBack");
        btnSearch = (Button) scene.lookup("#btnSearch");
        noResult = (Label) scene.lookup("#noResult");

        /* Click on search button */
        btnSearch.setOnMouseClicked(mouseEvent -> {
            ControllerDiary.searchInput = fieldSearch.getText();
            if (ControllerDiary.searchInput != null){
                pageController.active("search");
            }
        });

        /* Click menu today */
        btnToday.setOnMouseClicked(mouseEvent -> pageController.active("today"));

        /* Click menu diary */
        btnDiary.setOnMouseClicked(mouseEvent -> pageController.active("diary"));

        /* Click menu logout */
        btnLogout.setOnMouseClicked(mouseEvent -> pageController.active("login"));

        /* Click Back */
        btnBack.setOnMouseClicked(mouseEvent -> {
            pageController.active("diary");
//            ControllerDiary.pagDiary.setCurrentPageIndex(tempPage);
        });
    }

    @Override
    public void onActive() {
        scene = pageController.getScene("search");

        entity = new EntityDiary();

        System.out.println(ControllerDiary.searchInput);
        fieldSearch.setText(ControllerDiary.searchInput);

        scp.setContent(null);

        result = Tag.searchTag(ControllerDiary.searchInput, ControllerLogin.activeDiary);

        if (!result.isEmpty()) {
            noResult.setVisible(false);

            VBox VBoxNote = new VBox();
            VBoxNote.setPrefWidth(396);

            for (int i = 0; i < result.size(); i++) {
                searchBox = new Pane();
                searchBox.setPrefSize(396, 55.0);
                searchBox.setStyle("-fx-background-color:white; -fx-background-radius: 0; -fx-border-color: #388ABD; -fx-border-width: 1");



                String strDate = result.get(i).getDayStr().substring(6) + " " +
                        ControllerToday.convertMonth(result.get(i).getDayStr().substring(4, 6)) + " "
                        + result.get(i).getDayStr().substring(0, 4) + " - " +
                        result.get(i).getTimeStr().substring(0, 2) + ":" + result.get(i).getTimeStr().substring(2, 4);
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
                    DayStory selectedDay = null;
                    for (DayStory day : ControllerLogin.activeDiary.getListOfDayStory()) {
                        if (day.getDayStr().equals(targetDay)) {
                            selectedDay = day;
                            break;
                        }
                    }
                    ControllerDiary.selectedDayStoryDate = selectedDay.getDayStr();
                    System.out.println("size2: " + selectedDay.getListOfNote().size());
                    ControllerDiary.selectedDayStory = selectedDay;

                    pageController.active("summary");
                });
            }
            scp.setContent(VBoxNote);
        } else {
            String noResults = "No results for " + ControllerDiary.searchInput;
            noResult.setText(noResults);
            noResult.setVisible(true);
        }
    }
}
