import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;

@Entity
public class Account implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    @OneToOne(mappedBy = "account")
    private NotePast book;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.book = new NotePast();
    }

    public String getUserName() {
        return username;
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
        String dbPath = "database/data.db";
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
        String dbPath = "database/data.db";

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory(dbPath);
        EntityManager em = emf.createEntityManager();

        Account oAcc = em.find(Account.class,"");

        em.close();
        emf.close();


        return userList;
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


        Account x = new Account("Mirai","TaKub09");

        DayPage.createDayPage(x.getBook());
        Event.addEvent(DayPage.getTodayPage(x.getBook()),"I do OOAD Project @ECC");
        System.out.println(x);

        Database.closeConnection();




//        Event.addEvent(DayPage.getTodayPage(x.getBook()),"I do OOAD Project @ECC");

//        Event a = new Event("14","555","Helloooo");
//        a.addEvent(DayPage.getTodayPage(x.getBook()),"I do OOAD Project @ECC");
//        System.out.println(x);



//        DayPage.createDayPage(x.getBook());
//        Event.addEvent(DayPage.getTodayPage(x.getBook()),"I do OOAD Project @ECC");
//        System.out.println(x);

    }
}

