import java.util.*;



public class Webserver implements Runnable { // Web server removes elements from the buffer
  private int ID;
  public Buffer buffer;
  private Semaphore semaphore;
  
  private int num_elements;
  private Semaphore mutex ;
  
  public Webserver(int id, Buffer b, int numEl, Semaphore s,Semaphore l) {
    ID = id;
    buffer = b;
    num_elements = numEl;
    semaphore = s;
   
    mutex = l;
 
    

  }

  public int getID() {
    return ID;
  }

  public void removeElement() throws InterruptedException {
    int num = num_elements;
    while (num > 0) {
      semaphore.accquire();
      mutex.accquire();
        if (buffer.getBufferEmpty())
        {
         
          System.out.println("Buffer empty, Webserver " + ID + " will now wait");
          
          
          
        }
        else{
          buffer.remove(this);
          num--;
        }   
        
    mutex.release();
    
    semaphore.release();
      
      

    }
    
    
  }

  public void reportOutput()
  {
    System.out.println("Webserver" + ID + " has produced a total of " + num_elements + " Elements");
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