package fx;

import NotePast.Diary;
import NotePast.EntityDiary;
import NotePast.Note;
import NotePast.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ControllerDiary implements Controller {
    Scene scene;
    private PageController pageController;
    private Button btnToday, btnDiary, btnYearsAgo, btnLogout, btnAddEvent, doneAddEvent, closeAddEvent;
    private Pagination pag;
    private VBox pageBox[] = new VBox[10];
    private Boolean emptyPag[] = {true, true, true, true, true, true, true, true, true, true};
    private int maxPage = 0, index;
    private String dayStory;
    private List<Pane> paneDiary = new ArrayList<>();
    private Pane objCreateDiary = new Pane();

    EntityDiary entity = Main.entity;
    User activeAcc;
    Diary activeDiary;

    public ControllerDiary(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        Scene scene = pageController.getScene("diary");

        btnToday = (Button) scene.lookup("#btnToday");
        btnDiary = (Button) scene.lookup("#btnDiary");
        btnLogout = (Button) scene.lookup("#btnLogout");
        pag = (Pagination) scene.lookup("#pag");
        VBox moo = (VBox) scene.lookup("#moo");

        /* create pagination pane */
        pag.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);




        /* Click menu today */
        btnToday.setOnMouseClicked(mouseEvent -> pageController.active("today"));

        /* Click menu diary */
//        btnDiary.setOnMouseClicked(mouseEvent -> pageController.active("diary"));

        pag.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                if (!emptyPag[pageIndex] | pageIndex == 0) {
                    return createPage(pageIndex);
                }
                return null;
            }
        });
    }

    private VBox createPage(int pageIndex) {
        if (emptyPag[pageIndex]) {
            pageBox[pageIndex] = new VBox();
            pageBox[pageIndex].setPrefHeight(330.0);
            pageBox[pageIndex].setPrefWidth(413.0);
            emptyPag[pageIndex] = false;
//            pageBox[0].setStyle("-fx-background-color:blue");


        }
        return pageBox[pageIndex];
    }

//    private void drawDiaryPane() {
//        for (int i = 0; i < activeDiary.getListOfDayStory().size(); i++) {
//            Note note = activeDiary.getToday().getListOfNote().get(i);
//            inputHH = note.getTimeStr().substring(0, 2);
//            inputMM = note.getTimeStr().substring(2, 4);
//            index = note.getNoteID() - 1;
//            if (checkTimeFormat()) {
//                timeTodayEvent = inputHH + ":" + inputMM;
//
//                page = index / 6;
//
//                NotePane newNotePane = new NotePane(index, inputHH, inputMM, note.getNoteText(), timeTodayEvent);
//                paneEvent.add(newNotePane);
//
//                objCreateEvent = ((NotePane) paneEvent.get(index)).getNoteBox();
//                GridPane.setMargin(objCreateEvent, new Insets(12, 7, 12, 7));
//
//                textAreaEvent.clear();
//                addEventBox.setVisible(false);
//
//                if (index % 6 == 0) {
//                    createPage(page);
//                    pageBox[page].add(objCreateEvent, 0, 0);
//                } else if (index % 6 == 1) {
//                    pageBox[page].add(objCreateEvent, 1, 0);
//                } else if (index % 6 == 2) {
//                    pageBox[page].add(objCreateEvent, 0, 1);
//                } else if (index % 6 == 3) {
//                    pageBox[page].add(objCreateEvent, 1, 1);
//                } else if (index % 6 == 4) {
//                    pageBox[page].add(objCreateEvent, 0, 2);
//                } else {
//                    pageBox[page].add(objCreateEvent, 1, 2);
//                }
//
////                pag.setCurrentPageIndex(page);
//
//                /* -------------TA!!!!!!! ------------- */
//            }
//        }
//    }

    @Override
    public void onActive() {
        activeAcc = ControllerLogin.activeAcc;
        activeDiary = ControllerLogin.activeDiary;

        for (int i =0; i < 10; i++) {
            emptyPag[i] = true;
        }
        maxPage = 0;

        index = 0;
        dayStory = "29 NOVEMBER 2018";

        DiaryPane newDiaryPane = new DiaryPane(index, dayStory);
        paneDiary.add(newDiaryPane);

        objCreateDiary = ((DiaryPane) paneDiary.get(index)).getDiaryBox();


        createPage(0);

        pageBox[0].getChildren().add(objCreateDiary);

    }
}
