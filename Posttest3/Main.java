import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== TOKO MATERIAL =====");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Pelanggan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> Admin.menuAdmin();
                case 2 -> Pelanggan.menuLoginPelanggan();
                case 3 -> System.out.println("Terima kasih telah menggunakan layanan kami.");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);

        input.close();
    }
}