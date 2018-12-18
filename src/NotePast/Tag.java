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
        System.out.println(diaryID);
        System.out.println(diary);
        if (!entity.searchTag(diaryID, name, type)) {
            Tag newTag = entity.createTag(diaryID, name, type);
            diary.getListOfTag().add(newTag);
        }
        entity.addNoteinTag(diaryID, noteID, name, type);
    }

    public static List<Note> searchTag(String searchInput, Diary diary) {
        List<Tag> allTag = diary.getListOfTag();
        List<Note> result = new ArrayList<>();
        String searchWord;
        if (searchInput.substring(0, 1).equals("@") || searchInput.substring(0, 1).equals("#")) {
            searchWord = searchInput.substring(1);
            String searchType = searchInput.substring(0, 1);
            for (int i = 0; i < allTag.size(); i++) {
                Tag tag = allTag.get(i);
                if (searchWord.toLowerCase().equals(tag.getTagName().toLowerCase())
                        && searchType.equals(tag.getTagType())) {
                    for (Note n : tag.getListOfNote()) {
                        result.add(n);
                    }
                }
            }
        } else {
            searchWord = searchInput;
            for (int i = 0; i < allTag.size(); i++) {
                Tag tag = allTag.get(i);
                if (searchWord.toLowerCase().equals(tag.getTagName().toLowerCase())) {
                    for (Note n : tag.getListOfNote()) {
                        result.add(n);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "Tag: " + tagName + " (" + tagType + ")\n"
                + "Note: " + listOfNote + "\n";
    }
}
