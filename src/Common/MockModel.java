package Common;

import Database.DbMark;

import java.text.SimpleDateFormat;
import java.util.Random;

public class MockModel {
    public static UserType userType;
    Random r= new Random();
    public MockModel() {
        if(userType==null)
            userType=UserType.student;
    }
    public boolean logIn(String[] loginData){
        return loginData[0].equals("admin") && loginData[1].equals("admin");
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
