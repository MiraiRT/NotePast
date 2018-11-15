import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
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

    public static boolean authen(String username, String password){
        return Database.login(username,password);
    }

    public static boolean signup(String username,String password){
        boolean isAvail = Database.signUp(username);
        if(isAvail) {
            Account.createAccount(username,password);
            return true;
        }
        return false;
    }

    public static Account getAccount (String username) {
        return Database.getAccount(username);
    }

    public NotePast getNotePass(){
        return this.book.get(0);
    }





    @Override
    public String toString() {
        return  "--------Account--------\n"
                + "User: " + this.username + "\n"
                + "Pass: " + this.password + "\n\n"
                + this.book + "\n-----------------------\n";
    }

    public static void main(String[] args) {

        Database.openConnection();
        // Create Account //
        createAccount("Ta","Mirai");



        String user = "Ta";
        String pass = "Mirai";
        Account activeAcc;

        if(Account.authen(user,pass)) {
            activeAcc = Account.getAccount(user);
        } else {
            while (true) {
                System.out.println("Tik");
            }
        }


        // Create 1st DayPage //
        DayPage.addDayPage(activeAcc.getNotePass().getNotePastID(),activeAcc.getNotePass().getDayID());
        activeAcc.getNotePass().increaseDayID();

        // Create 1.1 Event //
        DayPage todayPage = activeAcc.getNotePass().getLastDayPage();
        int npID = activeAcc.getNotePass().getNotePastID();
        int dpID = todayPage.getDayID();
        int eventID = todayPage.getEventID();
        Event.addEvent(npID,dpID,eventID,"Hey Kids 1.1");
        System.out.println(npID + " " + dpID + " " + eventID);
        activeAcc.getNotePass().getLastDayPage().increaseEventID();

        // Create 1.2 Event //
        todayPage = activeAcc.getNotePass().getLastDayPage();
        npID = activeAcc.getNotePass().getNotePastID();
        dpID = todayPage.getDayID();
        eventID = todayPage.getEventID();
        Event.addEvent(npID,dpID,eventID,"Hey Kids 1.2");
        System.out.println(npID + " " + dpID + " " + eventID);
        activeAcc.getNotePass().getLastDayPage().increaseEventID();

        Event.deleteEvent(todayPage,1);


        // Create 2nd DayPage //
        DayPage.addDayPage(activeAcc.getNotePass().getNotePastID(),activeAcc.getNotePass().getDayID());
        activeAcc.getNotePass().increaseDayID();

        // Create 2.1 Event //
        todayPage = activeAcc.getNotePass().getLastDayPage();
        npID = activeAcc.getNotePass().getNotePastID();
        dpID = todayPage.getDayID();
        eventID = todayPage.getEventID();
        Event.addEvent(npID,dpID,eventID,"Hey Kids 2.1");
        System.out.println(npID + " " + dpID + " " + eventID);
        activeAcc.getNotePass().getLastDayPage().increaseEventID();

        System.out.println(activeAcc);

        Database.closeConnection();


    }
}

