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
    private Button btnDiary, btnYearsAgo, btnLogout, btnAddEvent, doneAddEvent, closeAddEvent;
    Button del, yesDel, noDel;
    private Label dateToday;
    private Group addEventBox, popupDel;
    private TextArea textAreaEvent;
    private TextField HH, MM;
    private int page, pageIndex = -1, count = -1;
    String inputHH, inputMM;
    private Pagination pag;
    private AnchorPane pagPane;
    private GridPane pageBox[] = new GridPane[10], gridToday;
    private Boolean checkPag[] = {true, true, true, true, true};
    private Pane paneEvent[] = new Pane[20], objCreateEvent;

    private String day = new SimpleDateFormat("dd").format(new Date());
    private String month = new SimpleDateFormat("MM").format(new Date());
    private String year = new SimpleDateFormat("yyyy").format(new Date());
    private String hour = new SimpleDateFormat("HH").format(new Date());
    private String min = new SimpleDateFormat("mm").format(new Date());

    ControllerToday(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initilize() {
        scene = pageController.getScene("today");

        dateToday = (Label) scene.lookup("#dateToday");
        addEventBox = (Group) scene.lookup("#addEventBox");
        HH = (TextField) scene.lookup("#HH");
        MM = (TextField) scene.lookup("#MM");
        btnAddEvent = (Button) scene.lookup("#btnAddEvent");
        closeAddEvent = (Button) scene.lookup("#closeAddEvent");
        textAreaEvent = (TextArea) scene.lookup("#textAreaEvent");
        doneAddEvent = (Button) scene.lookup("#doneAddEvent");
        btnYearsAgo = (Button) scene.lookup("#btnYearsAgo");
        btnDiary = (Button) scene.lookup("#btnDiary");
        btnLogout = (Button) scene.lookup("#btnLogout");
        pag = (Pagination) scene.lookup("#pag");
        pagPane = (AnchorPane) scene.lookup("#pagPane");
        gridToday = (GridPane) scene.lookup("#gridToday");


//        pag.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
//        pag.setStyle("-fx-page-information-color:false");
        AnchorPane.setTopAnchor(pag, 0.0);
        AnchorPane.setLeftAnchor(pag, 0.0);
        AnchorPane.setRightAnchor(pag, 0.0);
        AnchorPane.setBottomAnchor(pag, 0.0);

        /* Pagination */
        pag.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
//                if(pageIndex==2){
                return createPage(pageIndex);
//                }
//                return null;
            }
        });



        /* show today date */
        dateToday.setText(day + " " + convertMonth(month) + " " + year);

        /* Click menu diary */
        btnDiary.setOnMouseClicked(mouseEvent -> pageController.active("diary"));

        /* Click menu yearsago */
        btnYearsAgo.setOnMouseClicked(mouseEvent -> pageController.active("yearsago"));

        /* Click menu logout */
        btnLogout.setOnMouseClicked(mouseEvent -> pageController.active("login"));

        /* Click add event */
        btnAddEvent.setOnMouseClicked(mouseEvent -> {
            addEventBox.setVisible(true);
            HH.setText(hour);
            MM.setText(min);
//            System.out.println(btnAddEvent.getParent());
        });

        /* Click close add event box */
        closeAddEvent.setOnMouseClicked(mouseEvent -> addEventBox.setVisible(false));

        /* Click done on add event box */
        doneAddEvent.setOnMouseClicked(mouseEvent -> {
            if (checkTime()){
                String textTodayEvent = textAreaEvent.getText();
                inputHH = "0" + inputHH;
                inputMM = "0" + inputMM;
                count++;
                page = count/6;

                createPage(page);
                paneEvent[count] = new CreateEvent(count, inputHH, inputMM, textTodayEvent);
                objCreateEvent = ((CreateEvent) paneEvent[count]).getEvent();
                GridPane.setMargin(objCreateEvent, new Insets(12, 7, 12, 7));

                textAreaEvent.clear();
                addEventBox.setVisible(false);

                if (count%6 == 0) {
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
                /* -------------TA!!!!!!! -------------
                 * sent String text TodayEvent, hourEvent, minuteEvent*/
            }
        });



    }

    @Override
    public void onActive() {

    }

    private GridPane createPage(int pageIndex) {
        if (checkPag[pageIndex]) {
            System.out.println(pageIndex);
            pageBox[pageIndex] = new GridPane();
            pageBox[pageIndex].setPrefHeight(299.0);
            pageBox[pageIndex].setPrefWidth(413.0);
            checkPag[pageIndex] = false;

            pagPane.getChildren().add(pageBox[pageIndex]);
        }
        return pageBox[pageIndex];
    }

//    private Pane createEvent(int count, String inputHH, String inputMM, String textTodayEvent){
//        Pane event = new Pane();
//        event.setStyle(" -fx-background-color:white; -fx-background-radius: 5;");
//        event.setPrefHeight(75.0);
//        event.setPrefWidth(195);
//
//        timeEvent = new Label(inputHH.substring(inputHH.length()-2) + ":" + inputMM.substring(inputMM.length()-2));
//        timeEvent.setLayoutX(7.0);
//        timeEvent.setLayoutY(6.0);
//        timeEvent.setStyle("-fx-text-Fill:#1d1f1f");
//        timeEvent.setFont(new Font("Segoe UI bold", 13));
//
//        textEvent = new Label(textTodayEvent);
//        textEvent.setFont(new Font("Segoe UI", 12));
//        textEvent.setLayoutX(7.0);
//        textEvent.setLayoutY(29.0);
//        textEvent.setPrefSize(181.0, 38.0);
//        textEvent.setWrapText(true);
//        textEvent.setStyle("-fx-line-Spacing:3px; -fx-alignment:TOP_LEFT; -fx-content-Display:TOP; -fx-text-Fill:#1d1f1f");
//
//        Label pageLabel = new Label("count: "+(count));
//        event.getChildren().addAll(timeEvent, textEvent);
//
//        return event;
//    }

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

    private boolean checkTime() {
        inputHH = HH.getText();
        int intInputHH = 0;
        for (int i = 0; i < inputHH.length(); i++) {
            intInputHH += ((inputHH.charAt(i) - 48) * (int) (Math.pow(10, inputHH.length() - 1 - i)));
        }
        if (intInputHH >= 0 && intInputHH < 24) {
            int intInputMM = 0;
            inputMM = MM.getText();
            for (int i = 0; i < inputMM.length(); i++) {
                intInputMM += ((inputMM.charAt(i) - 48) * (int) (Math.pow(10, inputMM.length() - 1 - i)));
            }
                return intInputMM >= 0 && intInputMM < 60;
        }
        return false;
    }
}
