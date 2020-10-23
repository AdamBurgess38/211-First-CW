import java.util.*;

public class Buffer //Provides data and operations onto the fixed-length buffer
{
    private LinkedList < Object > buf_list;
    private int elements;						//Number of elements currently on the queue
    private int buf_size;	
    private boolean bufferFull = false;
    private boolean bufferEmpty = true;
    private Semaphore mutex = new Semaphore(1);
    


    public Buffer(int n) //Buffer creation, with n indicating the maximum capacity
    {
        buf_list = new LinkedList < Object > ();
        buf_size = n;
    }

    public synchronized void add(int n, User user)						//Add element to queue
    {	
      //mutex.accquire();
      elements++;
      buf_list.add(n);
      System.out.println("User " + user.getID() + " has added an element " + elements + "/" + buf_size);
      checkBufferStatus();
      //mutex.release();
    }

    

    public synchronized void remove(Webserver webServer)
    {
        //mutex.accquire();
        elements--;
        System.out.println(elements);
        buf_list.removeLast();
        System.out.println("Webserver" + webServer.getID() + "has removed element" + elements + "/" + buf_size);
       // mutex.release();
        
    }

    public  boolean getBufferFull()
    {
        return bufferFull;
    }

    public  boolean getBufferEmpty()
    {
        return bufferEmpty;
    }

    private void checkBufferStatus()
    {
        bufferEmpty = elements == 0;
        bufferFull = elements == buf_size;
    }


    








}