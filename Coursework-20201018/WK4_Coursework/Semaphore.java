public class Semaphore {
    private int count;


    public Semaphore(int n)
    {
      count = n;
    }


    public synchronized void accquire()
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


    public synchronized void release()
    {
      count++;
      if(count <= 0)
      {
        notify();
      }
    }
  
  }