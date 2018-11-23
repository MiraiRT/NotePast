package fx;

import NotePast.EntityDiary;
import NotePast.User;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerToday implements Controller {
    Scene scene;
    private PageController pageController;
    private Button btnToday, btnDiary, btnYearsAgo, btnLogout, btnAddEvent;
    private Button doneAddEvent, closeAddEvent, doneEditEvent, closeEditEvent;
    private Label dateToday;
    private Group addEventBox, editEventBox, popupDel;
    private TextArea textAreaEvent, textAreaEventEdit;
    private TextField HH, MM, HHedit, MMedit;
    private int page, objectNum, index = -1;
    private String inputHH, inputMM, hourEdit, minEdit;
    private String textTodayEvent, timeTodayEvent;
    private Pagination pag;
    private AnchorPane pagPane;
    private GridPane pageBox[] = new GridPane[10];
    private Boolean emptyPag[] = {true, true, true, true, true, true, true, true, true, true};
    private Pane btnEvent0, btnEvent1, btnEvent2, btnEvent3, btnEvent4, btnEvent5;
    private Button delEvent0, delEvent1, delEvent2, delEvent3, delEvent4, delEvent5;
    private List<Pane> paneEvent = new ArrayList<>();
    private Pane objCreateEvent = new Pane();

    private String day = new SimpleDateFormat("dd").format(new Date());
    private String month = new SimpleDateFormat("MM").format(new Date());
    private String year = new SimpleDateFormat("yyyy").format(new Date());
    private String hour;// = new SimpleDateFormat("HH").format(new Date());
    private String min;// = new SimpleDateFormat("mm").format(new Date());
    EntityDiary entity;

    User activeAcc = ControllerLogin.activeAcc;

    ControllerToday(PageController pageController) {
        this.pageController = pageController;
        this.entity = new EntityDiary();
    }

    @Override
    public void initialize() {
        scene = pageController.getScene("today");

        dateToday = (Label) scene.lookup("#dateToday");
        addEventBox = (Group) scene.lookup("#addEventBox");
        editEventBox = (Group) scene.lookup("#editEventBox");
        HH = (TextField) scene.lookup("#HH");
        MM = (TextField) scene.lookup("#MM");
        HHedit = (TextField) scene.lookup("#HHedit");
        MMedit = (TextField) scene.lookup("#MMedit");
        btnAddEvent = (Button) scene.lookup("#btnAddEvent");
        closeAddEvent = (Button) scene.lookup("#closeAddEvent");
        closeEditEvent = (Button) scene.lookup("#closeEditEvent");
        textAreaEvent = (TextArea) scene.lookup("#textAreaEvent");
        doneAddEvent = (Button) scene.lookup("#doneAddEvent");
        doneEditEvent = (Button) scene.lookup("#doneEditEvent");
        btnYearsAgo = (Button) scene.lookup("#btnYearsAgo");
        btnToday = (Button) scene.lookup("#btnToday");
        btnDiary = (Button) scene.lookup("#btnDiary");
        btnLogout = (Button) scene.lookup("#btnLogout");
        pag = (Pagination) scene.lookup("#pag");
        pagPane = (AnchorPane) scene.lookup("#pagPane");
        textAreaEventEdit = (TextArea) scene.lookup("#textAreaEventEdit");
        popupDel = (Group) scene.lookup("#popupDel");

        btnEvent0 = (Pane) scene.lookup("#btnEvent0");
        btnEvent1 = (Pane) scene.lookup("#btnEvent1");
        btnEvent2 = (Pane) scene.lookup("#btnEvent2");
        btnEvent3 = (Pane) scene.lookup("#btnEvent3");
        btnEvent4 = (Pane) scene.lookup("#btnEvent4");
        btnEvent5 = (Pane) scene.lookup("#btnEvent5");
        delEvent0 = (Button) scene.lookup("#delEvent0");
        delEvent1 = (Button) scene.lookup("#delEvent1");
        delEvent2 = (Button) scene.lookup("#delEvent2");
        delEvent3 = (Button) scene.lookup("#delEvent3");
        delEvent4 = (Button) scene.lookup("#delEvent4");
        delEvent5 = (Button) scene.lookup("#delEvent5");

        pag.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
//        pag.setStyle("-fx-page-information-color:false");
        AnchorPane.setTopAnchor(pag, 0.0);
        AnchorPane.setLeftAnchor(pag, 0.0);
        AnchorPane.setRightAnchor(pag, 0.0);
        AnchorPane.setBottomAnchor(pag, 0.0);

        /* Pagination */
        pag.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                if (!emptyPag[pageIndex]) {
                    return createPage(pageIndex);
                }
                return null;
            }
        });

        /* show today date */
        dateToday.setText(day + " " + convertMonth(month) + " " + year);

        /* Click menu today */
        btnToday.setOnMouseClicked(mouseEvent -> pag.setCurrentPageIndex(0));

        /* Click menu diary */
        btnDiary.setOnMouseClicked(mouseEvent -> pageController.active("diary"));

        /* Click menu years ago */
        btnYearsAgo.setOnMouseClicked(mouseEvent -> pageController.active("yearsago"));

        /* Click menu logout */
        btnLogout.setOnMouseClicked(mouseEvent -> pageController.active("login"));

        /* Click add event */
        btnAddEvent.setOnMouseClicked(mouseEvent -> {
            addEventBox.setVisible(true);
            hour = new SimpleDateFormat("HH").format(new Date());
            min = new SimpleDateFormat("mm").format(new Date());
            HH.setText(hour);
            MM.setText(min);
        });

        /* Click close add event box */
        closeAddEvent.setOnMouseClicked(mouseEvent -> addEventBox.setVisible(false));

        /* Click done on add event box */
        doneAddEvent.setOnMouseClicked(mouseEvent -> {
            inputHH = HH.getText();
            inputMM = MM.getText();

            if (checkTimeFormat()) {
                textTodayEvent = textAreaEvent.getText();
                inputHH = "0" + inputHH;
                inputMM = "0" + inputMM;
                timeTodayEvent = inputHH.substring(inputHH.length() - 2) + ":" + inputMM.substring(inputMM.length() - 2);

//                String timeStr = inputHH.substring(inputHH.length() - 2) + inputMM.substring(inputMM.length() - 2);
//                String dayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
//                //DayStory.addDay(entity,activeAcc.getDiary());
//                Note.addNote(entity, activeAcc.getDiary(), textTodayEvent,dayStr , timeStr);

                index = paneEvent.size();
                page = index / 6;

                NotePane newNotePane = new NotePane(index, inputHH, inputMM, textTodayEvent, timeTodayEvent);
                paneEvent.add(newNotePane);

                objCreateEvent = ((NotePane) paneEvent.get(index)).getNoteBox();
                GridPane.setMargin(objCreateEvent, new Insets(12, 7, 12, 7));

                textAreaEvent.clear();
                addEventBox.setVisible(false);

                if (index % 6 == 0) {
                    createPage(page);
                    pageBox[page].add(objCreateEvent, 0, 0);
                } else if (index % 6 == 1) {
                    pageBox[page].add(objCreateEvent, 1, 0);
                } else if (index % 6 == 2) {
                    pageBox[page].add(objCreateEvent, 0, 1);
                } else if (index % 6 == 3) {
                    pageBox[page].add(objCreateEvent, 1, 1);
                } else if (index % 6 == 4) {
                    pageBox[page].add(objCreateEvent, 0, 2);
                } else {
                    pageBox[page].add(objCreateEvent, 1, 2);
                }
                System.out.println(paneEvent.size() + " " + index);
                pag.setCurrentPageIndex(page);
                /* -------------TA!!!!!!! ------------- */
            }
        });

        /* Click on event */
        btnEvent0.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex() * 6);

            if (index >= objectNum) {
                showEdit(objectNum);
            }
        });

        btnEvent1.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex() * 6) + 1;

            if (index >= objectNum) {
                showEdit(objectNum);
            }
        });

        btnEvent2.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex() * 6) + 2;

            if (index >= objectNum) {
                showEdit(objectNum);
            }
        });

        btnEvent3.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex() * 6) + 3;

            if (index >= objectNum) {
                showEdit(objectNum);
            }
        });

        btnEvent4.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex() * 6) + 4;

            if (index >= objectNum) {
                showEdit(objectNum);
            }
        });

        btnEvent5.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex() * 6) + 5;

            if (index >= objectNum) {
                showEdit(objectNum);
            }
        });

        /* Click close edit event box */
        closeEditEvent.setOnMouseClicked(mouseEvent -> editEventBox.setVisible(false));

        /* Click done on edit event box */
        doneEditEvent.setOnMouseClicked(mouseEvent -> {
            inputHH = HHedit.getText();
            inputMM = MMedit.getText();

            if (checkTimeFormat()) {
                textTodayEvent = textAreaEventEdit.getText();
                ((NotePane) paneEvent.get(objectNum)).setInputHH(inputHH);
                ((NotePane) paneEvent.get(objectNum)).setInputMM(inputMM);
                inputHH = "0" + inputHH;
                inputMM = "0" + inputMM;
                timeTodayEvent = inputHH.substring(inputHH.length() - 2) + ":" + inputMM.substring(inputMM.length() - 2);
                ((NotePane) paneEvent.get(objectNum)).setTextTodayEvent(textTodayEvent);
                ((NotePane) paneEvent.get(objectNum)).setTimeTodayEvent(timeTodayEvent);

                objCreateEvent = ((NotePane) paneEvent.get(objectNum)).getNoteBox();
                GridPane.setMargin(objCreateEvent, new Insets(12, 7, 12, 7));

                textAreaEventEdit.clear();
                editEventBox.setVisible(false);

                if (objectNum % 6 == 0) {
                    pageBox[page].add(objCreateEvent, 0, 0);
                } else if (objectNum % 6 == 1) {
//                    pageBox[page].getChildren().clear();
                    pageBox[page].add(objCreateEvent, 1, 0);
                } else if (objectNum % 6 == 2) {
                    pageBox[page].add(objCreateEvent, 0, 1);
                } else if (objectNum % 6 == 3) {
                    pageBox[page].add(objCreateEvent, 1, 1);
                } else if (objectNum % 6 == 4) {
                    pageBox[page].add(objCreateEvent, 0, 2);
                } else {
                    pageBox[page].add(objCreateEvent, 1, 2);
                }
            }
        });
    }

    @Override
    public void onActive() {
        addEventBox.setVisible(false);
        editEventBox.setVisible(false);
        popupDel.setVisible(false);
    }

    private GridPane createPage(int pageIndex) {
        if (emptyPag[pageIndex]) {
            pageBox[pageIndex] = new GridPane();
            pageBox[pageIndex].setPrefHeight(299.0);
            pageBox[pageIndex].setPrefWidth(413.0);
            emptyPag[pageIndex] = false;

            pagPane.getChildren().add(pageBox[pageIndex]);
        }
        return pageBox[pageIndex];
    }

    private void showEdit(int objectNum) {
        editEventBox.setVisible(true);

        hourEdit = (((NotePane) paneEvent.get(objectNum)).getInputHH());
        hourEdit = hourEdit.substring(hourEdit.length() - 2);
        minEdit = ((NotePane) paneEvent.get(objectNum)).getInputMM();
        minEdit = minEdit.substring(minEdit.length() - 2);
        textTodayEvent = ((NotePane) paneEvent.get(objectNum)).getTextTodayEvent();

        HHedit.setText(hourEdit);
        MMedit.setText(minEdit);
        textAreaEventEdit.setText(textTodayEvent);
    }

    private boolean checkTimeFormat() {
        int intInputHH = 0;
        for (int i = 0; i < inputHH.length(); i++) {
            intInputHH += ((inputHH.charAt(i) - 48) * (int) (Math.pow(10, inputHH.length() - 1 - i)));
        }
        if (intInputHH >= 0 && intInputHH < 24) {
            int intInputMM = 0;
            for (int i = 0; i < inputMM.length(); i++) {
                intInputMM += ((inputMM.charAt(i) - 48) * (int) (Math.pow(10, inputMM.length() - 1 - i)));
            }
            return intInputMM >= 0 && intInputMM < 60;
        }
        return false;
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
}