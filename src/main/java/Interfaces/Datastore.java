package Interfaces;

import Implementations.MessageImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public interface Datastore {
    HashMap<String, Queue<MessageImpl>> queue = null;
    HashMap<String, Boolean> existingUsers = null;
    HashMap<String, HashSet<String>> subscribers = null;
    public boolean addTopic(String topic, String user) throws Exception;
    public boolean checkIfTopicExists(String topic) throws Exception;
    public boolean addMessage(String topic, MessageImpl message) throws Exception;
    public boolean addUser(String user, boolean isAdmin) throws Exception;
    public boolean subscribeToTopic(String user, String topic) throws Exception;


    public default HashMap<String, Queue<MessageImpl>> getQueue() {
        return queue;
    }

    public default HashMap<String, Boolean> getExistingUsers() {
        return existingUsers;
    }

    public default HashMap<String, HashSet<String>> getSubscribers() {
        return subscribers;
    }
}
