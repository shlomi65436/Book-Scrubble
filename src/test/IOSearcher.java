package test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOSearcher {
    public static boolean search(String word, String... fileNames){
        for(String file : fileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(word)) {
                        return true;
                    }
                }
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
