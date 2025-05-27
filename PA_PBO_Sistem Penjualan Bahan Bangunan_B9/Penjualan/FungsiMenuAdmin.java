package Penjualan;

import Data.BahanBangunan;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FungsiMenuAdmin {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<BahanBangunan> daftarBahan = new ArrayList<>();
    private static int lastId = 5;
    public static List<Boolean> statusKonfirmasi = new ArrayList<>();
    public static List<String> riwayatPesanan = new ArrayList<>();
    public static List<String> detailPesanan = new ArrayList<>();

    static {
        // Data dummy
        daftarBahan.add(new BahanBangunan(1, "Semen Tiga Roda", "Semen berkualitas tinggi untuk konstruksi", 77000, 100));
        daftarBahan.add(new BahanBangunan(2, "Batu Bata Merah", "Batu bata untuk dinding rumah", 5000, 1000));
        daftarBahan.add(new BahanBangunan(3, "Pasir Putih", "Pasir halus untuk plester dan acian", 150000, 50));
        daftarBahan.add(new BahanBangunan(4, "Cat Tembok Dulux", "Cat tembok warna putih 5kg", 135000, 30));
        daftarBahan.add(new BahanBangunan(5, "Paku 5cm", "Paku baja untuk kayu", 15000, 100));
    }

    public static void tambahBahan() {
        try {
            int id = lastId + 1;
            System.out.println("ID Bahan (otomatis): " + id);

            System.out.print("Masukkan Nama Bahan: ");
            String nama = input.nextLine();

            System.out.print("Masukkan Deskripsi Bahan: ");
            String deskripsi = input.nextLine();

            System.out.print("Masukkan Harga: ");
            int harga = Integer.parseInt(input.nextLine());
            if (harga < 0) {
                throw new IllegalArgumentException("Harga tidak boleh negatif!");
            }

            System.out.print("Masukkan Stok: ");
            int stok = Integer.parseInt(input.nextLine());
            if (stok < 0) {
                throw new IllegalArgumentException("Stok tidak boleh negatif!");
            }

            // Semua input valid, simpan dan naikkan lastId
            BahanBangunan bahan = new BahanBangunan(id, nama, deskripsi, harga, stok);
            daftarBahan.add(bahan);
            lastId = id;
            System.out.println("Bahan berhasil ditambahkan!\n");

        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Pastikan Harga dan Stok berupa angka.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }


    public static void lihatDaftarBahan() {
        if (daftarBahan.isEmpty()) {
            System.out.println("Belum ada data bahan.\n");
        } else {
            for (BahanBangunan b : daftarBahan) {
                System.out.println(b.infoBahanBangunan());
            }
        }
    }

    public static void perbaruiBahan() {
        try {
            for (BahanBangunan b : daftarBahan) {
                System.out.println(b.infoBahanBangunan());
            }
            System.out.print("Masukkan ID bahan yang ingin diperbarui (0 untuk batal): ");
            int id = Integer.parseInt(input.nextLine());

            if (id == 0) {
                System.out.println("Perubahan dibatalkan.\n");
                return;
            }

            for (BahanBangunan b : daftarBahan) {
                if (b.getIdBahan() == id) {
                    System.out.print("Masukkan Nama Baru: ");
                    b.setNama(input.nextLine());

                    System.out.print("Masukkan Deskripsi Baru: ");
                    b.setDeskripsi(input.nextLine());

                    System.out.print("Masukkan Harga Baru: ");
                    int harga = Integer.parseInt(input.nextLine());
                    if (harga < 0) {
                        throw new IllegalArgumentException("Harga tidak boleh negatif!");
                    }
                    b.setHarga(harga);

                    System.out.print("Masukkan Stok Baru: ");
                    int stok = Integer.parseInt(input.nextLine());
                    if (stok < 0) {
                        throw new IllegalArgumentException("Stok tidak boleh negatif!");
                    }
                    b.setStok(stok);

                    System.out.println("Data berhasil diperbarui!\n");
                    return;
                }
            }
            System.out.println("ID bahan tidak ditemukan.\n");

        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Pastikan Harga dan Stok berupa angka.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }


    public static void hapusBahan() {
        try {
            for (BahanBangunan b : daftarBahan) {
                System.out.println(b.infoBahanBangunan());
            }
            System.out.print("Masukkan ID bahan yang ingin dihapus (0 untuk batal): ");
            int id = Integer.parseInt(input.nextLine());

            if (id == 0) {
                System.out.println("Penghapusan dibatalkan.\n");
                return;
            }

            for (int i = 0; i < daftarBahan.size(); i++) {
                if (daftarBahan.get(i).getIdBahan() == id) {
                    daftarBahan.remove(i);
                    System.out.println("Bahan berhasil dihapus.\n");
                    return;
                }
            }
            System.out.println("ID bahan tidak ditemukan.\n");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. ID harus berupa angka.\n");
        }
    }


    public static void konfirmasiPesanan() {
        if (riwayatPesanan.isEmpty()) {
            System.out.println("Tidak ada pesanan untuk dikonfirmasi.");
            return;
        }

        System.out.println("\n=== Daftar Pesanan ===");
        for (int i = 0; i < riwayatPesanan.size(); i++) {
            String status = statusKonfirmasi.get(i) ? "(Sudah dikonfirmasi)" : "(Belum dikonfirmasi)";
            System.out.println((i + 1) + ". " + riwayatPesanan.get(i) + " " + status);
        }

        System.out.print("\nMasukkan nomor pesanan yang ingin dikonfirmasi/batal: ");
        try {
            int index = Integer.parseInt(input.nextLine()) - 1;
            if (index >= 0 && index < riwayatPesanan.size()) {
                System.out.println("1. Konfirmasi pesanan");
                System.out.println("2. Batalkan pesanan");
                System.out.print("Pilih opsi: ");
                String pilihan = input.nextLine();

                switch (pilihan) {
                    case "1":
                        if (statusKonfirmasi.get(index)) {
                            System.out.println("Pesanan sudah dikonfirmasi sebelumnya.");
                        } else {
                            statusKonfirmasi.set(index, true);
                            System.out.println("Pesanan berhasil dikonfirmasi.");
                        }
                        break;

                    case "2":
                        if (statusKonfirmasi.get(index)) {
                            System.out.println("Pesanan sudah dikonfirmasi dan tidak bisa dibatalkan.");
                        } else {
                            riwayatPesanan.remove(index);
                            statusKonfirmasi.remove(index);
                            System.out.println("Pesanan berhasil dibatalkan.");
                        }
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } else {
                System.out.println("Nomor pesanan tidak ditemukan.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Harus berupa angka.");
        }
    }


    public static ArrayList<BahanBangunan> getDaftarBahan() {
        return daftarBahan;
    }
}