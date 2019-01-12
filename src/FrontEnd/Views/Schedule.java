package FrontEnd.Views;

import Common.MockModel;
import Common.UserType;
import FrontEnd.Forms.LessonForm;
import FrontEnd.Page;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Schedule extends Page {
    //to potrzebuje
    private String subjects[][];//format np "nazwaPrzedmiotu:nazwisko:sala", w tabeli dzien godzina
    //plan moze byc zwracany po id klasy albo id nauczyciela
    // w tym drugim przypadku powinno byc "nazwaPrzedmiotu:klasa:sala"
    private String allGroupsNames[]; //nazwy wszystkich klas - tylko dla adminów
    private int maxNumOfLesson; //maksymalna ilosc godzin w dniu
    //+ metodki
    // edycja - tylko dla adminów
    // sprawdzanie czy istieje lekcja o podanym dniu i godzinie

    //Tego juz nie
    private boolean isAdmin;
    @Override
    public void createGUI() {
        subjects = model.schelude();

        maxNumOfLesson =7;//usunac z koncowej
        if(MockModel.getUserType()==UserType.admin) {
            allGroupsNames = model.getClassList();
            addTopMenu(maxNumOfLesson + 3);
            isAdmin=true;
            addAdminOptionsPanel();
        }
        else {
            addTopMenu(maxNumOfLesson + 2);
            isAdmin = false;
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
        newLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLesson();
            }
        });
        adminPanel.add(newLessonButton);

        JButton deleteLessonButton = new JButton("Usun lekcje");
        deleteLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelected();
            }
        });
        adminPanel.add(deleteLessonButton);

        adminPanel.add(new JLabel("Wybór Klasy:"));

        JComboBox comboBox = new JComboBox<>(allGroupsNames);
        comboBox.addActionListener(e -> {
            reloadGroup(comboBox.getSelectedIndex());
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
            String[] lessonData = breakLessonData(i,number);
            JButton lessonLabel = new JButton("<html>"+lessonData[0]+"<br>"+lessonData[1]+" "+lessonData[2]);
            lessonLabel.setBackground(Color.white);
            lessonLabel.setForeground(Color.BLACK);
            if(isAdmin){

            }
            else {
                lessonLabel.setEnabled(false);
            }
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            lessonLabel.setBorder(border);
            lessonPanel.add(lessonLabel);
        }
        lessonPanel.add(new JLabel(""));
        return lessonPanel;
    }

    private void addNewLesson(){
        LessonForm newLesson = new LessonForm(null,null);
        newLesson.setVisible(true);
        String[] changes = newLesson.getData();
        if(changes!=null) {
            //TODO;dodac dopisanie lekcji
            System.out.println("Dodawanie lekcji");
            reloadGroup(-1);
            reloadPanels();
        }
    }

    private void deleteSelected(){

    }

    private void reloadGroup(int classNumber){
        if(classNumber<0){
            //TODO:załduj zmieniony plan lekcji
            subjects = model.schelude2();
        }
        else{
        //TODO: zaladuj nowy plan lekcji
        subjects = model.schelude2();
        }

    }
    //Blad przy wywalaniu panelu
    private void reloadPanels(){
            for (int i = maxNumOfLesson+2;i>1;i--){
                System.out.println("Usuwanie Panelu");
                this.deletePanel(i);
            }
            maxNumOfLesson = 6;
            for(int i =0;i<maxNumOfLesson;i++){
                this.addSubPanel(addLessonPanel(i),50,i+2);
            }
            this.mainContent.repaint();
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

}
