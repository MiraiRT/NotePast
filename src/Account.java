import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account implements Serializable {

    private String userName;
    private String password;
//    private NotePast book;
    @OneToMany(mappedBy = "account")
    private NotePast book = new NotePast();
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

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("C:/Users/HP/Downloads/objectdb-2.7.6/db/test05.odb");
        EntityManager em = emf.createEntityManager();

        Account x = new Account("A","tatatata");
        System.out.println(x);

//        Set<NotePast> noteset = new HashSet<NotePast>();
//        Set<DayPage> dpset = new HashSet<DayPage>();
//        Set<Event> evset = new HashSet<Event>();

//        NotePast a = new NotePast();

//        x.setNotePasts(a);

//        DayPage.createDayPage(x.getBook());
//        Event.addEvent(DayPage.getTodayPage(x.book),"Hi");
//        Event.addEvent(DayPage.getTodayPage(x.book),"Hi2");
//        System.out.println(x);

        em.getTransaction().begin();
        em.persist(x);
//        x.setNotePasts();

        em.getTransaction().commit();

        em.close();
        emf.close();

//
//
//        Event.addEvent(x.getBook().getStackOfDayPage().get(0),"A");
//        System.out.println(x.getUserName() + " " + x.getPassword());
//        System.out.println("\n" + x.getBook());
//        System.out.println(x.getBook().getStackOfDayPage());
//        System.out.println(x.getBook() + "\n");

    }
}

