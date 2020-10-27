public class BinarySemaphore {
    private int count;


    public BinarySemaphore(int n)
    {
      count = n;
    }


    public synchronized void pause()
    {
      count--;
      if(count <0)
      {
        try{
          wait();
        }
        catch(InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }


    public synchronized void resume()
    {
      count++;
      if(count <= 0)
      {
        notify();
      }
    }
  
  }