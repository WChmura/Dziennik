package Database.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class Message {

    private int id_message;
    private int id_sender;
    private int id_recipient;
    private String topic;
    private String msg;
    /* 0 - przeczytana, 1 - nieprzeczytana */
    private int readed;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1060);

    public Message(int id_sender, int id_recipient, String topic, String msg, int readed) {
        this.id_message = ID_GENERATOR.getAndIncrement();
        this.id_sender = id_sender;
        this.id_recipient = id_recipient;
        this.topic = topic;
        this.msg = msg;
        this.readed = readed;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    public int getId_recipient() {
        return id_recipient;
    }

    public void setId_recipient(int id_recipient) {
        this.id_recipient = id_recipient;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id_message=" + id_message +
                ", id_sender=" + id_sender +
                ", id_recipient=" + id_recipient +
                ", topic='" + topic + '\'' +
                ", msg='" + msg + '\'' +
                ", readed=" + readed +
                '}';
    }
}
