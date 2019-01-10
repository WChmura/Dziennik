package FrontEnd.Views;

import Common.AttendanceValues;
import Common.MockModel;
import Common.UserType;
import FrontEnd.Colors;
import FrontEnd.Forms.ChangeClassForm;
import FrontEnd.Forms.NewMarkForm;
import FrontEnd.Forms.SelectLessonForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lesson extends Page{
    //to potrzebuje - tylko dla nauczycieli
    private String[] students;//imiona i nazwiska studentów
    private String groupName; //nazwa klasy
    private String[] classNames; //list klas
    //+ metodki
    // na dodawanie nowej ocenki
    // taka co jest dam tablice czy uczen byl obecny, a ona to wbije do bazy
    // funkcja co dostanie id_klasy, id_nauczyciela, date w formacie "" i mi powie czy taka lekcja istnieje

    //to już nie
    private AttendanceValues[] attendances;
    private JButton[][] newMarks;
    private int[] numOfMarks;
    private int numOfStudents;
    int lessonId;
    int teacherId;
    //TODO: dopisać okienko pokzywania ocen
    @Override
    public void createGUI() {
        classNames=model.getClassList();
        int groupNumber = chooseGroup();
        groupName = classNames[groupNumber];
        students = model.getMockStudents();
        numOfStudents = 3;
        newMarks = new JButton[numOfStudents][4];
        numOfMarks = new int[numOfStudents];
        attendances = new AttendanceValues[numOfStudents];
        addTopMenu(numOfStudents + 2);
        this.addSubPanel(GroupNamePanel(),30);
        System.out.println("Dodano nazwe klasy");
        for(int i=0;i<numOfStudents;i++){
            this.addSubPanel(StudentPanel(i),50);
            System.out.println("Dodano studenta");
            attendances[i]=AttendanceValues.present;
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
                    //TODO: dopisanie obecnosci calej klasy
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
            //TODO: wysłac do bazy
            //new DbMark();
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
        return comboBox;
    }

    private int chooseGroup(){
        //TODO:to by mozna przerobic, bo brzydkie i na około
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
