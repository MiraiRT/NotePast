import javax.persistence.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Account implements Serializable {

    // Database : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Acc;
    public int getId() {
        return id_Acc;
    }
    // Database : ObjectDB //

    private String username;
    private String password;
    private int accID;

    private static int numOfAcc = 0;

    @OneToOne
    private List<NotePast> book;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.book = new ArrayList<>();
        numOfAcc++;
        this.accID = numOfAcc;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public List<NotePast> getBook() {
        return book;
    }


    // Method //

    public static Account createAccount(String username,String password){
        Account acc = Database.createAccount(username,password);
        // Auto create NotePast //
        NotePast book = NotePast.createNotePast(acc.getAccID());
        return acc;
    }

    public NotePast getNotePass(){
        return this.book.get(0);
    }

    // Authen //


//    public static class CheckText{
//        public String name;
//        public CheckText(String name){
//            this.name = name;
//        }
//    }


    @Override
    public String toString() {
        return  "--------Account--------\n"
                + "User: " + this.username + "\n"
                + "Pass: " + this.password + "\n\n"
                + this.book + "\n-----------------------\n";
    }

    public static void main(String[] args) {

//        List<CheckText> l = new ArrayList<>();
//
//        l.add(new CheckText("000100"));
//        l.add(new CheckText("122500"));
//        l.add(new CheckText("005000"));
//
//        Collections.sort(l.);
//
//        System.out.println(l);



        Database.openConnection();
        // Create Account //
        Account newAcc = createAccount("Ta","Mirai");

        // Create 1st DayPage //
        DayPage.addDayPage(newAcc.getNotePass().getNotePastID(),newAcc.getNotePass().getDayID());
        newAcc.getNotePass().increaseDayID();

        // Create 1.1 Event //
        DayPage todayPage = newAcc.getNotePass().getLastDayPage();
        int npID = newAcc.getNotePass().getNotePastID();
        int dpID = todayPage.getDayID();
        int eventID = todayPage.getEventID();
        Event.addEvent(npID,dpID,eventID,"Hey Kids 1.1");
        newAcc.getNotePass().getLastDayPage().increaseEventID();

        // Create 1.2 Event //
        todayPage = newAcc.getNotePass().getLastDayPage();
        npID = newAcc.getNotePass().getNotePastID();
        dpID = todayPage.getDayID();
        eventID = todayPage.getEventID();
        Event.addEvent(npID,dpID,eventID,"Hey Kids 1.2");
        newAcc.getNotePass().getLastDayPage().increaseEventID();

        // Create 2nd DayPage //
        DayPage.addDayPage(newAcc.getNotePass().getNotePastID(),newAcc.getNotePass().getDayID());
        newAcc.getNotePass().increaseDayID();

        // Create 2.1 Event //
        todayPage = newAcc.getNotePass().getLastDayPage();
        npID = newAcc.getNotePass().getNotePastID();
        dpID = todayPage.getDayID();
        eventID = todayPage.getEventID();
        Event.addEvent(npID,dpID,eventID,"Hey Kids 2.1");
        newAcc.getNotePass().getLastDayPage().increaseEventID();

        //System.out.println(newAcc);
        //System.out.println(newAcc.getBook());

        Database.closeConnection();


    }
}

