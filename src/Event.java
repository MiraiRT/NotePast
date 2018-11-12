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
    private List<String> eventTag;

    public Event(String day,String time,String noteText){
        this.day = day;
        this.time = time;
        this.eventID = genEventID(time);
        this.eventID2 = numOfEvent;
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

    public static int genEventID (String time) {
        String str = time;
        int ID = 0;
        for(int i = 0; i<str.length(); i++) {
            ID = ID + ((str.charAt(i)-48)*(int)(Math.pow(10,str.length()-1-i)));
        }
        return ID;
    }

    public static boolean addEvent(DayPage todayPage){
        String day = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String time = (new SimpleDateFormat("HHmm")).format(new Date());
        todayPage.addEventToStack(new Event(day,time,"HI BITCH!!!"));
        return true;
    }

    public static boolean editEvent(DayPage todayPage){
        // To do //
        return false;
    }

    public static boolean deleteEvent(DayPage todayPage){
        // To do //
        return false;
    }

    public String toString() {
        return "ID1: " + eventID + "\n" + "ID2: " + eventID2 + "\n";
    }

    public static void main(String[] args) {
        String day = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String time = (new SimpleDateFormat("HHmm")).format(new Date());
        System.out.println(genEventID(time));
    }
}
