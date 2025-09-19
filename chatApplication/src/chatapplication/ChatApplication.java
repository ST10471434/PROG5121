
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class ChatApplication {

    
    private static Map<String, User>registeredUsers = new HashMap<>();
    
    public static void main(String[] args){
        Login loginSystem = new Login();
        
        User newUser = new User("MY_K1","Ch&&secake12!","John","Dee","(+27)5 234 6789");
        String registrationResult = loginSystem.registerUser(newUser);
        System.out.println(registrationResult);
        
        User loginAttempt = new User("MY_K1","Ch&&secake12!","John","Dee","(+27)5 234 6789");
        
        String loginResult = loginSystem.returnLoginStatus(loginAttempt);
        System.out.println(loginResult);
      }
class User{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
            

public User(String username, String password, String firstName, String lastName, String phoneNumber){
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
            
}

public String getUsername(){return username;}
public void setUsername(String username){this.username =username;}

public String getPassword(){return firstName;}
public void  setPassword(String password) { this.password = password;} 

public String getfirstName(){return firstName;}
public void setFirstName(String firstName) {this.firstName =firstName;}

public String getlastName(){return lastName;}
public void setLastName(String lastname){this.lastName= lastName;}

public String getPhoneNumber(){return phoneNumber;}
public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}       
}
class Login {
    private static Map<String,User>registeredUsers = new HashMap<>();
    
    public boolean checkUserName(String username){
        return username != null &&
                username.length() <=5 &&
                username.contains("_");
    }public boolean checkPasswordComplexity(String password){
        if (password == null || password.length()<8){return false;
    }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        
        for (char c : password.toCharArray()) { 
            if (Character.isUpperCase(c))hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (Character.isLetterOrDigit(c))hasSpecial =true;
            
            
        }
        return hasCapital && hasNumber && hasSpecial;
    }
    public boolean checkPhoneNumber(String phoneNumber){
        Pattern pattern = Pattern.compile("(=27)5 234 6789");
        return phoneNumber != null && pattern.matcher(phoneNumber).matches();
    }
    public String registerUser(User user){
        if (!checkUserName(user.getUsername())) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more thatn five characters in length.";
        }
        if (!checkPasswordComplexity(user.getPassword())) {
            return "Password is incorrectly formatted;please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
            
        }
        if (!checkPhoneNumber(user.getUsername())){
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
        //if all validations pass ,register the user
        registeredUsers.put(user.getUsername(),user);
        return "Username was successfully captured.\nPasswprd was successfully added.\nCell phone number was successfully added.";
        
                     
     }
    public boolean loginUser(String username, String password){
        User registeredUser = registeredUsers.get(username);
        return registeredUser != null && registeredUser.getPassword().equals(password);
    }
    public String returnLoginStatus(User user){
        if (loginUser(user.getUsername(),user.getPassword())){
            User registeredUser = registeredUsers.get(user.getUsername());
            return "Welcome" + registeredUser.getfirstName() + "." + registeredUser.getlastName() +"It is great to have you back again.";
        } else{
            return "Username or password is incorrect, please try again.";
        }
    }
            
    }
}