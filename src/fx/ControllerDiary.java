package fx;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.util.Callback;

public class ControllerDiary implements Controller {
    PageController pageController;
    private Scene scene;

    public ControllerDiary(PageController pageController) {
        this.pageController = pageController;
    }

    @Override
    public void initilize() {
        Scene scene = pageController.getScene("diary");

    }

    @Override
    public void onActive() {

    }
}
