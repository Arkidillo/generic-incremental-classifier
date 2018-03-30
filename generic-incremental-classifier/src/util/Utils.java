package util;

import java.io.*;

public class Utils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.err.println("Failed Thread.sleep()");
            e.printStackTrace();
        }
    }

    public static void copyFile(File source, File dest){
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // given the name of the file, get the '.x' file extension
    public static String getExtension(String filename) {
        // get the index of the '.'
        int i = filename.length() - 1;
        for (; i >= 0; i--) {
             if (filename.charAt(i) == '.') break;
        }

        // Start from the . to the end of the filename
        return filename.substring(i + 1, filename.length());
    }
}
