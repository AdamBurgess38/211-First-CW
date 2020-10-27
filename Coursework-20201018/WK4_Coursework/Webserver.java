import java.util.*;



public class Webserver implements Runnable { // Web server removes elements from the buffer
  private int ID;
  public static Buffer buf;
  private Semaphore full;
  private Semaphore empty;
  private int num_elements;
  private Semaphore mutex ;

  public Webserver(int id, Buffer b, int numEl, Semaphore s, Semaphore f, Semaphore l) {
    ID = id;
    buf = b;
    num_elements = numEl;
    empty = s;
    full = f;
    mutex = l;
    

  }

  public int getID() {
    return ID;
  }

  public void removeElement() throws InterruptedException {
    int num = num_elements;
    while (num > 0) {
      full.accquire();
      mutex.accquire();
        if (buf.getBufferEmpty())
        {
         
          System.out.println("Buffer empty, Webserver" + ID + "will now wait");
          
          
          
        }
        else{
          buf.remove(this);
          num--;
        }   
        
    mutex.release();
    //Thread.sleep(1000);
     full.release();
      
      

    }
    System.out.println("Webserver" + ID + " has produced a total of " + num_elements + "Elements");
    
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