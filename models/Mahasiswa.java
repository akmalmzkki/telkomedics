package telkomedics.models;

import telkomedics.helper.TelkomedicsException;

public class Mahasiswa extends Pengguna {
    private String nim;
    private String nama;
    private String password;
    private String noHp;
    private String alamat;

    public String getId() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    @Override
    public void setId(String nim) throws TelkomedicsException {
        if (nim == null || nim.isEmpty()) {
            throw new TelkomedicsException("nim tidak boleh kosong");
        }

        if (nim.length() != 10) {
            throw new TelkomedicsException("nim harus 10 digit");
        }

        this.nim = nim;
    }

    @Override
    public void setNama(String nama) throws TelkomedicsException {
        if (nama == null || nama.isEmpty()) {
            throw new TelkomedicsException("nama tidak boleh kosong");
        }

        this.nama = nama;
    }

    @Override
    public void setPassword(String password) throws TelkomedicsException {
        if (password == null || password.isEmpty()) {
            throw new TelkomedicsException("password tidak boleh kosong");
        }

        if (!password.matches(".*[0-9].*") || !password.matches(".*[a-zA-Z].*")) {
            throw new TelkomedicsException("password harus mengandung angka dan huruf");
        }

        this.password = password;
    }

    public void setNoHp(String noHp) throws TelkomedicsException {
        if (noHp == null || noHp.isEmpty()) {
            throw new TelkomedicsException("noHp tidak boleh kosong");
        }

        if (noHp.length() != 12) {
            throw new TelkomedicsException("noHp harus 12 digit");
        }

        this.noHp = noHp;
    }

    public void setAlamat(String alamat) throws TelkomedicsException {
        if (alamat == null || alamat.isEmpty()) {
            throw new TelkomedicsException("alamat tidak boleh kosong");
        }

        this.alamat = alamat;
    }
}
