import java.util.ArrayList;
import java.util.List;

public abstract class Tag {
    public String tagName;
    private List<Event> stackOfEvent = new ArrayList<>();

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Event> getStackOfEvent() {
        return stackOfEvent;
    }

    public void setStackOfEvent(List<Event> stackOfEvent) {
        this.stackOfEvent = stackOfEvent;
    }

    @Override
    public String toString() {
        return "Tag: " + tagName + "\n"
                + "Event: " + stackOfEvent + "\n";
    }
}
