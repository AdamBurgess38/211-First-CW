import java.util.*;

public class User implements Runnable{
  private int ID;
  private int num_elements;
  public static Buffer buf;
  private Semaphore full;
  private Semaphore empty;
  private Barrier barrier;
  
  

  public User(int id, int el, Buffer b, Semaphore s, Semaphore f) // Created user will add a certain number of elements to the
                                                     // buffer.
  {
    ID = id;
    num_elements = el;
    buf = b;
    full = f;
    empty = s;
   
    
  }

  public void add_elements() { 
    int n = 0;// Add element to buffer, element value iterates from 0, 1, 2 .... num_elements
    while (num_elements > 0)
    {			

        empty.accquire();
        if(buf.getBufferFull())
        {
          full.release();
        }
        else
        {
          System.out.println("User ID" + ID);
          buf.add(n,this);							
          n++;
          num_elements--;
          full.release();
        }
	  }			
  }


  public int getID()
  {
    return ID;
  }

  @Override
  public void run() {
    add_elements();

  }

  //Need condiotn here to check if buffer is full! Otherwise, continue the loop. IN ADD METHOD FORM W3.
       
}   