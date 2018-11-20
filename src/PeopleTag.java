import java.util.List;

public class PeopleTag extends Tag {
    public PeopleTag(String people) {
        super.tagName = people;
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
        return "Tag: " + this.tagName + "\n"
                + "Note: " + super.getListOfNote() + "\n";
    }
}
