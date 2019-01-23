package FrontEnd.Views;

import Common.UserType;
import Database.pojo.Timetable;
import FrontEnd.Forms.DeleteLesson;
import FrontEnd.Forms.LessonForm;
import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Schedule extends Page implements ActionListener {
    private String[][] subjects;
    private Timetable[][] timetables;
    private String[] allGroupsNames;
    private int maxNumOfLesson;
    private JComboBox comboBox;

    @Override
    public void createGUI() {
        maxNumOfLesson =8;
        model = createNewModel();
        if(userType==UserType.admin){
            allGroupsNames = model.getAllGroupsNames().toArray(new String[0]);
            subjects = model.getScheduleOfGroup(allGroupsNames[0]);
            timetables = model.getTimetables(allGroupsNames[0]);
            addTopMenu(maxNumOfLesson + 3);
            addAdminOptionsPanel();
        }
        else {
            subjects = model.getScheduleOfAccount();
            addTopMenu(maxNumOfLesson + 2);
        }
        addWeekLabels();
        for(int i=0;i<maxNumOfLesson;i++)
            this.addSubPanel(addLessonPanel(i));
    }

    private void addAdminOptionsPanel(){
        JPanel adminPanel = new JPanel(new GridLayout(1,6,10,10));

        JButton newLessonButton = new JButton("Dodaj nowa lekcja");
        newLessonButton.addActionListener(e -> addNewLesson());
        JButton deleteLessonButton = new JButton("Usun lekcje");
        deleteLessonButton.addActionListener(e -> deleteSelected());

        adminPanel.add(new JLabel("Wybór Klasy:"));
        comboBox = new JComboBox<>(allGroupsNames);
        comboBox.addActionListener(e -> reloadPanels());

        adminPanel.add(new JLabel(""));
        adminPanel.add(newLessonButton);
        adminPanel.add(deleteLessonButton);
        adminPanel.add(comboBox);
        adminPanel.add(new JLabel(""));
        this.addSubPanel(adminPanel);
    }

    private JPanel addLessonPanel(int number){
        JPanel lessonPanel = new JPanel(new GridLayout(1,7,5,5));
        JLabel hourLabel = new JLabel(lessonTime(number));
        hourLabel.setHorizontalAlignment(JLabel.RIGHT);
        lessonPanel.add(hourLabel);

        for(int i=0;i<5;i++){
            if(subjects[i][number]!=null) {
                String[] lessonData = breakLessonData(i, number);
                JComponent lessonLabel;
                if(userType==UserType.admin) {
                    JButton lessonButton = new JButton("<html>" + lessonData[0] + "<br>" + lessonData[1] + " " + lessonData[2]);
                    lessonButton.addActionListener(this);
                    lessonButton.setHorizontalAlignment(JButton.LEFT);
                    lessonButton.setActionCommand(i +" "+number);
                    lessonLabel = lessonButton;
                }
                else
                    lessonLabel = new JLabel("<html>" + lessonData[0] + "<br>" + lessonData[1] + " " + lessonData[2]);

                lessonLabel.setBackground(Color.white);
                lessonLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                lessonPanel.add(lessonLabel);
            }
            else
                lessonPanel.add(new JLabel(""));
        }
        lessonPanel.add(new JLabel(""));
        return lessonPanel;
    }

    private void addNewLesson(){
        String[] changes = new LessonForm(null,null).getData();
        if(changes!=null&&!doesLessonExist(changes[4],changes[5],changes[1],changes[0])) {
            model.addTimetable(
                    Integer.parseInt(changes[0]), Integer.parseInt(changes[2]), Integer.parseInt(changes[1]),
                    Integer.parseInt(changes[4]), Integer.parseInt(changes[5]), Integer.parseInt(changes[3])
            );
            reloadPanels();
        }
    }

    private void deleteSelected(){
        String[] changes = new DeleteLesson(null).getData();
        if(changes!=null) {
            if(model.doesLessonExistForGroup(Integer.parseInt(changes[1]),Integer.parseInt(changes[2]),Integer.parseInt(changes[0]))) {
                model.deleteTimetable(Integer.parseInt(changes[0]), Integer.parseInt(changes[1]), Integer.parseInt(changes[2]));
                reloadPanels();
            }
        }
    }

    private void reloadPanels(){
        subjects = model.getScheduleOfGroup((String)comboBox.getSelectedItem());
        for (int i = maxNumOfLesson+1;i>1;i--)
            this.deletePanel(i);
        for(int i =0;i<maxNumOfLesson;i++)
            this.addSubPanel(addLessonPanel(i),i+2);
        this.mainContent.repaint();
        this.setSize(this.getWidth()+1,this.getHeight());
    }

    private String lessonTime(int num){
        switch(num){
            case 0:
                return "8:00-8:45";
            case 1:
                return "8:55-9:40";
            case 2:
                return "9:50-10:35";
            case 3:
                return "10:45-11:30";
            case 4:
                return "11:45-12:30";
            case 5:
                return "12:45-13:30";
            case 6:
                return "13:40-14:25";
            case 7:
                return "14:30-15:15";
        }
        return null;
    }

    private void addWeekLabels(){
        JPanel lessonPanel = new JPanel(new GridLayout(1,7,5,5));
        lessonPanel.add(new JLabel(""));

        JLabel monLabel = new JLabel("Poniedziałek");
        monLabel.setHorizontalAlignment(JLabel.CENTER);
        lessonPanel.add(monLabel);

        JLabel tuLabel = new JLabel("Wtorek");
        tuLabel.setHorizontalAlignment(JLabel.CENTER);
        lessonPanel.add(tuLabel);

        JLabel wedLabel = new JLabel("Sroda");
        wedLabel.setHorizontalAlignment(JLabel.CENTER);
        lessonPanel.add(wedLabel);

        JLabel thuLabel = new JLabel("Czwartek");
        thuLabel.setHorizontalAlignment(JLabel.CENTER);
        lessonPanel.add(thuLabel);

        JLabel friLabel = new JLabel("Piatek");
        friLabel.setHorizontalAlignment(JLabel.CENTER);
        lessonPanel.add(friLabel);

        lessonPanel.add(new JLabel(""));
        this.addSubPanel(lessonPanel);
    }

    private String[] breakLessonData(int day,int lesson){
        return subjects[day][lesson].split(":");
    }

    private boolean doesLessonExist(String sday, String slesson,String stechaerId, String sgroupId){
        int day = Integer.parseInt(sday);
        int lesson = Integer.parseInt(slesson);
        if(model.doesLessonExistForGroup(day,lesson,Integer.parseInt(sgroupId)))
            return true;
        return model.doesLessonExistForTeacher(day, lesson, Integer.parseInt(stechaerId));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] numbers = e.getActionCommand().split(" ");
        int day = Integer.parseInt(numbers[0]);
        int number = Integer.parseInt(numbers[1]);
        String[] changes = new LessonForm(null, timetables[day][number]).getData();
        if (changes != null) {
            model.editTimetable(
                    Integer.parseInt(changes[0]), Integer.parseInt(changes[2]), Integer.parseInt(changes[1]),
                    Integer.parseInt(changes[4]), Integer.parseInt(changes[5]), Integer.parseInt(changes[3])
            );
            reloadPanels();
        }
    }
}