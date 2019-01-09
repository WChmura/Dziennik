package FrontEnd.Views;

import Common.MockModel;
import Common.UserType;
import FrontEnd.Colors;
import FrontEnd.Forms.EditMarkForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Marks extends Page {
    //To potrzebuje
    private int numOfSubjects=5;//ile ten konkrenty uczen ma przedmiotow
    private int subjectsIds[];//idki wszstkich przedmiotów tego konkretnego ucznia
    private int maxNumOfMarks = 25;
    private Database.pojo.Mark[] marks; // wszystkie jego ocenki
    // + metoda do zmiany oceny, ja podaje cale pojo.Mark <- tylko dla nauczycieli
    private String studentNames;// + tylko dla nauczycieli -ich uczniowie i adminów -wszyscy uczniowie

    //to już nie
    private JButton marksButtons[][];
    private int marksValues[][];
    private int marksId[][];
    @Override
    public void createGUI() {
        marks = model.getMockMarks();
        marksValues = new int[numOfSubjects][maxNumOfMarks];
        marksId = new int[numOfSubjects][maxNumOfMarks];
        marksButtons = new JButton[numOfSubjects][maxNumOfMarks];
        if(MockModel.getUserType()==UserType.teacher){
            addTopMenu(numOfSubjects+2);
            addTeacherPanel();
        }
        else{
            addTopMenu(numOfSubjects+1);
        }
        for(int i=0;i<numOfSubjects;i++){
            addMarkPanel(i);
        }

    }
    //dodac myszke po najechaniu ->klasa wewnetrzna?
    //abo bardziej jakiś mouse listener
    private JButton configureMarkButton(JButton button,int value,int i){
        if(value!=0){
            button = new JButton(String.valueOf(value));
            button.setBorderPainted(false);
            button.setBackground(Color.white);
            if(MockModel.userType==UserType.teacher){
                button.addActionListener(ae -> editMark(i));
            }
        }
        else{
            button = new JButton("");
            button.setBorderPainted(false);
            button.setBackground(Colors.main);
        }
        return button;
    }

    private void editMark(int i){
        EditMarkForm edit = new EditMarkForm(null,marks[i]);
        edit.setVisible(true);
        String[] changesInMark = edit.getData();
        if(changesInMark!=null) {
            //TODO;dodac zmiane oceny
        }
    }

    private void addTeacherPanel(){
        JPanel teacherPanel = new JPanel();
        String[] s = model.getStudentsList();
        final JComboBox<String> comboBox = new JComboBox<>(s);
        comboBox.addActionListener(e -> {
            //String studentName = (String)comboBox.getSelectedItem();
            //TODO:załaduj nowe dane
            marks = model.getMockMarks();
            convertMarks();
            updateValues();
        });
        teacherPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(teacherPanel,30);
    }

    private void addMarkPanel(int num){
        JPanel subjectPanel = new JPanel();
        JPanel marksPanel = new JPanel();
        subjectPanel.setBackground(Colors.main);
        marksPanel.setBackground(Colors.main);
        convertMarks();
        marksPanel.setLayout(new GridLayout(1,25,5,0));
        for(int i=0;i<maxNumOfMarks;i++){
            marksPanel.add(configureMarkButton(new JButton(),marksValues[num][i],marksId[num][i]));
        }
        subjectPanel.add(new JLabel("Przedmiot: "+ num),BorderLayout.EAST);
        subjectPanel.add(marksPanel);
        this.addSubPanel(subjectPanel,50);
    }
    private void convertMarks(){
        for(int i=0;i<numOfSubjects;i++) {
            int numOfMarks=0;
            int subjectId = i;
            for (int j = 0; j < marks.length; j++) {
                if (marks[j].getSubjectID() == subjectId) {
                    marksValues[i][numOfMarks]=marks[j].getMark();
                    marksId[i][numOfMarks++]=j;
                }
            }
        }
    }
    private void updateValues(){
        for(int i=0;i<numOfSubjects;i++){
            for(int j=0;j<maxNumOfMarks;j++){

            }
        }
    }
}
