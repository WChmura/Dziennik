package FrontEnd.Views;

import Common.AttendanceValues;
import Common.UserType;
import Database.DbMark;
import Database.DbStudent;
import FrontEnd.Forms.EditMarkForm;
import FrontEnd.Forms.NewMarkForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lesson extends Page{
    DbStudent students[];
    AttendanceValues attendances[];
    int numOfStudents;
    int lessonId;
    int teacherId;
    @Override
    public void createGUI() {
        students = model.getMockStudents();
        numOfStudents = students.length;
        attendances = new AttendanceValues[numOfStudents];
        addTopMenu(numOfStudents+2);
        for(int i=0;i<numOfStudents;i++){
            addStudentPanel(i);
            attendances[i]=AttendanceValues.present;
        }
        addEndLessonPanel();
    }

    private void addStudentPanel(int number) {
        JPanel studentPanel = new JPanel(new GridLayout(1,10,10,0));
        String name = students[number].getFirstName()+" "+students[number].getSecondName();
        studentPanel.add(new JLabel(" "));
        studentPanel.add(new JLabel(name));

        JButton markButton = new JButton("Dodaj ocene");
        markButton.addActionListener(ae -> addMark(number));
        studentPanel.add(markButton);

        String[] s = {"obecny", "nieobecny", "usprawiedliwiony"};
        final JComboBox<String> comboBox = new JComboBox<>(s);
        comboBox.addActionListener(e -> {
            String studentName = (String)comboBox.getSelectedItem();
            if (studentName != null) {
                switch (studentName){
                    case "obecny":
                        attendances[number]=AttendanceValues.present;
                        break;
                    case "nieobecny":
                        attendances[number]=AttendanceValues.absent;
                        break;
                    case "usprawiedliwiony":
                        attendances[number]=AttendanceValues.absentJustified;
                        break;
                        default:
                            System.out.println("Bledna wartosc");
                }
            }
        });
        studentPanel.add(comboBox);
        for(int i=0;i<6;i++){
            studentPanel.add(new JLabel(" "));
        }
        this.addSubPanel(studentPanel,30);
    }
    private void addEndLessonPanel(){

    }
    private void addMark(int studentNum){
        NewMarkForm edit = new NewMarkForm(null,String.valueOf(studentNum));
        edit.setVisible(true);
        String[] changesInMark = edit.getData();
        if(changesInMark!=null) {
            // setSize
            //TODO: dopisać jak będzie połączenie z bazą
            //new DbMark();
        }
    }
}
