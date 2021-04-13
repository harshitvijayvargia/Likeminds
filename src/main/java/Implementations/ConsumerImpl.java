package Implementations;

import Interfaces.Consumer;
import Interfaces.Datastore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;

public class ConsumerImpl implements Consumer {
    Datastore ds = null;

    public ConsumerImpl(Datastore ds)
    {
        this.ds = ds;
    }
    @Override
    public void processMessages() {
        HashMap<String, Queue<MessageImpl>> queue = ds.getQueue();
        for(Map.Entry pair: queue.entrySet())
        {
            String topic = (String) pair.getKey();
            Queue<MessageImpl> q = (Queue<MessageImpl>) pair.getValue();
            HashSet<String> users = ds.getSubscribers().get(topic);
            for(MessageImpl message:q)
            {
                for(String user:users)
                {
                    message.setUser(user);
                    System.out.println(message);
                }
            }
        }
    }
}
