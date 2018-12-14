package NotePast;

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

    public void closeConnection() {
        em.close();
        emf.close();
    }

    public Note addNote(int diaryID, int dayID, int noteID, String datStr, String time, String text) {
        em.getTransaction().begin();
        Note newNote = new Note(noteID, datStr, time, text);
        em.persist(newNote);
        em.getTransaction().commit();

        // Find DayStory Page in DB and add Note to stack //
        em.getTransaction().begin();
        String sql = "SELECT c FROM DayStory c Where c.id_DayStory =" + dayID + "";
        TypedQuery<DayStory> query = em.createQuery(sql, DayStory.class);
        List<DayStory> result = query.getResultList();
        result.get(0).getListOfNote().add(newNote);
        em.getTransaction().commit();

        // then Find Diary in DB and add DayStory to stack //
        em.getTransaction().begin();
        String sql2 = "SELECT a FROM Diary a Where a.id_Diary = " + diaryID + "";
        TypedQuery<Diary> queryDiary = em.createQuery(sql2, Diary.class);
        List<Diary> resultNP = queryDiary.getResultList();
        String sql3 = "SELECT a FROM DayStory a Where a.id_Diary = " + diaryID + "";
        TypedQuery<DayStory> queryDP = em.createQuery(sql3, DayStory.class);
        List<DayStory> resultDP = queryDP.getResultList();
        resultNP.get(0).setListOfDayStory(resultDP);
        em.getTransaction().commit();
        return newNote;
    }

    public void editNote(int noteID, String time, String text) {
        em.getTransaction().begin();
        String sql = "SELECT c FROM Note c Where c.id_Note =" + noteID + "";
        TypedQuery<Note> query = em.createQuery(sql, Note.class);
        List<Note> result = query.getResultList();
        result.get(0).setNoteText(text);
        result.get(0).setTimeStr(time);
        em.getTransaction().commit();
    }

    public void deleteNote(int dayID, int noteID) {
        em.getTransaction().begin();
        String sql = "SELECT c FROM Note c Where c.id_Note =" + noteID + "";
        TypedQuery<Note> query = em.createQuery(sql, Note.class);
        List<Note> result = query.getResultList();
        em.remove(result.get(0));
        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql2 = "SELECT c FROM DayStory c Where c.id_DayStory =" + dayID + "";
        TypedQuery<DayStory> query2 = em.createQuery(sql2, DayStory.class);
        List<DayStory> result2 = query2.getResultList();
        em.refresh(result2.get(0));
        em.getTransaction().commit();
    }

    public Note getNote(int noteID) {
        String sql = "SELECT c FROM Note c Where c.id_Note =" + noteID + "";
        TypedQuery<Note> query = em.createQuery(sql, Note.class);
        List<Note> result = query.getResultList();
        return result.get(0);
    }

    public DayStory addDay(int diaryID, String day, int dayID) {
        em.getTransaction().begin();
        DayStory newPage = new DayStory(diaryID, day, dayID);
        em.persist(newPage);
        em.getTransaction().commit();

        // Find Diary in DB and add DayStory to stack //
        em.getTransaction().begin();
        String sqlNP = "SELECT a FROM Diary a Where a.id_Diary =" + diaryID + "";
        TypedQuery<Diary> queryNP = em.createQuery(sqlNP, Diary.class);
        List<Diary> resultNP = queryNP.getResultList();
        resultNP.get(0).getListOfDayStory().add(newPage);
        em.getTransaction().commit();
        return newPage;
    }

    public void deleteDay(int diaryID, int dayID) {
        em.getTransaction().begin();
        String sql = "SELECT c FROM DayStory c Where c.id_DayStory =" + dayID + "";
        TypedQuery<DayStory> query = em.createQuery(sql, DayStory.class);
        List<DayStory> result = query.getResultList();
        em.remove(result.get(0));
        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql2 = "SELECT c FROM Diary c Where c.id_Diary =" + diaryID + "";
        TypedQuery<Diary> query2 = em.createQuery(sql2, Diary.class);
        List<Diary> result2 = query2.getResultList();
        em.refresh(result2.get(0));
        em.getTransaction().commit();
    }

    public Diary createDiary(int userID) {
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

    public User createUser(String username, String password) {
        em.getTransaction().begin();
        User user = new User(username, password);
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public boolean login(String username, String password) {
        String sql = "SELECT c FROM User c Where c.username LIKE '" + username + "' AND c.password LIKE '" + password + "'";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        List<User> result = query.getResultList();
        if (result.isEmpty()) {
            return false;
        } else return true;
    }

    public boolean signUp(String username) {
        String sql = "SELECT c FROM User c Where c.username LIKE '" + username + "'";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        List<User> result = query.getResultList();
        if (result.isEmpty()) {
            return true;
        } else return false;
    }

    public User getAccount(String username) {
        String sql = "SELECT c FROM User c Where c.username LIKE '" + username + "'";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        List<User> result = query.getResultList();
        return result.get(0);
    }

    //Add tag that use in note
    public void addNoteinTag(int diaryID, int noteID, String name, String type) {
        Note note = getNote(noteID);

        em.getTransaction().begin();
        String sql = "SELECT c FROM Tag c Where c.tagName LIKE '" + name + "'";
        TypedQuery<Tag> query = em.createQuery(sql, Tag.class);
        List<Tag> result = query.getResultList();
        result.get(0).getListOfNote().add(note);
        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql2 = "SELECT c FROM Tag c Where c.id_Diary =" + diaryID + "";
        TypedQuery<Tag> query2 = em.createQuery(sql2, Tag.class);
        List<Tag> result2 = query2.getResultList();

        String sql3 = "SELECT c FROM Diary c Where c.id_Diary =" + diaryID + "";
        TypedQuery<Diary> query3 = em.createQuery(sql3, Diary.class);
        List<Diary> result3 = query3.getResultList();

        result3.get(0).setListOfTag(result2);
        em.getTransaction().commit();
    }

    public void deleteNoteinTag(int noteID, String name) {
//        em.getTransaction().begin();
//        String sql = "SELECT c FROM Tag c Where c.tagName LIKE '" + name + "'";
//        TypedQuery<Tag> query = em.createQuery(sql, Tag.class);
//        List<Tag> result = query.getResultList();
//        result.get(0).getStackOfNote().add(note);
//        String sql2 = "SELECT c FROM Tag c Where c.tagName LIKE '" + name + "'";
//        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql = "SELECT c FROM Diary c Where c.stackOfTag.tagName LIKE '"
                + name + "' AND c.stackOfTag.stackOfNote.id_Note =" + noteID + "";
        TypedQuery<Diary> query = em.createQuery(sql, Diary.class);
        List<Diary> result = query.getResultList();
        em.remove(result.get(0));
        em.getTransaction().commit();
    }

    // Create new tag in diary
    public Tag createTag(int diaryID, String name, String type) {
        em.getTransaction().begin();
        Tag newTag = new Tag(diaryID,name,type);
        em.persist(newTag);
        em.getTransaction().commit();

        em.getTransaction().begin();
        String sql = "SELECT a FROM Diary a Where a.id_Diary =" + diaryID + "";
        TypedQuery<Diary> query = em.createQuery(sql, Diary.class);
        List<Diary> result = query.getResultList();
        result.get(0).getListOfTag().add(newTag);
        em.getTransaction().commit();
        return newTag;
    }

    public boolean searchTag(int diaryID, String name,String type) {
//        String sql = "SELECT c FROM Diary c Where c.id_Diary =" + diaryID + "";
//        TypedQuery<Diary> query = em.createQuery(sql, Diary.class);
//        List<Diary> result = query.getResultList();

//        String sql2 = "SELECT a FROM Tag a Where a.tagName LIKE '" + name + "' AND a.tagType LIKE '" + type + "'";
//        TypedQuery<Tag> query2 = em.createQuery(sql2, Tag.class);
//        List<Tag> result2 = query2.getResultList();

        String sql3 = "SELECT n, m FROM Diary n, Tag m Where n.id_Diary ="
                + diaryID + " AND m.getTagName() LIKE '" + name
                + "' AND m.getTagType() LIKE '" + type + "'";
        TypedQuery<Tag> query3 = em.createQuery(sql3, Tag.class);
        List<Tag> result3 = query3.getResultList();

        if (result3.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
