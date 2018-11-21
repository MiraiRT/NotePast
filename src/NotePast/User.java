package NotePast;

import javax.persistence.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User implements Serializable {

    // EntityDiary : ObjectDB //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_User;

    public int getId() {
        return id_User;
    }
    // EntityDiary : ObjectDB //

    private String username;
    private String password;
    private int userID;

    private static int numOfAcc = 0;

    @OneToOne
    private List<Diary> book;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.book = new ArrayList<>();
        numOfAcc++;
        this.userID = numOfAcc;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Diary> getBook() {
        return book;
    }

    // Method //

    public static User createAccount(EntityDiary entity, String username, String password) {
        User acc = entity.createUser(username, password);
        // Auto create Diary //
        Diary.createNotePast(entity, acc.getId());
        return acc;
    }

    public static boolean authen(EntityDiary entity, String username, String password) {
        return entity.login(username, password);
    }

    public static boolean signup(EntityDiary entity, String username, String password) {
        boolean isAvail = entity.signUp(username);
        if (isAvail) {
            User.createAccount(entity, username, password);
            return true;
        }
        return false;
    }

    public static User getAccount(EntityDiary entity, String username) {
        return entity.getAccount(username);
    }

    public Diary getDiary() {
        return this.book.get(0);
    }


    @Override
    public String toString() {
        return "--------User--------\n"
                + "User: " + this.username + "\n"
                + "Pass: " + this.password + "\n\n"
                + this.book + "\n-----------------------\n";
    }

    public static void main(String[] args) {

        EntityDiary entity = new EntityDiary();

        User.signup(entity, "Ta1", "Mirai");
        User.signup(entity, "Ta2", "Mirai");
        User.signup(entity, "Ta3", "Mirai");
        User.signup(entity, "Ta4", "Mirai");
        User.signup(entity, "Ta5", "Mirai");



        String username = "Ta1";
        String password = "Mirai";


        if (User.authen(entity, username, password)) {
            User activeAcc = User.getAccount(entity, username);
            Diary userDiary = activeAcc.getDiary();

            Day.addDay(entity, userDiary);

            String dayStr = userDiary.getToday().getDayStr();
            String timeStr = new SimpleDateFormat("HHmmss").format(new Date());
            String inputText = "I wake up";
            Note.addNote(entity, userDiary, inputText,dayStr,timeStr);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            dayStr = userDiary.getToday().getDayStr();
            timeStr = new SimpleDateFormat("HHmmss").format(new Date());
            inputText = "I sleep";
            Note.addNote(entity, userDiary, inputText,dayStr,timeStr);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Day.addDay(entity, userDiary);

            dayStr = userDiary.getToday().getDayStr();
            timeStr = new SimpleDateFormat("HHmmss").format(new Date());
            inputText = "I do nothing";
            Note.addNote(entity, userDiary, inputText,dayStr,timeStr);

            int onSelectedNote = 1;
            if (Note.isNoteInList(userDiary.getToday(), onSelectedNote)) {
                //Note.deleteNote(entity, userDiary.getToday(), onSelectedNote);
                //Note.editNote(entity, userDiary.getToday(), onSelectedNote, "999999", "Hey Kids 4.0");
            }

            int onSelectedDay = 2;
            if (Day.isDayInList(userDiary, onSelectedDay)) {
                //Day.deleteDay(entity,userDiary,onSelectedDay);
            }

            System.out.println(activeAcc);

        } else {
            System.out.println("This User Doesn't found");
        }
        entity.closeConnection();

    }
}
