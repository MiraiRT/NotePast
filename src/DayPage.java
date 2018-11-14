import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
public class DayPage implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() {
        return id;
    }

    private int dayID;
    private String day;
//    @OneToMany(mappedBy = "dayPage")
    private List<Event> stackOfEvent;

//    @ManyToOne(optional=false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="NotePast_ID")
//    public NotePast notePast;

    public DayPage(String day,int dayID){
        this.day = day;
        this.dayID = dayID;
        this.stackOfEvent = new ArrayList<>();
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public String getDay() {
        return day;
    }

    public List<Event> getStackOfEvent() {
        return stackOfEvent;
    }

    public void setStackOfEvent(List<Event> stackOfEvent) {
        this.stackOfEvent = stackOfEvent;
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

    public static boolean createDayPage(NotePast book) {
        int ID = DayPage.genDayID();

        if (ID > NotePast.getTodayID()) {
            String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
            NotePast.setTodayID(ID);
            book.getStackOfDayPage().add(new DayPage(dayStr,ID));
            System.out.println("\n\nNew Page Created\n\n");
            return true;
        }
        System.out.println("\n\nFailed to Create New Page\n\n");
        return false;
    }

    public static boolean deleteDayPage(NotePast book,int targetID) {
        if (targetID < NotePast.getTodayID()) {
            for(int i = 0; i < book.getStackOfDayPage().size(); i++) {
                if(book.getStackOfDayPage().get(i).getDayID() == targetID){
                    book.getStackOfDayPage().remove(i);
                    System.out.println("\n\nDeleted Select Page\n\n");
                    return true;
                }
            }
        }
        System.out.println("\n\nFailed to Delete Page\n\n");
        return false;
    }

    public static DayPage getTodayPage(NotePast book){
        int index = book.getStackOfDayPage().size()-1;
        return book.getStackOfDayPage().get(index);
    }

    @Override
    public String toString() {
        return  "\n\t-> DayPage" + "   Day ID: " + this.dayID + "\n\t"
                + this.stackOfEvent + "\n\n";
    }
}
