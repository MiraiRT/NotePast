package fx;

import NotePast.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerDiary implements Controller {
    Scene scene;
    private PageController pageController;
    private Button btnToday, btnDiary, btnYearsAgo, btnLogout, btnAddEvent, doneAddEvent, closeAddEvent;
    private Pagination pag;
    private VBox pageBox[] = new VBox[10];
    private Boolean emptyPag[] = {true, true, true, true, true, true, true, true, true, true};
    private int index, page, objectNum;
    private List<Pane> paneDiary = new ArrayList<>();
    private Pane objCreateDiary = new Pane();
    private Button diary0, diary1, diary2, diary3, diary4, diary5;

    EntityDiary entity;
    String hour = new SimpleDateFormat("HH").format(new Date());
    String min = new SimpleDateFormat("mm").format(new Date());
    public static String selectedDayStoryDate;
    public static DayStory selectedDayStory;

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
        diary0 = (Button) scene.lookup("#diary0");
        diary1 = (Button) scene.lookup("#diary1");
        diary2 = (Button) scene.lookup("#diary2");
        diary3 = (Button) scene.lookup("#diary3");
        diary4 = (Button) scene.lookup("#diary4");
        diary5 = (Button) scene.lookup("#diary5");

        /* create pagination pane */
        pag.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

        /* Click on day story */
        diary0.setOnMouseClicked(mouseEvent -> {
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - (pag.getCurrentPageIndex() * 6);
            if (index > objectNum) {
                showSummary(objectNum);
            }
        });

        diary1.setOnMouseClicked(mouseEvent -> {
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - ((pag.getCurrentPageIndex() * 6) + 1);
            System.out.println("objectNum: " + objectNum);
            System.out.println("index: " + index);
            if (index > objectNum) {
                showSummary(objectNum);
            }
        });

        diary2.setOnMouseClicked(mouseEvent -> {
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - ((pag.getCurrentPageIndex() * 6) + 2);
            if (index > objectNum) {
                showSummary(objectNum);
            }
        });

        diary3.setOnMouseClicked(mouseEvent -> {
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - ((pag.getCurrentPageIndex() * 6) + 3);
            if (index > objectNum) {
                showSummary(objectNum);
            }
        });

        diary4.setOnMouseClicked(mouseEvent -> {
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - ((pag.getCurrentPageIndex() * 6) + 4);
            if (index > objectNum) {
                showSummary(objectNum);
            }
        });

        diary5.setOnMouseClicked(mouseEvent -> {
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - ((pag.getCurrentPageIndex() * 6) + 5);
            if (index > objectNum) {
                showSummary(objectNum);
            }
        });
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
        }
        return pageBox[pageIndex];
    }

    private void drawDiaryPane() {
        for (int i = 0; i < ControllerLogin.activeDiary.getListOfDayStory().size(); i++) {
            index = i;
            page = index / 6;

            DayStory dayStory = ControllerLogin.activeDiary.getListOfDayStory().get(ControllerLogin.activeDiary.getListOfDayStory().size() - i - 1);
            String dateDayStory = dayStory.getDayStr().substring(6) + " " +
                    convertMonth(dayStory.getDayStr().substring(4, 6)) + " " + dayStory.getDayStr().substring(0, 4);
            DiaryPane newDiaryPane = new DiaryPane(index, dateDayStory);

            paneDiary.add(newDiaryPane);
            objCreateDiary = ((DiaryPane) paneDiary.get(index)).getDiaryBox();

            if (index % 6 == 0) {
                createPage(page);
            }

            pageBox[page].getChildren().add(objCreateDiary);
        }
    }

    private void showSummary(int objectNum) {
        selectedDayStoryDate = ((DiaryPane) paneDiary.get(objectNum)).getDayStory();
        selectedDayStory = ControllerLogin.activeDiary.getListOfDayStory().get(objectNum);

        System.out.println(selectedDayStory);
        pageController.active("summary");
    }

    private String convertMonth(String month) {
        switch (month) {
            case "01":
                return "JANUARY";
            case "02":
                return "FEBRUARY";
            case "03":
                return "MARCH";
            case "04":
                return "APRIL";
            case "05":
                return "MAY";
            case "06":
                return "JUNE";
            case "07":
                return "JULY";
            case "08":
                return "AUGUST";
            case "09":
                return "SEPTEMBER";
            case "10":
                return "OCTOBER";
            case "11":
                return "NOVEMBER";
            case "12":
                return "DECEMBER";
            default:
                return "ERROR";
        }
    }

    @Override
    public void onActive() {
        for (int i = 0; i < 10; i++) {
            emptyPag[i] = true;
        }
        if (ControllerLogin.activeDiary.getListOfDayStory() != null) {
            System.out.println("not null");
            drawDiaryPane();
        } else {
            System.out.println("errorrrrrrrrrrrrrrrrrr");
        }

        pag.setCurrentPageIndex(0);
    }
}
