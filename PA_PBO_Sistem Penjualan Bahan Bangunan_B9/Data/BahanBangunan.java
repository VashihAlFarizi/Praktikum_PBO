package Data;

public class BahanBangunan {
    private int idbahan;
    private String nama;
    private String deskripsi;
    private int harga;
    private int stok;

    // Konstruktor
    public BahanBangunan(int idBahan, String nama, String deskripsi, int harga, int stok) {
        this.idbahan = idBahan;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
    }


    // Getter dan Setter
    public int getIdBahan() {
        return idbahan;
    }

    public void setIdBahan(int idBahan) {
        this.idbahan = idBahan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String Nama) {
        this.nama = Nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String Deskripsi) {
        this.deskripsi = Deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int Stok) {
        this.stok = Stok;
    }

    // Method untuk menampilkan informasi bahan dalam bentuk string
    public String infoBahanBangunan() {
        return "ID: " + idbahan + "\n" +
           "Nama: " + nama + "\n" +
           "Deskripsi: " + deskripsi + "\n" +
           "Harga: Rp " + harga + "\n" +
           "Stok: " + stok + " unit\n";
    }


    // Method tambahan untuk update stok
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (jumlah <= stok) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi untuk dikurangi sebanyak " + jumlah);
        }
    }
}