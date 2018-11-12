package fx;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerToday {
    private String day = new SimpleDateFormat("dd").format(new Date());
    private String month = new SimpleDateFormat("MM").format(new Date());
    private String year = new SimpleDateFormat("yyyy").format(new Date());
    private String hour = new SimpleDateFormat("HH").format(new Date());
    private String min = new SimpleDateFormat("mm").format(new Date());
    String inputHH;
    String inputMM;
    private int count = 0;

    private String convertMonth(String month){
        if (month.equals("01")) {
            return "JANUARY";
        } else if (month.equals("02")) {
            return "FEBUARY";
        } else if (month.equals("03")) {
            return "MARCH";
        } else if (month.equals("04")) {
            return "APRIL";
        } else if (month.equals("05")) {
            return "MAY";
        } else if (month.equals("06")) {
            return "JUNE";
        } else if (month.equals("07")) {
            return "JULY";
        } else if (month.equals("08")) {
            return "AUGUST";
        } else if (month.equals("09")) {
            return "SEPTEMBER";
        } else if (month.equals("10")) {
            return "OCTOBER";
        } else if (month.equals("11")) {
            return "NOVEMBER";
        } else if (month.equals("12")) {
            return "DECEMBER";
        } else return "ERROR";
    }

    @FXML
    private Label dateToday;
    public Group addBox;
    public TextField HH;
    public TextField MM;
    public TextArea textEvent;
    public Pane event1;
    public Label event1Text;
    public Label timeEvent1;

    @FXML
    private void initialize() {
        dateToday.setText(day + " " + convertMonth(month) + " " + year);
    }
    public void addEvent(MouseEvent mouseEvent) {
        addBox.setVisible(true);
        HH.setText(hour);
        MM.setText(min);
    }
    public void doneEvent(MouseEvent mouseEvent) {
        System.out.print("xxxxxxxxxxxxxxxxxx");
        checkHH();
    }


    public void checkHH() {
        inputHH = HH.getText();
        int intInputHH = 0;
        for (int i = 0; i < inputHH.length(); i++) {
            intInputHH = intInputHH + ((inputHH.charAt(i) - 48) * (int) (Math.pow(10, inputHH.length() - 1 - i)));
        }
        if (intInputHH >= 0 && intInputHH < 24) {
            checkMM();
        }
    }
    public void checkMM() {
        inputMM = MM.getText();
        int intInputMM = 0;
        for (int i = 0; i < inputMM.length(); i++) {
            intInputMM = intInputMM + ((inputMM.charAt(i) - 48) * (int) (Math.pow(10, inputMM.length() - 1 - i)));
        }
        if (intInputMM >= 0 && intInputMM < 60) {
            String event = textEvent.getText(); /*!!!!!!!!!!!!!!!!!!TA!!!!!!!!!!!!!!!!!!*/
            addBox.setVisible(false);
            event1.setVisible(true);
            event1Text.setText(event);
            timeEvent1.setText(inputHH + ":" + inputMM);
            count++;
        }
    }
}
