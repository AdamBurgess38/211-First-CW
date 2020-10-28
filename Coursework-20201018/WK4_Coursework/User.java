import java.util.*;



public class User implements Runnable {
  private int ID;
  private int num_elements;
  public static Buffer buf;
  private Barrier barrier;
  private Semaphore semaphore;
  private boolean done = false;
  private Semaphore mutex;

  public User(int id, int el, Buffer b, Semaphore s, Semaphore l, Barrier barrier) // Created user will add a certain number of elements
                                                                  // to the
  // buffer.
  {
    ID = id;
    num_elements = el;
    buf = b;
    this.barrier = barrier;
    semaphore = s;
    mutex = l;
   

  }

  public void add_elements() throws InterruptedException {
    int num = num_elements;
    int n = 0;// Add element to buffer, element value iterates from 0, 1, 2 .... num_elements
    while (num > 0) {
      semaphore.accquire();
      
      mutex.accquire();
        if (buf.getBufferFull())
        {
          System.out.println("Buffer full, user " + ID + " will now wait");
          
          
      
        }
        else
       {
          buf.add(n,this);
          n++;
          num--;
        }
      mutex.release();
     
      semaphore.release();
      
      
    }
    
    done = true;
  }

  public void reportOutput()
  {
    System.out.println("User" + ID + " has produced a total of " + num_elements + " Elements");
  }

  public boolean getDone()
  {
    return done;
  }

  

  public int getID() {
    return ID;
  }

  @Override
  public void run() {
    try {
      add_elements();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //barrier.pause();
    //reportOutput();

  }

  //Need condiotn here to check if buffer is full! Otherwise, continue the loop. IN ADD METHOD FORM W3.
       
}   