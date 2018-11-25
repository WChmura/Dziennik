package FrontEnd;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Form extends JDialog {
    private String[] data;
    private ArrayList<JTextField> input = new ArrayList<>();
    public Form(Frame owner, String title) {
        super(owner, title, true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        pack();
    }

    public String[] getData() {
        return data;
    }
    protected void addTextField(String title){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(title);
        JTextField textField = new JTextField(32);
        panel.add(label);
        panel.add(textField);
        getContentPane().add(panel);
        input.add(textField);
        this.repaint();
    }
    protected void addButtons()
    {
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
        getContentPane().add(btnPanel,BorderLayout.PAGE_END);
        super.setSize(300,100+(30*input.size()));
        this.repaint();
    }
    private void okButton() {
        data = new String[input.size()];
        for(int i=0;i<input.size();i++)
            data[i]=input.get(i).getText();
        setVisible(false);
    }

    private void noButton() {
        input = null;
        setVisible(false);
    }
}