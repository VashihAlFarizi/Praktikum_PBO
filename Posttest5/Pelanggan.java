import java.util.ArrayList;
import java.util.Scanner;

public class Pelanggan extends User {
    private static ArrayList<BahanBangunan> keranjang = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();

    public Pelanggan(String username, String password) {
        super(username, password);
    }

    public static void menuLoginPelanggan() {
        int pilih;
        do {
            System.out.println("\n===== PELANGGAN =====");
            System.out.println("1. Registrasi");
            System.out.println("2. Login");
            System.out.println("3. Kembali");
            System.out.print("Pilih menu: ");
            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1 -> registrasi();
                case 2 -> loginPelanggan();
                case 3 -> System.out.println("Kembali ke menu utama...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 3);
    }

    private static void registrasi() {
        System.out.print("Masukkan username: ");
        String username = input.nextLine();
        System.out.print("Masukkan password: ");
        String password = input.nextLine();

        Pelanggan pelangganBaru = new Pelanggan(username, password);
        daftarPelanggan.add(pelangganBaru);

        System.out.println("Registrasi berhasil. Silakan login.");
    }

    private static void loginPelanggan() {
        System.out.print("Masukkan username: ");
        String username = input.nextLine();
        System.out.print("Masukkan password: ");
        String password = input.nextLine();

        for (Pelanggan p : daftarPelanggan) {
            if (p.login(username, password)) {
                System.out.println("Login berhasil. Selamat datang, " + username + "!");
                p.aksesMenu();
                return;
            }
        }
        System.out.println("Login gagal. Username atau password salah.");
    }

    @Override
    public void aksesMenu() {
        int pilihan;
        do {
            System.out.println("\n===== MENU PELANGGAN =====");
            System.out.println("1. Lihat Daftar Bahan Bangunan");
            System.out.println("2. Tambah ke Keranjang");
            System.out.println("3. Lihat Keranjang");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> lihatBahan();
                case 2 -> tambahKeranjang();
                case 3 -> menuKeranjang();
                case 4 -> System.out.println("Keluar dari akun pelanggan...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }

    private void lihatBahan() {
        ArrayList<BahanBangunan> daftar = Admin.getDaftarBahanBangunan();
        if (daftar.isEmpty()) {
            System.out.println("Tidak ada bahan bangunan.");
            return;
        }
        for (BahanBangunan b : daftar) {
            b.tampilkan();
        }
    }

    private void tambahKeranjang() {
        System.out.print("Masukkan ID bahan yang ingin ditambahkan: ");
        int id = input.nextInt();
        input.nextLine();
        ArrayList<BahanBangunan> daftar = Admin.getDaftarBahanBangunan();
        for (BahanBangunan b : daftar) {
            if (b.getId() == id) {
                keranjang.add(b);
                System.out.println("Berhasil ditambahkan ke keranjang.");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
    }

    private void menuKeranjang() {
        int pilihan;
        do {
            System.out.println("\n===== MENU KERANJANG =====");
            System.out.println("1. Lihat Isi Keranjang");
            System.out.println("2. Checkout");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Kembali");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> lihatKeranjang();
                case 2 -> checkout();
                case 3 -> hapusBarang();
                case 4 -> System.out.println("Kembali ke menu pelanggan...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }

    private void lihatKeranjang() {
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            for (BahanBangunan b : keranjang) {
                b.tampilkan();
            }
        }
    }

    private void checkout() {
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang masih kosong.");
            return;
        }
        double total = 0;
        for (BahanBangunan b : keranjang) {
            total += b.getHarga();
        }
        System.out.println("Total belanja: Rp" + total);
        keranjang.clear();
        System.out.println("Terima kasih telah berbelanja!");
    }

    private void hapusBarang() {
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang kosong.");
            return;
        }
        System.out.print("Masukkan ID barang yang ingin dihapus: ");
        int id = input.nextInt();
        input.nextLine();

        boolean ditemukan = false;
        for (int i = 0; i < keranjang.size(); i++) {
            if (keranjang.get(i).getId() == id) {
                keranjang.remove(i);
                ditemukan = true;
                System.out.println("Barang berhasil dihapus dari keranjang.");
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang dengan ID tersebut tidak ada di keranjang.");
        }
    }
}
