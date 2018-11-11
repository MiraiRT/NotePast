import java.util.Date;
import java.text.SimpleDateFormat;

public class NotePast {
    private Account writer;

    private static Date date = new Date();
    private static String day;
    private static String dayID;

    private DayPage today;

    public NotePast(Account writer){
        this.writer = writer;
        day = (new SimpleDateFormat("yyyy.MM.dd")).format(date);
        dayID = (new SimpleDateFormat("yyyyMMdd")).format(date);
        this.today = new DayPage(day,dayID);
    }




}
