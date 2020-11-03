public class Semaphore {
    private int count;

    /**
     * n is number of threads which can accquire at a time
     */
    public Semaphore(int n)
    {
      count = n;
    }

    /**
     * Thread will attempt to accquire Semaphore here.
     */
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

    /**
     * Count increased and if count <= 0 we notify a random thread.
     */
    public synchronized void release()
    {
      count++;
      if(count <= 0)
      {
        notify();
      }
    }
  
  }