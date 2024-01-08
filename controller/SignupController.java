package telkomedics.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import telkomedics.dao.SignupDao;

import telkomedics.helper.TelkomedicsException;

import telkomedics.models.Mahasiswa;

import telkomedics.view.Login;
import telkomedics.view.Signup;

public class SignupController {
    private Signup signup;
    private SignupDao signupDao;
    private Mahasiswa mahasiswa;

    public SignupController(Signup signup) {
        this.signup = signup;
        this.signup.setVisible(true);

        this.signupDao = new SignupDao();

        signup.signupButtonListener(new SignupListener());
        signup.closeButtonListener(new CloseListener());
    }

    class SignupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mahasiswa = new Mahasiswa();

            try {
                mahasiswa.setId(signup.getInputNim().getText());
                mahasiswa.setNama(signup.getInputNama().getText());
                mahasiswa.setPassword(new String(signup.getInputPassword().getPassword()));
                mahasiswa.setNoHp(signup.getInputNoHp().getText());
                mahasiswa.setAlamat(signup.getInputAlamat().getText());
            } catch (TelkomedicsException e1) {
                System.out.println(e1.getMessage());
            }

            String nim = mahasiswa.getId();
            String nama = mahasiswa.getNama();
            String password = mahasiswa.getPassword();
            String no_hp = mahasiswa.getNoHp();
            String alamat = mahasiswa.getAlamat();

            if (nim.equals("") || nama.equals("") || password.equals("") || no_hp.equals("") || alamat.equals("")) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!");
            } else {
                if (nim.length() != 10) {
                    JOptionPane.showMessageDialog(null, "NIM harus 10 digit!");
                    return;
                }
                if (no_hp.length() != 12) {
                    JOptionPane.showMessageDialog(null, "No HP harus 12 digit!");
                    return;
                }
                if (!password.matches(".*[0-9].*") || !password.matches(".*[a-zA-Z].*")) {
                    JOptionPane.showMessageDialog(null, "Password harus mengandung angka dan huruf!");
                    return;
                }

                if (!signupDao.isNimExist(mahasiswa)) {
                    signupDao.insertData(mahasiswa);
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
                    signup.dispose();

                    Login login = new Login();
                    new LoginController(login);
                } else {
                    JOptionPane.showMessageDialog(null, "NIM sudah terdaftar!");
                }
            }
        }
    }

    class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            signup.dispose();

            Login login = new Login();
            new LoginController(login);
        }
    }
}
