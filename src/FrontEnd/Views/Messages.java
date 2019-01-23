package FrontEnd.Views;

import FrontEnd.Colors;
import FrontEnd.Forms.NewMessageForm;
import FrontEnd.Page;
import javax.swing.*;
import java.awt.*;

public class Messages extends Page {
    private String [] sendMessages;
    private String [] receivedMessages;
    private String [] sendM;
    private String [] receivedM;
    private JComboBox<String> sentComboBox;
    private JComboBox<String> receivedComboBox;
    private JTextField sendTextField;
    private JTextField receivedTextField;

    @Override
    public void createGUI() {
        model = createNewModel();
        addTopMenu(1);
        setMessagesValues();
        addMessagesPanel();
    }

    private void setMessagesValues(){
        sendMessages=model.getSentMessages().toArray(new String[0]);
        receivedMessages =model.getReceivedMessages().toArray(new String[0]);
        String[] sendMessagesTitles = model.getSentMessagesTitles().toArray(new String[0]);
        String[] receivedMessagesTitles = model.getReceivedMessagesTitles().toArray(new String[0]);
        String[] sendMessagesRecipient = model.getSentMessagesRecipents().toArray(new String[0]);
        String[] receivedMessagesSender = model.getReceivedMessagesSenders().toArray(new String[0]);

        int namOfReceived = receivedMessages.length;
        int numOfSent = sendMessages.length;
        sendM = new String[numOfSent];
        for(int i = 0; i< numOfSent; i++)
            sendM[i]="'"+ sendMessagesTitles[i]+"' do "+ sendMessagesRecipient[i];
        receivedM = new String[namOfReceived];
        for(int i = 0; i< namOfReceived; i++)
            receivedM[i]="'"+ receivedMessagesTitles[i]+"' od "+ receivedMessagesSender[i];
    }

    private void addMessagesPanel(){
        JPanel messagePanel = new JPanel(new GridLayout(1,3,10,10));
        JPanel buttonPanel= new JPanel(new GridLayout(5,1,5,5));

        JButton newMessageButton = new JButton("Nowa wiadomosc");
        newMessageButton.addActionListener(e -> {
            String[] changes = new NewMessageForm(null).getData();
            if(changes!=null)
                model.sendMessage(changes[1],changes[2],changes[0]);
        });

        buttonPanel.setBackground(Colors.main);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(newMessageButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));

        messagePanel.add(addSentPanel());
        messagePanel.add(addReceivedPanel());
        messagePanel.add(buttonPanel);
        this.addSubPanel(messagePanel);
    }

    private JPanel addSentPanel(){
        JPanel sentPanel= new JPanel(new BorderLayout());
        JPanel sentSubPanel = new JPanel(new GridLayout(2,1));

        sentComboBox = new JComboBox<>(sendM);
        sentComboBox.addActionListener(e -> sendTextField.setText(sendMessages[sentComboBox.getSelectedIndex()]));
        JLabel sendLabel = new JLabel("Wyslane");
        sendLabel.setHorizontalAlignment(JLabel.CENTER);

        sendTextField = new JTextField();
        sendTextField.setEditable(false);

        sentSubPanel.setBackground(Colors.main);
        sentSubPanel.add(sendLabel);
        sentSubPanel.add(sentComboBox);
        sentPanel.add(sentSubPanel,BorderLayout.NORTH);
        sentPanel.add(sendTextField,BorderLayout.CENTER);
        return sentPanel;
    }

    private JPanel addReceivedPanel(){
        JPanel receivedPanel= new JPanel(new BorderLayout());
        JPanel receivedSubPanel = new JPanel(new GridLayout(2,1));

        receivedComboBox = new JComboBox<>(receivedM);
        receivedComboBox.addActionListener(e -> receivedTextField.setText(receivedMessages[receivedComboBox.getSelectedIndex()]));
        JLabel receivedLabel = new JLabel("Odebrane");
        receivedLabel.setHorizontalAlignment(JLabel.CENTER);

        receivedTextField = new JTextField();
        receivedTextField.setEditable(false);

        receivedSubPanel.setBackground(Colors.main);
        receivedSubPanel.add(receivedLabel);
        receivedSubPanel.add(receivedComboBox);
        receivedPanel.add(receivedSubPanel,BorderLayout.NORTH);
        receivedPanel.add(receivedTextField,BorderLayout.CENTER);
        return receivedPanel;
    }
}
