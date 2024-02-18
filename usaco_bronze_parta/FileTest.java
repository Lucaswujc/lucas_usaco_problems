package usaco_bronze_parta;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileTest {
    public static void main(String[] args) {
        File f = new File("test.file");
        try (PrintWriter writer = new PrintWriter(f)) {
            writer.println("A");
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try (DataOutputStream ds = new DataOutputStream(new FileOutputStream(new File("test2.file")))) {
            for (long  l = 0 ; l < 1000l; l++)
                ds.writeLong(l);
            ds.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
