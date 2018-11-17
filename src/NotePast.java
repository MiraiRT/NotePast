//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class NotePast implements Serializable {
//
//    // Database : ObjectDB //
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id_NP;
//    public int getId() {
//        return id_NP;
//    }
//    // Database : ObjectDB //
//
//    private int notePastID;
//
//    @OneToMany
//    private List<DayPage> stackOfDayPage;
//    private int dayID;
//
//    // Constructor //
//    public NotePast(int accID){
//        this.stackOfDayPage = new ArrayList<>();
//        this.notePastID = accID;
//        this.dayID = 1;
//    }
//    // Constructor //
//
//    // Getter Setter //
//    public int getNotePastID() {
//        return notePastID;
//    }
//
//    public void setNotePastID(int notePastID) {
//        this.notePastID = notePastID;
//    }
//
//    public List<DayPage> getStackOfDayPage() {
//        return stackOfDayPage;
//    }
//
//    public void setStackOfDayPage(List<DayPage> stackOfDayPage) {
//        this.stackOfDayPage = stackOfDayPage;
//    }
//
//    public int getDayID() {
//        return dayID;
//    }
//
//    public void setDayID(int dayID) {
//        this.dayID = dayID;
//    }
//    // Getter Setter //
//
//    public static NotePast createNotePast(int accID){
//        NotePast book = Database.createNotePast(accID);
//        return book;
//    }
//
//    public void increaseDayID(){
//        this.dayID++;
//    }
//
//    public DayPage getLastDayPage(){
//        int lastIndex = this.stackOfDayPage.size()-1;
//        return this.stackOfDayPage.get(lastIndex);
//    }
//
//
//
//    @Override
//    public String toString() {
//        return  "\n<<--NotePast-->>" + "\n"
//                + this.stackOfDayPage + "\n<<------------>>\n";
//    }
//}
