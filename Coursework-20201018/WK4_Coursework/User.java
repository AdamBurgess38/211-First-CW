import java.util.*;



public class User implements Runnable {
  private int ID;
  private int num_elements;
  public Buffer buffer;
  private Semaphore empty;
  private Semaphore full;
  private Semaphore mutex;
  private int num;
  private int n = 0;

  public User(int id, int el, Buffer b, Semaphore s, Semaphore mu, Semaphore full) // Created user will add a certain number of elements
                                                                   // to the
  // buffer.
  {
    this.ID = id;
    this.num_elements = el;
    this.buffer = b;
    this.full = full;
    this.empty = s;
    this.mutex = mu;
   

  }

  public void add_elements() throws InterruptedException {
    num = num_elements;
    n = 0;// Add element to buffer, element value iterates from 0, 1, 2 .... num_elements
    while (num > 0) {
      if(buffer.getBufferFull())
      {
        System.out.println("Buffer full, user " + ID + " will now wait");
        
      }
     
      empty.accquire();
      
        //mutex.accquire();
         
          buffer.add(n,this);
          
            
        //mutex.release();
      full.release();
     
    
    
      
      
    }
    
    
  }

  public void reportOutput()
  {
    System.out.println("User" + ID + " has produced a total of " + num_elements + " Elements");
  }

  
  public void decreaseNum()
  {
    n++;
    num--;
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