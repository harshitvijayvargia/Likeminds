package Interfaces;

public interface User {
    String name = null;
    boolean isAdmin = false;
    public boolean sendNotification(Message message);
}
