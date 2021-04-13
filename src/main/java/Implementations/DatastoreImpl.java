package Implementations;

import Interfaces.Datastore;
import Interfaces.Message;
import Interfaces.User;

import java.util.*;

public class DatastoreImpl implements Datastore {


    HashMap<String, Queue<MessageImpl>> queue = new HashMap<>();
    HashMap<String, Boolean> existingUsers = new HashMap<>();
    HashMap<String, HashSet<String>> subscribers = new HashMap<>();

    public HashMap<String, Queue<MessageImpl>> getQueue() {
        return queue;
    }

    public HashMap<String, Boolean> getExistingUsers() {
        return existingUsers;
    }

    public HashMap<String, HashSet<String>> getSubscribers() {
        return subscribers;
    }


    @Override
    public boolean addTopic(String topic, String user) throws Exception {
        if(checkIfTopicExists(topic))
            throw new Exception("Topic already exists");
        if(!checkIfUserExists(user))
            throw new Exception("User does not exist");
        if(!existingUsers.get(user))
            throw new Exception("The user does not have permission to create a topic");
        Queue<MessageImpl> messages = new LinkedList<>();
        queue.put(topic, messages);
        HashSet<String> users = new HashSet<>();
        subscribers.put(topic, users);
        return true;
    }

    @Override
    public boolean checkIfTopicExists(String topic) {
        return queue.containsKey(topic);
    }

    public boolean checkIfUserExists(String user)
    {
        return existingUsers.containsKey(user);
    }

    @Override
    public boolean addMessage(String topic, MessageImpl message) throws Exception {
        if(!checkIfTopicExists(topic))
            throw new Exception("The topic for which you are trying to add message does not exist");
        queue.get(topic).add(message);
        return true;
    }

    @Override
    public boolean addUser(String user, boolean isAdmin) throws Exception {
//        User user = new UserImpl(user, isAdmin);
        if(existingUsers.containsKey(user))
            throw new Exception("The user already exists");
        existingUsers.put(user, isAdmin);
        return true;
    }

    public boolean subscribeToTopic(String user, String topic) throws Exception
    {
        if(!checkIfUserExists(user))
            throw new Exception("The user does not exist");
        if(!checkIfTopicExists(topic))
            throw new Exception("The topic you are trying to subscribe does not exist");

        HashSet<String> subs = subscribers.get(topic);

        if(subs.contains(user))
            throw new Exception("The user is already subscribed");
        subs.add(user);
        subscribers.replace(topic, subs);
        return true;
    }
}
