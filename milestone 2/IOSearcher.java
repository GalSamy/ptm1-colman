package test;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class IOSearcher {
public static boolean search(String w,String...files){
    boolean flag = false;
    File[] Files = new File[files.length];
    for (int i = 0; i < files.length;i++){
        try{
        Files[i] = new File(files[i]);
        }catch (Exception e){}
    }
    for (File f : Files){
        try {
            Scanner myReader = new Scanner(f);
            while (myReader.hasNext()) {
                String data = myReader.next();
                if (data == w)
                    flag = true;
            }
            myReader.close();
        }catch (Exception e){}
    }
    return flag;
}
}
