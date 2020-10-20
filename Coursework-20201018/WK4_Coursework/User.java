import java.util.*;

public class User
{		
  private int ID;
  private int num_elements;
  public static Buffer buf;
  private Semaphore semaphore;

    
  public User(int id, int el, Buffer b, Semaphore s)							//Created user will add a certain number of elements to the buffer.
  {
     ID = id;
     num_elements = el;
     buf = b;
     semaphore = s;
  }


  public int getID()
  {
    return ID;
  }
       
}   