import Penjualan.*;
import java.util.Scanner;

import Data.User;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan = 0;

        do {
            System.out.println("\n===== SISTEM PENJUALAN BAHAN BANGUNAN =====");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("3. Keluar");

            System.out.print("Pilih menu: ");
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
                pilihan = 0;
                continue;
            }

            switch (pilihan) {
                case 1:
                    User user = FungsiLoginRegistrasi.login();
                    if (user != null) {
                        if (user.getRole().equalsIgnoreCase("admin")) {
                            menuAdmin();
                        } else {
                            menuPelanggan();
                        }
                    }
                    break;
                case 2:
                    FungsiLoginRegistrasi.Registrasi();
                    break;
                case 3:
                    System.out.println("Terima kasih, sampai jumpa.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }

    public static void menuAdmin() {
        int pilihanAdmin = 0;

        do {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Tambah Bahan Bangunan");
            System.out.println("2. Lihat Daftar Bahan Bangunan");
            System.out.println("3. Perbarui Bahan Bangunan");
            System.out.println("4. Hapus Bahan Bangunan");
            System.out.println("5. Konfirmasi Pesanan Pelanggan");
            System.out.println("6. Kembali ke menu utama");

            System.out.print("Pilih menu: ");
            try {
                pilihanAdmin = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
                continue;
            }

            switch (pilihanAdmin) {
                case 1:
                    FungsiMenuAdmin.tambahBahan();
                    break;
                case 2:
                    FungsiMenuAdmin.lihatDaftarBahan();
                    break;
                case 3:
                    FungsiMenuAdmin.perbaruiBahan();
                    break;
                case 4:
                    FungsiMenuAdmin.hapusBahan();
                    break;
                case 5:
                    FungsiMenuAdmin.konfirmasiPesanan();
                    break;
                case 6:
                    System.out.println("Kembali ke menu utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih antara 1 - 6.");
            }
        } while (pilihanAdmin != 6);
    }

    public static void menuPelanggan() {
        int pilihanPelanggan = 0;

        do {
            System.out.println("\n===== MENU PELANGGAN =====");
            System.out.println("1. Cari / Lihat Daftar Bahan Bangunan");
            System.out.println("2. Lihat Keranjang / Pesan");
            System.out.println("3. Riwayat Pesanan");
            System.out.println("4. Kembali");

            System.out.print("Pilih menu: ");
            try {
                pilihanPelanggan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }

            switch (pilihanPelanggan) {
                case 1:
                    FungsiMenuPelanggan.cariDanLihatBahan();
                    break;
                case 2:
                    FungsiMenuPelanggan.lihatKeranjang();
                    break;
                case 3:
                    FungsiMenuPelanggan.lihatRiwayatPesanan();
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, pilih 1-4.");
            }
        } while (pilihanPelanggan != 4);
    }
}