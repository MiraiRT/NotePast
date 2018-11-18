import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
public class Day implements Serializable {

    // EntityDiary : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String day;
    private List<Note> stackOfNote;
    private int noteID;

    public Day(int id_Diary, String day, int dayID){
        this.day = day;
        this.dayID = dayID;
        this.stackOfNote = new ArrayList<>();
        this.noteID = 1;
        this.id_Diary = id_Diary;
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

    public void setDay(String day) {
        this.day = day;
    }

    public List<Note> getStackOfNote() {
        return stackOfNote;
    }

    public void setStackOfNote(List<Note> stackOfNote) {
        this.stackOfNote = stackOfNote;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    // Method //

    public static Day addDay(EntityDiary entity, int diaryID, int dayID){
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        Day dp = entity.addDay(diaryID,dayStr,dayID);
        return dp;
    }

//    public static boolean isDayInList(Day day, int noteID){
//        int index = 0;
//        if (index < day.getStackOfNote().size()){
//            for(Note i : day.getStackOfNote()){
//                if(index < day.getStackOfNote().size()){
//                    return false;
//                }
//                if(i.getNoteID() == noteID) {
//                    return true;
//                }
//                index++;
//            }
//        }
//        return false;
//    }

    public static boolean isDayInList(Diary diary, int dayID){
        int index = 0;
        if (index < diary.getStackOfDay().size()){
            for(Day i : diary.getStackOfDay()){
                if(index >= diary.getStackOfDay().size()){
                    return false;
                }
                if(i.getDayID() == dayID) {
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    public static void deleteDay(EntityDiary entity,Diary diary, int dayID){
        int index = 0;
        for(Day i : diary.getStackOfDay()){
            if(i.getDayID() == dayID) {
                break;
            }
            index++;
        }
        diary.getStackOfDay().remove(index);
        System.out.println(diary);
        entity.deleteDay(diary.getId(),dayID);
    }

    public void increaseNoteID(){
        this.noteID++;
    }


    @Override
    public String toString() {
        return  "\n\t-> Day" + "   Day ID: " + this.dayID + "\n\t"
                + this.stackOfNote + "\n\n";
    }
}
