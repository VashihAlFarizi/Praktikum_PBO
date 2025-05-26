
package Penjualan;

import Data.*;
import java.util.*;

public class FungsiMenuPelanggan {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<BahanBangunan> daftarBahan = FungsiMenuAdmin.getDaftarBahan();
    private static ArrayList<BahanBangunan> keranjang = new ArrayList<>();
    public static List<String> detailPesanan = new ArrayList<>();


    public static void cariDanLihatBahan() {
        System.out.print("Masukkan nama bahan yang dicari (kosong untuk lihat semua): ");
        String keyword = input.nextLine().toLowerCase();

        boolean ditemukan = false;
        System.out.println("\n=== Daftar Bahan Bangunan ===");
        for (int i = 0; i < daftarBahan.size(); i++) {
            BahanBangunan b = daftarBahan.get(i);
            if (keyword.isEmpty() || b.getNama().toLowerCase().contains(keyword)) {
                System.out.printf("%d. %s - Harga: Rp %,d - Stok: %d\n", (i + 1), b.getNama(), b.getHarga(), b.getStok());
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Bahan bangunan tidak ditemukan.");
            return;
        }

        System.out.print("Pilih nomor bahan untuk ditambahkan ke keranjang (0 untuk batal): ");
        int pilih;
        try {
            pilih = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[!] Input tidak valid.");
            return;
        }

        if (pilih == 0) {
            System.out.println("Batal menambahkan bahan.");
            return;
        }

        if (pilih > 0 && pilih <= daftarBahan.size()) {
            BahanBangunan bahanDipilih = daftarBahan.get(pilih - 1);

            System.out.print("Masukkan jumlah yang ingin dibeli: ");
            int jumlah;
            try {
                jumlah = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[!] Input jumlah tidak valid.");
                return;
            }

            if (jumlah <= 0) {
                System.out.println("[!] Jumlah harus lebih dari 0.");
                return;
            }

            if (jumlah <= bahanDipilih.getStok()) {
                bahanDipilih.setStok(bahanDipilih.getStok() - jumlah);
                for (int i = 0; i < jumlah; i++) {
                    keranjang.add(bahanDipilih);
                }
                System.out.println("✅ " + jumlah + " " + bahanDipilih.getNama() + " ditambahkan ke keranjang.");
            } else {
                System.out.println("Stok tidak cukup. Tersedia: " + bahanDipilih.getStok());
            }
        } else {
            System.out.println("Nomor bahan tidak valid.");
        }
    }

    public static void lihatKeranjang() {
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang Anda kosong.");
            return;
        }

        Map<String, Integer> jumlahPerBahan = new LinkedHashMap<>();
        Map<String, BahanBangunan> bahanMap = new HashMap<>();

        for (BahanBangunan b : keranjang) {
            jumlahPerBahan.put(b.getNama(), jumlahPerBahan.getOrDefault(b.getNama(), 0) + 1);
            bahanMap.put(b.getNama(), b);
        }

        int total = 0;
        int nomor = 1;

        System.out.println("\n=== Keranjang Anda ===");
        for (String nama : jumlahPerBahan.keySet()) {
            BahanBangunan b = bahanMap.get(nama);
            int jumlah = jumlahPerBahan.get(nama);
            int subtotal = b.getHarga() * jumlah;
            System.out.printf("%d. %s - Rp %,d x %d = Rp %,d\n", nomor++, nama, b.getHarga(), jumlah, subtotal);
            total += subtotal;
        }
        System.out.println("Total belanja: Rp " + total);

        System.out.println("\n1. Hapus item");
        System.out.println("2. Lanjut ke pembayaran");
        System.out.println("3. Kembali");

        System.out.print("Pilih opsi: ");
        String opsi = input.nextLine();

        switch (opsi) {
            case "1":
                System.out.print("Masukkan nomor item yang ingin dihapus: ");
                try {
                    int no = Integer.parseInt(input.nextLine());
                    if (no >= 1 && no <= jumlahPerBahan.size()) {
                        String namaDihapus = (String) jumlahPerBahan.keySet().toArray()[no - 1];
                        keranjang.removeIf(b -> b.getNama().equals(namaDihapus));
                        System.out.println("Item '" + namaDihapus + "' dihapus dari keranjang.");
                    } else {
                        System.out.println("[!] Nomor tidak valid.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[!] Input harus angka.");
                }
                break;

            case "2":
                System.out.print("Masukkan uang pembayaran: Rp ");
                try {
                    int dibayar = Integer.parseInt(input.nextLine());
                    if (dibayar >= total) {
                        int kembali = dibayar - total;

                        // Buat detail bahan
                        StringBuilder detail = new StringBuilder();
                        int nomorDetail = 1;
                        for (String nama : jumlahPerBahan.keySet()) {
                            BahanBangunan b = bahanMap.get(nama);
                            int jumlah = jumlahPerBahan.get(nama);
                            int subtotal = b.getHarga() * jumlah;
                            detail.append(String.format("%d. %s - Rp %,d x %d = Rp %,d\n", nomorDetail++, nama, b.getHarga(), jumlah, subtotal));
                        }

                        // Tambahkan ke riwayat
                        FungsiMenuAdmin.riwayatPesanan.add("Total: Rp " + total);
                        FungsiMenuAdmin.statusKonfirmasi.add(false);
                        FungsiMenuAdmin.detailPesanan.add(detail.toString());

                        // Clear keranjang
                        keranjang.clear();

                        // Tampilkan struk ringkas
                        System.out.println("\n🧾 Pembayaran sukses!");
                        System.out.println("Total        : Rp " + total);
                        System.out.println("Dibayar      : Rp " + dibayar);
                        System.out.println("Kembalian    : Rp " + kembali);
                        System.out.println("Status       : Sudah Dibayar");
                        System.out.println("\nSilakan cek Riwayat Pesanan untuk melihat struk.");
                    } else {
                        System.out.println("Uang tidak cukup.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid.");
                }
                break;


            case "3":
                System.out.println("Kembali ke menu pelanggan...");
                break;

            default:
                System.out.println("Opsi tidak dikenal.");
        }
    }

    public static void lihatRiwayatPesanan() {
        List<String> riwayat = FungsiMenuAdmin.riwayatPesanan;
        List<Boolean> konfirmasi = FungsiMenuAdmin.statusKonfirmasi;

        if (riwayat.isEmpty()) {
            System.out.println("\nBelum ada riwayat pesanan.");
            return;
        }

        System.out.println("\n=== RIWAYAT PESANAN ===");
        for (int i = 0; i < riwayat.size(); i++) {
            String status = konfirmasi.get(i) ? "Dikonfirmasi" : " Menunggu konfirmasi";
            System.out.printf("%d. %s\n   Status: %s\n", (i + 1), riwayat.get(i), status);
        }

        System.out.print("\nMasukkan nomor pesanan untuk cetak struk (ENTER untuk batal): ");
        String inputStr = input.nextLine().trim();

        if (inputStr.isEmpty()) {
            System.out.println("Cetak struk dibatalkan.");
            return;
        }

        try {
            int index = Integer.parseInt(inputStr) - 1;
            if (index < 0 || index >= riwayat.size()) {
                System.out.println("Nomor tidak valid.");
                return;
            }

            if (!konfirmasi.get(index)) {
                System.out.println("Pesanan belum dikonfirmasi admin.");
                return;
            }

            System.out.println("\n========== STRUK ==========");
            System.out.println("No Pesanan : " + (index + 1));
            System.out.println("Detail     :");
            System.out.print(FungsiMenuAdmin.detailPesanan.get(index));
            System.out.println("Status     : Sudah dikonfirmasi admin");
            System.out.println("============================\n");

        } catch (NumberFormatException e) {
            System.out.println("[!] Input harus berupa angka.");
        }
    }
}