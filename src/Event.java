import java.util.ArrayList;
import java.util.List;

public class Event {
    private String time;
    private String eventID;
    private String noteText;
    private int numOfEvent = 0;
    private List<String> eventTag;



    public Event(String time,String eventID,String noteText){
        this.time = time;
        this.eventID = eventID;
        this.noteText = noteText;
        this.eventTag = new ArrayList<>();
        this.numOfEvent++;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getEventID () {
        int ID = 0;
        return ID;
    }

    public boolean addEvent(DayPage todayPage){
        // To do //
        return false;
    }

    public boolean editEvent(DayPage todayPage){
        // To do //
        return false;
    }

    public boolean deleteEvent(DayPage todayPage){
        // To do //
        return false;
    }

}
