package Common;

import Database.DbMark;

import java.text.SimpleDateFormat;
import java.util.Random;

public class MockModel {
    public static UserType userType;
    public String userName = null;
    private Random r= new Random();
    private AttendanceValues [][] attendanceValues;
    public MockModel() {
        if(userType==null)
            userType=UserType.student;
        attendanceValues = new AttendanceValues[2][34];
        attendanceValues[0][0]=attendanceValues[1][0]=AttendanceValues.absent;
        for(int i =1;i<29;i++){
            if(i%7==0)
                attendanceValues[0][i]=attendanceValues[1][i]=null;
            else
                attendanceValues[0][i]=attendanceValues[1][i]=AttendanceValues.present;
        }
        for(int i =29;i<34;i++){
            attendanceValues[0][i]=attendanceValues[1][i]=AttendanceValues.absentJustified;
        }
    }

    public AttendanceValues[][] getAttendanceValues(int numOfweeks,int startWeek){
        //Todo:powinno zwraca© tylko kilka tygodni
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
                new SimpleDateFormat(),"typ",r.nextInt(6)+1,1,"opis");
    }
}
