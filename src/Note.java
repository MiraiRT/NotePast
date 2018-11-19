import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Note implements Serializable {

    // EntityDiary : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Note;
    public int getId() {
        return id_Note;
    }
    // EntityDiary : ObjectDB //

    private int noteID;
    private String dayStr;
    private String timeStr;
    private String noteText;
    private List<Tag> noteTag;

    public Note(int noteID, String dayStr, String timeStr, String noteText){
        this.dayStr = dayStr;
        this.timeStr = timeStr;
        this.noteID = noteID;
        this.noteText = noteText;
        this.noteTag = new ArrayList<>();
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

    public List<Tag> getNoteTag() {
        return noteTag;
    }

    public void setNoteTag(List<Tag> noteTag) {
        this.noteTag = noteTag;
    }

    public static Note addNote(EntityDiary entity,Diary diary, String noteText){
        int diaryID = diary.getId();
        int dayID = diary.getToday().getId();
        int noteID = diary.getToday().getNoteIDGenerator();
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String timeStr = (new SimpleDateFormat("HHmmss")).format(new Date());
        Note newNote = entity.addNote(diaryID,dayID,noteID,dayStr,timeStr,noteText);
        diary.getToday().increaseNoteID();
        System.out.println("NoteID "+ noteID + " >> Added" + "\n");
        return newNote;
    }

    public static boolean isNoteInList(Day day, int noteID){
        int index = 0;
        if (index < day.getStackOfNote().size()){
            for(Note i : day.getStackOfNote()){
                if(i.getNoteID() == noteID) {
                    System.out.println("NoteID "+ noteID + " >> Found" + "\n");
                    return true;
                }
                index++;
            }
        }
        System.out.println("NoteID "+ noteID + " >> Didn't Found" + "\n");
        return false;
    }

    public static void editNote(EntityDiary entity,Day day, int noteID, String time, String noteText){
        int index = 0;
        for(Note i : day.getStackOfNote()){
            if(i.getNoteID() == noteID) {
                break;
            }
            index++;
        }
        Note note = day.getStackOfNote().get(index);
        note.setTimeStr(time);
        note.setNoteText(noteText);
        entity.editNote(note.getId(),time,noteText);
        System.out.println("NoteID "+ noteID + " >> Edited\n");
    }

    public static void deleteNote(EntityDiary entity,Day day, int noteID){
        int index = 0;
        for(Note i : day.getStackOfNote()){
            if(i.getNoteID() == noteID) {
                break;
            }
            index++;
        }
        day.getStackOfNote().remove(index);
        entity.deleteNote(day.getId(),noteID);
        System.out.println("NoteID "+ noteID + " >> Deleted\n");
    }

    public String toString() {
        return  "\n\t\t=> Note\n\t\t"
                + "Day: " + this.dayStr + " Time: " + this.timeStr + "\n\t\t"
                + "ID: " + this.noteID + "\n\t\t"
                + "Text:" + this.noteText + "\n\t\t"
                + "Tag" + this.noteTag + "\n\t";
    }

}
