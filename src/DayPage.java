import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class DayPage {
    private String day;
    private int numOfEvent = 0;
    private List stack;

    private static Date date = new Date();
    private static String time;
    private static String timeID;
    private static String dayID;

    public DayPage(String day,String dayID){
        this.day = day;
        this.dayID = dayID;
        this.stack = null;
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
            stack.add(new Event(time,timeID));
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
}
