import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class NotePast {

//    List<DayPage> dayPages = new ArrayList<DayPage>();

    @OneToMany(mappedBy = "notePast")
    private List<DayPage> stackOfDayPage = new ArrayList<DayPage>();

    @ManyToOne(optional=false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="ACC_ID", nullable=false)
    public Account account;

    // REF ID of All Obj //
    private static int todayID = 0;
//    @OneToMany(orphanRemoval=false)
//    Set<DayPage> dayPages;
//
//    public Set<DayPage> getDayPages() {
//        return dayPages;
//    }
//
//    public void setDayPages(Set<DayPage> dayPages) {
//        this.dayPages = dayPages;
//    }

    // Call this Constructor = Create new Account //
    public NotePast(){
        // Generate DayID //
        todayID = DayPage.genDayID();
        // Add Start DayPage to Stack //
        this.stackOfDayPage.add(new DayPage(todayID));
    }

    public List<DayPage> getStackOfDayPage() {
        return stackOfDayPage;
    }

    public void addDayPageToStack(DayPage newDay){
        this.stackOfDayPage.add(newDay);
    }

    public static int getTodayID() {
        return todayID;
    }

    public static void setTodayID(int todayID) {
        NotePast.todayID = todayID;
    }

    @Override
    public String toString() {
        return  "Today ID:" + todayID + "\n"
                + this.stackOfDayPage + "\n\n";
    }
}
