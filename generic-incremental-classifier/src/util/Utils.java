package util;

public class Utils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.err.println("Failed Thread.sleep()");
            e.printStackTrace();
        }
    }
}
