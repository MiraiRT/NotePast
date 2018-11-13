import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

public class Account implements Serializable{
    private String userName;
    private String password;
    private NotePast book;
    private Searching searchBook;

    public Account(String userName,String password) {
        this.userName = userName;
        this.password = password;
        this.book = new NotePast();
        this.searchBook = new Searching();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public NotePast getBook() {
        return book;
    }

    public static boolean loginChecker(String userName, String password) {
        ArrayList<Account> user = new ArrayList<>();
        user = loadAllUser(user);

        for (Account i : user) {
            if (i.getUserName().equals(userName) && i.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean login(String userName, String password) {
        ArrayList<Account> user = new ArrayList<>();
        user = loadAllUser(user);

        for (Account i : user) {
            if (i.getUserName().equals(userName) && i.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRegister(Account newAcc) {
        ArrayList<Account> userList = new ArrayList<>();
        userList = loadAllUser(userList);
        System.out.println(userList);
        for (Account i : userList) {
            if (i.getUserName().equals(newAcc.getUserName())) {
                return false;
            }
        }
        return true;
    }

    public static void saveNewUser(Account newUser) {
        String dbPath ="database/user.db";
        try {
            FileOutputStream fileOut = new FileOutputStream(dbPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(newUser);
            objectOut.close();
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Account> loadAllUser(ArrayList<Account> userList) {
        String dbPath ="database/user.db";
        try {
            FileInputStream fileIn = new FileInputStream(dbPath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            userList = (ArrayList<Account>)objectIn.readObject();
            objectIn.close();
            fileIn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }


    @Override
    public String toString() {
        return "User: " + this.userName + "\n"
                + "Pass: " + this.password + "\n\n"
                + "NotePass: " + this.userName + "\n"
                + this.book + "\n";
    }

    public static void main(String[] args) {


        Account x = new Account("A","tatatata");
        if(Account.checkRegister(x)){
            Account.saveNewUser(x);
            System.out.println("Added " + x);
        }


//        x = new Account("B","tbtbtbtb");
//        if(checkRegister(x)){
//            saveNewUser(x);
//            System.out.println("Added " + x);
//        }
//
//        x = new Account("C","tctctctc");
//        if(checkRegister(x)){
//            saveNewUser(x);
//            System.out.println("Added " + x);
//        }
//
//        Account y;
//        if(loginChecker("A","tatatata")){
//            System.out.println("Login Success");
//        }







//        DayPage.createDayPage(x.getBook());
//        Event.addEvent(DayPage.getTodayPage(x.book),"Hi");
//        Event.addEvent(DayPage.getTodayPage(x.book),"Hi2");
//        System.out.println(x);


    }
}

