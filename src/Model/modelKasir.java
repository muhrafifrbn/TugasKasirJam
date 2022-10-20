/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Controller.KendaliKasir;
import View.KasirJam;
import java.sql.SQLException;
import Koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author lenovot470s
 */
public class modelKasir implements KendaliKasir{
       int harga, hargaDiameter, total, bayar, stok, jumlahBarang;
       String jenis, kodeJam;
       
       
    @Override
    public void Simpan(KasirJam ksr) throws SQLException {
      
    }

    @Override
    public void Ubah(KasirJam ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Hapus(KasirJam ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Tampil(KasirJam ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void KlikTable(KasirJam ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Reset(KasirJam ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AutoKodeKategori(KasirJam ksr) throws SQLException {
         PreparedStatement statement = null;
        Connection con = koneksi.getcon();
       int nomor_berikutnya = 0;
       String urutan = "";
        try {
            
            String COUNTER = "SELECT ifnull(max(convert(right(no_transaksi,5), signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_transaksi,5), signed integer))),0) as panjang FROM datakasir";
            
            statement = con.prepareStatement(COUNTER);
            ResultSet rs2 = statement.executeQuery();
            if(rs2.next()){
                nomor_berikutnya = rs2.getInt("kode") + 1;
               if (rs2.getInt("kode") != 0) {
                            if (rs2.getInt("panjang") == 1) {
                                urutan = "NMTSI" + "0000" + nomor_berikutnya;
                            } else if (rs2.getInt("panjang") == 2) {
                               urutan = "NMTSI" + "000" + nomor_berikutnya;
                            }else if (rs2.getInt("panjang") == 3) {
                               urutan = "NMTSI" + "00" + nomor_berikutnya;
                            }else if (rs2.getInt("panjang") == 4) {
                               urutan = "NMTSI" + "0" + nomor_berikutnya;
                            }else if (rs2.getInt("panjang") == 5) {
                               urutan = "NMTSI"+ nomor_berikutnya;
                            }
                    ksr.txtTransaksi.setText(urutan);
                }
               else {
                   urutan = "NMTSI"+ "00001";
                  ksr.txtTransaksi.setText(urutan);
               }
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    @Override
    public void Bayar(KasirJam ksr) throws SQLException {
       bayar = Integer.valueOf(ksr.txtPembayaran.getText());
       if(bayar < total){
           JOptionPane.showMessageDialog(null, "Uang Anda Tidak Cukup!");
           ksr.txtKembalian.setText("");
       }else if(bayar == total){
           ksr.txtKembalian.setText("Tidak Ada Kembalian");
       }else{
           bayar -= total;
            ksr.txtKembalian.setText("RP."+String.valueOf(bayar));
       }
    }

    @Override
    public void Hitung(KasirJam ksr) throws SQLException {
       jumlahBarang = Integer.valueOf(ksr.txtJumlahBarang.getText());
      if(jumlahBarang > stok){
         JOptionPane.showMessageDialog(null, "Stock Hannya Tersisa "+stok);
         ksr.txtJumlahBarang.setText("");
      }
      else{
        int hargaJam = harga;
        int hasilHargaAksesoris = DataAksesoris(ksr) * jumlahBarang;
        int hasiHargaDiameter = TampilHargaDiamterJam(ksr) * jumlahBarang;
        int hasilHargaJam = hargaJam*jumlahBarang;
        total = hasilHargaJam + hasiHargaDiameter + hasilHargaAksesoris;
       ksr.lblTotalHarga.setText("RP."+String.valueOf(total)); 
          UpdateDataStokJam(ksr);
      }
    }
    
    public void TampilDataJam(KasirJam ksr) throws SQLException{
         String tmp = (String) ksr.cmbMerkJam.getSelectedItem();
        try {
            Connection con = koneksi.getcon();
            String sql = "SELECT kode_jam,jenis_jam,harga,stok FROM barangjam WHERE merek_jam=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tmp);
            ResultSet rs = pst.executeQuery();
           
            if (rs.next()) {
                ksr.lblHargaJam.setText("RP."+rs.getString("harga"));
                ksr.lblJenis.setText("Jam "+rs.getString("jenis_jam"));
                harga = Integer.valueOf(rs.getString("harga"));
                jenis = rs.getString("jenis_jam");
                stok = Integer.valueOf(rs.getString("stok"));
                kodeJam = rs.getString("kode_jam");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void TampilComboMerkJam(KasirJam ksr) throws SQLException{
        try {
            Connection con = koneksi.getcon();
            String sql = "SELECT * FROM barangjam";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
                ksr.cmbMerkJam.addItem(rs.getString("merek_jam"));
            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
   public int TampilHargaDiamterJam(KasirJam ksr) throws SQLException{
        if(ksr.cmbDiameter.getSelectedItem().equals("4,5")){
            ksr.lblHargaDiameter.setText("RP.50000");
            hargaDiameter = 50000;
        }else if(ksr.cmbDiameter.getSelectedItem().equals("2,6")){
            ksr.lblHargaDiameter.setText("RP.30000");
            hargaDiameter = 30000;
        }else if(ksr.cmbDiameter.getSelectedItem().equals("3,4")){
            ksr.lblHargaDiameter.setText("RP.200000");
            hargaDiameter = 20000;
        }
        return hargaDiameter;
   }
    
   public int DataAksesoris(KasirJam ksr) throws SQLException{
        String aksesoris;
       int hargaAksesoris = 0;
       aksesoris ="";
        if (ksr.cbStrap.isSelected()) {
           aksesoris += ksr.cbStrap.getText() + ", ";
           hargaAksesoris += 20000;
        }
        if (ksr.cbBaterai.isSelected()) {
            aksesoris += ksr.cbBaterai.getText() + ", ";
             hargaAksesoris += 5000;
        }
        if (ksr.cbBox.isSelected()) {
            aksesoris += ksr.cbBox.getText() + ", ";
            hargaAksesoris += 10000;
        }
        if (aksesoris.equals("")) {
            ksr.txtTambahan.setText("Anda Tidak Memilih Aksesoris Tambahan");            
        } else {
           aksesoris = aksesoris.substring(0, aksesoris.length()-2) + ".";
             ksr.txtTambahan.setText(aksesoris);
        } 
        return hargaAksesoris;
   }
   
   public void UpdateDataStokJam(KasirJam ksr) throws SQLException{
      String tmp = (String) ksr.cmbMerkJam.getSelectedItem();
      stok -= jumlahBarang;
       try {
            Connection con = koneksi.getcon();
            String sql = "UPDATE barangjam SET stok=? WHERE kode_jam=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, stok);
            pst.setString(2, kodeJam);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            pst.close();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
            System.out.println(e);
       }
   }
}
