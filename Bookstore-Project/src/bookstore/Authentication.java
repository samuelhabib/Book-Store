package bookstore;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Authentication {
    String login;
    
    public Authentication(String login){
        this.login = login;
    }
    
    public static String Auth(String user, String pass){
        try {
            File login_file = new File("customers.txt");
            boolean customer;
            try (Scanner login_scan = new Scanner(login_file)){
                customer = false;
                
                if(user.equals("admin") && pass.equals("admin") || user.equals(" ") && pass.equals(" ") ){
                    login_scan.close();
                    return "Admin";  
                }
                
                while(login_scan.hasNextLine() == true){
                    String temp_login = login_scan.nextLine();

                    String[] tempStr = temp_login.split("/", 5);
                    String username = tempStr[0];
                    String password = tempStr[1];
                    
                    if(username.equals(user) && password.equals(pass)){
                        customer = true;
                    }
                    
                }
                
                login_scan.close();
            }
            
            if(customer){
                return "Customer";
                
            } else{
                return "Error";
            }

        } catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        
        return "Error";
    }

}
