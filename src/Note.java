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
    private String day;
    private String time;
    private String noteText;
    private List<Tag> noteTag;

    public Note(int noteID, String day, String time, String noteText){
        this.day = day;
        this.time = time;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public static Note addNote(EntityDiary entity,int diaryID, int dayID, int noteID, String noteText){
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String timeStr = (new SimpleDateFormat("HHmmss")).format(new Date());
        Note newNote = entity.addNote(diaryID,dayID,noteID,dayStr,timeStr,noteText);
        return newNote;
    }

    public static boolean isNoteInList(Day day, int noteID){
        int index = 0;
        if (index < day.getStackOfNote().size()){
            for(Note i : day.getStackOfNote()){
                if(index >= day.getStackOfNote().size()){
                    return false;
                }
                if(i.getNoteID() == noteID) {
                    return true;
                }
                index++;
            }
        }
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
        note.setTime(time);
        note.setNoteText(noteText);
        entity.editNote(day.getId(),time,noteText);
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

    }

    public String toString() {
        return  "\n\t\t=> Note\n\t\t"
                + "Day: " + this.day + " Time: " + this.time + "\n\t\t"
                + "ID: " + this.noteID + "\n\t\t"
                + "Text:" + this.noteText + "\n\t\t"
                + "Tag" + this.noteTag + "\n\t";
    }

}
