package FrontEnd.Views;

import Common.MockModel;
import Common.UserType;
import Database.DbMark;
import FrontEnd.Forms.EditMarkForm;
import FrontEnd.Page;
import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Marks extends Page {
    private int numOfSubjects=5;
    private DbMark[] marks;
    @Override
    public void createGUI() {
        if(MockModel.getUserType()==UserType.teacher){
            addTopMenu(numOfSubjects+2);
            addTeacherPanel();
        }
        else{
            addTopMenu(numOfSubjects+1);
        }
        marks = model.getMockMarks();
        for(int i=0;i<numOfSubjects;i++){
            addMarkPanel(i);
        }

    }
    //dodac myszke po najehaniu ->klasa wewnetrza?
    private JButton configureMarkButton(int value,int i){
        JButton button = new JButton(String.valueOf(value));
        button.setBorderPainted(false);
        button.setBackground(Color.white);
        if(MockModel.userType==UserType.teacher){
            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent ae) { editMark(i); }
            });
        }
        return button;
    }
    private void editMark(int i){
        EditMarkForm edit = new EditMarkForm(null,marks[i]);
        edit.setVisible(true);
        String[] changesInMark = edit.getData();
        if(changesInMark!=null) {
            marks[i].setMark(Integer.valueOf(changesInMark[0]));
            marks[i].setWeight(Integer.valueOf(changesInMark[1]));
            marks[i].setType(changesInMark[2]);
            marks[i].setDescription(changesInMark[3]);
        }
        //TODO:ustaw w bazie danuych tez
    }
    private void addTeacherPanel(){
    }
    private void addMarkPanel(int subjectId){
        JPanel subjectPanel = new JPanel();
        JPanel marksPanel = new JPanel();
        int numOfMarks=0;
        marksPanel.setLayout(new GridLayout(1,30,5,0));
        for(int i =0;i<marks.length;i++){
            if(marks[i].getSubjectID()==subjectId){
                JButton markButton = configureMarkButton(marks[i].getMark(),i);
                marksPanel.add(markButton);
                numOfMarks++;
            }
        }
        for(int j=numOfMarks;j<20;j++){
            marksPanel.add(new JLabel());
        }
        subjectPanel.add(new JLabel("Przedmiot: "),BorderLayout.EAST);
        subjectPanel.add(marksPanel);
        this.addSubPanel(subjectPanel,50);
    }
}
