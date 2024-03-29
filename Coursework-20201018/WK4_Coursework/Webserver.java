import java.util.*;



public class Webserver implements Runnable { // Web server removes elements from the buffer
    private int ID;
    public Buffer buffer;
    private Semaphore empty;
    private Semaphore full;
    private int num_elements;
    int num;

    public Webserver(int id, Buffer b, int numEl, Semaphore empty, Semaphore full) {
        this.ID = id;
        this.buffer = b;
        this.num_elements = numEl;
        this.empty = empty;
        this.full = full;
    }
    /**
     * 
     * @return The Webservers ID.
     */
    public int getID() {
        return ID;
    }
    /**
     * Method to remove all elements Webserver has been assigned.
     * @throws InterruptedException
     */
    public void removeElement() throws InterruptedException {
        num = num_elements;
        while (num > 0) {
            if (buffer.getBufferEmpty())
                System.out.println("Buffer empty, Webserver " + ID + " will now wait");
            //Tests if buffer is full.
            full.accquire();
            buffer.remove(this);
            //Allows another element to be added.
            empty.release();

        }


    }

    /**
     * Reports the operations Webserver has carried out.
     */
    public void reportOutput() {
        System.out.println("Webserver" + ID + " has removed a total of " + num_elements + " Elements");
    }

    /**
     * Insures while loop doesn't run indefintetly.
     */
    public void decreaseNum() {
        num--;
    }





    @Override
    public void run() {

        try {
            removeElement();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





    }


}