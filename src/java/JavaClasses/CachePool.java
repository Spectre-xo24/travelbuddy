/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClasses;

import PATH.RootConfig;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Darkness
 */
public class CachePool {
    private int random;
    
    //constructor
    public void CachePool(){
        //default
    }
    public void CachePool(int random){
        this.random = random;
    }
    @Override
    public String toString() {
        return "CachePool {" + 
                "random=" + random + 
                "}";
    }
    
    //get methods
    public int getRandom() {
        return random;
    }

    
    //setters
    public void setRandom(int random) {
        this.random = random;
    }
    
    //method to read first entry
    public int readFirstEntry() {
        checkAndCreateUserFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(RootConfig.getCachePoolPath()))){
            Gson gson = new Gson();
            CachePool[] cachePoolArray = gson.fromJson(reader, CachePool[].class);
            
            if (cachePoolArray != null && cachePoolArray.length > 0){
                return cachePoolArray[0].getRandom();
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Error reading first entry or array is empty");
    }
    
    //method to delete first entry
    public String deleteFirstEntry() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RootConfig.getCachePoolPath()))) {
            Gson gson = new Gson();
            CachePool[] cachePoolArray = gson.fromJson(reader, CachePool[].class);
            if (cachePoolArray != null && cachePoolArray.length > 0) {
                //entries above 0 added to new
                CachePool[] updatedArray = Arrays.copyOfRange(cachePoolArray, 1, cachePoolArray.length);
                cachePoolArray = updatedArray;
                //write to file
                try (Writer writer = new FileWriter(RootConfig.getCachePoolPath())) {
                    gson.toJson(cachePoolArray, writer);
                }
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
            return "error deleting first entry";
        }
        return "item successfully removed";
    }
    
    //method if not found create testuser
    public void checkAndCreateUserFile() {
        File userFile = new File(RootConfig.getUserPath());
        if(!userFile.exists()){
            //if file does not exist create
            List<UserAccount> defaultUserList = new ArrayList<>();
                    defaultUserList.add(new UserAccount(00001,"testaccount1","password1","firstname1","surname1","test1@gmail.com"));
                    //defaultUserList.add(new UserAccount(99998,"testaccount2","password2","firstname2","surname2","test2@gmail.com"));
            try (FileWriter writer = new FileWriter(RootConfig.getUserPath())){
                Gson gson = new Gson();
                gson.toJson(defaultUserList, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
