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
                Students s = new Students(resultSet.getString("MASV"), resultSet.getString("Hoten"), resultSet.getString("Email"),
                        resultSet.getString("SoDT"), resultSet.getString("Diachi"), resultSet.getString("Hinh"), resultSet.getString("Gioitinh"));
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

    public boolean save(Students s) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            for (Students st : listDB) {
                if (st.getMASV().equalsIgnoreCase(s.getMASV())) {
                    return false;
                }
            }
            String gioiTinh = "";
            if (s.getGioitinh().equalsIgnoreCase("Nam")) {
                gioiTinh = "1";
            } else {
                gioiTinh = "0";
            }
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            String sql = "insert into students(MASV,Hoten,Email,SoDT,Gioitinh,Diachi,Hinh) values (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, s.getMASV());
            ps.setString(2, s.getHoten());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getSoDT());
            ps.setString(5, gioiTinh);
            ps.setString(6, s.getDiachi());
            ps.setString(7, s.getHinh());
            ps.execute();
            listDB.add(s);
            String sqlInsertGrade = "insert into Grade(MASV) values (?)";
            ps = connection.prepareStatement(sqlInsertGrade);
            ps.setString(1, s.getMASV());
            ps.execute();
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
        return true;
    }

    public ArrayList<Students> remove(String s) {
        Connection connection = null;
        PreparedStatement ps = null;

        for (Students st : listDB) {
            if (s.equals(st.getMASV())) {
                listDB.remove(st);
                break;
            }
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            // Xóa học sinh từ bảng GRADE
            String sql = "delete from Grade where MASV = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, s);
            ps.execute();

            // Xóa học sinh từ bảng STUDENTS
            sql = "delete from STUDENTS where MASV = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, s);
            int count = ps.executeUpdate();
            if (count <= 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public ArrayList<Students> update(Students s) {
        Connection connection = null;
        PreparedStatement ps = null;
        String gioiTinh = "";
        if (s.getGioitinh().equalsIgnoreCase("Nam")) {
            gioiTinh = "1";
        } else {
            gioiTinh = "0";
        }
        for (Students st : listDB) {
            if (st.equals(st.getMASV())) {
                st.setDiachi(s.getDiachi());
                st.setEmail(s.getEmail());
                st.setGioitinh(s.getGioitinh());
                st.setHinh(s.getHinh());
                st.setHoten(s.getHoten());
                st.setSoDT(s.getSoDT());
                break;
            }
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AsmJV3;user=sa;password=12");
            // update học sinh từ bảng STUDENTS
            String sql = "update students set diachi = ?,email=?,gioitinh=?,hinh=?,hoten=?,sodt=? where masv=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, s.getDiachi());
            ps.setString(2, s.getEmail());
            ps.setString(3, gioiTinh);
            ps.setString(4, s.getHinh());
            ps.setString(5, s.getHoten());
            ps.setString(6, s.getSoDT());
            ps.setString(6, s.getSoDT());
            ps.setString(7, s.getMASV());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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
