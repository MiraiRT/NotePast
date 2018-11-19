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

    public void addNewTag(Diary diary, Tag tag) {
        diary.getStackOfTag().add(tag);
    }

    public void deleteTag(Diary diary, Tag tag) {
        if (tag instanceof LocationTag) {
            for (int i = 0; i < diary.getStackOfTag().size(); i++) {
                if(diary.getStackOfTag().get(i) instanceof LocationTag) {
                    if (diary.getStackOfTag().get(i).getTagName() == tag.getTagName()){
                        diary.getStackOfTag().remove(i);
                        break;
                    }
                }
            }
        } else {

        }


    }

//    public Tag addNoteToTag(Diary diary, Note note, String tagName);
//
//    public void deleteNoteInTag();
//
//    public boolean isTagInList(Diary diary, int noteID);

    @Override
    public String toString() {
        return "Tag: " + tagName + "\n"
                + "Note: " + stackOfNote + "\n";
    }
}
