package thread;


import lombok.extern.slf4j.Slf4j;
import thread.bo.LiftOff;

@Slf4j
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0 ;i < 5;i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");
    }

}
