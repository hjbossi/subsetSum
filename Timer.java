// Dale Skrien
// Timer Class
// CS375
// 4-21-17


// class definition
public class Timer {
    // variable declarations
    private static long startTime;
    private static long endTime;

    // start timer
    public static void start() {
        startTime = System.nanoTime();
    }

    // stop timer
    public static void stop() {
        endTime = System.nanoTime();
    }

    /**
     * @return the milliseconds between the last call of start and stop
     */
    public static long getRuntime() {
        // return total runtime
        return (endTime - startTime) / 1000000;
    }
}

// end of Timer.java