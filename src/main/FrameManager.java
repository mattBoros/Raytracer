package main;

/**
 * Created by Matt on 4/19/2017.
 */
public class FrameManager {

    private static final long MILLISECONDS_BETWEEN_LAG_PRINTS = 1000;

    private final long millisecondsPerFrame;
    private long lastStart = 0;
    private long lastLagPrint = 0;

    public FrameManager(int fps){
        millisecondsPerFrame = Math.round(1000.0 / fps);
    }

    public void startFrame(){
        lastStart = System.currentTimeMillis();
    }

    public void endFrame(){
        long now = System.currentTimeMillis();
        long diff = now - lastStart;
        if(millisecondsPerFrame > diff){
            long timeToWait = millisecondsPerFrame - diff;
            try {
                Thread.sleep(timeToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if(now - lastLagPrint > MILLISECONDS_BETWEEN_LAG_PRINTS){
                Printer.print("lag");
                lastLagPrint = now;
            }
        }
    }





}
