package Common;

import java.sql.Date;
import java.util.ArrayList;
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
    public Database.pojo.Mark[] getMockMarks(){
        Database.pojo.Mark[] marks = new Database.pojo.Mark[20];
        for(int i=0;i<20;i++){
            marks[i]=addRandomMark();
        }
        return marks;
    }
    private Database.pojo.Mark addRandomMark(){
        return new Database.pojo.Mark(r.nextInt(5),5,5,new Date(1,1,1),r.nextInt(5)+1,1,"ocenka");
    }
    public Database.pojo.Presence[][] getMockAttendances(int startWeek, int numOfweeks){
        Database.pojo.Presence[][] attendances = new Database.pojo.Presence[numOfweeks][35];
        for(int i=0;i<numOfweeks;i++)
            for(int j=0;j<35;j++)
                attendances[i][j]= new Database.pojo.Presence(new Date(1,1,1),1,1,1,1);
        return attendances;
    }
    public String[] getStudentsList(){

        return names;
    }
    public String[] getMockStudents(){
        Database.pojo.Student[] score = new Database.pojo.Student[3];
        for(int i=0;i<3;i++){
            score[i]=new Database.pojo.Student(1,"anon",String.valueOf(i),"Krakow","m","1");
        }
        String[] wynik = new String[3];
        for (int i=0;i<3;i++){
            wynik[i]=score[i].getFirstName()+" "+score[i].getSecondName();
        }
        return wynik;
    }
    public String[] getMockStudents2(){
        Database.pojo.Student[] score = new Database.pojo.Student[3];
        for(int i=0;i<3;i++){
            score[i]=new Database.pojo.Student(1,"dvnqembhygqui",String.valueOf(i),"Krakow","m","1");
        }
        String[] wynik = new String[3];
        for (int i=0;i<3;i++){
            wynik[i]=score[i].getFirstName()+" "+score[i].getSecondName();
        }
        return wynik;
    }
    public String getClassName(){
        return "VII A";
    }
    public String[] getClassList(){
        String score [] = {"I A", "II A", "III A","IV A","V A","VI A","VII A","VIII A"};
        return score;
    }
    public String[][] getStudentsByClasses(){
        String[][] score = new String[8][10];
        for(int i=0;i<8;i++){
            for(int j =0;j<10;j++)
                score[i][j]=(String.valueOf((i*10)+j));
        }
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
