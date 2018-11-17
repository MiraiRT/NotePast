package fx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

public class ControllerSummary implements Controller {
    PageController pageController;
    Label dateSummary;

    public ControllerSummary(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initilize() {
        Scene scene = pageController.getScene("summary");
        AnchorPane acp = (AnchorPane) scene.lookup("#acp");
        ScrollPane scp = (ScrollPane) scene.lookup("#scp");

        dateSummary = (Label) scene.lookup("#dateSummary");
        VBox eachDay = new VBox();
        Label time = new Label();
        Label text = new Label();
        Button bt1 = new Button("Button One");
        Button bt2 = new Button("Button One");
        Button bt3 = new Button("Button One");
        Button bt4 = new Button("Button One");
        Button bt5 = new Button("Button One");
        Button bt6 = new Button("Button One");
        Button bt7 = new Button("Button One");
        Button bt8 = new Button("Button One");
        Button bt9 = new Button("Button One");
        Button bt10 = new Button("Button One");
        Button bt11 = new Button("Button One");
        Button bt12 = new Button("Button One");
        Button bt13 = new Button("Button One");
        Button bt14 = new Button("Button One");
        Button bt15 = new Button("15151515");

        String dateSummaryDB = "!!!!!!!!!"; /* TA!!!!!!!!!!!!!!!!!!!!!!!!! */
        dateSummary.setText(dateSummaryDB);
        time.setText("09:45");
        text.setText("Miusov, as a man man of breeding and deilcacy, could not but feel some inwrd qualms, when he reached the Father Superior's with Ivan: he felt ashamed of havin lost his temper. He felt that he ought to have disdaimed that despicable wretch, Fyodor Pavlovitch, too much to have been upset by him in Father Zossima's cell, and so to have forgotten himself. &quot;Teh monks were not to blame, in any case,&quot; he reflceted, on the steps. &quot;And if they're decent people here (and the Father Superior, I understand, is a nobleman) why not be friendly and courteous withthem? I won't argue, I'll fall in with everything, I'll win them by politness, and show them that I've nothing to do with that Aesop, thta buffoon, that Pierrot, and have merely been takken in over this affair, just as they have.&quot;&#10;");

        eachDay.getChildren().addAll(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14);
        eachDay.getChildren().add(bt15);
        scp.setContent(eachDay);
    }

    @Override
    public void onActive() {
        Scene scene = pageController.getScene("summary");

    }
}
