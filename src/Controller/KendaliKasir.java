/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;
import View.KasirJam;
import java.sql.SQLException;
/**
 *
 * @author lenovot470s
 */
public interface KendaliKasir {
    public void Simpan(KasirJam ksr) throws SQLException;
    public void Ubah(KasirJam ksr) throws SQLException;
    public void Hapus(KasirJam ksr) throws SQLException;
    public void Tampil(KasirJam ksr) throws SQLException;
    public void KlikTable(KasirJam ksr) throws SQLException;
    public void Reset(KasirJam ksr) throws SQLException;
    public void AutoKodeKategori(KasirJam ksr) throws SQLException;
    public void Bayar(KasirJam ksr) throws SQLException;
    public void Hitung(KasirJam ksr) throws SQLException;
}
