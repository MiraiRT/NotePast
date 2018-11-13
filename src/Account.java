public class Account{

    private String userName;
    private String password;
    private NotePast book;

    public Account(String userName,String password) {
        this.userName = userName;
        this.password = password;
        this.book = new NotePast();
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

    @Override
    public String toString() {
        return "User: " + this.userName + "\n"
                + "Pass: " + this.password + "\n\n"
                + "NotePass: " + this.userName + "\n"
                + this.book + "\n";
    }

    public static void main(String[] args) {

        Account x = new Account("A","tatatata");
        DayPage.createDayPage(x.getBook());
        Event.addEvent(DayPage.getTodayPage(x.book),"Hi");
        Event.addEvent(DayPage.getTodayPage(x.book),"Hi2");
        System.out.println(x);


//
//
//        Event.addEvent(x.getBook().getStackOfDayPage().get(0),"A");
//        System.out.println(x.getUserName() + " " + x.getPassword());
//        System.out.println("\n" + x.getBook());
//        System.out.println(x.getBook().getStackOfDayPage());
//        System.out.println(x.getBook() + "\n");

    }
}

