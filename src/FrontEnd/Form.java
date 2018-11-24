package FrontEnd;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
//TODO:fix it
public class Form extends JDialog {
    private String[] data;
    private ArrayList<JTextField> input = new ArrayList<JTextField>();
    int numOfTextFields =0;
    public Form(Frame owner, String title) {
        super(owner, title, true);
        setLayout(new GridLayout(3,1));
        JPanel btnPanel = new JPanel();
        JButton okBtn   = new JButton("Wyslij");
        JButton noBtn   = new JButton("Anuluj");
        btnPanel.add(okBtn);
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                okButton();
            }
        });
        noBtn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                noButton();
            }
        });
        btnPanel.add(noBtn);
        getContentPane().add(btnPanel);
        pack();
    }

    public String[] getData() {
        return data;
    }
    protected void addTextField(String title){
        JTextField textField = new JTextField(32);
        getContentPane().add(textField);
        input.add(textField);
        this.repaint();
    }
    private void okButton() {
        data = new String[input.size()];
        for(int i=0;i<=input.size();i++)
            data[i]=input.get(i).getText();
        setVisible(false);
    }

    private void noButton() {
        input = null;
        setVisible(false);
    }
}