package Data;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, "admin");
    }

    @Override
    public void infoRole() {
        System.out.println("Selamat Datang Admin.");
    }
}