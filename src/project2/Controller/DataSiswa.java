package project2.Controller;

import project2.Data    baseConnection;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSiswa extends javax.swing.JFrame{{
    Connection koneksi;
    DefaultTableModel dtm;

    DataSiswa() {
        koneksi = DatabaseCoxnnection.getKoneksi("localhost", "3306", "root", "very_strong_password", "tugas_bd_sekolah");
    }

    public void showData() {
        String[] kolom = {"nis", "nama_siswa", "alamat", "jenis_kelamin"};
        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stm = koneksi.createStatement();
            String query = "SELECT * FROM siswa";
            ResultSet rs = stm.executeQuery(query);
            int no = 1;

            while (rs.next()) {
                String nis = rs.getString("nis");
                String nama = rs.getString("nama_siswa");
                String alamat = rs.getString("alamat");
                String jenis_kelamin = rs.getString("jenis_kelamin");

                dtm.addRow(new String[]{no + "" , nis, nama, alamat, jenis_kelamin});
                no++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tbl_siswa.setModel(dtm);
    }
}