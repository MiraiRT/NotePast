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
    public List<Note> getListOfNote() {
        return super.getListOfNote();
    }

    @Override
    public void setListOfNote(List<Note> listOfNote) {
        super.setListOfNote(listOfNote);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
