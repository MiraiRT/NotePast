package NotePast;

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
    private List<Day> listOfDay;
    private List<Tag> listOfTag;

    private int dayIDGenerator;

    // Constructor //
    public Diary(int accID) {
        this.listOfDay = new ArrayList<>();
        this.listOfTag = new ArrayList<>();
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

    public List<Day> getListOfDay() {
        return listOfDay;
    }

    public void setListOfDay(List<Day> listOfDay) {
        this.listOfDay = listOfDay;
    }

    public int getDayIDGenerator() {
        return dayIDGenerator;
    }

    public void setDayIDGenerator(int dayIDGenerator) {
        this.dayIDGenerator = dayIDGenerator;
    }

    public List<Tag> getListOfTag() {
        return listOfTag;
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
        int lastIndex = this.listOfDay.size() - 1;
        return this.listOfDay.get(lastIndex);
    }

    @Override
    public String toString() {
        return "\n<<--Diary-->>" + "\n"
                + this.listOfDay + "\n<<------------>>\n";
    }
}
