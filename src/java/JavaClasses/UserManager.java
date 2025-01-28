package JavaClasses;

import PATH.RootConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Darkness
 */
public class UserManager {
    private static final String userJson = RootConfig.getUserPath();
    private static final String cachePool = RootConfig.getCachePoolPath();
    
    private static final Gson gson = new GsonBuilder().create();
    
    public static void create(String[] args) throws IOException {
        createUserJsonIfNeeded();
    }
    
    public static void createUserJsonIfNeeded() throws IOException {
        Gson gson = new GsonBuilder().create();
        //check if existing
        if(!Files.exists(Paths.get(userJson))) {
            //create default random/username/password/first/surname/email
            UserAccount defaultUser = new UserAccount(99999,"user1","password1","bob","hope","bob.hope@gmail.com");
            writeUserToJson(Arrays.asList(defaultUser));
        } else {
            //if found, read
            List<UserAccount> existingUsers = readUsersFromJson();
        }
    }
        
    public static List<UserAccount> readUsersFromJson() throws IOException {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(userJson)));
            UserAccount[] userArray = gson.fromJson(jsonContent, UserAccount[].class);
            return new ArrayList<> (Arrays.asList(userArray)); 
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error reading users from JSON", e);
        }
    }

    public static void writeUserToJson(List<UserAccount> user) {
        try (FileWriter writer = new FileWriter(userJson)) {
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserAccount authenticate(String username, String password) throws IOException {
        List <UserAccount> userList = readUsersFromJson();
        for (UserAccount user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                //sucess
                return user;
            }
        }
        //failure to authenticate
        return null;
    }
}
