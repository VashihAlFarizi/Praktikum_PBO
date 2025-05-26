package Penjualan;

import Data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FungsiLoginRegistrasi {
    private static ArrayList<User> daftarAkun = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    static {
        // Admin default
        daftarAkun.add(new Admin("admin", "admin123"));
    }

    public static void Registrasi() {
        System.out.println("\n===== REGISTRASI =====");
        System.out.print("Masukkan username: ");
        String username = input.nextLine();

        // Cek username sudah ada
        for (User akun : daftarAkun) {
            if (akun.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Username sudah terdaftar, coba yang lain.");
                return;
            }
        }

        System.out.print("Masukkan password: ");
        String password = input.nextLine();

        daftarAkun.add(new Pelanggan(username, password));
        System.out.println("Registrasi berhasil. Silakan login.");
    }

    public static User login() {
        System.out.println("\n===== LOGIN =====");
        System.out.print("Masukkan username: ");
        String username = input.nextLine();
        System.out.print("Masukkan password: ");
        String password = input.nextLine();

        for (User akun : daftarAkun) {
            if (akun.getUsername().equals(username) && akun.getPassword().equals(password)) {
                System.out.println("Login berhasil sebagai " + akun.getRole());
                return akun;
            }
        }

        System.out.println("Username atau password salah.");
        return null;
    }
}