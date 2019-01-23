package FrontEnd;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Form extends JDialog{
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
        panel.add(new JLabel(title));
        JPasswordField passwordField = new JPasswordField(initialValue);
        panel.add(passwordField);
        getContentPane().add(panel);
        input.add(passwordField);
    }

    protected void addTextField(String title,String initialValue){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(title));
        JTextField textField = new JTextField(initialValue);
        panel.add(textField);
        getContentPane().add(panel);
        input.add(textField);
    }

    protected void addNonEditableTextField(String title,String initialValue){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(title));
        JTextField textField = new JTextField(initialValue);
        textField.setEditable(false);
        panel.add(textField);
        getContentPane().add(panel);
        input.add(textField);
    }

    protected void addBigTextField(){
        JPanel panel = new JPanel(new GridLayout(1,1));
        JTextField textField = new JTextField(null);
        input.add(textField);
        panel.add(textField);
        getContentPane().add(panel);
    }

    protected void addComboBox(String title, String[] answers){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        final JComboBox<String> comboBox = new JComboBox<>(answers);
        comboBoxScores=answers[0];
        comboBox.addActionListener(e -> comboBoxScores = (String)comboBox.getSelectedItem());
        panel.add(new JLabel(title));
        panel.add(comboBox);
        getContentPane().add(panel);
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
        setVisible(true);
        this.repaint();
    }

    private void okButton() {
        if(!checkDataValues())
            wrongValuesMessage();
        else
            getDataValues();
    }

    private void getDataValues(){
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

    private void wrongValuesMessage(){
            JOptionPane.showMessageDialog(null, "Podano bledne dane");
    }

    protected boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
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