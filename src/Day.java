import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
public class Day implements Serializable {

    // EntityDiary : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Day;
    public int getId() {
        return id_Day;
    }


    private int id_Diary;
    public int getId_Diary() {
        return id_Diary;
    }
    // EntityDiary : ObjectDB //

    private int dayID;
    private String day;
    private List<Note> stackOfNote;
    private int eventID;

    public Day(int id_Diary, String day, int dayID){
        this.day = day;
        this.dayID = dayID;
        this.stackOfNote = new ArrayList<>();
        this.eventID = 1;
        this.id_Diary = id_Diary;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Note> getStackOfNote() {
        return stackOfNote;
    }

    public void setStackOfNote(List<Note> stackOfNote) {
        this.stackOfNote = stackOfNote;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    // Method //

    public static Day addDayPage(EntityDiary entity,int npID, int dayID){
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        Day dp = entity.addDay(npID,dayStr,dayID);
        return dp;
    }

    public void increaseNoteID(){
        this.eventID++;
    }


    @Override
    public String toString() {
        return  "\n\t-> Day" + "   Day ID: " + this.dayID + "\n\t"
                + this.stackOfNote + "\n\n";
    }
}
