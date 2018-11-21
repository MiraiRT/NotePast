package NotePast;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static Note addNote(EntityDiary entity, Diary diary, String noteText,String dayStr,String timeStr) {
        int diaryID = diary.getId();
        int dayID = diary.getToday().getId();
        int noteID = diary.getToday().getNoteIDGenerator();
        Note newNote = entity.addNote(diaryID, dayID, noteID, dayStr, timeStr, noteText);
        diary.getToday().increaseNoteID();
        System.out.println("NoteID " + noteID + " >> Added" + "\n");
        return newNote;
    }

    public static boolean isNoteInList(Day day, int noteID) {
        int index = 0;
        if (index < day.getListOfNote().size()) {
            for (Note i : day.getListOfNote()) {
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

    public static void editNote(EntityDiary entity, Day day, int noteID, String time, String noteText) {
        int index = 0;
        for (Note i : day.getListOfNote()) {
            if (i.getNoteID() == noteID) {
                break;
            }
            index++;
        }
        Note note = day.getListOfNote().get(index);
        note.setTimeStr(time);
        note.setNoteText(noteText);
        entity.editNote(note.getId(), time, noteText);
        System.out.println("NoteID " + noteID + " >> Edited\n");
    }

    public static void deleteNote(EntityDiary entity, Day day, int noteID) {
        int index = 0;
        for (Note i : day.getListOfNote()) {
            if (i.getNoteID() == noteID) {
                break;
            }
            index++;
        }
        day.getListOfNote().remove(index);
        entity.deleteNote(day.getId(), noteID);
        System.out.println("NoteID " + noteID + " >> Deleted\n");
    }

    public String toString() {
        return "\n\t\t=> Note\n\t\t"
                + "Day: " + this.dayStr + " Time: " + this.timeStr + "\n\t\t"
                + "ID: " + this.noteID + "\n\t\t"
                + "Text: " + this.noteText + "\n\t";
    }

}
