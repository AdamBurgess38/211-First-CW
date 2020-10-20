import java.util.*;
public class startServer
   {
  	Buffer b;										//Creation of buffer object  

        public startServer()
   {												//Creates execution scenario between user and webservers on buffer
        
        long startTime = System.currentTimeMillis();		
																
												//Instantiate all objects (webserver, users, buffer)
	b = new Buffer(bufferSize);								
	
												//Equally subdivide user inputted elements across all user objects

	System.out.println("-----------------------");
	
												//Outputs the total number of elements added/removed from user and webserver		

	System.out.println("-----------------------");
	//System.out.println("Buffer has " + X + " elements remaining");			//Check to see buffer if all elements produced from users have been successfully removed by webservers
	System.out.println("-----------------------");
												//Checks if all users and web servers successfully finished
				
	long endTime = System.currentTimeMillis();
	System.out.println("-----------------------");
     	System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");		
	
    }
  
public static void main(String[] args)
  {

    System.out.println("Enter buffer capacity");					//Insert user inputted values for program execution
    
    System.out.println("Enter number of users");
    
    System.out.println("Enter number of servers");
    
    System.out.println("Enter total number of elements");

    startServer start = new startServer();
  }
}
