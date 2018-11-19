import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
public class Day implements Serializable {

    // EntityDiary : ObjectDB //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Day;

    public int getId() {
        return id_Day;
    }


    private int id_Diary;

    public int getId_Diary() {
        return id_Diary;
    }
    // EntityDiary : ObjectDB //

    private int dayID;
    private String dayStr;
    private List<Note> stackOfNote;
    private int noteIDGenerator;

    public Day(int id_Diary, String dayStr, int dayID) {
        this.dayStr = dayStr;
        this.dayID = dayID;
        this.stackOfNote = new ArrayList<>();
        this.noteIDGenerator = 1;
        this.id_Diary = id_Diary;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public List<Note> getStackOfNote() {
        return stackOfNote;
    }

    public void setStackOfNote(List<Note> stackOfNote) {
        this.stackOfNote = stackOfNote;
    }

    public int getNoteIDGenerator() {
        return noteIDGenerator;
    }

    public void setNoteIDGenerator(int noteIDGenerator) {
        this.noteIDGenerator = noteIDGenerator;
    }

    // Method //

    public static Day addDay(EntityDiary entity, Diary diary) {
        int diaryID = diary.getDiaryID();
        int dayID = diary.getDayIDGenerator();
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        Day dp = entity.addDay(diaryID, dayStr, dayID);
        diary.increaseDayID();
        System.out.println("DayID " + dayID + " >> Added" + "\n");
        return dp;
    }

    public static boolean isDayInList(Diary diary, int dayID) {
        int index = 0;
        if (index < diary.getStackOfDay().size()) {
            for (Day i : diary.getStackOfDay()) {
                if (i.getDayID() == dayID) {
                    System.out.println("DayID " + dayID + " >> Found" + "\n");
                    return true;
                }
                index++;
            }
        }
        System.out.println("DayID " + dayID + " >> Didn't Found" + "\n");
        return false;
    }

    public static void deleteDay(EntityDiary entity, Diary diary, int dayID) {
        int index = 0;
        for (Day i : diary.getStackOfDay()) {
            if (i.getId() == dayID) {
                break;
            }
            index++;
        }
        diary.getStackOfDay().remove(index);
        entity.deleteDay(diary.getId(), dayID);
    }

    public void increaseNoteID() {
        this.noteIDGenerator++;
    }

    @Override
    public String toString() {
        return "\n\t-> Day" + "   Day ID: " + this.dayID + "\n\t"
                + this.stackOfNote + "\n\n";
    }
}
