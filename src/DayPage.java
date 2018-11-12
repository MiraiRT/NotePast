import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class DayPage {
    private int dayID;
    private List<Event> stackOfEvent;

    public DayPage(int dayID){
        this.dayID = dayID;
        this.stackOfEvent = new ArrayList<>();
    }

    public int getDayID() {
        return dayID;
    }

    public List<Event> getStackOfEvent() {
        return stackOfEvent;
    }

    public void addEventToStack(Event newEvent){
        stackOfEvent.add(newEvent);
    }

    public static int genDayID() {
        // Get System Time -> Convert to String Format //
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        dayStr = dayStr.substring(2);
        int ID = 0;
        for(int i = 0; i<dayStr.length(); i++) {
            ID = ID + ((dayStr.charAt(i)-48)*(int)(Math.pow(10,dayStr.length()-1-i)));
        }
        return ID;
    }

    public static boolean createDayPage(NotePast user) {
        int ID = DayPage.genDayID();
        // Convert String to Int //
        if (ID > NotePast.getTodayID()) {
            // Add New DayPage (another day) to Stack //
            user.addDayPageToStack(new DayPage(ID));
            NotePast.setTodayID(ID);
            // DayPage Added Alert: ***Insert UI Here*** //
            return true;
        }
        // Create Today, Yesterday or Past -> Unexpected Case: ***Insert UI Here*** //
        return false;
    }

    public static boolean deleteDayPage(NotePast user,int targetID) {
        if (targetID < NotePast.getTodayID()) {
            int i = 0;
            while(true) {
                if(i >= user.getStackOfDayPage().size()){
                    // Didn't Found DayPage: ***Insert UI Here*** //
                    return false;
                }
                if(user.getStackOfDayPage().get(i).getDayID() == targetID){
                    // Found DayPage -> Confirm Delete: ***Insert UI Here*** //
                    user.getStackOfDayPage().remove(i);
                    // DayPage Removed: ***Update DB Here*** //
                    return true;
                }
                i++;
            }
        }
        // Delete Today and Tomorrow Case -> Unexpected Case: ***Insert UI Here*** //
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + dayID + "\n";
    }
}
