public class OrderedThread extends Thread {

    private int id;
    private static int flag = 0;

    public OrderedThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (flag != id){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%s thread run, id is %s\n", Thread.currentThread().getName(), id);
        synchronized (OrderedThread.class){
            flag++;
        }
    }

    public static void main(String[] args) {
        OrderedThread[] orderedThreads = new OrderedThread[10];
        for (int i = 0; i < 10; i++) {
            orderedThreads[i] = new OrderedThread(i);
            orderedThreads[i].start();
        }

    }
}