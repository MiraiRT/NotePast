package fx;

import NotePast.DayStory;
import NotePast.EntityDiary;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ControllerSummary implements Controller {
    Scene scene;
    private PageController pageController;
    private Label dateSummary;
    private ScrollPane scp;
    private  Group popupDel;
    private Button btnToday, btnDiary, btnBack, btnLogout;
    private Button btnDel, yesDel, noDel;

    EntityDiary entity;

    public ControllerSummary(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        scene = pageController.getScene("summary");
        scp = (ScrollPane) scene.lookup("#scp");

        btnToday = (Button) scene.lookup("#btnToday");
        btnDiary = (Button) scene.lookup("#btnDiary");
        btnBack = (Button) scene.lookup("#btnBack");
        btnLogout = (Button) scene.lookup("#btnLogout");
        btnDel = (Button) scene.lookup("#btnDel");
        popupDel = (Group) scene.lookup("#popupDel");
        yesDel = (Button) scene.lookup("#yesDel");
        noDel = (Button) scene.lookup("#noDel");

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

        /* Delete day story */
        btnDel.setOnMouseClicked(mouseEvent -> {
            popupDel.setVisible(true);
        });

        yesDel.setOnMouseClicked(mouseEvent -> {
            popupDel.setVisible(false);
            System.out.println("ControllerDiary.selectedDayStory.getId(): " + ControllerDiary.selectedDayStory.getId());
            DayStory.deleteDayStory(entity, ControllerLogin.activeDiary, ControllerDiary.selectedDayStory.getId());
//            eraseDiaryPane();
            pageController.active("diary");
        });

        noDel.setOnMouseClicked(mouseEvent -> popupDel.setVisible(false));

    }

    @Override
    public void onActive() {
        scene = pageController.getScene("summary");

        entity = new EntityDiary();

        popupDel.setVisible(false);

        dateSummary = (Label) scene.lookup("#dateSummary");
        dateSummary.setText(ControllerDiary.selectedDayStoryDate);

        VBox eachDay = new VBox();
        eachDay.setPrefWidth(380);
        eachDay.setSpacing(20);

        ControllerDiary.selectedDayStory.summaryNote();

        for (int i = 0; i < ControllerDiary.selectedDayStory.getListOfNote().size(); i++) {
            VBox group = new VBox();
            Label time = new Label();
            Label text = new Label();

            time.setFont(new Font("Segoe UI bold", 13));
            text.setFont(new Font("Segoe UI", 11));
            text.setWrapText(true);

            String timeSt = ControllerDiary.selectedDayStory.getListOfNote().get(i).getTimeStr();
            String textSt = ControllerDiary.selectedDayStory.getListOfNote().get(i).getNoteText();

            time.setText(timeSt.substring(0, 2) + ":" + timeSt.substring(2, 4));
            text.setText(textSt);
            group.getChildren().addAll(time, text);
            eachDay.getChildren().add(group);
        }
        scp.setContent(eachDay);
    }
}
