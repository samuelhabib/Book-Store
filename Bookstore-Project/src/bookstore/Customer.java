package bookstore;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Customer {
    String username;
    String password;
    int points;
    String status;
    
    public Customer(String username, String password, int points){
        this.username = username;
        this.password = password;
        this.points = points;
        this.status = "Silver";
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }

    public int getPoints(){
        return points;
    }
    
    public String getStatus(){
        return status;
    }
    
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public void setPoints(int points){
        this.points = points;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public int updatePoints(int total){
        points = points + total * 10; 
        return points;

    }
    
    public int redeemPoints(int totalCost){
        int cost;
        int originalPoints = points;
        
        points = originalPoints - (totalCost * 100);
        
        if(points > 0){
            cost = 0;
            
        } else{
            cost = totalCost - (originalPoints/100);
            points = 0;
        }
        
        return cost;
    }
    
    
    public static String[] init_customer(String customer){
        
        try {
            File customer_file = new File("customers.txt");
            try (Scanner customer_scan = new Scanner(customer_file)) {
                String[] tempStr;
                
                while(customer_scan.hasNextLine() == true){
                    String temp_line = customer_scan.nextLine();
                    tempStr = temp_line.split("/", 5);
                    String temp_username2 = tempStr[0];
                    
                    if(temp_username2.equals(customer)){
                        return tempStr;
                    }
                }
                customer_scan.close();
            }

        }catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        
        return null;
    }
    
    
}
