package fx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

class CreateEvent extends Pane {
    private int count;
    private String inputHH,inputMM, textTodayEvent, timeTodayEvent;
    private Label timeEvent, textEvent;


    public CreateEvent(int count, String inputHH, String inputMM, String textTodayEvent, String timeTodayEvent) {
        this.count = count;
        this.inputHH = inputHH;
        this.inputMM = inputMM;
        this.textTodayEvent = textTodayEvent;
        this.timeTodayEvent = timeTodayEvent;
    }

    public Pane getEvent() {
        Pane event = new Pane();
        event.setStyle(" -fx-background-color:white; -fx-background-radius: 5;");
        event.setPrefHeight(75.0);
        event.setPrefWidth(195);

        timeEvent = new Label(timeTodayEvent);
        timeEvent.setLayoutX(7.0);
        timeEvent.setLayoutY(6.0);
        timeEvent.setStyle("-fx-text-Fill:#1d1f1f");
        timeEvent.setFont(new Font("Segoe UI bold", 13));

        textEvent = new Label(textTodayEvent);
        textEvent.setFont(new Font("Segoe UI", 12));
        textEvent.setLayoutX(7.0);
        textEvent.setLayoutY(29.0);
        textEvent.setPrefSize(181.0, 38.0);
        textEvent.setWrapText(true);
        textEvent.setStyle("-fx-line-Spacing:3px; -fx-alignment:TOP_LEFT; -fx-content-Display:TOP; -fx-text-Fill:#1d1f1f");

        Image delImg = new Image(getClass().getResourceAsStream("../images/bin2.png"));
        ImageView delImgView = new ImageView(delImg);
        delImgView.setFitHeight(16);
        delImgView.setFitWidth(16);
        Button del = new Button( );
        del.setGraphic(delImgView);
        del.setStyle("-fx-background-color: transparent");
        del.setLayoutX(163);
        del.setLayoutY(4);

        event.getChildren().addAll(timeEvent, textEvent, del);

        return event;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInputHH() {
        return inputHH;
    }

    public void setInputHH(String inputHH) {
        this.inputHH = inputHH;
    }

    public String getInputMM() {
        return inputMM;
    }

    public void setInputMM(String inputMM) {
        this.inputMM = inputMM;
    }

    public String getTextTodayEvent() {
        return textTodayEvent;
    }

    public void setTextTodayEvent(String textTodayEvent) {
        this.textTodayEvent = textTodayEvent;
    }

    public String getTimeTodayEvent() {
        return timeTodayEvent;
    }

    public void setTimeTodayEvent(String timeTodayEvent) {
        this.timeTodayEvent = timeTodayEvent;
    }

    public Label getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(Label timeEvent) {
        this.timeEvent = timeEvent;
    }

    public Label getTextEvent() {
        return textEvent;
    }

    public void setTextEvent(Label textEvent) {
        this.textEvent = textEvent;
    }

}
