import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Diary implements Serializable {

    // EntityDiary : ObjectDB //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Diary;

    public int getId() {
        return id_Diary;
    }
    // EntityDiary : ObjectDB //

    private int diaryID;

    @OneToMany
    private List<Day> stackOfDay;
    private List<Tag> stackOfTag;

    private int dayIDGenerator;

    // Constructor //
    public Diary(int accID) {
        this.stackOfDay = new ArrayList<>();
        this.stackOfTag = new ArrayList<>();
        this.diaryID = accID;
        this.dayIDGenerator = 1;
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

    public int getDayIDGenerator() {
        return dayIDGenerator;
    }

    public void setDayIDGenerator(int dayIDGenerator) {
        this.dayIDGenerator = dayIDGenerator;
    }

    public List<Tag> getStackOfTag() {
        return stackOfTag;
    }

    // Getter Setter //

    public static Diary createNotePast(EntityDiary entity, int accID) {
        Diary book = entity.createDiary(accID);
        return book;
    }

    public void increaseDayID() {
        this.dayIDGenerator++;
    }

    public Day getToday() {
        // It's mean -> Get Today //
        int lastIndex = this.stackOfDay.size() - 1;
        return this.stackOfDay.get(lastIndex);
    }

    @Override
    public String toString() {
        return "\n<<--Diary-->>" + "\n"
                + this.stackOfDay + "\n<<------------>>\n";
    }
}
