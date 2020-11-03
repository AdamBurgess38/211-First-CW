import java.util.*;
/**
 * Buffer, can add and remove elements, built using ArrayList from Collections API
 * @author Adam Burgess
 */
public class Buffer // Provides data and operations onto the fixed-length buffer
{
    private LinkedList < Object > buf_list;
    private int elements;
    private int buf_size;
    private boolean bufferFull = false;
    private boolean bufferEmpty = true;
    private Semaphore mutex = new Semaphore(1);


    /**
     * Buffer creation, with n indicating the maximum capacity
     * @param n The max capacity of the buffer.
     */
    public Buffer(int n) {
        buf_list = new LinkedList < Object > ();
        buf_size = n;
    }

    /**
     * Adds an element to buffer
     * @param v Value to be added to buffer
     * @param user The User instance which added the element.
     */
    public void add(int v, User user) {
        //Mutex attempts to lock, meaning only one thread can execute this method at a time.
        mutex.accquire();
        elements++;
        buf_list.add(v);
        System.out.println("User " + user.getID() + " has added an element " + elements + "/" + buf_size);
        checkBufferStatus();
        user.decreaseNum();
        mutex.release();
        //Mutex releases, this allows another thread to enter.
    }


    /**
     * Adds an element to buffer
     * @param webServer The webserver instance which removes an element from the buffer.
     */
    public void remove(Webserver webServer) {
        //Mutex attempts to lock, meaning only one thread can execute this method at a time.
        mutex.accquire();
        elements--;
        buf_list.removeLast();
        System.out.println("Webserver" + webServer.getID() + "has removed element" + elements + "/" + buf_size);
        checkBufferStatus();
        webServer.decreaseNum();
        mutex.release();
        //Mutex releases, this allows another thread to enter.

    }

    /**
     * Accessor method
     * @return Returns status of if the buffer is currently full.
     */
    public boolean getBufferFull() {
        return bufferFull;
    }

    /**
     * Accessor method
     * @return Returns status of if the buffer is currently empty.
     */
    public boolean getBufferEmpty() {
        return bufferEmpty;
    }

    /**
     * Two comparisions which successfully declare the status of the buffer.
     */
    private void checkBufferStatus() {
        bufferEmpty = elements == 0;
        bufferFull = elements == (int) buf_size;
    }

    /**
     * Report the status of buffer
     */
    public void reportBufferCapacity() {
        System.out.println("Buffer has " + elements + " elements remaining");
    }









}