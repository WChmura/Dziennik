package Common;

public class MockModel {
    public static UserType userType;

    public MockModel() {
        if(userType==null)
            userType=UserType.student;
    }
    public boolean logIn(String[] loginData){
        if(loginData[0]=="admin"&&loginData[1]=="admin"){
            return true;
        }
        else
            return false;
    }
}
