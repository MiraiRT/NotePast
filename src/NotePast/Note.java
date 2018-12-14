package NotePast;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Note implements Serializable {

    // EntityDiary : ObjectDB //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Note;

    public int getId() {
        return id_Note;
    }
    // EntityDiary : ObjectDB //

    private int noteID;
    private String dayStr;
    private String timeStr;
    private String noteText;

    public Note(int noteID, String dayStr, String timeStr, String noteText) {
        this.dayStr = dayStr;
        this.timeStr = timeStr;
        this.noteID = noteID;
        this.noteText = noteText;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public static Note addNote(EntityDiary entity, Diary diary, String noteText, String timeStr) {
        int diaryID = diary.getId();
        int dayID = diary.getToday().getId();
        int noteID = diary.getToday().getNoteIDGenerator();
        String dayStr = diary.getToday().getDayStr();

        Note newNote = entity.addNote(diaryID, dayID, noteID, dayStr, timeStr, noteText);
        diary.getToday().increaseNoteID();
        Note.detectTag(entity, diary, newNote);

        System.out.println("NoteID " + noteID + " >> Added" + "\n");
        return newNote;
    }

    public static boolean isNoteInList(DayStory dayStory, int noteID) {
        int index = 0;
        if (index < dayStory.getListOfNote().size()) {
            for (Note i : dayStory.getListOfNote()) {
                if (i.getNoteID() == noteID) {
                    System.out.println("NoteID " + noteID + " >> Found" + "\n");
                    return true;
                }
                index++;
            }
        }
        System.out.println("NoteID " + noteID + " >> Didn't Found" + "\n");
        return false;
    }

    public static void editNote(EntityDiary entity, DayStory dayStory, int noteID, String time, String noteText) {
        int index = 0;
        for (Note i : dayStory.getListOfNote()) {
            if (i.getNoteID() == noteID) {
                break;
            }
            index++;
        }
        Note note = dayStory.getListOfNote().get(index);
        note.setTimeStr(time);
        note.setNoteText(noteText);
        entity.editNote(note.getId(), time, noteText);
        System.out.println("NoteID " + noteID + " >> Edited\n");
    }

    public static void deleteNote(EntityDiary entity, DayStory dayStory, int noteID) {
        int index = 0;
        for (Note i : dayStory.getListOfNote()) {
            if (i.getNoteID() == noteID) {
                break;
            }
            index++;
        }
        dayStory.getListOfNote().remove(index);
        entity.deleteNote(dayStory.getId(), noteID);
        System.out.println("NoteID " + noteID + " >> Deleted\n");
    }

    public static void detectTag(EntityDiary entity, Diary diary, Note newNote) {
        String[] tag = newNote.getNoteText().split(" ");
        for (int i = 0; i < tag.length; i++) {
            if (tag[i].substring(0, 1).equals("@") || tag[i].substring(0, 1).equals("#")) {
                String type;
                if (tag[i].substring(0, 1).equals("@")) {
                    type = "Location";
                } else {
                    type = "People";
                }

                String name = tag[i].substring(1);
                Tag.addNoteToTag(entity, diary, newNote.getId(), name, type);
            }
        }
    }

    public String toString() {
        return "\n\t\t=> Note\n\t\t"
                + "Day: " + this.dayStr + " Time: " + this.timeStr + "\n\t\t"
                + "ID: " + this.noteID + "\n\t\t"
                + "Text: " + this.noteText + "\n\t";
    }

}
