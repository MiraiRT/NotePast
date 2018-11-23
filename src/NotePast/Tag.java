package NotePast;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Tag;

    private int id_Diary;
    public int getId_Diary() {
        return id_Diary;
    }


    private String tagName;
    private String tagType;
    private List<Note> listOfNote = new ArrayList<>();

    public Tag(int id_Diary, String tagName, String tagType) {
        this.tagName = tagName;
        this.tagType = tagType;
        this.id_Diary = id_Diary;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public List<Note> getListOfNote() {
        return listOfNote;
    }

    public void setListOfNote(List<Note> listOfNote) {
        this.listOfNote = listOfNote;
    }

    public static void addNoteToTag(EntityDiary entity, Diary diary, int noteID, String name, String type) {
        int diaryID = diary.getId();
        if (!entity.searchTag(diaryID, name, type)) {
            Tag newTag = entity.createTag(diaryID, name, type);
            diary.getListOfTag().add(newTag);
        }
        entity.addNoteinTag(diaryID, noteID, name, type);
    }

    @Override
    public String toString() {
        return "Tag: " + tagName + "\n"
                + "Note: " + listOfNote + "\n";
    }
}
