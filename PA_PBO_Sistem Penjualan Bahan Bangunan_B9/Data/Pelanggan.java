package Data;


public class Pelanggan extends User {
    public Pelanggan(String username, String password) {
        super(username, password, "user");
    }

    @Override
    public void infoRole() {
        System.out.println("Selamat Datang Pelanggan Nikmati Layanan Kami.");
    }

}