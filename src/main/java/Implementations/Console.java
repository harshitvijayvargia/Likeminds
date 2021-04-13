package Implementations;

import Interfaces.Consumer;
import Interfaces.Datastore;
import Interfaces.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console  {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Datastore ds = new DatastoreImpl();
    static Consumer consumer = new ConsumerImpl(ds);
    public static void main(String[] args) throws IOException
    {
        choice();
    }

    public static void choice() throws IOException
    {
        System.out.println("1. Add User");
        System.out.println("2. Add Topic");
        System.out.println("3. Subscribe Topic");
        System.out.println("4. Push a Message");
        System.out.println("5. Process Messages");
        int c = Integer.parseInt(br.readLine());

        try
        {
            processChoice(c);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            choice();
        }
    }

    public static void processChoice(int choice) throws Exception
    {
        if(choice==1)
        {
            System.out.println("Enter User Name");
            String name = br.readLine();
            System.out.println("Enter 1 if the user is an admin. /n Enter 0 if the user is not an Admin");
            int isAdmin = Integer.parseInt(br.readLine());
            if(isAdmin<0 || isAdmin>1)
                throw new Exception("Please enter a valid value");
            if(ds.addUser(name, isAdmin==1))
            {
                System.out.println("User added succssfully");
                choice();
            }
        }
        else if(choice==2)
        {
            System.out.println("Enter Topic Name");
            String topic = br.readLine();
            System.out.println("Enter User Name");
            String user = br.readLine();
            if(ds.addTopic(topic, user))
            {
                System.out.println("Topic added succssfully");
                choice();
            }
        }
        else if(choice==3)
        {
            System.out.println("Enter Topic Name");
            String topic = br.readLine();
            System.out.println("Enter User Name");
            String user = br.readLine();
            if(ds.subscribeToTopic(user,topic))
            {
                System.out.println("Subscribed successfully");
                choice();

            }
        }
        else if(choice==4)
        {
            int id;
            String topicName;
            String text;
            System.out.println("Enter ID for the message");
            id = Integer.parseInt(br.readLine());
            System.out.println("Enter Topic for message");
            topicName = br.readLine();
            System.out.println("Enter Text for message");
            text = br.readLine();
            MessageImpl message = new MessageImpl(id, topicName, text);
            if(ds.addMessage(topicName, message))
            {
                System.out.println("Message added successfully");
                choice();
            }
        }
        else if(choice==5)
        {
            consumer.processMessages();
            choice();
        }

    }

}
