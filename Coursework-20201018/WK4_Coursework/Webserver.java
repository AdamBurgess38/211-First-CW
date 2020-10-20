import java.util.*;

public class Webserver
{										//Web server removes elements from the buffer
  private int ID;
  private int num_elements;
  public static Buffer buf;
  private Semaphore semaphore;
  public Webserver(int id, int el, Buffer b, Semaphore s)			
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