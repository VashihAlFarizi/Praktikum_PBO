public class BahanBangunan {
    private int id;
    private String nama;
    private String deskripsi;
    private int harga;
    private int stok;

    public BahanBangunan(int id, String nama, String deskripsi, int harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
    }

    // Fungsi utama
    public void tampilkan() {
        System.out.println("ID: " + id + " | Nama: " + nama + " | Deskripsi: " + deskripsi +
                " | Harga: Rp" + harga + " | Stok: " + stok);
    }

    // Overoloading
    public void tampilkan(boolean detail) {
        if (detail) {
            System.out.println("======= Detail Bahan Bangunan =======");
            System.out.println("ID       : " + id);
            System.out.println("Nama     : " + nama);
            System.out.println("Deskripsi: " + deskripsi);
            System.out.println("Harga    : Rp" + harga);
            System.out.println("Stok     : " + stok);
        } else {
            tampilkan();
        }
    }
}
