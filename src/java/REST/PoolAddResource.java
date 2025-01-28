package REST;

import JavaClasses.csrngNumber;
import PATH.RootConfig;
import static REST.RandomNoResource.performNumberRequestAndSave;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Darkness
 */
@Path("PoolAdd")
public class PoolAddResource {
    public static void createCachePoolIfNotExist() {
        String cacheFile = RootConfig.getCachePoolPath();
        // Check if the cache file exists
        if (!Files.exists(FileSystems.getDefault().getPath(cacheFile))) {
            // Create a JSON array with a default value
            JsonArray jsonArray = new JsonArray();
            JsonObject initialObject = new JsonObject();
            initialObject.addProperty("random", 99999);
            jsonArray.add(initialObject);

            // Convert the array to a string
            Gson gson = new GsonBuilder().create();
            String updatedJsonArray = gson.toJson(jsonArray);

            // Write the JSON array to the cache pool file
            try (FileWriter fileWriter = new FileWriter(cacheFile)) {
                fileWriter.write(updatedJsonArray);
                System.out.println("Cache pool file created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void createUsedPoolIfNotExist() {
        String usedFile = RootConfig.getUsedPoolPath();
        // Check if the cache file exists
        if (!Files.exists(FileSystems.getDefault().getPath(usedFile))) {
            // Create a JSON array with a default value
            JsonArray jsonArray = new JsonArray();
            JsonObject initialObject = new JsonObject();
            initialObject.addProperty("random", 99999);
            jsonArray.add(initialObject);

            // Convert the array to a string
            Gson gson = new GsonBuilder().create();
            String updatedJsonArray = gson.toJson(jsonArray);

            // Write the JSON array to the cache pool file
            try (FileWriter fileWriter = new FileWriter(usedFile)) {
                fileWriter.write(updatedJsonArray);
                System.out.println("Cache pool file created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static int getTargetRandomValue(String inFile) throws FileNotFoundException, IOException{
        try (Reader inReader = new FileReader(inFile)) {
            JsonArray inArray = JsonParser.parseReader(inReader).getAsJsonArray();
            if (inArray.size()>0){
                JsonElement firstElement = inArray.get(0);
                if(firstElement.isJsonObject()) {
                    JsonObject firstObject = firstElement.getAsJsonObject();
                    JsonElement randomElement = firstObject.get("random");
                    if (randomElement != null && randomElement.isJsonPrimitive()) {
                        return randomElement.getAsJsonPrimitive().getAsInt();
                    }
                }
            }
        }
        //default for when target cannot be found
        return -1;
    }
    private static boolean checkIfNumExists(String usedFile, String inFile) throws FileNotFoundException, IOException {
        int targetRandomValue = getTargetRandomValue(inFile);
        try (Reader outReader = new FileReader(usedFile)) {
            JsonArray outArray = JsonParser.parseReader(outReader).getAsJsonArray();
            
            String keyToSearch = "random";
            
            for (JsonElement outElement : outArray) {
                if (outElement.isJsonObject()) {
                    JsonObject outObject = outElement.getAsJsonObject();
                    JsonElement randomElement = outObject.get(keyToSearch);
                    
                    if (randomElement != null && randomElement.isJsonPrimitive()) {
                        int randomValue = randomElement.getAsJsonPrimitive().getAsInt();
                        
                        if (randomValue == targetRandomValue) {
                            performNumberRequestAndSave();
                            return true; //found
                        }
                    }
                }
            }
        }
        return false;
    }
    
    //method to update pools.
    public static void addCacheUsedPools() {
        //ensure files exist
        createCachePoolIfNotExist();
        createUsedPoolIfNotExist();
        
        String cacheFile = RootConfig.getCachePoolPath();
        String usedFile = RootConfig.getCachePoolPath();
        
        try (Reader outReader = new FileReader(cacheFile)) {
            JsonArray cacheArray = JsonParser.parseReader(outReader).getAsJsonArray();
            //check size of array
            int size = cacheArray.size();
            while (size < 10){
                //add numbers to pools 
                uniqueNumGen();
                size++;
            }
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
    }
    
    //ensures unique numbers are added to pools
    public static void uniqueNumGen() throws IOException {
        //String cacheFile = RootConfig.getCachePoolPath();
        String usedFile = RootConfig.getUsedPoolPath();
        String inFile = RootConfig.getTempJSONPath();
        
        //maximum attempts to get a unique number
        int maxAttempts = 10;
        for (int attempt = 1 ; attempt <= maxAttempts ; attempt++) {
            if (!checkIfNumExists(usedFile,inFile)) {
                //generated successfully
                appendCachePool();
                appendUsedPool();
            } else {
                RandomNoResource.performNumberRequestAndSave();
            }
            System.out.println("Attempt " + attempt + ": Generating a unique number...");
            RandomNoResource.performNumberRequestAndSave();
        }
        throw new IOException("!!Unique number could not be generated!!");
    }
    public static void appendUsedPool() {
        String outFile = RootConfig.getUsedPoolPath();
        String inFile = RootConfig.getTempJSONPath();
        
        try (Reader reader = new FileReader(inFile)) {
            // Parse the existing JSON file
            JsonArray jsonArray;

            try {
                jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            } catch (JsonParseException e) {
                // If the file is empty or not a valid
                jsonArray = new JsonArray();
            }
            
            
            // Extract the "random" value from the first object
            int newRandomValue = jsonArray.size() > 0 ?
                    jsonArray.get(0).getAsJsonObject().get("random").getAsInt() : 0;

            // Create a new JSON object with the desired structure
            JsonObject newJsonObject = new JsonObject();
            newJsonObject.addProperty("random", newRandomValue);

            //remove all existing from array
            while (jsonArray.size()>0){
                jsonArray.remove(0);
            }
            
            // new JSON object for array
            jsonArray.add(newJsonObject);
            
            try (Reader outReader = new FileReader(outFile)) {
                JsonArray outArray = JsonParser.parseReader(outReader).getAsJsonArray();
                //add elements
                jsonArray.addAll(outArray);
            } catch (IOException | JsonParseException e) {
                e.printStackTrace();
            }

            // Convert the updated JSON array to a string
            Gson gson = new GsonBuilder().create();
            String updatedJsonArray = gson.toJson(jsonArray);

            // Write the updated JSON array to the output file
            try (FileWriter fileWriter = new FileWriter(outFile)) {
                fileWriter.write(updatedJsonArray);
                System.out.println("New JSON value added to file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void appendCachePool() {
        String outFile = RootConfig.getCachePoolPath();
        String inFile = RootConfig.getTempJSONPath();
        
        try (Reader reader = new FileReader(inFile)) {
            // Parse the existing JSON file
            JsonArray jsonArray;

            try {
                jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            } catch (JsonParseException e) {
                // If the file is empty or not a valid
                jsonArray = new JsonArray();
            }
            
            
            // Extract the "random" value from the first object
            int newRandomValue = jsonArray.size() > 0 ?
                    jsonArray.get(0).getAsJsonObject().get("random").getAsInt() : 0;

            // Create a new JSON object with the desired structure
            JsonObject newJsonObject = new JsonObject();
            newJsonObject.addProperty("random", newRandomValue);

            //remove all existing from array
            while (jsonArray.size()>0){
                jsonArray.remove(0);
            }
            
            // new JSON object for array
            jsonArray.add(newJsonObject);
            
            try (Reader outReader = new FileReader(outFile)) {
                JsonArray outArray = JsonParser.parseReader(outReader).getAsJsonArray();
                //add elements
                jsonArray.addAll(outArray);
            } catch (IOException | JsonParseException e) {
                e.printStackTrace();
            }

            // Convert the updated JSON array to a string
            Gson gson = new GsonBuilder().create();
            String updatedJsonArray = gson.toJson(jsonArray);

            // Write the updated JSON array to the output file
            try (FileWriter fileWriter = new FileWriter(outFile)) {
                fileWriter.write(updatedJsonArray);
                System.out.println("New JSON value added to file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //tests
    public static void test() {
        //create data
        List<csrngNumber> poxList = new ArrayList<>();
        poxList.add((csrngNumber) new csrngNumber("success",1,99999,85200));
        //convert
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(poxList);
        //extract
        int testRandomValue = poxList.get(0).getRandom();
        //create new 
        JsonObject newJsonObject = new JsonObject();
        newJsonObject.addProperty("status", "success");
        newJsonObject.addProperty("min", 1);
        newJsonObject.addProperty("max", 999);
        newJsonObject.addProperty("random", testRandomValue);
        //json obj to string
        String newJsonString = gson.toJson(newJsonObject);
        String output = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\usedPoolTest.json";
        //write
        try (FileWriter fileWriter = new FileWriter(output)) {
            fileWriter.write(jsonArray);
            fileWriter.write("\n"); // Add a newline for better readability
            fileWriter.write(newJsonString);
            System.out.println("JSON written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testfromfile() {
        String inputFilePath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\csrng.json";
        String outputFilePath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\usedPoolTest.json";

        try (FileReader fileReader = new FileReader(inputFilePath)) {
            // Parse the existing JSON file
            JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();

            // Extract the value from the first object
            int testFileRandomValue = jsonArray.get(0).getAsJsonObject().get("random").getAsInt();

            // Create a new JSON object with the desired structure
            JsonObject newJsonObject = new JsonObject();
            //newJsonObject.addProperty("status", true);
            //newJsonObject.addProperty("min", true);
            //newJsonObject.addProperty("max", true);
            newJsonObject.addProperty("random", testFileRandomValue);

            // Convert the new JSON object to a string
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String newJsonString = gson.toJson(newJsonObject);

            // Write the new JSON object to the output file
            try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                fileWriter.write(newJsonString);
                System.out.println("New JSON written to file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testAppendToFile() {
        String outFile = RootConfig.getUsedPoolPath();
        String inFile = RootConfig.getTempJSONPath();
        
        try (Reader reader = new FileReader(inFile)) {
            // Parse the existing JSON file
            JsonArray jsonArray;

            try {
                jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            } catch (JsonParseException e) {
                // If the file is empty or not a valid
                jsonArray = new JsonArray();
            }
            
            
            // Extract the "random" value from the first object
            int newRandomValue = jsonArray.size() > 0 ?
                    jsonArray.get(0).getAsJsonObject().get("random").getAsInt() : 0;

            // Create a new JSON object with the desired structure
            JsonObject newJsonObject = new JsonObject();
            newJsonObject.addProperty("random", newRandomValue);

            //remove all existing from array
            while (jsonArray.size()>0){
                jsonArray.remove(0);
            }
            
            // new JSON object for array
            jsonArray.add(newJsonObject);
            
            try (Reader outReader = new FileReader(outFile)) {
                JsonArray outArray = JsonParser.parseReader(outReader).getAsJsonArray();
                //add elements
                jsonArray.addAll(outArray);
            } catch (IOException | JsonParseException e) {
                e.printStackTrace();
            }

            // Convert the updated JSON array to a string
            Gson gson = new GsonBuilder().create();
            String updatedJsonArray = gson.toJson(jsonArray);

            // Write the updated JSON array to the output file
            try (FileWriter fileWriter = new FileWriter(outFile)) {
                fileWriter.write(updatedJsonArray);
                System.out.println("New JSON value added to file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }    
}