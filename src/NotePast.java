import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class NotePast {
    private Account writer;
    private List<DayPage> stackOfDayPage = new ArrayList<>();

    // REF ID of All Obj //
    private static int todayID = 0;

    // Call this Constructor = Create new Account //
    public NotePast(Account writer){
        // Set Writer to Find Data in DB //
        this.writer = writer;

        // Generate DayID //
        todayID = DayPage.genDayID();
        // Add Start DayPage to Stack //
        this.stackOfDayPage.add(new DayPage(todayID));
    }

    public List<DayPage> getStackOfDayPage() {
        return stackOfDayPage;
    }

    public void addDayPageToStack(DayPage newDay){
        stackOfDayPage.add(newDay);
    }

    public static int getTodayID() {
        return todayID;
    }

    public static void setTodayID(int todayID) {
        NotePast.todayID = todayID;
    }
}
