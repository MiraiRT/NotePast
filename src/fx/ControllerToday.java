package fx;

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
import java.util.Date;

public class ControllerToday implements Controller {
    Scene scene;
    private PageController pageController;
    private Button btnToday, btnDiary, btnYearsAgo, btnLogout, btnAddEvent;
    private Button doneAddEvent, closeAddEvent, doneEditEvent, closeEditEvent;
    private Label dateToday;
    private Group addEventBox, editEventBox, popupDel;
    private TextArea textAreaEvent, textAreaEventEdit;
    private TextField HH, MM, HHedit, MMedit;
    private int page, objectNum, count = -1;
    private String inputHH, inputMM, hourEdit, minEdit;
    private String textTodayEvent, timeTodayEvent;
    private Pagination pag;
    private AnchorPane pagPane;
    private GridPane pageBox[] = new GridPane[10];
    private Boolean emptyPag[] = {true, true, true, true, true, true, true, true, true, true};
    private Pane btnEvent0, btnEvent1, btnEvent2, btnEvent3, btnEvent4, btnEvent5;
    private Pane delEvent0, delEvent1, delEvent2, delEvent3, delEvent4, delEvent5;
    private Pane paneEvent[] = new Pane[200];
    private Pane objCreateEvent = new Pane();

    private String day = new SimpleDateFormat("dd").format(new Date());
    private String month = new SimpleDateFormat("MM").format(new Date());
    private String year = new SimpleDateFormat("yyyy").format(new Date());
    private String hour;// = new SimpleDateFormat("HH").format(new Date());
    private String min;// = new SimpleDateFormat("mm").format(new Date());

    ControllerToday(PageController pageController) {
        this.pageController = pageController;
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

        btnEvent0 = (Pane) scene.lookup("#btnEvent0");
        btnEvent1 = (Pane) scene.lookup("#btnEvent1");
        btnEvent2 = (Pane) scene.lookup("#btnEvent2");
        btnEvent3 = (Pane) scene.lookup("#btnEvent3");
        btnEvent4 = (Pane) scene.lookup("#btnEvent4");
        btnEvent5 = (Pane) scene.lookup("#btnEvent5");
        delEvent0 = (Pane) scene.lookup("#delEvent0");
        delEvent1 = (Pane) scene.lookup("#delEvent1");
        delEvent2 = (Pane) scene.lookup("#delEvent2");
        delEvent3 = (Pane) scene.lookup("#delEvent3");
        delEvent4 = (Pane) scene.lookup("#delEvent4");
        delEvent5 = (Pane) scene.lookup("#delEvent5");

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

            if (checkTime()){
                textTodayEvent = textAreaEvent.getText();
                inputHH = "0" + inputHH;
                inputMM = "0" + inputMM;
                timeTodayEvent = inputHH.substring(inputHH.length()-2) + ":" + inputMM.substring(inputMM.length()-2);

                count++;
                page = count/6;

              //  createPage(page);


                paneEvent[count] = new CreateEvent(count, inputHH, inputMM, textTodayEvent, timeTodayEvent);
                objCreateEvent = ((CreateEvent) paneEvent[count]).getEvent();
                GridPane.setMargin(objCreateEvent, new Insets(12, 7, 12, 7));

                for(int i=0; i<=count; i++) {
                    System.out.println(paneEvent[i]);
                }
                textAreaEvent.clear();
                addEventBox.setVisible(false);

                if (count%6 == 0) {
                    createPage(page);
                    pageBox[page].add(objCreateEvent, 0, 0);
                } else if (count%6 == 1) {
                    pageBox[page].add(objCreateEvent, 1, 0);
                } else if (count%6 == 2) {
                    pageBox[page].add(objCreateEvent, 0, 1);
                } else if (count%6 == 3) {
                    pageBox[page].add(objCreateEvent, 1, 1);
                } else if (count%6 == 4) {
                    pageBox[page].add(objCreateEvent, 0, 2);
                } else {
                    pageBox[page].add(objCreateEvent, 1, 2);
                }

                pag.setCurrentPageIndex(page);
                /* -------------TA!!!!!!! ------------- */
            }
        });

        /* Click on event */
        btnEvent0.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex()*6);

            if (count >= objectNum){
                showEdit(objectNum);
            }
        });

        btnEvent1.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex()*6) + 1;

            if (count >= objectNum){
                showEdit(objectNum);
            }
        });

        btnEvent2.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex()*6) + 2;

            if (count >= objectNum){
                showEdit(objectNum);
            }
        });

        btnEvent3.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex()*6) + 3;

            if (count >= objectNum){
                showEdit(objectNum);
            }
        });

        btnEvent4.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex()*6) + 4;

            if (count >= objectNum){
                showEdit(objectNum);
            }
        });

        btnEvent5.setOnMouseClicked(mouseEvent -> {
            objectNum = (pag.getCurrentPageIndex()*6) + 5;

            if (count >= objectNum){
                showEdit(objectNum);
            }
        });

        /* Click close edit event box */
        closeEditEvent.setOnMouseClicked(mouseEvent -> editEventBox.setVisible(false));

        /* Click done on edit event box */
        doneEditEvent.setOnMouseClicked(mouseEvent -> {
            inputHH = HHedit.getText();
            inputMM = MMedit.getText();

            if (checkTime()){
                textTodayEvent = textAreaEventEdit.getText();
                ((CreateEvent) paneEvent[objectNum]).setInputHH(inputHH);
                ((CreateEvent) paneEvent[objectNum]).setInputMM(inputMM);
                inputHH = "0" + inputHH;
                inputMM = "0" + inputMM;
                timeTodayEvent = inputHH.substring(inputHH.length() - 2) + ":" + inputMM.substring(inputMM.length() - 2);
                ((CreateEvent) paneEvent[objectNum]).setTextTodayEvent(textTodayEvent);
                ((CreateEvent) paneEvent[objectNum]).setTimeTodayEvent(timeTodayEvent);

                objCreateEvent = ((CreateEvent) paneEvent[objectNum]).getEvent();
                GridPane.setMargin(objCreateEvent, new Insets(12, 7, 12, 7));

                textAreaEventEdit.clear();
                editEventBox.setVisible(false);

                if (objectNum%6 == 0) {
                    pageBox[page].add(objCreateEvent, 0, 0);
                } else if (objectNum%6 == 1) {
//                    pageBox[page].getChildren().clear();
                    pageBox[page].add(objCreateEvent, 1, 0);
                } else if (objectNum%6 == 2) {
                    pageBox[page].add(objCreateEvent, 0, 1);
                } else if (objectNum%6 == 3) {
                    pageBox[page].add(objCreateEvent, 1, 1);
                } else if (objectNum%6 == 4) {
                    pageBox[page].add(objCreateEvent, 0, 2);
                } else {
                    pageBox[page].add(objCreateEvent, 1, 2);
                }
            }
        });
    }

    @Override
    public void onActive() {

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

        hourEdit = (((CreateEvent) paneEvent[objectNum]).getInputHH());
        hourEdit = hourEdit.substring(hourEdit.length()-2);
        minEdit = ((CreateEvent) paneEvent[objectNum]).getInputMM();
        minEdit = minEdit.substring(minEdit.length()-2);
        textTodayEvent = ((CreateEvent) paneEvent[objectNum]).getTextTodayEvent();

        HHedit.setText(hourEdit);
        MMedit.setText(minEdit);
        textAreaEventEdit.setText(textTodayEvent);
    }

    private boolean checkTime() {
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

    private String convertMonth(String month){
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