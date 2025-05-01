public final class BahanBangunan implements Displayable {
    private final int id;
    private String nama;
    private String deskripsi;
    private double harga;
    private int stok;
    private static int totalBahanTerdaftar = 0; // Variabel static

    public BahanBangunan(int id, String nama, String deskripsi, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
        totalBahanTerdaftar++; // Menambah counter setiap objek dibuat
    }

    // Implementasi method dari interface
    @Override
    public void tampilkanInfoSingkat() {
        System.out.println("[" + id + "] " + nama + " - Rp" + harga);
    }
    
    @Override
    public void tampilkanInfoLengkap() {
        System.out.println("\n===== DETAIL BAHAN =====");
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
        System.out.println("Deskripsi: " + deskripsi);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Stok: " + stok);
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public final void tampilkan() {
        tampilkanInfoSingkat();
        System.out.println("ID: " + id + " | Nama: " + nama + " | Deskripsi: " + deskripsi +
                " | Harga: Rp" + harga + " | Stok: " + stok);
    }

    public static int getTotalBahanTerdaftar() {
        return totalBahanTerdaftar;
    }

}