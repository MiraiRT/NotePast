import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event implements Serializable {

    // Database : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() {
        return id;
    }
    // Database : ObjectDB //

    private int eventID;
    private String day;
    private String time;
    private String noteText;
    private List<Tag> eventTag;

    public Event(int eventID,String day,String time,String noteText){
        this.day = day;
        this.time = time;
        this.eventID = eventID;
        this.noteText = noteText;
        this.eventTag = new ArrayList<>();
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
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

    public List<Tag> getEventTag() {
        return eventTag;
    }

    public void setEventTag(List<Tag> eventTag) {
        this.eventTag = eventTag;
    }

    public static Event addEvent(int npID,int dpID,int eventID,String noteText){
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String timeStr = (new SimpleDateFormat("HHmmss")).format(new Date());
        Event newEvent = Database.addEvent(npID,dpID,eventID,dayStr,timeStr,noteText);
        return newEvent;
    }

    public static boolean isEventInList(DayPage dp, int eventID){
        int index = 0;
        if (index < dp.getStackOfEvent().size()){
            for(Event i : dp.getStackOfEvent()){
                if(index < dp.getStackOfEvent().size()){
                    return false;
                }
                if(i.getEventID() == eventID) {
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    public static void editEvent(DayPage dp, int eventID,String time,String noteText){
        int index = 0;
        for(Event i : dp.getStackOfEvent()){
            if(i.getEventID() == eventID) {
                break;
            }
            index++;
        }
        Event event = dp.getStackOfEvent().get(index);
        event.setTime(time);
        event.setNoteText(noteText);
        Database.editEvent(dp.getId(),time,noteText);
    }

    public static void deleteEvent(DayPage dp, int eventID){
        int index = 0;
        for(Event i : dp.getStackOfEvent()){
            if(i.getEventID() == eventID) {
                break;
            }
            index++;
        }
        dp.getStackOfEvent().remove(index);
        Database.delEvent(dp.getId(),eventID);

    }

    public String toString() {
        return  "\n\t\t=> Event\n\t\t"
                + "Day: " + this.day + " Time: " + this.time + "\n\t\t"
                + "ID1: " + this.eventID + "\n\t\t"
                + "Text:" + this.noteText + "\n\t\t"
                + "Tag" + this.eventTag + "\n\t";
    }

}
