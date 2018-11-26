package Common;

import Database.DbMark;

import java.text.SimpleDateFormat;
import java.util.Random;

public class MockModel {
    public static UserType userType;
    public String userName = null;
    private Random r= new Random();
    public MockModel() {
        if(userType==null)
            userType=UserType.student;
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
