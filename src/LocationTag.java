import java.util.List;

public class LocationTag extends Tag {
    public LocationTag(String place) {
        super.tagName = place;
    }

    @Override
    public String getTagName() {
        return super.getTagName();
    }

    @Override
    public void setTagName(String tagName) {
        super.setTagName(tagName);
    }

    @Override
    public List<Note> getStackOfNote() {
        return super.getStackOfNote();
    }

    @Override
    public void setStackOfNote(List<Note> stackOfNote) {
        super.setStackOfNote(stackOfNote);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
