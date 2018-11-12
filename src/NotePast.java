import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class NotePast {
    private Account writer;
    private List<DayPage> stackOfDayPage = new ArrayList<>();
    private int dayID;

    // REF ID of All Obj //
    private static int todayID = 100000;


    public NotePast(Account writer){
        // Set Writer to Find Data in DB //
        this.writer = writer;

        // Generate DayID //
        this.dayID = genDayID();

        // Set New DayID //
        if (this.dayID > todayID) {
            todayID = this.dayID;
        }

        // Add Start DayPage //
        String dayFormat = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
        this.stackOfDayPage.add(new DayPage(dayFormat,this.dayID));
    }

    private int genDayID() {
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        int ID = 100000;
        for(int i = 2; i < dayStr.length(); i++) {
            ID = ID + ((dayStr.charAt(i)-48) * (int)(Math.pow(10,(dayStr.length()-1 - i))));
        }
        return ID;
    }

    public boolean createDayPage(NotePast user) {
        int ID = genDayID();
        if (ID > todayID) {
            String dayFormat = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
            user.stackOfDayPage.add(new DayPage(dayFormat,ID));
            todayID = ID;
            return true;
        }
        return false;
    }

    public boolean deleteDayPage(NotePast user,int targetID) {
        if (targetID < todayID) {
            int i = 0;
            while(true) {
                if(i >= user.stackOfDayPage.size()){
                    // Insert
                    return false;
                }
                if(user.stackOfDayPage.get(i).getDayID() == targetID){
                    user.stackOfDayPage.remove(i);
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<DayPage> l = new ArrayList<>();
        l.add(new DayPage("2561.10.10",611010));
        l.add(new DayPage("2561.10.11",611011));
        l.add(new DayPage("2561.10.12",611012));
        l.remove(1);
        System.out.println(l.get(1));

    }
}
