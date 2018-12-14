package fx;

import NotePast.Diary;
import NotePast.EntityDiary;
import NotePast.Note;
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
    private int page, objectNum, index = -1, onSelectedNote;
    private String inputHH, inputMM, hourEdit, minEdit;
    private String textTodayEvent, timeTodayEvent;
    private Pagination pag;
    private GridPane pageBox[] = new GridPane[10];
    private Boolean emptyPag[] = {true, true, true, true, true, true, true, true, true, true};
    private Pane btnEvent0, btnEvent1, btnEvent2, btnEvent3, btnEvent4, btnEvent5;
    private Button del, yesDel, noDel;
    private List<Pane> paneEvent = new ArrayList<>();
    private Pane objCreateEvent = new Pane();

    private String day = new SimpleDateFormat("dd").format(new Date());
    private String month = new SimpleDateFormat("MM").format(new Date());
    private String year = new SimpleDateFormat("yyyy").format(new Date());
    private String hour;
    private String min;

    EntityDiary entity = Main.entity;
    User activeAcc;
    Diary activeDiary;

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
        textAreaEventEdit = (TextArea) scene.lookup("#textAreaEventEdit");

        del = (Button) scene.lookup("#del");
        popupDel = (Group) scene.lookup("#popupDel");
        yesDel = (Button) scene.lookup("#yesDel");
        noDel = (Button) scene.lookup("#noDel");

        btnEvent0 = (Pane) scene.lookup("#btnEvent0");
        btnEvent1 = (Pane) scene.lookup("#btnEvent1");
        btnEvent2 = (Pane) scene.lookup("#btnEvent2");
        btnEvent3 = (Pane) scene.lookup("#btnEvent3");
        btnEvent4 = (Pane) scene.lookup("#btnEvent4");
        btnEvent5 = (Pane) scene.lookup("#btnEvent5");


        /* create pagination pane */
        pag.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

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
                inputHH = "0" + inputHH;
                inputMM = "0" + inputMM;
                timeTodayEvent = inputHH.substring(inputHH.length() - 2) + inputMM.substring(inputMM.length() - 2);

                String timeStr = timeTodayEvent;
                String inputText = textAreaEvent.getText();
                Note.addNote(entity, activeDiary, inputText, timeStr);

                eraseDayStoryPane();
                drawDayStoryPane();

                pag.setCurrentPageIndex(activeDiary.getToday().getListOfNote().size()/6);
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
                int tempPagenum = pag.getCurrentPageIndex();

                inputHH = "0" + inputHH;
                inputMM = "0" + inputMM;
                timeTodayEvent = inputHH.substring(inputHH.length() - 2) + inputMM.substring(inputMM.length() - 2);
                ((NotePane) paneEvent.get(objectNum)).setTextTodayEvent(textTodayEvent);
                ((NotePane) paneEvent.get(objectNum)).setTimeTodayEvent(timeTodayEvent);

                String timeStr = timeTodayEvent;
                String inputText = textAreaEventEdit.getText();

                onSelectedNote = activeDiary.getToday().getListOfNote().get(objectNum).getNoteID();
                Note.editNote(entity, activeDiary.getToday(), onSelectedNote, timeStr, inputText);

                eraseDayStoryPane();
                drawDayStoryPane();

                textAreaEventEdit.clear();
                editEventBox.setVisible(false);

                pag.setCurrentPageIndex(tempPagenum);
            }
        });

        del.setOnMouseClicked(mouseEvent -> {
            popupDel.setVisible(true);
        });

        yesDel.setOnMouseClicked(mouseEvent -> {
            popupDel.setVisible(false);
            editEventBox.setVisible(false);

            onSelectedNote = activeDiary.getToday().getListOfNote().get(objectNum).getNoteID();
            Note.deleteNote(entity, activeDiary.getToday(), onSelectedNote);

            eraseDayStoryPane();

            if (activeDiary.getToday().getListOfNote().size()%6 == 0) {
                if(activeDiary.getToday().getListOfNote().size()/6 != 0) {
                    emptyPag[activeDiary.getToday().getListOfNote().size() / 6] = true;
                }
                pag.setCurrentPageIndex(activeDiary.getToday().getListOfNote().size()/6 - 1);
            }

            drawDayStoryPane();
        });

        noDel.setOnMouseClicked(mouseEvent ->  popupDel.setVisible(false));

        /* Pagination */
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

    private GridPane createPage(int pageIndex) {
        if (emptyPag[pageIndex]) {
            pageBox[pageIndex] = new GridPane();
            pageBox[pageIndex].setPrefHeight(100.0);
            pageBox[pageIndex].setPrefWidth(413.0);
            emptyPag[pageIndex] = false;
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

    private void eraseDayStoryPane() {
        int i = 0;
        while (pageBox[i] != null){
            pageBox[i].getChildren().remove(objCreateEvent);
            pageBox[i].getChildren().clear();
            i++;
        }
    }

    private void drawDayStoryPane() {
        paneEvent.clear();
        for (int i = 0; i < activeDiary.getToday().getListOfNote().size(); i++) {
            Note note = activeDiary.getToday().getListOfNote().get(i);
            inputHH = note.getTimeStr().substring(0, 2);
            inputMM = note.getTimeStr().substring(2, 4);
            index = i;
            if (checkTimeFormat()) {
                timeTodayEvent = inputHH + ":" + inputMM;

                page = index / 6;

                NotePane newNotePane = new NotePane(index, inputHH, inputMM, note.getNoteText(), timeTodayEvent);
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
            }
        }
    }

    @Override
    public void onActive() {
        System.out.println("active today");
        activeAcc = ControllerLogin.activeAcc;
        activeDiary = ControllerLogin.activeDiary;

        addEventBox.setVisible(false);
        editEventBox.setVisible(false);
        popupDel.setVisible(false);

        for (int i =0; i < 10; i++) {
            emptyPag[i] = true;
        }
        if (activeDiary.getToday().getListOfNote() != null) {
            System.out.println("not null");
            drawDayStoryPane();
        }
        else {
            System.out.println("errorrrrrrrrrrrrrrrrrr");
        }

        pag.setCurrentPageIndex(0);


    }
}