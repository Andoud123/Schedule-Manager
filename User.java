import java.util.regex.Pattern;

public class User { 
    private static final String USERNAME_REGEX ="^.{5,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    private static final String PHONENUMBER_REGEX = "^\\+961 (3|70|71|76|78|81)/\\d{6}$";
    private String username; 
    private String password; 
    private String phoneNumber;

    public User(String username, String password, String phoneNumber) {
        this.username = username; 
        this.password = password; 
        this.phoneNumber = phoneNumber; 
    }
    public User() { 
        this.username = "username"; 
        this.password = "password"; 
        this.phoneNumber = " ";
    }
    public User (User user){ 
        this(user.username, user.password, user.phoneNumber);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    public String getUsername() {
		return username;
	}
    public String getPassword() { 
        return password;
    }
    public String getPhoneNumber(){ 
        return phoneNumber;
    }
    public boolean isValidUsername(){
        return Pattern.matches(USERNAME_REGEX, username);
    }
    public boolean isValidPassword(){
         return Pattern.matches(PASSWORD_REGEX, password);
    }
    public boolean isValidPhoneNumber(){
        return Pattern.matches(PHONENUMBER_REGEX, phoneNumber);
    }
}
