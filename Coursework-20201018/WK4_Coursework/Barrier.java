public class Barrier {
    public synchronized void pause() {
        try {
            wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public synchronized void resumeAll()
    {
        notifyAll();
    }
}