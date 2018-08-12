package main;

/**
 * Created by Matt on 4/26/2017.
 */
public class AlgorithmTimer {

    private static final int TEST_TRIALS = 50;

    private final String name;

    private final long[] algorithmTimes;

    private int finalAlgorithmToRun = -1;

    private int totalTestTrials;
    private int currentTestTrials = 0;

    public AlgorithmTimer(String name, int numAlgorithms){
        this(name, numAlgorithms, TEST_TRIALS);
    }

    public AlgorithmTimer(String name, int numAlgorithms, int testTrials){
        this.name = name.toUpperCase();
        totalTestTrials = numAlgorithms * testTrials;
        algorithmTimes = new long[numAlgorithms];
    }

    public boolean shouldRunAlgorithm(int algorithmNumber){
        if(finalAlgorithmToRun == -1){
            return true;
        }
        return finalAlgorithmToRun == algorithmNumber;
    }

    public void algorithmStart(int algorithmNumber){
        if(finalAlgorithmToRun != -1){
            return;
        }

        algorithmTimes[algorithmNumber] -= System.currentTimeMillis();
    }

    public void algorithmEnd(int algorithmNumber){
        if(finalAlgorithmToRun != -1){
            return;
        }

        algorithmTimes[algorithmNumber] += System.currentTimeMillis();
        currentTestTrials++;

        if(currentTestTrials == totalTestTrials){
            long shortestTime = Long.MAX_VALUE;
            finalAlgorithmToRun = -1;
            for(int i = 0; i < algorithmTimes.length; i++){
                if(algorithmTimes[i] < shortestTime){
                    shortestTime = algorithmTimes[i];
                    finalAlgorithmToRun = i;
                }
            }

            System.out.println(name  + " : finished tests");
            System.out.println(name + " : chosen algorithm = " + finalAlgorithmToRun);
            System.out.println(name + " : times = " + arrayToString(algorithmTimes));

        }
    }

    private static String arrayToString(long[] arr){
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < arr.length - 1; i++){
            sb.append(arr[i] + ", ");
        }
        sb.append(arr[arr.length - 1] + "]");
        return sb.toString();
    }
}
