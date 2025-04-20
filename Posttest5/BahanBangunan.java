public final class BahanBangunan { // keyword finall
    private final int id; // Final attribute
    private String nama;
    private String deskripsi;
    private double harga;
    private int stok;

    public BahanBangunan(int id, String nama, String deskripsi, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
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

    public final void tampilkan() { // Final method
        System.out.println("ID: " + id + " | Nama: " + nama + " | Deskripsi: " + deskripsi +
                " | Harga: Rp" + harga + " | Stok: " + stok);
    }
}
