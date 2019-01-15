package FrontEnd.Views;

import Common.UserType;
import Database.pojo.Mark;
import FrontEnd.Colors;
import FrontEnd.Forms.EditMarkForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;

public class Marks extends Page
{
    //To potrzebuje
    private int numOfSubjects;
    private int subjectsIds[];//idki wszstkich przedmiotów tego konkretnego ucznia
    private int maxNumOfMarks = 25;
    private Database.pojo.Mark[] marks;
    private String[] studentNames;

    //to już nie
    private JButton[][] marksButtons;
    private int[][] marksValues;
    private int[][] marksId;
    private String firstName;
    private String secondName;

    public Marks() {
    }

    public Marks(String value) {
        super(value);
    }

    @Override
    public void createGUI() {
        model = createNewModel();
        if(userType==UserType.student||userType==UserType.parent){
            firstName = model.getFirstName();
            secondName = model.getlastName();
            marks = model.getMarks(firstName,secondName).toArray(new Mark[0]);
        }

        else {
            if(userType==UserType.teacher)
                studentNames = model.getNamesOfGroup(model.getFormGroup()).toArray(new String[0]);
            else
                studentNames = model.getAllStudents().toArray(new String[0]);
            String[] studentData = studentNames[0].split(" ");
            firstName = studentData[0];
            secondName = studentData[1];
            marks = model.getMarks(studentData[0], studentData[1]).toArray(new Mark[0]);
        }
        numOfSubjects=model.getSubjectCountOfStudent(firstName,secondName);
        marksValues = new int[numOfSubjects][maxNumOfMarks];
        marksId = new int[numOfSubjects][maxNumOfMarks];
        marksButtons = new JButton[numOfSubjects][maxNumOfMarks];
        if(userType==UserType.teacher){
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

    private JButton configureMarkButton(JButton button,int value,int i){
        if(value!=0){
            button.setText(String.valueOf(value));
            button.setBorderPainted(false);
            button.setBackground(Color.white);
            if(userType==UserType.teacher){
                button.setEnabled(true);
                button.addActionListener(ae -> editMark(i));
            }
        }
        else{
            button.setText("");
            button.setBorderPainted(false);
            button.setBackground(Colors.main);
            button.setEnabled(false);
        }
        return button;
    }

    private void editMark(int i){
        EditMarkForm edit = new EditMarkForm(null,marks[i]);
        edit.setVisible(true);
        String[] changesInMark = edit.getData();
        if(changesInMark!=null) {
            marks[i].setMark(Integer.valueOf(changesInMark[1]));
            marks[i].setWeight(Integer.valueOf(changesInMark[2]));
            marks[i].setDescription(changesInMark[3]);
            model.updateMark(marks[i]);
        }
    }

    private void addTeacherPanel(){
        JPanel teacherPanel = new JPanel();
        final JComboBox<String> comboBox = new JComboBox<>(studentNames);
        comboBox.addActionListener(e -> {
            System.out.println("Nowy uczeń");
            String[] studentData = studentNames[comboBox.getSelectedIndex()].split(" ");
            firstName = studentData[0];
            secondName = studentData[1];
            marks = model.getMarks(firstName,secondName).toArray(new Mark[0]);
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
            marksButtons[num][i]=configureMarkButton(new JButton(),marksValues[num][i],marksId[num][i]);
            marksPanel.add(marksButtons[num][i]);
        }
        subjectPanel.add(new JLabel("Przedmiot: "+ num),BorderLayout.EAST);
        subjectPanel.add(marksPanel);
        this.addSubPanel(subjectPanel,50);
    }

    private void convertMarks(){
        for(int i=0;i<numOfSubjects;i++) {
            int numOfMarks=0;
            int subjectId = subjectsIds[i];
            for (int j = 0; j < marks.length; j++) {
                if (marks[j].getSubjectID() == subjectId) {
                    marksValues[i][numOfMarks]=marks[j].getMark();
                    marksId[i][numOfMarks++]=j;
                }
            }
            for(int j=numOfMarks;j<maxNumOfMarks;j++){
                marksValues[i][j]=0;
                marksId[i][j]=0;
            }
        }
    }

    private void updateValues(){
        for(int i=0;i<numOfSubjects;i++){
            for(int j=0;j<maxNumOfMarks;j++){
                marksButtons[i][j]=configureMarkButton(marksButtons[i][j],marksValues[i][j],marksId[i][j]);
                marksButtons[i][j].repaint();
            }
        }
    }
}
