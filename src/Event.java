import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    private String day;
    private String time;
    private int eventID;
    private int eventID2;
    private String noteText;

    private static int numOfEvent = 0;
    private List<Tag> eventTag;

    public Event(String day,String time,String noteText){
        this.day = day;
        this.time = time;
        this.eventID = genEventID(time);
        this.eventID2 = numOfEvent;
        this.noteText = noteText;
        this.eventTag = new ArrayList<>();
        this.numOfEvent++;
    }

    public int getEventID() {
        return eventID;
    }

    public int getEventID2() {
        return eventID2;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public static int genEventID (String time) {
        String str = time;
        int ID = 0;
        for(int i = 0; i<str.length(); i++) {
            ID = ID + ((str.charAt(i)-48)*(int)(Math.pow(10,str.length()-1-i)));
        }
        return ID;
    }

    public static boolean addEvent(DayPage todayPage,String noteText){
        String day = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String time = (new SimpleDateFormat("HHmm")).format(new Date());
        todayPage.addEventToStack(new Event(day,time,noteText));
        return true;
    }

    public static boolean editEvent(DayPage todayPage,int targetID,int targetID2){
        for(int i = 0; i< todayPage.getStackOfEvent().size();i++) {
            if (todayPage.getStackOfEvent().get(i).getEventID() == targetID && todayPage.getStackOfEvent().get(i).getEventID2() == targetID2) {
                // Change of Selected Event //
                return true;
            }
        }
        return false;
    }

    public static boolean deleteEvent(DayPage todayPage,int targetID,int targetID2){
        for(int i = 0; i< todayPage.getStackOfEvent().size();i++) {
            if (todayPage.getStackOfEvent().get(i).getEventID() == targetID && todayPage.getStackOfEvent().get(i).getEventID2() == targetID2) {
                todayPage.getStackOfEvent().remove(i);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return  "\nDay: " + this.day + " Time: " + this.time + "\n"
                + "ID1: " + this.eventID + "\n"
                + "ID2: " + this.eventID2 + "\n"
                + "Text:" + this.noteText + "\n"
                + "Tag" + this.eventTag + "\n";
    }

}
