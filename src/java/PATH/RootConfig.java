package PATH;

import com.google.gson.Gson;

public class RootConfig {
    //paths to JSONS
    //private static final String numberPoolPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\numberPool.json";
    //private static final String usedNumberPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\usedPool.json";
    private static final String userPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\user.json";
    private static final String cityPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\cityLocations.json";
    private static final String tempJSONPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\csrng.json";
    private static final String cachePoolPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\cachePool.json";
    private static final String usedPoolPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\usedPool.json";
    private static final String databasePath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\DB\\imperatoris.db";
    private static final String databaseConnection = "jdbc:sqlite:" + databasePath;
    private static final String publicEventsPath = "E:\\javaprojects\\netbeans\\TravelBuddyFinderApp\\JSON\\publicEvents.json";
    
    static {
        //load SQLite JDBC
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    //method for creating single Gson instance
    private static final Gson gsonInstance = new Gson();
        
    public static Gson getGson() {
        return gsonInstance;
    }
    
    public static String getDatabaseConnection() {
        return databaseConnection;
    }
    
    public static String getUserPath() {
        return userPath;
    }
    public static String getCityPath() {
        return cityPath;
    }
    public static String getTempJSONPath() {
        return tempJSONPath;
    }
    public static String getCachePoolPath() {
        return cachePoolPath;
    }
    public static String getUsedPoolPath() {
        return usedPoolPath;
    }
    public static String getPublicEventsPath() {
        return publicEventsPath;
    }
}
