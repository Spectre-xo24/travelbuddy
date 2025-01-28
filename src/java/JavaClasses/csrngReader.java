/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClasses;

import PATH.RootConfig;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Darkness
 */
public class csrngReader {
    public static void ReadOut(String[] args) throws FileNotFoundException, IOException{
        String filePath = RootConfig.getTempJSONPath();
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            csrngNumber number = gson.fromJson(reader, csrngNumber.class);
            System.out.println("Status: " + number.getStatus());
            System.out.println("Min: " + number.getMin());
            System.out.println("Max: " + number.getMax());
            System.out.println("Random: " + number.getRandom());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
