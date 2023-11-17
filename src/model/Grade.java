/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoantp
 */
public class Grade {

    private String StudentID, StudentName,image;
    private int no, scoreEnglish, scoreInformatic, scorePhysic;

    public Grade() {
    }

    public Grade(String StudentID, String StudentName, int scoreEnglish, int scoreInformatic, int scorePhysic) {
        this.StudentID = StudentID;
        this.StudentName = StudentName;
        this.scoreEnglish = scoreEnglish;
        this.scoreInformatic = scoreInformatic;
        this.scorePhysic = scorePhysic;
    }

    public Grade(String StudentID, String StudentName, int scoreEnglish, int scoreInformatic, int scorePhysic, String image) {
        this.StudentID = StudentID;
        this.StudentName = StudentName;
        this.image = image;
        this.scoreEnglish = scoreEnglish;
        this.scoreInformatic = scoreInformatic;
        this.scorePhysic = scorePhysic;
    }
    
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public int getScoreEnglish() {
        return scoreEnglish;
    }

    public void setScoreEnglish(int scoreEnglish) {
        this.scoreEnglish = scoreEnglish;
    }

    public int getScoreInformatic() {
        return scoreInformatic;
    }

    public void setScoreInformatic(int scoreInformatic) {
        this.scoreInformatic = scoreInformatic;
    }

    public int getScorePhysic() {
        return scorePhysic;
    }

    public void setScorePhysic(int scorePhysic) {
        this.scorePhysic = scorePhysic;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public double getScoreAverage() {
        return (double)Math.round(((double) scorePhysic +  (double) scoreEnglish +  (double) scoreInformatic) / 3*100)/100 ;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
