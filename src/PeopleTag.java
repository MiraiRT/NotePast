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
    public List<Note> getStackOfNote() {
        return super.getStackOfNote();
    }

    @Override
    public void setStackOfNote(List<Note> stackOfNote) {
        super.setStackOfNote(stackOfNote);
    }

    @Override
    public String toString() {
        return "Tag: " + this.tagName + "\n"
                + "Note: " + super.getStackOfNote() + "\n";
    }
}
