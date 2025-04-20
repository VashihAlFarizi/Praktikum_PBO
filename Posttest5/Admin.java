import java.util.ArrayList;
import java.util.Scanner;

public final class Admin extends User { // FINAL class (Instruksi 2)
    private static final ArrayList<BahanBangunan> daftarBahanBangunan = new ArrayList<>(); // FINAL atribut (Instruksi 2)
    private static final Scanner input = new Scanner(System.in);

    public Admin(String username, String password) {
        super(username, password);
    }

    public static ArrayList<BahanBangunan> getDaftarBahanBangunan() {
        return daftarBahanBangunan;
    }

    public static void menuAdmin() {
        int pilihan;
        do {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Tambah Bahan Bangunan");
            System.out.println("2. Lihat Daftar Bahan Bangunan");
            System.out.println("3. Perbarui Bahan Bangunan");
            System.out.println("4. Hapus Bahan Bangunan");
            System.out.println("5. Kembali ke menu utama");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> tambahBahanBangunan();
                case 2 -> lihatBahanBangunan();
                case 3 -> perbaruiBahanBangunan();
                case 4 -> hapusBahanBangunan();
                case 5 -> System.out.println("Kembali ke menu utama...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }

    private static boolean cekID(int id) {
        for (BahanBangunan bahan : daftarBahanBangunan) {
            if (bahan.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private static int validasiInputID() {
        while (true) {
            System.out.print("Masukkan ID: ");
            String inputStr = input.nextLine();
            if (inputStr.matches("\\d+")) {
                return Integer.parseInt(inputStr);
            } else {
                System.out.println("ID harus berupa angka. Coba lagi.");
            }
        }
    }

    private static void tambahBahanBangunan() {
        int id;
        do {
            id = validasiInputID();
            if (cekID(id)) {
                System.out.println("ID sudah digunakan! Masukkan ID lain.");
            }
        } while (cekID(id));

        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Deskripsi: ");
        String deskripsi = input.nextLine();
        System.out.print("Masukkan Harga: ");
        double harga = input.nextDouble();
        System.out.print("Masukkan Stok: ");
        int stok = input.nextInt();
        input.nextLine();

        daftarBahanBangunan.add(new BahanBangunan(id, nama, deskripsi, harga, stok));
        System.out.println("Bahan bangunan berhasil ditambahkan.");
    }

    private static void lihatBahanBangunan() {
        if (daftarBahanBangunan.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }
        for (BahanBangunan bahan : daftarBahanBangunan) {
            bahan.tampilkan();
        }
    }

    private static void perbaruiBahanBangunan() {
        int id = validasiInputID();

        for (BahanBangunan bahan : daftarBahanBangunan) {
            if (bahan.getId() == id) {
                System.out.print("Masukkan Nama Baru: ");
                bahan.setNama(input.nextLine());
                System.out.print("Masukkan Deskripsi Baru: ");
                bahan.setDeskripsi(input.nextLine());
                System.out.print("Masukkan Harga Baru: ");
                bahan.setHarga(input.nextDouble());
                System.out.print("Masukkan Stok Baru: ");
                bahan.setStok(input.nextInt());
                input.nextLine();
                System.out.println("Data berhasil diperbarui.");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
    }

    private static void hapusBahanBangunan() {
        int id = validasiInputID();
        daftarBahanBangunan.removeIf(bahan -> bahan.getId() == id);
        System.out.println("Bahan bangunan berhasil dihapus.");
    }

    // FINAL Method (Instruksi 2)
    @Override
    public final void aksesMenu() {
        menuAdmin(); 
    }
}
