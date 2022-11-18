package FirmXRay.util;

import FirmXRay.main.Logger;

import java.io.*;

public class Fileutil {

    public static void writeToFile(String path, String content, boolean append) {
        try {
            File file = new File(path);
            // if doesn't exist
            file.createNewFile();
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
            out.println(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isResultExist(String tag) {
        File file = new File("./output");
        String[] files = file.list();
        if (files == null)
            return false;

        for (String fname : files) {
            if (fname.equals(tag))
                return true;
        }

        return false;
    }

}
