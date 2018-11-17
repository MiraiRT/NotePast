//import javafx.scene.chart.PieChart;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//public class Database {
//    private static EntityManagerFactory emf;
//    private static EntityManager em;
//
//    public Database() {
//    }
//
//    public static void openConnection(){
//        emf = Persistence.createEntityManagerFactory("database/data.odb");
//        em = emf.createEntityManager();
//    }
//
//    public static void closeConnection(){
//        em.close();
//        emf.close();
//    }
//
//    public static Event addEvent(int npID,int dpID,int eventID,String day,String time,String text){
//        em.getTransaction().begin();
//        Event newEvent = new Event(eventID,day,time,text);
//        em.persist(newEvent);
//        em.getTransaction().commit();
//
//        // Find Day Page in DB and add Event to stack //
//        em.getTransaction().begin();
//        String sql = "SELECT c FROM DayPage c Where c.id_DP =" + dpID + "";
//        TypedQuery<DayPage> query = em.createQuery(sql,DayPage.class);
//        List<DayPage> result = query.getResultList();
//        result.get(0).getStackOfEvent().add(newEvent);
//        em.getTransaction().commit();
//
//        // then Find NotePast in DB and add DayPage to stack //
//        em.getTransaction().begin();
//        List<DayPage> dayPage = query.getResultList();
//        String sql2 = "SELECT a FROM NotePast a Where a.id_NP = " + npID + "" ;
//        TypedQuery<NotePast> queryNP = em.createQuery(sql2,NotePast.class);
//        List<NotePast> resultNP = queryNP.getResultList();
//        String sql3 = "SELECT a FROM DayPage a Where a.id_NP = " + npID + "" ;
//        TypedQuery<DayPage> queryDP = em.createQuery(sql3,DayPage.class);
//        List<DayPage> resultDP = queryDP.getResultList();
//        resultNP.get(0).setStackOfDayPage(resultDP);
//        em.getTransaction().commit();
//        return newEvent;
//    }
//
//    public static void editEvent(int eventID,String time,String text){
//        em.getTransaction().begin();
//        String sql = "SELECT c FROM Event c Where c.id =" + eventID + "";
//        TypedQuery<Event> query = em.createQuery(sql, Event.class);
//        List<Event> result = query.getResultList();
//        result.get(0).setNoteText(text);
//        result.get(0).setTime(time);
//        em.getTransaction().commit();
//    }
//
//    public static void delEvent(int dpID,int eventID){
//        em.getTransaction().begin();
//        String sql = "SELECT c FROM Event c Where c.id =" + eventID + "";
//        TypedQuery<Event> query = em.createQuery(sql,Event.class);
//        List<Event> result = query.getResultList();
//        em.remove(result.get(0));
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        String sql2 = "SELECT c FROM DayPage c Where c.id_DP =" + dpID + "";
//        TypedQuery<DayPage> query2 = em.createQuery(sql2,DayPage.class);
//        List<DayPage> result2 = query2.getResultList();
//        em.refresh(result2.get(0));
//        em.getTransaction().commit();
//    }
//
//    public static Event getEvent(int id_EV){
//        String sql = "SELECT c FROM Event c Where c.id =" + id_EV + "";
//        TypedQuery<Event> query =em.createQuery(sql, Event.class);
//        List<Event> result = query.getResultList();
//        return result.get(0);
//    }
//
//    public static DayPage addDayPage(int id_NP,String day,int dayID){
//        em.getTransaction().begin();
//        DayPage newPage = new DayPage(id_NP,day,dayID);
//        em.persist(newPage);
//        em.getTransaction().commit();
//        // Find NotePast in DB and add DayPage to stack //
//        em.getTransaction().begin();
//        String sqlNP = "SELECT a FROM NotePast a Where a.id_NP =" + id_NP + "";
//        TypedQuery<NotePast> queryNP = em.createQuery(sqlNP,NotePast.class);
//        List<NotePast> resultNP = queryNP.getResultList();
//        resultNP.get(0).getStackOfDayPage().add(newPage);
//        em.getTransaction().commit();
//        return newPage;
//
//    }
//
//    public static NotePast createNotePast(int id_Acc){
//        em.getTransaction().begin();
//        NotePast notePast = new NotePast(id_Acc);
//        em.persist(notePast);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        String sql = "SELECT c FROM Account c Where c.id_Acc =" + id_Acc + "";
//        TypedQuery<Account> query = em.createQuery(sql,Account.class);
//        List<Account> result = query.getResultList();
//        result.get(0).getBook().add(notePast);
//        em.getTransaction().commit();
//        return notePast;
//    }
//
//    public static Account createAccount(String username, String password){
//        em.getTransaction().begin();
//        Account account = new Account(username, password);
//        em.persist(account);
//        em.getTransaction().commit();
//        return account;
//    }
//
//    public static boolean login(String username, String password){
//        String sql = "SELECT c FROM Account c Where c.username LIKE '" + username + "' AND c.password LIKE '" + password + "'";
//        TypedQuery<Account> query = em.createQuery(sql,Account.class);
//        List<Account> result = query.getResultList();
//        if(result.isEmpty()){
//            return false;
//        }
//        else return true;
//    }
//
//    public static boolean signUp(String username){
//        String sql = "SELECT c FROM Account c Where c.username LIKE '" + username + "'";
//        TypedQuery<Account> query = em.createQuery(sql, Account.class);
//        List<Account> result = query.getResultList();
//        if(result.isEmpty()){
//            return true;
//        }
//        else return false;
//    }
//
//    public static Account getAccount(String username){
//        String sql = "SELECT c FROM Account c Where c.username LIKE '" + username + "'";
//        TypedQuery<Account> query = em.createQuery(sql, Account.class);
//        List<Account> result = query.getResultList();
//        return result.get(0);
//    }
//}
