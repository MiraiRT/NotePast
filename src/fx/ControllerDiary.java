package fx;

import NotePast.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.util.Callback;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerDiary implements Controller {
    Scene scene;
    private PageController pageController;
    private Button btnToday, btnDiary, btnSearch, btnLogout, btnAddEvent, doneAddEvent, closeAddEvent;
    public static Pagination pagDiary;
    private static VBox[] pageBoxDiary = new VBox[10];
    private Boolean emptyPag[] = {true, true, true, true, true, true, true, true, true, true};
    private int index, page, objectNum;
    private List<Pane> paneDiary = new ArrayList<>();
    private static Pane objCreateDiary = new Pane();
    private Button diary0, diary1, diary2, diary3, diary4, diary5;
    private TextField fieldSearch;

    EntityDiary entity;

    public static String selectedDayStoryDate;
    public static DayStory selectedDayStory;
    public static int tempPage;
    public static String searchInput;

    public ControllerDiary(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initialize() {
        Scene scene = pageController.getScene("diary");

        btnToday = (Button) scene.lookup("#btnToday");
        btnDiary = (Button) scene.lookup("#btnDiary");
        btnLogout = (Button) scene.lookup("#btnLogout");
        pagDiary = (Pagination) scene.lookup("#pag");
        diary0 = (Button) scene.lookup("#diary0");
        diary1 = (Button) scene.lookup("#diary1");
        diary2 = (Button) scene.lookup("#diary2");
        diary3 = (Button) scene.lookup("#diary3");
        diary4 = (Button) scene.lookup("#diary4");
        diary5 = (Button) scene.lookup("#diary5");
        btnSearch = (Button) scene.lookup("#btnSearch");
        fieldSearch = (TextField) scene.lookup("#fieldSearch");

        /* create pagination pane */
        pagDiary.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

        /* Click on day story */
        diary0.setOnMouseClicked(mouseEvent -> {
            tempPage = pagDiary.getCurrentPageIndex();
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - (tempPage*6);
            if (index >= tempPage*6) {
                showSummary(objectNum);
            }
        });

        diary1.setOnMouseClicked(mouseEvent -> {
            tempPage = pagDiary.getCurrentPageIndex();
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - (tempPage*6 + 1);
            if (index > tempPage*6 + 1) {
                showSummary(objectNum);
            }
        });

        diary2.setOnMouseClicked(mouseEvent -> {
            tempPage = pagDiary.getCurrentPageIndex();
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - (tempPage*6 + 2);
            if (index > tempPage*6 + 2) {
                showSummary(objectNum);
            }
        });

        diary3.setOnMouseClicked(mouseEvent -> {
            tempPage = pagDiary.getCurrentPageIndex();
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - (tempPage*6 + 3);
            if (index > tempPage*6 + 3) {
                showSummary(objectNum);
            }
        });

        diary4.setOnMouseClicked(mouseEvent -> {
            tempPage = pagDiary.getCurrentPageIndex();
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - (tempPage*6 + 4);
            if (index > tempPage*6 + 4) {
                showSummary(objectNum);
            }
        });

        diary5.setOnMouseClicked(mouseEvent -> {
            tempPage = pagDiary.getCurrentPageIndex();
            index = ControllerLogin.activeDiary.getListOfDayStory().size();
            objectNum = index - 1 - (tempPage*6 + 5);
            if (index > tempPage*6 + 5) {
                showSummary(objectNum);
            }
        });

        /* Click on search button */
        btnSearch.setOnMouseClicked(mouseEvent -> {
            searchInput = fieldSearch.getText();
            if (searchInput != null){
                pageController.active("search");
            }
        });

        /* Click menu today */
        btnToday.setOnMouseClicked(mouseEvent -> pageController.active("today"));

        /* Click menu diary */
        btnDiary.setOnMouseClicked(mouseEvent -> pageController.active("diary"));

        /* Click menu logout */
        btnLogout.setOnMouseClicked(mouseEvent -> pageController.active("login"));
    }

    private VBox createPage(int pageIndex) {
        if (emptyPag[pageIndex]) {
            pageBoxDiary[pageIndex] = new VBox();
            pageBoxDiary[pageIndex].setPrefHeight(330.0);
            pageBoxDiary[pageIndex].setPrefWidth(413.0);
            emptyPag[pageIndex] = false;
        }
        return pageBoxDiary[pageIndex];
    }

    static void eraseDiaryPane() {
        int i = 0;
        while (pageBoxDiary[i] != null) {
            pageBoxDiary[i].getChildren().clear();
            i++;
        }
    }

    private void drawDiaryPane() {
        paneDiary.clear();
        for (int i = 0; i < ControllerLogin.activeDiary.getListOfDayStory().size(); i++) {
            index = i;
            page = index / 6;

            DayStory dayStory = ControllerLogin.activeDiary.getListOfDayStory().get(ControllerLogin.activeDiary.getListOfDayStory().size() - i - 1);
            String dateDayStory = dayStory.getDayStr().substring(6) + " " +
                    ControllerToday.convertMonth(dayStory.getDayStr().substring(4, 6)) + " " + dayStory.getDayStr().substring(0, 4);
            DiaryPane newDiaryPane = new DiaryPane(index, dateDayStory);

            paneDiary.add(newDiaryPane);
            objCreateDiary = ((DiaryPane) paneDiary.get(index)).getDiaryBox();

            if (index % 6 == 0) {
                createPage(page);
            }

            pageBoxDiary[page].getChildren().add(objCreateDiary);
        }
    }

    private void showSummary(int objectNum) {
        selectedDayStory = ControllerLogin.activeDiary.getListOfDayStory().get(objectNum);
        selectedDayStoryDate = selectedDayStory.getDayStr().substring(6, 8) + " " +
                ControllerToday.convertMonth(selectedDayStory.getDayStr().substring(4, 6)) + " " +
                selectedDayStory.getDayStr().substring(0, 4);
        pageController.active("summary");
    }

    @Override
    public void onActive() {
        fieldSearch.clear();
        for (int i = 0; i < 10; i++) {
            emptyPag[i] = true;
        }
        if (ControllerLogin.activeDiary.getListOfDayStory() != null) {
            System.out.println("not null");
            drawDiaryPane();

            /* Pagination */
            pagDiary.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    if (!emptyPag[pageIndex] | pageIndex == 0) {
                        return createPage(pageIndex);
                    }
                    return null;
                }
            });
        } else {
            System.out.println("error");
        }


        pagDiary.setCurrentPageIndex(0);
    }
}
