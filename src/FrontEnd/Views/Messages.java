package FrontEnd.Views;

import FrontEnd.Colors;
import FrontEnd.Forms.EditMarkForm;
import FrontEnd.Forms.NewMessageForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Messages extends Page {
    //to potrzebuje - tylko dla nauczycieli i adminów
    private String [] sendMessages;
    private String [] recivedMessages;
    private String [] sendMessagesTitles;
    private String [] recivedMessagesTitles;
    private String [] sendMessagesRecipient;
    private String [] recivedMessagesSender;
    boolean [] recivedMessageIsRead;
    //+metodki
    //nowa wiadomosc
    //zmiana statusu na odczytana

    //tego juz nie
    private int numOfSended;
    private int numOfRecived;
    private String [] sendM;
    private String [] recivedM;
    private JComboBox<String> sendedComboBox;
    private JComboBox<String> recivedComboBox;
    private JTextField sendTextField;
    private JTextField recivedTextField;
    @Override
    public void createGUI() {
        model = createNewModel();
        addTopMenu(1);
        setMessagesValues();
        addMessagesPanel();
    }

    private void setMessagesValues(){
        /*sendMessages=model.messages();
        recivedMessages=model.messages();
        sendMessagesTitles=model.messages();
        recivedMessagesTitles=model.messages();
        sendMessagesRecipient=model.messages();
        recivedMessagesSender=model.messages();
        numOfRecived=recivedMessages.length;
        numOfSended=sendMessages.length;
        sendM = new String[numOfSended];
        for(int i=0;i<numOfSended;i++){
            sendM[i]="'"+sendMessagesTitles[i]+"' do "+ sendMessagesRecipient[i];
        }
        recivedM = new String[numOfRecived];
        for(int i=0;i<numOfRecived;i++){
            recivedM[i]="'"+recivedMessagesTitles[i]+"' od "+ recivedMessagesSender[i];
        }*/
    }

    private void addMessagesPanel(){
        JPanel messagePanel = new JPanel(new GridLayout(1,3,10,10));

        JPanel buttonPanel= new JPanel(new GridLayout(5,1,5,5));
        buttonPanel.setBackground(Colors.main);
        buttonPanel.add(new JLabel(""));
        JButton newMessageButton = new JButton("Nowa wiadomosc");
        newMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMessageForm edit = new NewMessageForm(null);
                edit.setVisible(true);
                String[] changes = edit.getData();
                if(changes!=null) {
                    //TODO:dodac wysłanie
                }
            }
        });
        buttonPanel.add(newMessageButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));

        messagePanel.add(addSentPanel());
        messagePanel.add(addRecivedPanel());
        messagePanel.add(buttonPanel);

        this.addSubPanel(messagePanel,100);
    }

    private JPanel addSentPanel(){
        JPanel sendedPanel= new JPanel(new BorderLayout());
        sendedPanel.setBackground(Colors.main);
        sendedComboBox = new JComboBox<>(sendM);
        sendedComboBox.addActionListener(e -> {
            sendTextField.setText(sendMessages[sendedComboBox.getSelectedIndex()]);
        });
        JLabel sendLabel = new JLabel("Wyslane");
        sendLabel.setBackground(Colors.main);
        sendLabel.setHorizontalAlignment(JLabel.CENTER);
        sendTextField = new JTextField();
        JPanel sendedSubPanel = new JPanel(new GridLayout(2,1));
        sendedSubPanel.setBackground(Colors.main);
        sendedSubPanel.add(sendLabel);
        sendedSubPanel.add(sendedComboBox);
        sendedPanel.add(sendedSubPanel,BorderLayout.NORTH);
        sendedPanel.add(sendTextField,BorderLayout.CENTER);
        return sendedPanel;
    }

    private JPanel addRecivedPanel(){
        JPanel recivedPanel= new JPanel(new BorderLayout());
        recivedPanel.setBackground(Colors.main);
        recivedComboBox = new JComboBox<>(recivedM);
        recivedComboBox.addActionListener(e -> {
            recivedTextField.setText(recivedMessages[recivedComboBox.getSelectedIndex()]);
        });
        JLabel recivedLabel = new JLabel("Odebrane");
        recivedLabel.setBackground(Colors.main);
        recivedLabel.setHorizontalAlignment(JLabel.CENTER);
        recivedTextField = new JTextField();
        JPanel recivedSubPanel = new JPanel(new GridLayout(2,1));
        recivedSubPanel.setBackground(Colors.main);
        recivedSubPanel.add(recivedLabel);
        recivedSubPanel.add(recivedComboBox);
        recivedPanel.add(recivedSubPanel,BorderLayout.NORTH);
        recivedPanel.add(recivedTextField,BorderLayout.CENTER);
        return recivedPanel;
    }
}
