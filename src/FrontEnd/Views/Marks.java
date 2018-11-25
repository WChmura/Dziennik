package FrontEnd.Views;

import Common.UserType;
import Database.DbMark;
import FrontEnd.Page;
import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Marks extends Page {
    int numOfSubjects=4;
    @Override
    public void createGUI() {
        addPanel();
        DbMark[] marks = model.getMockMarks();
        JPanel subjectsPanel = new JPanel();
        subjectsPanel.setSize(mainContent.getWidth(),mainContent.getHeight());
        subjectsPanel.setLayout(new GridLayout(numOfSubjects+1,1,0,0));
        for(int i=0;i<numOfSubjects;i++){
            JPanel subjectPanel = new JPanel();
            int numOfMarks=0;
            JPanel marksPanel = new JPanel();
            marksPanel.setLayout(new GridLayout(1,20,5,0));
            JLabel label = new JLabel("Przedmiot: "+i);
            //label.setSize(80,20);
            subjectPanel.add(label,BorderLayout.EAST);
            for(int j =0;j<marks.length;j++){
                if(marks[j].getSubjectID()==i){
                    JButton markButton = configureMarkButton(marks[j].getMark());
                    //markButton.setSize(80,20);
                    marksPanel.add(markButton);
                    numOfMarks++;
                }
            }
            for(int j=numOfMarks;j<20;j++){
                JLabel emptyLabel = new JLabel();
                marksPanel.add(emptyLabel);
            }
            //subjectPanel.setSize(50+(20*numOfMarks),50);
            subjectPanel.add(marksPanel);
            subjectsPanel.add(subjectPanel);
        }
        mainContent.add(subjectsPanel);
    }
    private JButton configureMarkButton(int value){
        JButton button = new JButton(String.valueOf(value));
        button.setBorderPainted(false);
        //button.setFocusPainted(false);
        //button.setContentAreaFilled(false);
        button.setBackground(Color.white);
        return button;
    }
    private void addTeacherPanel(){

    }
}
