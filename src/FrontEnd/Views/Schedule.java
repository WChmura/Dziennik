package FrontEnd.Views;

import Common.UserType;
import FrontEnd.Forms.DeleteLesson;
import FrontEnd.Forms.LessonForm;
import FrontEnd.Page;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Schedule extends Page {
    private String[][] subjects;
    private String[] allGroupsNames;
    private int maxNumOfLesson;
    private boolean isAdmin;
    private JComboBox comboBox;
    @Override
    public void createGUI() {
        maxNumOfLesson =8;
        model = createNewModel();
        if(userType!=UserType.admin){
            subjects = model.getScheduleOfAccount();
            addTopMenu(maxNumOfLesson + 2);
            isAdmin = false;
        }
        else {
            allGroupsNames = model.getAllGroupsNames().toArray(new String[0]);
            subjects = model.getScheduleOfGroup(allGroupsNames[0]);
            addTopMenu(maxNumOfLesson + 3);
            isAdmin=true;
            addAdminOptionsPanel();
        }
        addWeekLabels();
        for(int i=0;i<maxNumOfLesson;i++){
            this.addSubPanel(addLessonPanel(i),50);
        }
    }

    private void addAdminOptionsPanel(){
        JPanel adminPanel = new JPanel(new GridLayout(1,6,10,10));
        adminPanel.add(new JLabel(""));

        JButton newLessonButton = new JButton("Dodaj nowa lekcja");
        newLessonButton.addActionListener(e -> addNewLesson());
        adminPanel.add(newLessonButton);

        JButton deleteLessonButton = new JButton("Usun lekcje");
        deleteLessonButton.addActionListener(e -> deleteSelected());
        adminPanel.add(deleteLessonButton);

        adminPanel.add(new JLabel("Wybór Klasy:"));

        comboBox = new JComboBox<>(allGroupsNames);
        comboBox.addActionListener(e -> {
            reloadGroup();
            reloadPanels();
        });
        adminPanel.add(comboBox);
        adminPanel.add(new JLabel(""));
        this.addSubPanel(adminPanel,50);
    }

    private JPanel addLessonPanel(int number){
        JPanel lessonPanel = new JPanel(new GridLayout(1,7,5,5));
        JLabel hourLabel = new JLabel(lessonTime(number));
        hourLabel.setHorizontalAlignment(JLabel.RIGHT);
        lessonPanel.add(hourLabel);

        for(int i=0;i<5;i++){
            if(subjects[i][number]!=null) {
                String[] lessonData = breakLessonData(i, number);
                JButton lessonLabel = new JButton("<html>" + lessonData[0] + "<br>" + lessonData[1] + " " + lessonData[2]);
                lessonLabel.setHorizontalAlignment(JButton.LEFT);
                lessonLabel.setBackground(Color.white);
                lessonLabel.setForeground(Color.BLACK);
                if (isAdmin) {
                    lessonLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LessonForm newLesson = new LessonForm(null, null);
                            newLesson.setVisible(true);
                            String[] changes = newLesson.getData();
                            if (changes != null) {
                                //TODO;dodac edycjelekcji
                            /*reloadGroup(-1);
                            reloadPanels();*/
                            }
                        }
                    });
                } else {
                    lessonLabel.setFocusable(false);
                }
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                lessonLabel.setBorder(border);
                lessonPanel.add(lessonLabel);
            }
            else
                lessonPanel.add(new JLabel(""));
        }
        lessonPanel.add(new JLabel(""));
        return lessonPanel;
    }

    private void addNewLesson(){
        LessonForm newLesson = new LessonForm(null,null);
        newLesson.setVisible(true);
        String[] changes = newLesson.getData();
        if(changes!=null&&!doesLessonExist(changes[4],changes[5],changes[1],changes[0])) {
            System.out.println("Id klasy "+changes[0]);
            System.out.println("Id sali "+changes[2]);
            System.out.println("Id nauczyciela "+changes[1]);
            System.out.println("dzien "+changes[4]);
            System.out.println("godzina "+changes[5]);
            System.out.println("Id przedmiotu "+changes[3]);
            model.addTimetable(
                    Integer.parseInt(changes[0]),
                    Integer.parseInt(changes[2]),
                    Integer.parseInt(changes[1]),
                    Integer.parseInt(changes[4]),
                    Integer.parseInt(changes[5]),
                    Integer.parseInt(changes[3])
            );
            System.out.println("Dodawanie lekcji");
            reloadGroup();
            reloadPanels();
        }
    }

    private void deleteSelected(){
        DeleteLesson newLesson = new DeleteLesson(null);
        newLesson.setVisible(true);
        String[] changes = newLesson.getData();
        if(changes!=null) {
            //TODO;dodac usuwanie lekcji
            subjects[Integer.parseInt(changes[0])][Integer.parseInt(changes[1])]=null;
            System.out.println("Usuwanie lekcji");
            reloadPanels();
        }
    }

    private void reloadGroup(){
        subjects = model.getScheduleOfGroup((String)comboBox.getSelectedItem());
    }

    private void reloadPanels(){
        for (int i = maxNumOfLesson+1;i>1;i--){
            this.deletePanel(i);
        }
        for(int i =0;i<maxNumOfLesson;i++){
            this.addSubPanel(addLessonPanel(i),50,i+2);
        }
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
        this.addSubPanel(lessonPanel,50);
    }

    private String[] breakLessonData(int day,int lesson){
        String string = subjects[day][lesson];
        return string.split(":");
    }

    private boolean doesLessonExist(String sday, String slesson,String stechaerId, String sgroupId){
        System.out.println("Badanie czy jest");
        System.out.println(sday+","+slesson);
        System.out.print("Uczy: " +stechaerId);
        System.out.println(" Klase:"+ sgroupId);
        int day = Integer.parseInt(sday);
        int lesson = Integer.parseInt(slesson);
        int teacherId = Integer.parseInt(stechaerId);
        int groupId = Integer.parseInt(sgroupId);
        if(model.doesLessonExistForGroup(day,lesson,groupId)) {
            System.out.println("jest dla klasy");
            return true;
        }
        if(model.doesLessonExistForTeacher(day,lesson,teacherId)) {
            System.out.println("jest dla nauczyciela");
            return true;
        }
        System.out.println("Nie ma");
        return false;
    }

}