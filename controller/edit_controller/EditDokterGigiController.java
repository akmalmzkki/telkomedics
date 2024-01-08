package telkomedics.controller.edit_controller;

import telkomedics.controller.AdminDashboardController;

import telkomedics.dao.edit_dao.EditDokterGigiDao;

import telkomedics.helper.TelkomedicsException;

import telkomedics.models.DokterGigi;

import telkomedics.view.AdminDashboard;
import telkomedics.view.FrameEditDokterGigi;

public class EditDokterGigiController {
    private FrameEditDokterGigi frameEditDokterGigi;
    private EditDokterGigiDao editDokterGigiDao;
    private DokterGigi dokterGigi;

    public EditDokterGigiController(FrameEditDokterGigi frameEditDokterGigi, DokterGigi dokterGigi) {
        this.frameEditDokterGigi = frameEditDokterGigi;
        frameEditDokterGigi.setVisible(true);

        this.editDokterGigiDao = new EditDokterGigiDao();

        this.frameEditDokterGigi.simpanEditButtonListener(new SimpanEditButtonListener());
        this.frameEditDokterGigi.hapusButtonListener(new HapusButtonListener());
        this.frameEditDokterGigi.kembaliButtonListener(new KembaliButtonListener());

        this.frameEditDokterGigi.getSetIdDokter().setText(dokterGigi.getId());
        this.frameEditDokterGigi.getSetNamaDokter().setText(dokterGigi.getNama());
    }

    class SimpanEditButtonListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            dokterGigi = new DokterGigi();

            try {
                dokterGigi.setId(frameEditDokterGigi.getSetIdDokter().getText());
                dokterGigi.setHari(frameEditDokterGigi.getEditHariComboBox().getSelectedItem().toString());
                dokterGigi.setNama(frameEditDokterGigi.getSetNamaDokter().getText());

                if (dokterGigi.getNama().equals("-")) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong!");
                    return;
                }
            } catch (TelkomedicsException e1) {
                System.out.println(e1.getMessage());
            }

            if (editDokterGigiDao.inputIdDokterEdit(dokterGigi)) {
                if (editDokterGigiDao.hariDuplicated(dokterGigi) > 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Sudah ada dokter pada hari tersebut");
                    return;
                } else {
                    int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mengubah data ini?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION);
                    if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
                        editDokterGigiDao.editHariNamaDokterBasedOnId(dokterGigi);
                        frameEditDokterGigi.dispose();
                        javax.swing.JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                    } else {
                        return;
                    }
    
                    frameEditDokterGigi.getSetIdDokter().setText("");
                    frameEditDokterGigi.getSetNamaDokter().setText("");
                    
                    AdminDashboard adminDashboard = new AdminDashboard();
                    new AdminDashboardController(adminDashboard);
                    frameEditDokterGigi.dispose();
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "ID Dokter tidak ditemukan!");
            }
        }
    }

    class HapusButtonListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {     
            dokterGigi = new DokterGigi();

            try {
                dokterGigi.setId(frameEditDokterGigi.getSetIdDokter().getText());
            } catch (TelkomedicsException e1) {
                System.out.println(e1.getMessage());
            }

            if (editDokterGigiDao.inputIdDokterEdit(dokterGigi)) {
                int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION);
                if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
                    editDokterGigiDao.hapusDokterGigi(dokterGigi);
                    javax.swing.JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                } else {
                    return;
                }

                frameEditDokterGigi.getSetIdDokter().setText("");

                AdminDashboard adminDashboard = new AdminDashboard();
                new AdminDashboardController(adminDashboard);
                frameEditDokterGigi.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "ID Dokter tidak ditemukan!");
            }
        }
    }

    class KembaliButtonListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            AdminDashboard adminDashboard = new AdminDashboard();
            new AdminDashboardController(adminDashboard);
            frameEditDokterGigi.dispose();
        }
    }
}
