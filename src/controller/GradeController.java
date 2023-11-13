/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Grade;
import model.Students;

/**
 *
 * @author hoantp
 */
public class GradeController {

    ArrayList<Grade> listDB = new ArrayList<>();

    public ArrayList<Grade> fullDB() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            String sql = "SELECT s.MASV, s.Hoten, g.Tinhoc, g.Tienganh, g.GDTC,(g.Tinhoc+ g.Tienganh+ g.GDTC)/3[Điểm trung bình]"
                    + " FROM grade g JOIN STUDENTS s ON g.MASV = s.MASV "
                    + "order by [Điểm trung bình] desc";

            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Grade g = new Grade(resultSet.getString("MASV"), resultSet.getString("Hoten"), resultSet.getInt("Tienganh"), resultSet.getInt("Tinhoc"), resultSet.getInt("GDTC"));
                listDB.add(g);
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

    public ArrayList<Grade> save(int row, Students s, Grade g) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");

            String sqlUpdateStudent = "update STUDENTS set Hinh=? where masv = ?";
            ps = connection.prepareStatement(sqlUpdateStudent);
            ps.setString(1, s.getHinh());
            ps.setString(2, g.getStudentID());
            ps.execute();

            //Update Grade
            String sqlUpdateGrade = "update Grade set Tienganh= ?,tinhoc= ? , GDTC = ? where masv = ?";
            ps = connection.prepareStatement(sqlUpdateGrade);
            ps.setInt(1, g.getScoreEnglish());
            ps.setInt(2, g.getScoreInformatic());
            ps.setInt(3, g.getScorePhysic());
            ps.setString(4, g.getStudentID());

            ps.execute();
            listDB.set(row, g);
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

    public ArrayList<Grade> update(int row, Students s, Grade g) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            //Update Students
            String sqlUpdateStudent = "update STUDENTS set Hinh=? where masv = ?";
            ps = connection.prepareStatement(sqlUpdateStudent);
            ps.setString(1, s.getHinh());
            ps.setString(2, g.getStudentID());
            ps.execute();

            //Update Grade
            String sqlUpdateGrade = "update Grade set Tienganh= ?,tinhoc= ? , gdtc = ? where masv = ?";
            ps = connection.prepareStatement(sqlUpdateGrade);
            ps.setInt(1, g.getScoreEnglish());
            ps.setInt(2, g.getScoreInformatic());
            ps.setInt(3, g.getScorePhysic());
            ps.setString(4, g.getStudentID());

            ps.execute();
            listDB.set(row, g);

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

    public ArrayList<Grade> delete(int i, Grade g) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            // Xóa học sinh từ bảng Grade
            String sql = "delete from Grade where MASV = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, g.getStudentID());
            ps.execute();

            // Xóa học sinh từ bảng STUDENTS
            sql = "delete from STUDENTS where MASV = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, g.getStudentID());
            ps.execute();
            listDB.remove(i);
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

    public Grade search(String id) {
        Grade g = new Grade();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            String sql = "select * from grade g join STUDENTS s on g.MASV = s.MASV where s.masv = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                g = new Grade(
                        rs.getString("MASV"),
                        rs.getString("Hoten"),
                        rs.getInt("Tienganh"),
                        rs.getInt("Tinhoc"),
                        rs.getInt("GDTC"));
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
        return g;
    }

    int i = -1;

    public Grade first() {
        Grade g = listDB.get(0);
        return g;
    }

    public Grade next() {
        i++;
        if (i >= listDB.size()) {
            i = 0;
        }
        Grade g = listDB.get(i);
        return g;
    }

    public Grade prev() {
        i--;
        if (i < 0) {
            i = listDB.size() - 1;
        }
        Grade g = listDB.get(i);
        return g;
    }

    public Grade last() {
        Grade g = listDB.get(listDB.size() - 1);
        return g;
    }
}