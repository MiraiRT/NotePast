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
        emf = Persistence.createEntityManagerFactory("database/allDB.odb");
        em = emf.createEntityManager();
    }

    public static void closeConnection(){
        em.close();
        emf.close();
    }

    public void addEvent(int id,String day,String time,String text){
        em.getTransaction().begin();
        Event newEvent = new Event(day,time,text);
        em.persist(newEvent);
        em.getTransaction().commit();

        // Find Day Page in DB and add Event to stack //
        em.getTransaction().begin();
        String sql = "SELECT c FROM DayPage Where c.id =" + id + "";
        TypedQuery<DayPage> query = em.createQuery(sql,DayPage.class);
        List<DayPage> result = query.getResultList();
        result.get(0).getStackOfEvent().add(newEvent);
        em.getTransaction().commit();

        // then Find NotePast in DB and add DayPage to stack //
        em.getTransaction().begin();
        String sqlNP = "SELECT c FROM NotePast Where c.id =" + id + "";
        TypedQuery<NotePast> queryNP = em.createQuery(sqlNP,NotePast.class);
        List<NotePast> resultNP = queryNP.getResultList();
        //resultNP.get(0).getStackOfDayPage().get(DayPage.getTodayPage(resultNP.get(0)));
        em.getTransaction().commit();
    }

    public void addDayPage(String day,int dayID){
        em.getTransaction().begin();
        DayPage newPage = new DayPage(day,dayID);
        em.persist(newPage);
        em.getTransaction().commit();
        // Find NotePast in DB and add DayPage to stack //
    }
}
