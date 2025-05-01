import java.util.ArrayList;
import java.util.InputMismatchException;
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
            
            // [DITAMBAH] Error handling untuk input menu
            try {
                pilih = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                input.nextLine();
                pilih = 0; // Reset pilihan jika error
                continue;
            }

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
        
        // [DITAMBAH] Validasi username sudah ada
        for (Pelanggan p : daftarPelanggan) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Error: Username sudah digunakan!");
                return;
            }
        }
        
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
        System.out.println("Error: Login gagal. Username atau password salah.");
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
            
            // [DITAMBAH] Error handling untuk input menu
            try {
                pilihan = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                input.nextLine();
                pilihan = 0; // Reset pilihan jika error
                continue;
            }

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
            System.out.println("Tidak ada bahan bangunan tersedia.");
            return;
        }
        
        System.out.println("\nTotal bahan tersedia: " + BahanBangunan.getTotalBahanTerdaftar());
        System.out.println("===== DAFTAR BAHAN =====");
        
        for (BahanBangunan b : daftar) {
            b.tampilkanInfoSingkat(); // Menggunakan method dari interface
        }
        
        // [DITAMBAH] Fitur pencarian berdasarkan nama (Permintaan 1)
        System.out.print("Cari bahan (kosongkan untuk lihat semua): ");
        String keyword = input.nextLine().toLowerCase();
        
        System.out.println("\n===== DAFTAR BAHAN =====");
        boolean found = false;
        for (BahanBangunan b : daftar) {
            if (keyword.isEmpty() || b.getNama().toLowerCase().contains(keyword)) {
                b.tampilkan();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Tidak ditemukan bahan dengan kata kunci '" + keyword + "'");
        }
    }

    private void tambahKeranjang() {
        // [DIUBAH] Fitur input dengan nama bahan (Permintaan 2)
        System.out.print("Masukkan NAMA bahan yang ingin ditambahkan: ");
        String namaBahan = input.nextLine();
        
        ArrayList<BahanBangunan> daftar = Admin.getDaftarBahanBangunan();
        ArrayList<BahanBangunan> hasilPencarian = new ArrayList<>();
        
        // Cari bahan berdasarkan nama
        for (BahanBangunan b : daftar) {
            if (b.getNama().equalsIgnoreCase(namaBahan)) {
                hasilPencarian.add(b);
            }
        }
        
        if (hasilPencarian.isEmpty()) {
            System.out.println("Error: Bahan tidak ditemukan!");
            return;
        }
        
        // Jika hanya ditemukan 1 bahan
        if (hasilPencarian.size() == 1) {
            keranjang.add(hasilPencarian.get(0));
            System.out.println("Berhasil menambahkan '" + hasilPencarian.get(0).getNama() + "' ke keranjang");
        } 
        // Jika ditemukan lebih dari 1 (nama sama)
        else {
            System.out.println("\nDitemukan beberapa bahan:");
            for (int i = 0; i < hasilPencarian.size(); i++) {
                System.out.print((i+1) + ". ");
                hasilPencarian.get(i).tampilkan();
            }
            
            // [DITAMBAH] Error handling untuk pilihan bahan
            try {
                System.out.print("Pilih nomor bahan: ");
                int pilihan = input.nextInt();
                input.nextLine();
                
                if (pilihan > 0 && pilihan <= hasilPencarian.size()) {
                    keranjang.add(hasilPencarian.get(pilihan-1));
                    System.out.println("Berhasil menambahkan ke keranjang");
                } else {
                    System.out.println("Error: Pilihan tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                input.nextLine();
            }
        }
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
            
            // [DITAMBAH] Error handling untuk input menu
            try {
                pilihan = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                input.nextLine();
                pilihan = 0; // Reset pilihan jika error
                continue;
            }

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
            System.out.println("\n===== ISI KERANJANG =====");
            double total = 0;
            for (BahanBangunan b : keranjang) {
                b.tampilkan();
                total += b.getHarga();
            }
            System.out.println("Total: Rp" + total);
        }
    }

    private void checkout() {
        if (keranjang.isEmpty()) {
            System.out.println("Error: Keranjang masih kosong!");
            return;
        }
        
        // [DIUBAH] Tampilkan detail checkout
        System.out.println("\n===== CHECKOUT =====");
        double total = 0;
        for (BahanBangunan b : keranjang) {
            b.tampilkan();
            total += b.getHarga();
        }
        System.out.println("Total belanja: Rp" + total);
        keranjang.clear();
        System.out.println("Terima kasih telah berbelanja!");
    }

    private void hapusBarang() {
        if (keranjang.isEmpty()) {
            System.out.println("Error: Keranjang kosong!");
            return;
        }
        
        // [DITAMBAH] Error handling untuk input ID
        try {
            System.out.print("Masukkan ID barang yang ingin dihapus: ");
            int id = input.nextInt();
            input.nextLine();
            
            boolean ditemukan = false;
            for (int i = 0; i < keranjang.size(); i++) {
                if (keranjang.get(i).getId() == id) {
                    String namaBarang = keranjang.get(i).getNama();
                    keranjang.remove(i);
                    ditemukan = true;
                    System.out.println("Berhasil menghapus '" + namaBarang + "' dari keranjang");
                    break;
                }
            }
            
            if (!ditemukan) {
                System.out.println("Error: Barang dengan ID tersebut tidak ada di keranjang");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: ID harus berupa angka!");
            input.nextLine();
        }
    }
}