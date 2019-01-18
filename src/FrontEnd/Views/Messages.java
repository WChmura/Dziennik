package FrontEnd.Views;

import FrontEnd.Colors;
import FrontEnd.Forms.NewMessageForm;
import FrontEnd.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Messages extends Page {
    private String [] sendMessages;
    private String [] receivedMessages;
    private String[] sendMessagesTitles;
    private String[] receivedMessagesTitles;
    private String[] sendMessagesRecipient;
    private String[] receivedMessagesSender;
    private Boolean[] recivedMessageIsRead;
    //+metodki
    //nowa wiadomosc
    //zmiana statusu na odczytana

    private int numOfSended;
    private int namOfReceived;
    private String [] sendM;
    private String [] receivedM;
    private JComboBox<String> sendedComboBox;
    private JComboBox<String> receivedComboBox;
    private JTextField sendTextField;
    private JTextField receivedTextField;
    @Override
    public void createGUI() {
        model = createNewModel();
        System.out.println("Dodano nowy model");
        addTopMenu(1);
        System.out.println("Dodano topka");
        setMessagesValues();
        addMessagesPanel();
    }

    private void setMessagesValues(){
        sendMessages=model.getSentMessages().toArray(new String[0]);
        receivedMessages =model.getReceivedMessages().toArray(new String[0]);
        System.out.println("pobierz3");
        sendMessagesTitles = model.getSentMessagesTitles().toArray(new String[0]);
        receivedMessagesTitles = model.getReceivedMessagesTitles().toArray(new String[0]);
        sendMessagesRecipient = model.getSentMessagesRecipents().toArray(new String[0]);
        receivedMessagesSender = model.getReceivedMessagesSenders().toArray(new String[0]);
        recivedMessageIsRead = model.areReceivedMessagesRead().toArray(new Boolean[0]);
        System.out.println("Pobrnao wiadomosci z bazy");
        namOfReceived = receivedMessages.length;
        numOfSended=sendMessages.length;
        sendM = new String[numOfSended];
        for(int i=0;i<numOfSended;i++){
            sendM[i]="'"+ sendMessagesTitles[i]+"' do "+ sendMessagesRecipient[i];
        }
        receivedM = new String[namOfReceived];
        for(int i = 0; i< namOfReceived; i++){
            receivedM[i]="'"+ receivedMessagesTitles[i]+"' od "+ receivedMessagesSender[i];
        }
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
                    model.sendMessage(changes[1],changes[2],changes[0]);
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
        sendTextField.setEditable(false);
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
        receivedComboBox = new JComboBox<>(receivedM);
        receivedComboBox.addActionListener(e -> {
            receivedTextField.setText(receivedMessages[receivedComboBox.getSelectedIndex()]);
        });
        JLabel recivedLabel = new JLabel("Odebrane");
        recivedLabel.setBackground(Colors.main);
        recivedLabel.setHorizontalAlignment(JLabel.CENTER);
        receivedTextField = new JTextField();
        receivedTextField.setEditable(false);
        JPanel recivedSubPanel = new JPanel(new GridLayout(2,1));
        recivedSubPanel.setBackground(Colors.main);
        recivedSubPanel.add(recivedLabel);
        recivedSubPanel.add(receivedComboBox);
        recivedPanel.add(recivedSubPanel,BorderLayout.NORTH);
        recivedPanel.add(receivedTextField,BorderLayout.CENTER);
        return recivedPanel;
    }
}
