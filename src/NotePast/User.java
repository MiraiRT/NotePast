package NotePast;

import javax.persistence.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

//    public static void main(String[] args) {
//
//        EntityDiary entity = new EntityDiary();
//
//        String username = "Mirai";
//        String password = "Ta1234";
//
//        // Sign-up Section //
////        User.signup(entity, username, password);
////        DayStory.addDay(entity, User.getAccount(entity, username).getDiary());
//
//
//        if (User.authen(entity, username, password)) {
//            // Login Section //
//            User activeAcc = User.getAccount(entity, username);
//            Diary userDiary = activeAcc.getDiary();
//
//            DayStory.addDay(entity, userDiary);
//
////            String timeStr = new SimpleDateFormat("HHmm").format(new Date());
//
//            String timeStr = new SimpleDateFormat("HHmm").format(new Date());
//            String inputText = "I'm watching Anime @MyRoom .Bunny-Girl so fun!!!";
//            Note.addNote(entity, userDiary, inputText, timeStr);
//
//            timeStr = new SimpleDateFormat("HHmm").format(new Date());
//            inputText = "I go to @ECC with #Tik #Pom to do OOAD Assignment";
//            Note.addNote(entity, userDiary, inputText, timeStr);
//
//            timeStr = new SimpleDateFormat("HHmm").format(new Date());
//            inputText = "I'm so sleepy. Good Night another me!! #Mirai";
//            Note.addNote(entity, userDiary, inputText, timeStr);
//
////            DayStory.addDay(entity, userDiary);
////            DayStory.addDay(entity, userDiary);
////            DayStory.addDay(entity, userDiary);
////            DayStory.addDay(entity, userDiary);
////            DayStory.addDay(entity, userDiary);
////
////            // Test : Add/Edit/Delete Note //
////            int today = Integer.parseInt(userDiary.getToday().getDayStr());
////            String dayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
////            int newDay = Integer.parseInt(dayStr);
////
////            if (userDiary.getListOfDayStory().size() == 0 || newDay > today) {
////                DayStory.addDay(entity, userDiary);
////            }
//
//        } else {
//            System.out.println("This User Doesn't found");
//        }
//        entity.closeConnection();
//    }

}