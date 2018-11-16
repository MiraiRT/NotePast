import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Diary implements Serializable {

    // EntityDiary : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Diary;
    public int getId() {
        return id_Diary;
    }
    // EntityDiary : ObjectDB //

    private int diaryID;

    @OneToMany
    private List<Day> stackOfDay;
    private int dayID;

    // Constructor //
    public Diary(int accID){
        this.stackOfDay = new ArrayList<>();
        this.diaryID = accID;
        this.dayID = 1;
    }
    // Constructor //

    // Getter Setter //
    public int getDiaryID() {
        return diaryID;
    }

    public void setDiaryID(int diaryID) {
        this.diaryID = diaryID;
    }

    public List<Day> getStackOfDay() {
        return stackOfDay;
    }

    public void setStackOfDay(List<Day> stackOfDay) {
        this.stackOfDay = stackOfDay;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }
    // Getter Setter //

    public static Diary createNotePast(EntityDiary entity,int accID){
        Diary book = entity.createDiary(accID);
        return book;
    }

    public void increaseDayID(){
        this.dayID++;
    }

    public Day getLastDayInStack(){
        // It's mean -> Get Today //
        int lastIndex = this.stackOfDay.size()-1;
        return this.stackOfDay.get(lastIndex);
    }



    @Override
    public String toString() {
        return  "\n<<--Diary-->>" + "\n"
                + this.stackOfDay + "\n<<------------>>\n";
    }
}
