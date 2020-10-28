import java.util.*;



public class Webserver implements Runnable { // Web server removes elements from the buffer
  private int ID;
  public static Buffer buf;
  private Semaphore semaphore;
  private Barrier barrier;
  private int num_elements;
  private Semaphore mutex ;
  private Boolean done = false;
  public Webserver(int id, Buffer b, int numEl, Semaphore s,Semaphore l, Barrier barrier) {
    ID = id;
    buf = b;
    num_elements = numEl;
    semaphore = s;
   
    mutex = l;
    this.barrier = barrier;
    

  }

  public int getID() {
    return ID;
  }

  public void removeElement() throws InterruptedException {
    int num = num_elements;
    while (num > 0) {
      semaphore.accquire();
      mutex.accquire();
        if (buf.getBufferEmpty())
        {
         
          System.out.println("Buffer empty, Webserver " + ID + " will now wait");
          
          
          
        }
        else{
          buf.remove(this);
          num--;
        }   
        
    mutex.release();
    
    semaphore.release();
      
      

    }
    
    done = true;
  }

  public void reportOutput()
  {
    System.out.println("Webserver" + ID + " has produced a total of " + num_elements + " Elements");
  }

  public boolean getDone()
  {
    return done;
  }


  

  @Override
  public void run() {

    try {
      removeElement();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //barrier.pause();
    //reportOutput();
      



  }
  
   
}   