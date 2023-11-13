/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Students;

/**
 *
 * @author hoantp
 */
public class StudentsCtroller {
    ArrayList<Students> listDB = new ArrayList<>();

    public ArrayList<Students> fullDB() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            String sql = "select * from grade g join STUDENTS s on g.MASV = s.MASV";

            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Students s = new Students(resultSet.getString("MASV"), resultSet.getString("Hoten"), resultSet.getString("Email")
                        , resultSet.getString("SoDT") , resultSet.getString("Diachi"), resultSet.getString("Hinh"), resultSet.getBoolean("Gioitinh"));
                listDB.add(s);
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
        return listDB;
    }
}
