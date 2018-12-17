package fx;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

class DiaryPane extends Pane {
    private int index;
    private String datedayStory;
    private StackPane dateDiary;

    public DiaryPane(int index, String dayStory) {
        this.index = index;
        this.datedayStory = dayStory;
    }

    public VBox getDiaryBox() {
        VBox diaryBox = new VBox();
        diaryBox.setPrefHeight(55);
        diaryBox.setPrefWidth(413);

        dateDiary = new StackPane();
        dateDiary.setPrefSize(413, 55.0);
//        dateDiary.setMinSize(413, 55.0);
        dateDiary.setStyle("-fx-background-color:white; -fx-background-radius: 0; -fx-border-color: #388ABD; -fx-border-width: 1");
        Label date = new Label(datedayStory);
        date.setStyle("-fx-text-Fill:#1d1f1f");
        date.setFont(new Font("Segoe UI bold", 15));
        dateDiary.getChildren().add(date);
        StackPane.setAlignment(date, Pos.CENTER);

        diaryBox.getChildren().add(dateDiary);

        return diaryBox;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDayStory() {
        return datedayStory;
    }

    public void setDayStory(String dayStory) {
        this.datedayStory = dayStory;
    }
}
