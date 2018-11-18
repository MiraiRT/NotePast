import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EntityDiary {
    private EntityManagerFactory emf;
    private EntityManager em;

    public EntityDiary() {
        this.emf = Persistence.createEntityManagerFactory("database/data.odb");
        this.em = emf.createEntityManager();

    }

    public void closeConnection(){
        em.close();
        emf.close();
    }

    public Note addNote(int diaryID, int dayID, int noteID, String datStr, String time, String text){
        em.getTransaction().begin();
        Note newNote = new Note(noteID,datStr,time,text);
        em.persist(newNote);
        em.getTransaction().commit();

        // Find Day Page in DB and add Note to stack //
        em.getTransaction().begin();
        String sql = "SELECT c FROM Day c Where c.id_Day =" + dayID + "";
        TypedQuery<Day> query = em.createQuery(sql, Day.class);
        List<Day> result = query.getResultList();
        result.get(0).getStackOfNote().add(newNote);
        em.getTransaction().commit();

        // then Find Diary in DB and add Day to stack //
        em.getTransaction().begin();
        String sql2 = "SELECT a FROM Diary a Where a.id_Diary = " + diaryID + "" ;
        TypedQuery<Diary> queryDiary = em.createQuery(sql2, Diary.class);
        List<Diary> resultNP = queryDiary.getResultList();
        String sql3 = "SELECT a FROM Day a Where a.id_Diary = " + diaryID + "" ;
        TypedQuery<Day> queryDP = em.createQuery(sql3, Day.class);
        List<Day> resultDP = queryDP.getResultList();
        resultNP.get(0).setStackOfDay(resultDP);
        em.getTransaction().commit();
        return newNote;
    }

    public void editNote(int noteID, String time, String text){
        em.getTransaction().begin();
        String sql = "SELECT c FROM Note c Where c.id_Note =" + noteID + "";
        TypedQuery<Note> query = em.createQuery(sql, Note.class);
        List<Note> result = query.getResultList();
        result.get(0).setNoteText(text);
        result.get(0).setTime(time);
        em.getTransaction().commit();
    }

    public void deleteNote(int dayID, int noteID){
        em.getTransaction().begin();
        String sql = "SELECT c FROM Note c Where c.id_Note =" + noteID + "";
        TypedQuery<Note> query = em.createQuery(sql, Note.class);
        List<Note> result = query.getResultList();
        em.remove(result.get(0));
        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql2 = "SELECT c FROM Day c Where c.id_Day =" + dayID + "";
        TypedQuery<Day> query2 = em.createQuery(sql2, Day.class);
        List<Day> result2 = query2.getResultList();
        em.refresh(result2.get(0));
        em.getTransaction().commit();
    }

    public Note getNote(int noteID){
        String sql = "SELECT c FROM Note c Where c.id_Note =" + noteID + "";
        TypedQuery<Note> query =em.createQuery(sql, Note.class);
        List<Note> result = query.getResultList();
        return result.get(0);
    }

    public Day addDay(int diaryID, String day, int dayID){
        em.getTransaction().begin();
        Day newPage = new Day(diaryID,day,dayID);
        em.persist(newPage);
        em.getTransaction().commit();
        // Find Diary in DB and add Day to stack //
        em.getTransaction().begin();
        String sqlNP = "SELECT a FROM Diary a Where a.id_Diary =" + diaryID + "";
        TypedQuery<Diary> queryNP = em.createQuery(sqlNP, Diary.class);
        List<Diary> resultNP = queryNP.getResultList();
        resultNP.get(0).getStackOfDay().add(newPage);
        em.getTransaction().commit();
        return newPage;
    }

    public void deleteDay(int diaryID, int dayID){
        em.getTransaction().begin();
        String sql = "SELECT c FROM Day c Where c.id_Day =" + dayID + "";
        TypedQuery<Day> query = em.createQuery(sql, Day.class);
        List<Day> result = query.getResultList();
        em.remove(result.get(0));
        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql2 = "SELECT c FROM Diary c Where c.id_Diary =" + diaryID + "";
        TypedQuery<Diary> query2 = em.createQuery(sql2,Diary.class);
        List<Diary> result2 = query2.getResultList();
        em.refresh(result2.get(0));
        em.getTransaction().commit();
    }


    public Diary createDiary(int userID){
        em.getTransaction().begin();
        Diary diary = new Diary(userID);
        em.persist(diary);
        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql = "SELECT c FROM User c Where c.id_User =" + userID + "";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        List<User> result = query.getResultList();
        result.get(0).getBook().add(diary);
        em.getTransaction().commit();
        return diary;
    }

    public User createUser(String username, String password){
        em.getTransaction().begin();
        User user = new User(username, password);
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public boolean login(String username, String password){
        String sql = "SELECT c FROM User c Where c.username LIKE '" + username + "' AND c.password LIKE '" + password + "'";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        List<User> result = query.getResultList();
        if(result.isEmpty()){
            return false;
        }
        else return true;
    }

    public boolean signUp(String username){
        String sql = "SELECT c FROM User c Where c.username LIKE '" + username + "'";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        List<User> result = query.getResultList();
        if(result.isEmpty()){
            return true;
        }
        else return false;
    }

    public User getAccount(String username){
        String sql = "SELECT c FROM User c Where c.username LIKE '" + username + "'";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        List<User> result = query.getResultList();
        return result.get(0);
    }
}
