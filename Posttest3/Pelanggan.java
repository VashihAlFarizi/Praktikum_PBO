import java.util.ArrayList;
import java.util.Scanner;

public class Pelanggan extends User {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<BahanBangunan> keranjang = new ArrayList<>();
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
                menuPelanggan();
                return;
            }
        }
        System.out.println("Login gagal. Username atau password salah.");
    }

    public static void menuPelanggan() {
        int pilihan;
        do {
            System.out.println("\n===== MENU PELANGGAN =====");
            System.out.println("1. Cari Bahan Bangunan");
            System.out.println("2. Lihat Keranjang");
            System.out.println("3. Logout");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> cariBahan();
                case 2 -> lihatKeranjang();
                case 3 -> System.out.println("Logout...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }

    private static void cariBahan() {
        System.out.print("Masukkan nama bahan yang dicari: ");
        String keyword = input.nextLine().toLowerCase();

        boolean ditemukan = false;
        for (BahanBangunan bahan : Admin.getDaftarBahanBangunan()) {
            if (bahan.getNama().toLowerCase().contains(keyword)) {
                ditemukan = true;
                bahan.tampilkan();
                System.out.print("Tambah ke keranjang? (y/n): ");
                String pilih = input.nextLine();
                if (pilih.equalsIgnoreCase("y")) {
                    keranjang.add(bahan);
                    System.out.println("Bahan ditambahkan ke keranjang.");
                }
            }
        }
        if (!ditemukan) {
            System.out.println("Bahan tidak ditemukan.");
        }
    }

    private static void lihatKeranjang() {
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang kosong.");
            return;
        }
        for (BahanBangunan bahan : keranjang) {
            bahan.tampilkan();
        }
    }
}