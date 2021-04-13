package Interfaces;

import java.util.HashMap;
import java.util.HashSet;

public interface Consumer {
    HashMap<String, HashSet<Integer>> processed = null;
    public void processMessages();
}
