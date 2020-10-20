import java.util.*;

public class Buffer //Provides data and operations onto the fixed-length buffer
{
    private LinkedList < Object > buf_list;


    public Buffer(int n) //Buffer creation, with n indicating the maximum capacity
    {
        buf_list = new LinkedList < Object > ();
    }

}