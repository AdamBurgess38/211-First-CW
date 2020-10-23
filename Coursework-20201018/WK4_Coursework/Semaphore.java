import java.util.*;
public class Semaphore
{
    private int count;
    public Semaphore(int val)
    {
        count = val;// Should check it’s >= 0
    }

    public synchronized void accquire()           
    {               
        if(count > 0)         
    {
        count--;
    }
        else
    {
    try{                                     
        wait();
        }  
        catch (InterruptedException e){e.printStackTrace();}
            }   
    }
 
    public synchronized void release()      
     {      
        if(count <= 0)
        {
            notify();
        }
        else
            {
            count++;
            }
    } 
    
    
}

