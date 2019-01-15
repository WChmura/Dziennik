package FrontEnd.Views;

import FrontEnd.Colors;
import FrontEnd.Forms.NewMarkForm;
import FrontEnd.Forms.SelectLessonForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;


public class Lesson extends Page{
    //to potrzebuje - tylko dla nauczycieli
    private String[] students;//imiona i nazwiska studentów
    private String groupName; //nazwa klasy
    private String[] classNames; //list klas
    //+ metodki
    // funkcja co dostanie id_klasy, id_nauczyciela, date w formacie "" i mi powie czy taka lekcja istnieje

    //to już nie
    private int[] attendances;
    private JButton[][] newMarks;
    private int[] numOfMarks;
    private int numOfStudents;

    //TODO:Form ze sprawdzaniem
    @Override
    public void createGUI() {
        model = createNewModel();
        classNames=model.getAllGroupsNames().toArray(new String[0]);
        int groupNumber = chooseGroup();
        groupName = classNames[groupNumber];
        students = model.getNamesOfGroup(groupName).toArray(new String[0]);
        numOfStudents = students.length;
        newMarks = new JButton[numOfStudents][4];
        numOfMarks = new int[numOfStudents];
        attendances = new int[numOfStudents];
        addTopMenu(numOfStudents + 2);
        this.addSubPanel(GroupNamePanel(),30);
        System.out.println("Dodano nazwe klasy");
        for(int i=0;i<numOfStudents;i++){
            this.addSubPanel(StudentPanel(i),50);
            System.out.println("Dodano studenta");
            attendances[i]=0;
        }
        addEndLessonPanel();
    }

    private JPanel GroupNamePanel(){
        JPanel classPanel = new JPanel(new GridLayout(1,10));
        classPanel.add(new JLabel(" "));
        classPanel.add(new JLabel("Klasa " + groupName));
        for(int i=0;i<8;i++){
            classPanel.add(new JLabel(" "));
        }
        System.out.println("Dodawanie nazey klasy");
        return classPanel;

    }

    private JPanel StudentPanel(int number) {
        JPanel studentPanel = new JPanel(new GridLayout(1,10,10,0));
        studentPanel.add(new JLabel(" "));
        studentPanel.add(new JLabel(students[number]));

        JButton markButton = new JButton("Dodaj ocene");
        markButton.addActionListener(ae -> addMark(number));
        studentPanel.add(markButton);
        studentPanel.add(comboBoxSubPanel(number));
        studentPanel.add(marksSubPanel(number));
        for(int i=0;i<5;i++){
            studentPanel.add(new JLabel(" "));
        }
        return studentPanel;
    }

    private void addEndLessonPanel(){
        JPanel endLessonPanel = new JPanel(new GridLayout(1,5,5,5));
        JButton button = new JButton("Zapisz zmiany");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(confirmationPane()) {
                    model.insertPresences(new Date(2019,1,21),groupName,attendances);
                }
            }
        });
        endLessonPanel.add(new JLabel(" "));
        endLessonPanel.add(button);
        for(int i=0;i<3;i++){
            endLessonPanel.add(new JLabel(" "));
        }
        this.addSubPanel(endLessonPanel,50);
    }

    private void addMark(int studentNum){
        NewMarkForm edit = new NewMarkForm(null,String.valueOf(studentNum));
        edit.setVisible(true);
        String[] changesInMark = edit.getData();
        if(changesInMark!=null) {
            newMarks[studentNum][numOfMarks[studentNum]].setText(changesInMark[1]);
            newMarks[studentNum][numOfMarks[studentNum]++].setBackground(Color.white);
            String[] studentData = students[studentNum].split(" ");
            model.addMark(studentData[0],studentData[1],new Date(2019,1,21),
                    Integer.parseInt(changesInMark[0]),Integer.parseInt(changesInMark[1]),changesInMark[2]);
        }
    }

    private JPanel marksSubPanel(int number){
        JPanel marksPanel = new JPanel(new GridLayout(1,4,5,5));
        marksPanel.setBackground(Colors.main);
        for(int i=0;i<4;i++){
            JButton button=new JButton("");
            button.setEnabled(false);
            button.setBackground(Colors.main);
            button.setBorderPainted(false);
            button.setMargin(new Insets(0,0,0,0));
            newMarks[number][i]=button;
            marksPanel.add(newMarks[number][i]);
        }
        return marksPanel;
    }

    private JComboBox<String> comboBoxSubPanel(int number){
        String[] s = {"obecny", "nieobecny", "usprawiedliwiony"};
        final JComboBox<String> comboBox = new JComboBox<>(s);
        comboBox.addActionListener(e -> {
            String studentName = (String)comboBox.getSelectedItem();
            if (studentName != null) {
                switch (studentName){
                    case "obecny":
                        attendances[number]=0;
                        break;
                    case "nieobecny":
                        attendances[number]=1;
                        break;
                    case "usprawiedliwiony":
                        attendances[number]=2;
                        break;
                    default:
                        System.out.println("Bledna wartosc");
                }
            }
        });
        return comboBox;
    }

    private int chooseGroup(){
        SelectLessonForm selectClass = new SelectLessonForm(null,classNames);
        selectClass.setVisible(true);
        String[] changesInStudents = selectClass.getData();
        if(changesInStudents!=null) {
            for(int i=0;i<classNames.length;i++){
                if(classNames[i].equals(changesInStudents[0])){
                    return i;
                }
            }
        }
        return 0;
    }
}
