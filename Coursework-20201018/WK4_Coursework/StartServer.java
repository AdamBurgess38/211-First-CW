import java.util.*;




public class StartServer {
    Buffer buffer;
    Semaphore empty;
    Semaphore full;

    public StartServer(int bufferSize, int numElements, int numServers, int numUser) { //Creates execution scenario between user and webservers on buffer

        long startTime = System.currentTimeMillis();

        //Instantiate all objects (webserver, users, buffer)
        buffer = new Buffer(bufferSize);
        full = new Semaphore(0);
        empty = new Semaphore(bufferSize);

        //Equally subdivide user inputted elements across all user objects

        int elementsPerUser[] = new int[numUser];
        int elementsPerServer[] = new int[numServers];
        int remU = numElements % numUser;
        int remS = numElements % numServers;
        int numElementsUser = numElements / numUser;
        int numElementsServer = numElements / numServers;
        int remainderDistributed = 0;
        for (int i = 0; i < numUser; i++) {
            elementsPerUser[i] = numElementsUser;
        }
        int x = 0;
        //Even spread the remainder.
        while (remainderDistributed != remU) {
            elementsPerUser[x] += 1;
            if (x == elementsPerUser.length - 1) {
                x = 0;
            } else
                x++;
            remainderDistributed++;
        }


        for (int i = 0; i < numServers; i++) {
            elementsPerServer[i] = numElementsServer;
        }
        x = 0;
        remainderDistributed = 0;
        //Even spread the remainder.
        while (remainderDistributed != remS) {
            elementsPerServer[x] += 1;
            if (x == elementsPerServer.length - 1) {
                x = 0;
            } else
                x++;
            remainderDistributed++;
        }
        User[] users = new User[numUser];
        Webserver[] servers = new Webserver[numServers];
        Thread[] threads = new Thread[numUser + numServers];
        int y = 0;
        for (int i = 0; i < numUser; i++) {
            System.out.println(elementsPerUser[i]);
            users[i] = new User(i, elementsPerUser[i], buffer, empty, full);
            threads[y] = new Thread(users[i]);
            y++;
        }
        for (int i = 0; i < numServers; i++) {
            System.out.println(elementsPerServer[i]);
            servers[i] = new Webserver(i, buffer, elementsPerServer[i], empty, full);
        }

        for (int i = 0; i < numServers; i++) {
            threads[y] = new Thread(servers[i]);
            y++;
        }





        System.out.println("-----------------------");
        for (int i = 0; i < numUser + numServers; i++) {
            threads[i].start();

        }







        for (int i = 0; i < numUser + numServers; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {

            }
        }
        for (User user: users) {
            user.reportOutput();

        }
        for (Webserver server: servers) {
            server.reportOutput();

        }

        //Outputs the total number of elements added/removed from user and webserver		

        System.out.println("-----------------------");
        //System.out.println("Buffer has " + X + " elements remaining");			//Check to see buffer if all elements produced from users have been successfully removed by webservers
        System.out.println("-----------------------");
        //Checks if all users and web servers successfully finished
        buffer.reportBufferCapacity();
        long endTime = System.currentTimeMillis();
        System.out.println("-----------------------");
        System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");

    }

    public static void main(String[] args) {
        int buffersize, numUsers, numServers, totalElements;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter buffer capacity"); //Insert user inputted values for program execution
        buffersize = scan.nextInt();
        System.out.println("Enter number of users");
        numUsers = scan.nextInt();
        System.out.println("Enter number of servers");
        numServers = scan.nextInt();
        System.out.println("Enter total number of elements");
        totalElements = scan.nextInt();
        scan.close();
        StartServer start = new StartServer(buffersize, totalElements, numServers, numUsers);
    }
}