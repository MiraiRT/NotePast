package fx;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

class DiaryPane extends Pane {
    private int index;
    private String dayStory;
    private Button dateDiary;

    public DiaryPane(int index, String dayStory) {
        this.index = index;
        this.dayStory = dayStory;
    }

    public Pane getDiaryBox() {
        Pane diaryBox = new Pane();
        diaryBox.setPrefHeight(55.0);
        diaryBox.setPrefWidth(413);

        dateDiary = new Button(dayStory);
        dateDiary.setPrefHeight(55.0);
        dateDiary.setPrefWidth(413);
        dateDiary.setStyle("-fx-background-color:white; -fx-background-radius: 0; -fx-border-color: #388ABD; -fx-border-width: 1");
        dateDiary.setStyle("-fx-text-Fill:#1d1f1f");
        dateDiary.setFont(new Font("Segoe UI bold", 15));

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
        return dayStory;
    }

    public void setDayStory(String dayStory) {
        this.dayStory = dayStory;
    }
}
