import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Admin extends User {
    private static final ArrayList<BahanBangunan> daftarBahanBangunan = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);

    public Admin(String username, String password) {
        super(username, password);
    }

    public static ArrayList<BahanBangunan> getDaftarBahanBangunan() {
        return daftarBahanBangunan;
    }

    public static void menuAdmin() {
        int pilihan = 0;
        do {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Tambah Bahan Bangunan");
            System.out.println("2. Lihat Daftar Bahan Bangunan");
            System.out.println("3. Perbarui Bahan Bangunan");
            System.out.println("4. Hapus Bahan Bangunan");
            System.out.println("5. Kembali ke menu utama");
            System.out.print("Pilih menu: ");
            
            // Error Handling untuk Menu harus angka
            try {
                pilihan = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                input.nextLine();
                continue;
            }

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

    private static boolean cekNama(String nama) {
        for (BahanBangunan bahan : daftarBahanBangunan) {
            if (bahan.getNama().equalsIgnoreCase(nama)) {
                return true;
            }
        }
        return false;
    }

    private static void tambahBahanBangunan() {
        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();
        
        // Error handling untuk nama bahan yang sudah ada
        if (cekNama(nama)) {
            System.out.println("Error: Nama bahan sudah ada!");
            return;
        }

        System.out.print("Masukkan Deskripsi: ");
        String deskripsi = input.nextLine();
        
        double harga = 0;
        int stok = 0;
        
        try {
            System.out.print("Masukkan Harga: ");
            harga = input.nextDouble();
            
            System.out.print("Masukkan Stok: ");
            stok = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Harga dan stok harus berupa angka!");
            input.nextLine();
            return;
        }

        // ID otomatis (fitur tambahan no 3)
        int id = daftarBahanBangunan.isEmpty() ? 1 : 
                daftarBahanBangunan.get(daftarBahanBangunan.size() - 1).getId() + 1;

        daftarBahanBangunan.add(new BahanBangunan(id, nama, deskripsi, harga, stok));
        System.out.println("Bahan bangunan berhasil ditambahkan.");
    }

    private static void lihatBahanBangunan() {
        if (daftarBahanBangunan.isEmpty()) {
            System.out.println("Belum ada data bahan bangunan.");
            return;
        }
        
        System.out.println("\nTotal bahan terdaftar: " + BahanBangunan.getTotalBahanTerdaftar());
        System.out.println("===== DAFTAR BAHAN BANGUNAN =====");
        
        for (BahanBangunan bahan : daftarBahanBangunan) {
            bahan.tampilkanInfoSingkat(); // Menggunakan method dari interface
        }
    }

    private static void perbaruiBahanBangunan() {
        System.out.print("Masukkan ID bahan yang ingin diperbarui: ");
        int id;
        
        // error Handling untuk input id
        try {
            id = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: ID harus berupa angka!");
            input.nextLine();
            return;
        }

        for (BahanBangunan bahan : daftarBahanBangunan) {
            if (bahan.getId() == id) {
                System.out.print("Masukkan Nama Baru: ");
                String namaBaru = input.nextLine();
                
                // Error handling untuk nama bahan yang sudah ada
                if (!namaBaru.equalsIgnoreCase(bahan.getNama()) && cekNama(namaBaru)) {
                    System.out.println("Error: Nama bahan sudah digunakan!");
                    return;
                }
                
                bahan.setNama(namaBaru);
                System.out.print("Masukkan Deskripsi Baru: ");
                bahan.setDeskripsi(input.nextLine());
                
                // error handling untuk input harga dan stok baru
                try {
                    System.out.print("Masukkan Harga Baru: ");
                    bahan.setHarga(input.nextDouble());
                    
                    System.out.print("Masukkan Stok Baru: ");
                    bahan.setStok(input.nextInt());
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Error: Harga dan stok harus berupa angka!");
                    input.nextLine();
                    return;
                }
                
                System.out.println("Data berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Error: ID tidak ditemukan.");
    }

    private static void hapusBahanBangunan() {
        System.out.print("Masukkan ID bahan yang ingin dihapus: ");
        int id;
        
        // error handling untuk input id
        try {
            id = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: ID harus berupa angka!");
            input.nextLine();
            return;
        }

        boolean removed = daftarBahanBangunan.removeIf(bahan -> bahan.getId() == id);
        if (removed) {
            System.out.println("Bahan bangunan berhasil dihapus.");
        } else {
            System.out.println("Error: ID tidak ditemukan.");
        }
    }

    @Override
    public final void aksesMenu() {
        menuAdmin();
    }
}