import java.util.*;
/**
 * Buffer, can add and remove elements, built using ArrayList from Collections API
 * @author Adam Burgess
 */
public class Buffer // Provides data and operations onto the fixed-length buffer
{
    private LinkedList<Object> buf_list;
    private int elements; // Number of elements currently on the queue
    private int buf_size;
    private boolean bufferFull = false;
    private boolean bufferEmpty = true;
    
    
    /**
     * Buffer creation, with n indicating the maximum capacity
     * @param n The max capacity of the buffer.
     */
    public Buffer(int n) 
    {
        buf_list = new LinkedList<Object>();
        buf_size = n;
    }

    /**
     * Adds an element to buffer
     * @param v Value to be added to buffer
     * @param user The User instance which added the element.
     */
    public  void add(int v, User user) 
    {
        elements++;
        buf_list.add(v);
        System.out.println("User " + user.getID() + " has added an element " + elements + "/" + buf_size);
        checkBufferStatus();  
    }

    
    /**
     * Adds an element to buffer
     * @param webServer The webserver instance which removes an element from the buffer.
     */
    public  void remove(Webserver webServer)
    { 
        elements--;
        buf_list.removeLast();
        System.out.println("Webserver" + webServer.getID() + "has removed element" + elements + "/" + buf_size);
        checkBufferStatus();   
    }

    /**
     * Accessor method
     * @return Returns status of if the buffer is currently full.
     */
    public  boolean getBufferFull()
    {
        return bufferFull;
    }

    /**
     * Accessor method
     * @return Returns status of if the buffer is currently empty.
     */
    public  boolean getBufferEmpty()
    {
        return bufferEmpty;
    }

    /**
     * Two comparisions which successfully declare the status of the buffer.
     */
    private void checkBufferStatus()
    {
        bufferEmpty = elements == 0;
        bufferFull = elements == (int)buf_size;
    }


    public void reportBufferCapacity()
    {
        System.out.println("Buffer has " + elements + " elements remaining");
    }


   


    








}