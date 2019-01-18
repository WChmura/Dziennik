package FrontEnd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Form extends JDialog {
    private String[] data;
    protected ArrayList<JTextField> input = new ArrayList<>();
    protected String comboBoxScores = null;
    public Form(Frame owner, String title) {
        super(owner, title, true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        pack();
    }

    public String[] getData() {
        return data;
    }

    protected void addPasswordField(String title,String initialValue){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(title);
        JPasswordField passwordField = new JPasswordField(initialValue);
        panel.add(label);
        panel.add(passwordField);
        getContentPane().add(panel);
        input.add(passwordField);
        this.repaint();
    }

    protected void addTextField(String title,String initialValue){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(title);
        JTextField textField = new JTextField(initialValue);
        panel.add(label);
        panel.add(textField);
        getContentPane().add(panel);
        input.add(textField);
        this.repaint();
    }

    protected void addNonEditableTextField(String title,String initialValue){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(title);
        JTextField textField = new JTextField(initialValue);
        textField.setEnabled(false);
        panel.add(label);
        panel.add(textField);
        getContentPane().add(panel);
        input.add(textField);
        this.repaint();
    }

    protected void addBigTextField(String initialValue,boolean editable){
        JPanel panel = new JPanel(new GridLayout(1,1));
        JTextField textField = new JTextField(initialValue);
        if(editable)
            input.add(textField);
        else
            textField.setEnabled(false);
        panel.add(textField);
        getContentPane().add(panel);
        this.repaint();
    }

    protected void addComboBox(String title, String[] answers){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(title);
        final JComboBox<String> comboBox = new JComboBox<>(answers);
        comboBox.addActionListener(e -> {
            comboBoxScores = (String)comboBox.getSelectedItem();
        });
        panel.add(label);
        panel.add(comboBox);
        getContentPane().add(panel);

        this.repaint();
    }

    protected void addButtons() {
        JPanel btnPanel = new JPanel();
        JButton okBtn   = new JButton("Wyslij");
        JButton noBtn   = new JButton("Anuluj");
        okBtn.addActionListener(ae -> okButton());
        noBtn.addActionListener(ae -> noButton());
        btnPanel.add(okBtn);
        btnPanel.add(noBtn);
        getContentPane().add(btnPanel,BorderLayout.PAGE_END);
        super.setSize(300,100+(30*input.size()));
        this.repaint();
    }

    private void okButton() {
        if(!checkDataValues()){
            if(wrongValuesMessage()){
                getDataValues();
            }
        }
        else {
            getDataValues();
        }
    }

    private void getDataValues(){
        //TODO: sprawdzic
        //uproszczona wersja -> tylko jeden comboBox mozliwy w Formie
        data = new String[input.size()+1];
        for (int i = 0; i < input.size(); i++)
            data[i] = input.get(i).getText();
        if(comboBoxScores!=null)
            data[input.size()] = comboBoxScores;
        setVisible(false);
    }

    private void noButton() {
        input = null;
        setVisible(false);
    }

    protected boolean checkDataValues(){
        return true;
    }

    protected boolean wrongValuesMessage(){
        int n = JOptionPane.showConfirmDialog(
                this,
                "Wprowadziles bledne dane./n" +
                        "Na pewno chcesz kontynuować?",
                "",
                JOptionPane.YES_NO_OPTION);
        return n != 1;
    }

    protected boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    protected boolean isNumeric(String str,int maxNumber) {
        double d;
        try {
             d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return d < maxNumber;
    }
}