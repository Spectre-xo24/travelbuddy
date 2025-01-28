/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import PATH.RootConfig;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.Path;
 
@Path("RandomNo")
public class RandomNoResource {

    //create JSON of randomnumber from CSRNG
    public static void performNumberRequestAndSave() throws IOException {
        int minimum = 1;
        int maximum = 99999;
        String csrngPath = "https://csrng.net/csrng/csrng.php?min=" 
                + minimum + "&max=" 
                + maximum + "";
        String savePath = RootConfig.getTempJSONPath();
        
        try {
            //URL
            String apiUrl = csrngPath;
            URL url = new URL(apiUrl);
            //open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //get
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            //check response success
            if (responseCode == HttpURLConnection.HTTP_OK){
                //read response 
                Gson gson = new Gson();
                //(new InputStreamReader(connection.getInputStream()),JsonObject.class
                JsonArray jsonResponseArray = gson.fromJson(new InputStreamReader(connection.getInputStream()), JsonArray.class);
                if(jsonResponseArray.size() > 0) {
                    JsonObject jsonResponse = jsonResponseArray.get(0).getAsJsonObject();
                    if ("success".equals(jsonResponse.get("status").getAsString())) {
                        //get values
                        int min = jsonResponse.get("min").getAsInt();
                        int max = jsonResponse.get("max").getAsInt();
                        int random = jsonResponse.get("random").getAsInt();

                        //create JSON object
                        JsonArray resultArray = new JsonArray();
                        JsonObject resultObject = new JsonObject();
                        resultObject.addProperty("status","success");
                        resultObject.addProperty("min", min);
                        resultObject.addProperty("max", max);
                        resultObject.addProperty("random", random);
                        resultArray.add(resultObject);
                        //save location for JSON
                        String filePath = savePath;
                        try (FileWriter fileWriter = new FileWriter(filePath)) {
                            gson.toJson(resultArray, fileWriter);
                        }
                        System.out.println("JSON saved successfully: " + filePath);
                    } else {
                        System.err.println("Error: status not success");
                    }
                }
            } else {
                System.err.println("Error: HTTP request failed with code: " + responseCode);
            }
            //close connection
            connection.disconnect();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
