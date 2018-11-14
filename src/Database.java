import javafx.scene.chart.PieChart;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Database {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public Database() {
    }

    public static void openConnection(){
        emf = Persistence.createEntityManagerFactory("database/allDB2.odb");
        em = emf.createEntityManager();
    }

    public static void closeConnection(){
        em.close();
        emf.close();
    }

    public static void addEvent(int id,String day,String time,String text){
        em.getTransaction().begin();
        Event newEvent = new Event(day,time,text);
        em.persist(newEvent);
        em.getTransaction().commit();

        // Find Day Page in DB and add Event to stack //
        em.getTransaction().begin();
        String sql = "SELECT c FROM DayPage c Where c.id =" + id + "";
        TypedQuery<DayPage> query = em.createQuery(sql,DayPage.class);
        List<DayPage> result = query.getResultList();
        result.get(0).getStackOfEvent().add(newEvent);
        em.getTransaction().commit();

        // then Find NotePast in DB and add DayPage to stack //
        em.getTransaction().begin();
        List<DayPage> dayPage = query.getResultList();
        String sqlNP = "SELECT a FROM NotePast a Where a.id_NP =" + id + "";
        TypedQuery<NotePast> queryNP = em.createQuery(sqlNP,NotePast.class);
        List<NotePast> resultNP = queryNP.getResultList();
        resultNP.get(0).setStackOfDayPage(dayPage);
        em.getTransaction().commit();
    }

//    public void setDayPage(int id, List<DayPage> dayPage){
//        em.getTransaction().begin();
//        String sqlNP = "SELECT c FROM NotePast Where c.id =" + id + "";
//        TypedQuery<NotePast> queryNP = em.createQuery(sqlNP,NotePast.class);
//        List<NotePast> resultNP = queryNP.getResultList();
//        resultNP.get(0).setStackOfDayPage(dayPage);
//        em.getTransaction().commit();
//    }

    public static void addDayPage(int id_NP,String day,int dayID){
        em.getTransaction().begin();
        DayPage newPage = new DayPage(day,dayID);
        em.persist(newPage);
        em.getTransaction().commit();
        // Find NotePast in DB and add DayPage to stack //
        em.getTransaction().begin();
        String sqlNP = "SELECT a FROM NotePast a Where a.id_NP =" + id_NP + "";
        TypedQuery<NotePast> queryNP = em.createQuery(sqlNP,NotePast.class);
        List<NotePast> resultNP = queryNP.getResultList();
        resultNP.get(0).getStackOfDayPage().add(newPage);
        em.getTransaction().commit();

    }

    public void delEvent(int id){
        em.getTransaction().begin();
        String sql = "SELECT c FROM Event c Where c.id =" + id + "";
        TypedQuery<Event> query = em.createQuery(sql,Event.class);
        List<Event> result = query.getResultList();
        em.remove(result.get(0));
        em.getTransaction().commit();
    }

    public static void createNotePast(int id_Acc){
        em.getTransaction().begin();
        NotePast notePast = new NotePast();
        em.persist(notePast);
        em.getTransaction().commit();
        em.getTransaction().begin();
        String sql = "SELECT c FROM Account c Where c.id_Acc =" + id_Acc + "";
        TypedQuery<Account> query = em.createQuery(sql,Account.class);
        List<Account> result = query.getResultList();
        result.get(0).getBook().add(notePast);
        em.getTransaction().commit();
    }

    public static void createAccount(String username, String password){
        em.getTransaction().begin();
        Account account = new Account(username, password);
        em.persist(account);
        em.getTransaction().commit();
    }
}
