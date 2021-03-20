package thread.bo;

public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "),";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            //Thread.yield() 方法，线程调度器，使当前线程由执行状态，变成为就绪状态，让出cpu时间，在下一个线程执行时候，此线程有可能被执行，也有可能没有被执行
            Thread.yield();
        }
    }
}
