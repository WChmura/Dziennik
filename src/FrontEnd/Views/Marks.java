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
        addPanel();
        marks = model.getMockMarks();
        JPanel subjectsPanel = new JPanel();
        subjectsPanel.setSize(mainContent.getWidth(),mainContent.getHeight());
        subjectsPanel.setLayout(new GridLayout(numOfSubjects+1,1,0,0));
        for(int i=0;i<numOfSubjects;i++){
            JPanel subjectPanel = new JPanel();
            int numOfMarks=0;
            JPanel marksPanel = new JPanel();
            marksPanel.setLayout(new GridLayout(1,40,5,0));
            JLabel label = new JLabel("Przedmiot: "+i);
            //label.setSize(80,20);
            subjectPanel.add(label,BorderLayout.EAST);
            for(int j =0;j<marks.length;j++){
                if(marks[j].getSubjectID()==i){
                    JButton markButton = configureMarkButton(marks[j].getMark(),j);
                    //markButton.setSize(80,20);
                    marksPanel.add(markButton);
                    numOfMarks++;
                }
            }
            for(int j=numOfMarks;j<20;j++){
                JLabel emptyLabel = new JLabel();
                marksPanel.add(emptyLabel);
            }
            subjectPanel.setSize(mainContent.getWidth(),50);
            subjectPanel.add(marksPanel);
            subjectsPanel.add(subjectPanel);
        }
        mainContent.add(subjectsPanel,BorderLayout.NORTH);
    }
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
        /*DbMark editedMark = edit.ge();
        if(editedMark!=null) {
            marks[i]=editedMark
        }*/
    }
    private void addTeacherPanel(){

    }
}
