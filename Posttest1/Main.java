import java.util.ArrayList;
import java.util.Scanner;

class BahanBangunan {
    int id;
    String nama;
    String deskripsi;
    double harga;
    int stok;

    public BahanBangunan(int id, String nama, String deskripsi, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
    }

    public void tampilkan() {
        System.out.println("ID: " + id + " | Nama: " + nama + " | Harga: Rp" + harga + " | Stok: " + stok);
    }
}

public class Main {
    static ArrayList<BahanBangunan> daftarBahanBangunan = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n===== MENU TOKO MATERIAL =====");
            System.out.println("1. Tambah Bahan Bangunan");
            System.out.println("2. Lihat Daftar Bahan Bangunan");
            System.out.println("3. Perbarui Bahan Bangunan");
            System.out.println("4. Hapus Bahan Bangunan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    tambahBahanBangunan();
                    break;
                case 2:
                    lihatBahanBangunan();
                    break;
                case 3:
                    perbaruiBahanBangunan();
                    break;
                case 4:
                    hapusBahanBangunan();
                    break;
                case 5:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }

    static boolean cekID(int id) {
        for (BahanBangunan bahan : daftarBahanBangunan) {
            if (bahan.id == id) {
                return true;
            }
        }
        return false; 
    }

    static int validasiInputID() {
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

    static void tambahBahanBangunan() {
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

    static void lihatBahanBangunan() {
        if (daftarBahanBangunan.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }
        for (BahanBangunan bahan : daftarBahanBangunan) {
            bahan.tampilkan();
        }
    }

    static void perbaruiBahanBangunan() {
        int id = validasiInputID();

        for (BahanBangunan bahan : daftarBahanBangunan) {
            if (bahan.id == id) {
                System.out.print("Masukkan Nama Baru: ");
                bahan.nama = input.nextLine();
                System.out.print("Masukkan Deskripsi Baru: ");
                bahan.deskripsi = input.nextLine();
                System.out.print("Masukkan Harga Baru: ");
                bahan.harga = input.nextDouble();
                System.out.print("Masukkan Stok Baru: ");
                bahan.stok = input.nextInt();
                input.nextLine();
                System.out.println("Data berhasil diperbarui.");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
    }

    static void hapusBahanBangunan() {
        int id = validasiInputID();

        for (BahanBangunan bahan : daftarBahanBangunan) {
            if (bahan.id == id) {
                daftarBahanBangunan.remove(bahan);
                System.out.println("Bahan bangunan berhasil dihapus.");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
    }
}