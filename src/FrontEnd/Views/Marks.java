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
    private int numOfSubjects;
    private Integer[] subjectsIds;
    private int maxNumOfMarks;
    private Database.pojo.Mark[] marks;
    private String[] studentNames;
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
        maxNumOfMarks=25;
        model = createNewModel();
        if(userType==UserType.student||userType==UserType.parent){
            firstName = model.getFirstName();
            secondName = model.getlastName();
        }
        else{
            if(userType==UserType.teacher)
                studentNames = model.getNamesOfGroup(model.getFormGroup()).toArray(new String[0]);
            else
                studentNames = model.getAllStudents().toArray(new String[0]);
            String[] studentData;
            if(receivedValue !=null)
                studentData = receivedValue.split(" ");
            else
                studentData = studentNames[0].split(" ");
            firstName = studentData[0];
            secondName = studentData[1];
        }
        marks = model.getMarks(firstName,secondName).toArray(new Mark[0]);
        subjectsIds = model.getSubjectsOfStudent(firstName,secondName).toArray(new Integer[0]);
        numOfSubjects=model.getSubjectCountOfStudent(firstName,secondName);
        marksId = new int[numOfSubjects][maxNumOfMarks];
        marksButtons = new JButton[numOfSubjects][maxNumOfMarks];
        if(userType==UserType.teacher||userType==UserType.admin){
            addTopMenu(numOfSubjects+2);
            addTeacherPanel();
        }
        else
            addTopMenu(numOfSubjects+1);
        for(int i=0;i<numOfSubjects;i++)
            addMarkPanel(i);
    }

    private JButton configureMarkButton(JButton button,int value,int i){
        if(value!=0){
            button.setText(String.valueOf(value));
            button.setBackground(Color.white);
            if(userType==UserType.teacher||userType==UserType.admin)
                button.addActionListener(ae -> editMark(i));
            else
                button.setEnabled(false);
        }
        else{
            button.setText("");
            button.setBackground(Colors.main);
            button.setEnabled(false);
        }
        button.setBorderPainted(false);
        return button;
    }

    private void editMark(int i){
        String[] changesInMark = new EditMarkForm(null,marks[i]).getData();
        if(changesInMark!=null) {
            marks[i].setMark(Integer.valueOf(changesInMark[0]));
            marks[i].setWeight(Integer.valueOf(changesInMark[1]));
            marks[i].setDescription(changesInMark[2]);
            model.updateMark(marks[i]);
            marks = model.getMarks(firstName,secondName).toArray(new Mark[0]);
            convertMarks();
            updateValues();
        }
    }

    private void addTeacherPanel(){
        JPanel teacherPanel = new JPanel();
        final JComboBox<String> comboBox = new JComboBox<>(studentNames);
        if(receivedValue !=null)
            comboBox.setSelectedItem(receivedValue);
        comboBox.addActionListener(e -> {
            String[] studentData = studentNames[comboBox.getSelectedIndex()].split(" ");
            firstName = studentData[0];
            secondName = studentData[1];
            marks = model.getMarks(firstName,secondName).toArray(new Mark[0]);
            convertMarks();
            updateValues();
        });
        teacherPanel.add(comboBox,BorderLayout.EAST);
        this.addSubPanel(teacherPanel);
    }

    private void addMarkPanel(int num){
        JPanel subjectPanel = new JPanel();
        subjectPanel.setLayout(new GridBagLayout());
        JPanel marksPanel = new JPanel();
        marksPanel.setLayout(new GridLayout(1,25,5,0));
        marksPanel.setBackground(Colors.main);
        convertMarks();

        GridBagConstraints c = new GridBagConstraints();

        for(int i=0;i<maxNumOfMarks;i++){
            marksButtons[num][i]=configureMarkButton(new JButton(),marksValues[num][i],marksId[num][i]);
            marksPanel.add(marksButtons[num][i]);
        }

        String subjectName =  model.getSubjectName(subjectsIds[num]);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 5;
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.insets = new Insets(5,0,0,5);
        subjectPanel.add(new JLabel(subjectName), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 25;
        c.gridx = 1;
        subjectPanel.add(marksPanel, c);
        this.addSubPanel(subjectPanel);
    }

    private void convertMarks(){
        marksValues = new int[numOfSubjects][maxNumOfMarks];
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
