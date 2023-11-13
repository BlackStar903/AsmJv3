/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Students;

/**
 *
 * @author hoantp
 */
public class fillTable {

    public DefaultTableModel fullDB(DefaultTableModel dtm) {
        Object objTB = new Object();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            dtm.setRowCount(0);
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            String sql = "SELECT s.MASV, s.Hoten, g.Tinhoc, g.Tienganh, g.GDTC,(g.Tinhoc+ g.Tienganh+ g.GDTC)/3[Điểm trung bình]"
                    + " FROM grade g JOIN STUDENTS s ON g.MASV = s.MASV";
            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                objTB = new Object[]{
                    resultSet.getString("MASV"),
                    resultSet.getString("Hoten"),
                    resultSet.getInt("Tienganh"),
                    resultSet.getInt("Tinhoc"),
                    resultSet.getInt("GDTC"),
                    resultSet.getDouble("Điểm trung bình")
                };
                dtm.addRow((Object[]) objTB);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GradeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GradeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dtm;
    }
}
