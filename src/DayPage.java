import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
public class DayPage implements Serializable {

    // Database : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() {
        return id;
    }
    // Database : ObjectDB //

    private int dayID;
    private String day;
    private List<Event> stackOfEvent;
    private int eventID;

    public DayPage(String day,int dayID){
        this.day = day;
        this.dayID = dayID;
        this.stackOfEvent = new ArrayList<>();
        this.eventID = 1;
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

    public List<Event> getStackOfEvent() {
        return stackOfEvent;
    }

    public void setStackOfEvent(List<Event> stackOfEvent) {
        this.stackOfEvent = stackOfEvent;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    // Method //

    public static DayPage addDayPage(int npID,int dayID){
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        DayPage dp = Database.addDayPage(npID,dayStr,dayID);
        return dp;
    }

    public void increaseEventID(){
        this.eventID++;
    }


    @Override
    public String toString() {
        return  "\n\t-> DayPage" + "   Day ID: " + this.dayID + "\n\t"
                + this.stackOfEvent + "\n\n";
    }
}
