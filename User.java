public class User { 
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    private String username; 
    private String password; 
    private String phoneNumber;

    public User(String username, String password, String phoneNumber) {
        this.username = username; 
        setPassword(password); 
        this.phoneNumber = phoneNumber; 
    }
    public User() { 
        this.username = "username"; 
        this.password = "pa$$word"; 
        this.phoneNumber = " ";
    }
    public User (User user){ 
        this(user.username, user.password, user.phoneNumber);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) { 
        if (password.matches(PASSWORD_REGEX)) { 
            this.password = password;
        } else {
            System.out.println("Invalid password. Password must contain at least one lowercase letter, one uppercase letter, one digit, and be at least 8 characters long.");
        }
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
}