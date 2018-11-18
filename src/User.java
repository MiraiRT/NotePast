import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {

    // EntityDiary : ObjectDB //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_User;
    public int getId() {
        return id_User;
    }
    // EntityDiary : ObjectDB //

    private String username;
    private String password;
    private int userID;
    private List<PeopleTag> stackOfPeopleTag;
    private List<LocationTag> stackOfLocationTag;

    private static int numOfAcc = 0;

    @OneToOne
    private List<Diary> book;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.book = new ArrayList<>();
        numOfAcc++;
        this.userID = numOfAcc;
        this.stackOfLocationTag = new ArrayList<>();
        this.stackOfPeopleTag = new ArrayList<>();
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

    public List<PeopleTag> getStackOfPeopleTag() {
        return stackOfPeopleTag;
    }

    public void setStackOfPeopleTag(List<PeopleTag> stackOfPeopleTag) {
        this.stackOfPeopleTag = stackOfPeopleTag;
    }

    public List<LocationTag> getStackOfLocationTag() {
        return stackOfLocationTag;
    }

    public void setStackOfLocationTag(List<LocationTag> stackOfLocationTag) {
        this.stackOfLocationTag = stackOfLocationTag;
    }


    // Method //

    public static User createAccount(EntityDiary entity,String username, String password){
        User acc = entity.createUser(username,password);
        // Auto create Diary //
        Diary book = Diary.createNotePast(entity,acc.getUserID());
        return acc;
    }

    public static boolean authen(EntityDiary entity,String username, String password){
        return entity.login(username,password);
    }

    public static boolean signup(EntityDiary entity,String username,String password){
        boolean isAvail = entity.signUp(username);
        if(isAvail) {
            User.createAccount(entity,username,password);
            return true;
        }
        return false;
    }

    public static User getAccount (EntityDiary entity,String username) {
        return entity.getAccount(username);
    }

    public Diary getDiary(){
        return this.book.get(0);
    }





    @Override
    public String toString() {
        return  "--------User--------\n"
                + "User: " + this.username + "\n"
                + "Pass: " + this.password + "\n\n"
                + this.book + "\n-----------------------\n";
    }

    public static void main(String[] args) {

        EntityDiary entity = new EntityDiary();
        // Create User //
        createAccount(entity,"Ta","Mirai");

        String username = "Ta";
        String password = "Mirai";
        User activeAcc;

        if(User.authen(entity,username,password)) {
            activeAcc = User.getAccount(entity,username);
        } else {
            while (true) {
                System.out.println("Tik");
            }
        }


        // Create 1st Day //
        Day.addDay(entity,activeAcc.getDiary().getDiaryID(),activeAcc.getDiary().getDayID());
        activeAcc.getDiary().increaseDayID();

        // Create 1.1 Note //
        Day today = activeAcc.getDiary().getLastDayInStack();
        int npID = activeAcc.getDiary().getDiaryID();
        int dpID = today.getDayID();
        int eventID = today.getNoteID();
        Note.addNote(entity,npID,dpID,eventID,"Hey Kids 1.1");
        activeAcc.getDiary().getLastDayInStack().increaseNoteID();

        // Create 1.2 Note //
        today = activeAcc.getDiary().getLastDayInStack();
        npID = activeAcc.getDiary().getDiaryID();
        dpID = today.getDayID();
        eventID = today.getNoteID();
        Note.addNote(entity,npID,dpID,eventID,"Hey Kids 1.2");
        activeAcc.getDiary().getLastDayInStack().increaseNoteID();

        // Create 2nd Day //
        Day.addDay(entity,activeAcc.getDiary().getDiaryID(),activeAcc.getDiary().getDayID());
        activeAcc.getDiary().increaseDayID();

        // Create 2.1 Note //
        today = activeAcc.getDiary().getLastDayInStack();
        npID = activeAcc.getDiary().getDiaryID();
        dpID = today.getDayID();
        eventID = today.getNoteID();
        Note.addNote(entity,npID,dpID,eventID,"Hey Kids 2.1");
        activeAcc.getDiary().getLastDayInStack().increaseNoteID();


        if(Note.isNoteInList(today,1)) {
            Note.deleteNote(entity, activeAcc.getDiary().getLastDayInStack(), 1);
        }

        if(Day.isDayInList(activeAcc.getDiary(),2)) {
            Day.deleteDay(entity,activeAcc.getDiary(),2);
        }



        entity.closeConnection();

        System.out.println(activeAcc);




    }
}

