package FrontEnd.Views;

import FrontEnd.Forms.ChangePasswordForm;
import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;

public class Settings extends Page {

    @Override
    public void createGUI() {
        model = createNewModel();
        addTopMenu(3);
        addSubPanel(new JPanel());
        addButtonsPanel();
    }
    private void addButtonsPanel(){
        JPanel panel = new JPanel(new GridLayout(1,5,10,10));
        JButton personalData = new JButton("Pokaz dane osobowe");
        personalData.addActionListener(e -> topPanel.goToPage("personalData"));

        JButton changePassword = new JButton("Zmien haslo");
        changePassword.addActionListener(e -> {
            String[] changes = new ChangePasswordForm(null,model.getPassword(),false).getData();
            if(changes!=null) {
                model.changePassword(changes[1]);
                JOptionPane.showMessageDialog(null, "Haslo zostalo zmienione");
            }
        });

        panel.add(new JLabel(""));
        panel.add(personalData);
        panel.add(changePassword);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        this.addSubPanel(panel);
    }
}
