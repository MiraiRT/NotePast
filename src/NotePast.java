import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NotePast implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_NP;
    public int getId() {
        return id_NP;
    }

//    @OneToMany(mappedBy = "notePast")
    private List<DayPage> stackOfDayPage;

//    @OneToOne(mappedBy = "book")
//    public Account getAccount(){ account; }

    // REF ID of All Obj //
    private static int todayID = 0;

    // Call this Constructor = Create new Account //
    public NotePast(){
        this.stackOfDayPage = new ArrayList<>();
    }

    public static int getTodayID() {
        return todayID;
    }

    public static void setTodayID(int todayID) {
        NotePast.todayID = todayID;
    }

    public List<DayPage> getStackOfDayPage() {
        return stackOfDayPage;
    }

    public void setStackOfDayPage(List<DayPage> stackOfDayPage) {
        this.stackOfDayPage = stackOfDayPage;
    }

    @Override
    public String toString() {
        return  "<<--NotePast-->>" + "\n"
                +"Today ID:" + todayID + "\n"
                + this.stackOfDayPage + "\n<<------------>>\n";
    }
}
