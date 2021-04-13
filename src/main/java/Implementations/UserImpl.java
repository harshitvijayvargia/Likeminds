package Implementations;

import Interfaces.Message;
import Interfaces.User;

public class UserImpl implements User {

    String name = null;
    boolean isAdmin = false;

    public UserImpl(String name, boolean isAdmin)
    {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean sendNotification(Message message) {
        return false;
    }
}
