package NotePast;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

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

    private String dayStr;
    private String timeStr;
    private String noteText;

    public Note(String dayStr, String timeStr, String noteText) {
        this.dayStr = dayStr;
        this.timeStr = timeStr;
        this.noteText = noteText;
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

    // Add New Note to Today Story //
    public static Note addNote(EntityDiary entity, Diary diary, String noteText, String timeStr) {
        int diaryID = diary.getId();
        int dayID = diary.getToday().getId();
        String dayStr = diary.getToday().getDayStr();

        Note newNote = entity.addNote(diaryID, dayID, dayStr, timeStr, noteText);
        Note.detectTag(entity, diary, newNote);

        System.out.println("NoteID " + newNote.getId() + " >> Added" + "\n");
        return newNote;
    }

    // Edit Note in Today Story //
    public static void editNote(EntityDiary entity, Diary diary, int noteID, String time, String noteText) {
        DayStory today = diary.getToday();
        int index = 0;
        for (Note i : today.getListOfNote()) {
            if (i.getId() == noteID) {
                break;
            }
            index++;
        }
        Note note = today.getListOfNote().get(index);
        note.setTimeStr(time);
        note.setNoteText(noteText);

        Note.detectTag(entity,diary,note);
        entity.editNote(note.getId(), time, noteText);


        System.out.println("NoteID " + noteID + " >> Edited\n");
    }

    // Delete Note in Today Story //
    public static void deleteNote(EntityDiary entity, DayStory dayStory, int noteID) {
        int index = 0;
        for (Note i : dayStory.getListOfNote()) {
            if (i.getId() == noteID) {
                break;
            }
            index++;
        }
        dayStory.getListOfNote().remove(index);
        entity.deleteNote(dayStory.getId(), noteID);
        System.out.println("NoteID " + noteID + " >> Deleted\n");
    }

    // Detect Tag (@,#) in new Note //
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
                + "ID: " + this.id_Note + "\n\t\t"
                + "Text: " + this.noteText + "\n\t";
    }

}
