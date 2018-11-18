import java.util.ArrayList;
import java.util.List;

public abstract class Tag {
    public String tagName;
    private List<Note> stackOfNote = new ArrayList<>();

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Note> getStackOfNote() {
        return stackOfNote;
    }

    public void setStackOfNote(List<Note> stackOfNote) {
        this.stackOfNote = stackOfNote;
    }

    public abstract Tag addTag(User user,Note note,String name);

    public abstract void deleteTag();

    public abstract boolean isTagInList(User user, int noteID);

    @Override
    public String toString() {
        return "Tag: " + tagName + "\n"
                + "Note: " + stackOfNote + "\n";
    }
}
