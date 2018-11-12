import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class DayPage {
    private String dayStr;
    private int dayID;
    private int numOfEvent = 0;
    private List stackOfEvent;

    private static Date date = new Date();
    private static String time;
    private static String timeID;

    public DayPage(String dayStr,int dayID){
        this.dayStr = dayStr;
        this.dayID = dayID;
        this.stackOfEvent = null;
    }

    public int getDayID() {
        return dayID;
    }

    public int getNumOfEvent() {
        return numOfEvent;
    }

    public boolean createEvent() {
        /**
         *
         *
         Insert UI Here
         *
         *
         **/
        boolean isSave = true;
        if (isSave) {
            getCurrentTime();
            stackOfEvent.add(new Event(time,timeID));
            numOfEvent++;
            return true;
        } else {
            return false;
        }
    }

    private static void getCurrentTime() {
        time = (new SimpleDateFormat("HH:mm:ss")).format(date);
        timeID = (new SimpleDateFormat("HHmmss")).format(date);
    }

    public String toString() {
        return "Day: " + dayStr + " ID: " + dayID + "\n";
    }
}
