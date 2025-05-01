import java.util.Scanner;

public class Main {
    public static void tampilkanHeader(String judul) {
        int panjang = judul.length() + 4;
        System.out.println("\n" + "=".repeat(panjang));
        System.out.println("  " + judul + "  ");
        System.out.println("=".repeat(panjang));
    }

    // Static method untuk menampilkan informasi aplikasi
    public static void tampilkanInfoAplikasi() {
        System.out.println("\nAplikasi Toko Material v1.0");
        System.out.println("Total bahan terdaftar: " + BahanBangunan.getTotalBahanTerdaftar());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        tampilkanInfoAplikasi();

        do {
            tampilkanHeader("TOKO MATERIAL");
            System.out.println("1. Admin");
            System.out.println("2. Pelanggan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            // Error handling untuk input menu
            try {
                pilihan = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Input harus berupa angka!");
                input.nextLine();
                pilihan = 0;
                continue;
            }

            switch (pilihan) {
                case 1 -> {
                    tampilkanHeader("MENU ADMIN");
                    Admin.menuAdmin();
                }
                case 2 -> {
                    tampilkanHeader("MENU PELANGGAN");
                    Pelanggan.menuLoginPelanggan();
                }
                case 3 -> {
                    tampilkanHeader("TERIMA KASIH");
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
        
        input.close();
    }
}