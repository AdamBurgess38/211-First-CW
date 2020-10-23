import java.util.*;

public class Webserver implements Runnable
{				//Web server removes elements from the buffer
  private int ID;
  public static Buffer buf;
  private Semaphore full;
  private Semaphore empty;
  private int num_elements;
  private Barrier barrier;
  
  
  public Webserver(int id,Buffer b,int numEl, Semaphore s, Semaphore f)			
  {
    ID = id;
    buf = b;
    num_elements = numEl;
    empty = s;
    full = f;
    
   

  }


  public int getID()
  {
    return ID;
  }


  public void removeElement()
  {
    

    while (num_elements > 0) {
          full.accquire();
          System.out.println("Web ID" + ID);
          if(buf.getBufferEmpty())
          {
            empty.release();
          }
          else
          {
            buf.remove(this);
            num_elements--;
            empty.release();
          }
          
          System.out.println("Web ID" + ID);
      
	  }
    
  }

  @Override
  public void run() {
      
        removeElement();
      



  }
  
   
}   