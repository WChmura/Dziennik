package Common;

import Database.DbMark;
import Database.DbPresence;
import Database.DbStudent;

import java.sql.Date;
import java.util.Random;

public class MockModel {
    public static UserType userType;
    public String userName = null;
    private Random r= new Random();
    private AttendanceValues [] attendanceValues;
    String[] names = { "Nowak", "Kowalski", "Anon"};
    public MockModel() {
        if(userType==null)
            userType=UserType.student;
        attendanceValues = new AttendanceValues[35];
        attendanceValues[0]=null;
        attendanceValues[1]=AttendanceValues.absent;
        for(int i =2;i<30;i++){
            if(i%7==0)
                attendanceValues[i]=null;
            else
                attendanceValues[i]=AttendanceValues.present;
        }
        for(int i =30;i<35;i++){
            attendanceValues[i]=AttendanceValues.absentJustified;
        }
    }

    public AttendanceValues[] getAttendanceValues(){
        return attendanceValues;
    }
    public static UserType getUserType() {
        return userType;
    }

    public boolean logIn(String[] loginData){
        if (loginData[0].equals("admin") && loginData[1].equals("admin")){
            userName="Admin";
            userType=UserType.admin;
            return true;
        }else if(loginData[0].equals("tw") && loginData[1].equals("tw")){
            userType=UserType.teacher;
            userName ="Tw";
            return true;
        }else if(loginData[0].equals("s") && loginData[1].equals("s")){
            userType=UserType.student;
            userName = "s";
            return true;
        }
        return false;
    }
    public DbMark[] getMockMarks(){
        DbMark[] marks = new DbMark[20];
        for(int i=0;i<20;i++){
            marks[i]=addRandomMark();
        }
        return marks;
    }
    private DbMark addRandomMark(){
        return new DbMark(r.nextInt(1000),r.nextInt(5),17,r.nextInt(5),
                new Date(2000,5,1),r.nextInt(6)+1,1,"opis");
    }
    public DbPresence[][] getMockAttendances(int startWeek, int numOfweeks){
        DbPresence[][] attendances = new DbPresence[numOfweeks][35];
        for(int i=0;i<numOfweeks;i++){
            for(int j=0;j<35;j++){
                attendances[i][j]= new DbPresence(r.nextInt(1000),new Date(1999,2,23),1,1,1,1);
            }
        }
        return attendances;
    }
    public String[] getStudentsList(){

        return names;
    }
    public DbStudent[] getMockStudents(){
        DbStudent[] score = new DbStudent[3];
        for(int i=0;i<3;i++){
            score[i]=new DbStudent(r.nextInt(1000),r.nextInt(1000),"Jan",names[i],"Krakow",
                    "m",String.valueOf(r.nextInt(1000)));
        }
        return score;
    }

    public String getClassName(){
        return "VII A";
    }
    public String[] getClassList(){
        String score [] = {"I A", "II A", "III A","IV A","V A","VI A","VII A","VIII A"};
        return score;
    }
    public int getNumOfLessons(){
        return 35;
    }
    public String[] getAccountNames(){
        String score [] = {"I A", "II A", "III A","IV A","V A","VI A","VII A","VIII A",
        "B","C","D","E","F","G"};
        return score;
    }
}
