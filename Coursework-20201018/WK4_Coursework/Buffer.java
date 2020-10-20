import java.util.*;

public class Buffer //Provides data and operations onto the fixed-length buffer
{
    private LinkedList < Object > buf_list;
    private int elements;						//Number of elements currently on the queue
    private int buf_size;	


    public Buffer(int n) //Buffer creation, with n indicating the maximum capacity
    {
        buf_list = new LinkedList < Object > ();
        buf_size = n;
    }

    public void add(int n, User user)						//Add element to queue
    {	   	
      elements++;
      buf_list.add(n);
      System.out.println("User " + user.getID() + " has added element " + elements);
    }

    public void remove(Webserver webServer)
    {
        elements--;
        buf_list.removeLast();
        System.out.println("Webserver" + webServer.getID() + "has removed element" + elements);
    }








}