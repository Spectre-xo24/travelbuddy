package JavaClasses;

/**
 *
 * @author Darkness
 */
public class UserAccount {
    private int userID;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String email;
    
    //constructors
    public UserAccount(){
        //default
    }
    public UserAccount(int userID, String username, String password, String firstname, String surname, String email){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
    }
    @Override 
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username" + username +
                ", password" + password +
                ", firstname" + firstname + 
                ", surname" + surname +
                ", email" + email +
                "}";
    }
        
    //get
    public int getUserID(){
        return userID;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }
    
    //set
    public void setUserID(int userID){
        this.userID = userID;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
