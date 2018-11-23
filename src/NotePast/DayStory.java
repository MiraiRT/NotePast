package NotePast;

import javafx.collections.transformation.SortedList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;

@Entity
public class DayStory implements Serializable {

    // EntityDiary : ObjectDB //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_DayStory;

    public int getId() {
        return id_DayStory;
    }


    private int id_Diary;

    public int getId_Diary() {
        return id_Diary;
    }
    // EntityDiary : ObjectDB //

    private int dayID;
    private String dayStr;
    private List<Note> listOfNote;
    private int noteIDGenerator;

    public DayStory(int id_Diary, String dayStr, int dayID) {
        this.dayStr = dayStr;
        this.dayID = dayID;
        this.listOfNote = new ArrayList<>();
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

    public List<Note> getListOfNote() {
        return listOfNote;
    }

    public void setListOfNote(List<Note> listOfNote) {
        this.listOfNote = listOfNote;
    }

    public int getNoteIDGenerator() {
        return noteIDGenerator;
    }

    public void setNoteIDGenerator(int noteIDGenerator) {
        this.noteIDGenerator = noteIDGenerator;
    }

    // Method //

    public static DayStory addDay(EntityDiary entity, Diary diary) {
        int diaryID = diary.getDiaryID();
        int dayID = diary.getDayIDGenerator();
        String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        DayStory dp = entity.addDay(diaryID, dayStr, dayID);
        diary.increaseDayID();
        System.out.println("DayID " + dayID + " >> Added" + "\n");
        return dp;
    }

    public static boolean isDayStoryInList(Diary diary, int dayID) {
        int index = 0;
        if (index < diary.getListOfDayStory().size()) {
            for (DayStory i : diary.getListOfDayStory()) {
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

    public static void deleteDayStory(EntityDiary entity, Diary diary, int dayID) {
        int index = 0;
        for (DayStory i : diary.getListOfDayStory()) {
            if (i.getId() == dayID) {
                break;
            }
            index++;
        }
        diary.getListOfDayStory().remove(index);
        entity.deleteDay(diary.getId(), dayID);
    }

    public void increaseNoteID() {
        this.noteIDGenerator++;
    }

    public void summaryNote() {
        Collections.sort(listOfNote, (Note a1, Note a2) -> a1.getTimeStr().compareTo(a2.getTimeStr()) );
//        List<Note> l = new ArrayList<>();
//        //Collections.sort(listOfNote.);
//        for (Note i : listOfNote) {
//            if (l.size() == 0) {
//                l.add(i);
//            } else {
//                int toPush = Integer.parseInt(i.getTimeStr());
//                for (int j = 0; j < l.size(); j++) {
//                    int inList = Integer.parseInt(l.get(j).getTimeStr());
//                    System.out.println("inList :" + inList + " toPush :" + toPush);
//                    if(toPush < inList){
//                        l.add(j,i);
//                    } else if(toPush == inList){
//                        if(i.getNoteID() < l.get(j).getNoteID()){
//                            l.add(j,i);
//                        } else {
//                            l.add(j+1,i);
//                        }
//
//                    }
//                }
//                l.add(i);
//            }
//        }
//        listOfNote = l;
//        System.out.println(listOfNote);
    }

    @Override
    public String toString() {
        return "\n\t-> DayStory" + "   DayStory ID: " + this.dayID + "\n\t"
                + this.listOfNote + "\n\n";
    }
}
