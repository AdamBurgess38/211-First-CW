import java.util.concurrent.locks.ReentrantLock;

public class Lock
{
    ReentrantLock lock = new ReentrantLock();

    public void Add()
    { 
    synchronized(lock)
        {
            try{
                Thread.sleep(1);
            }
            catch(InterruptedException e)
            {

            }
            {
                return;
            }
        }
     
    }
}