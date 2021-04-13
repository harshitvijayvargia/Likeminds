package Implementations;

import Interfaces.Message;

public class MessageImpl implements Message {
    int id;
    String topicName;
    String text;
    String user;

    public MessageImpl(int id, String topicName, String text)
    {
        this.id = id;
        this.topicName = topicName;
        this.text = text;
    }

    public boolean setUser(String user)
    {
        this.user = user;
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{"+"\n"+"   topic:"+topicName+"\n"+"    message:"+text+"\n"+"   sentTo:"+user+"\n"+"}");
        return builder.toString();
    }
}
