package NotePast;

import java.util.ArrayList;
import java.util.List;

public abstract class Tag {
    public String tagName;
    private List<Note> listOfNote = new ArrayList<>();

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Note> getListOfNote() {
        return listOfNote;
    }

    public void setListOfNote(List<Note> listOfNote) {
        this.listOfNote = listOfNote;
    }

    public static void addNewTag(Diary diary, Tag tag) {
        diary.getListOfTag().add(tag);
    }

    public static void deleteTag(Diary diary, Tag tag) {
        if (tag instanceof LocationTag) {
            for (int i = 0; i < diary.getListOfTag().size(); i++) {
                if (diary.getListOfTag().get(i) instanceof LocationTag) {
                    if (diary.getListOfTag().get(i).getTagName() == tag.getTagName()) {
                        diary.getListOfTag().remove(i);
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < diary.getListOfTag().size(); i++) {
                if (diary.getListOfTag().get(i) instanceof PeopleTag) {
                    if (diary.getListOfTag().get(i).getTagName() == tag.getTagName()) {
                        diary.getListOfTag().remove(i);
                        break;
                    }
                }
            }
        }
    }

    public static void addNoteToTag(Diary diary, Note note, String tag) {
        List<Tag> list = diary.getListOfTag();
        char typeOfTage = tag.charAt(0);
        String tagName = tag.substring(1);
        // Check this Tag already Define //
        if (list.size() == 0) {
            // Add New Tag //
        } else {
            for (int i = 0; i < list.size(); i++) {
                boolean con1 = (list.get(i).getTagName() == tagName);
                boolean con2 = (list.get(i) instanceof PeopleTag);
                if (con1 && con2) {
                    // Add new Note to Stack //
                    list.get(i).getListOfNote().add(note);
                }
            }
        }
    }
//
//    public static void deleteNoteInTagList();
//
//    public static boolean isTagInList(Diary diary, int noteID);

    @Override
    public String toString() {
        return "Tag: " + tagName + "\n"
                + "Note: " + listOfNote + "\n";
    }
}
