package bookstore;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Scanner;


public class Customer_Loading {
    String customer;
    
    private static final Customer_Loading user_file2 = new Customer_Loading("customers.txt");
    
    public Customer_Loading(String customer_init){
        this.customer = customer_init;
    }
    
    public static Customer_Loading getInstance(){
        return user_file2;
    }

    
    public static ArrayList<String> getCustomers(){
        try {
            File customer_file = new File("customers.txt");
            
            ArrayList<String> customer_list;
            try (Scanner customer_scan = new Scanner(customer_file)) {
                customer_list = new ArrayList<>();
                while(customer_scan.hasNextLine() == true){
                    String temp_customer = customer_scan.nextLine();
                    customer_list.add(temp_customer);
                }
                customer_scan.close();
            }
            
            return customer_list;
            

        } catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        
        return null;
    }
    
    
    public static void addCustomer(String username, String password){
        try {
            File customer_file = new File("customers.txt");
            String text;
            username = username.trim();
            
            try (FileWriter file_writer = new FileWriter(customer_file, true)){
                
                if(customer_file.length() == 0){
                    text = username + "/" + password + "/0";
                } else{
                    text = "\n" + username + "/" + password + "/0";
                }
                
                BufferedReader reader;
                int tempnum = 0;
                reader = new BufferedReader(new FileReader("customers.txt"));
			String line = reader.readLine();
			while (line != null) {
                            
                                       
				   if(line.equals(text.trim())){
                                  
                                       tempnum = 1;
                                     }
			
				line = reader.readLine();
			}
			reader.close();
                        
                        System.out.println(tempnum);
                        if(tempnum == 0){
                            file_writer.write(text);
                            file_writer.close();
             
                        }else if(tempnum == 1){
                            
                            System.out.println("Error");
                        }
             
            }
            
        }catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        
    }
    
    
    
    public static void deleteCustomer(String lineToRemove){
        try {
            File customer_file = new File("customers.txt");
            File tempCustomer_file = new File("tempCustomers.txt");
            Scanner customer_scan = new Scanner(customer_file);
            String temp_username = "";
            
            try (FileWriter file_writer = new FileWriter(tempCustomer_file, true)){
                String[] tempStr;
                
                while(customer_scan.hasNextLine() == true){
                    String temp_line = customer_scan.nextLine();
                    tempStr = temp_line.split("/", 5);
                    String temp_username2 = tempStr[0];
                    
                    if(temp_username2.equals(lineToRemove)) continue;
                    
                    temp_username += tempStr[0];
                    temp_username += "/" + tempStr[1] + "/" + tempStr[2] + "\n";
                }
                
                temp_username = temp_username.replaceFirst("[\n\r]+$", "");
                file_writer.write(temp_username);
                file_writer.close();
                customer_scan.close();
                
                if(!tempCustomer_file.renameTo(customer_file)){
                    Path source = tempCustomer_file.toPath();
                    Path newdir = customer_file.toPath();
                    Files.move(source, newdir, REPLACE_EXISTING);
                }
            }

        }catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
        
        
    }
    
    
    
    public static void UpdateCustomer(String user, int total){

         try {
            File customer_file = new File("customers.txt");
            File tempCustomer_file = new File("tempCustomers.txt");
            Scanner customer_scan = new Scanner(customer_file);
            String temp_username = "";

            try (FileWriter file_writer = new FileWriter(tempCustomer_file, true)){
             String[] tempStr;

                while(customer_scan.hasNextLine() == true){
                    String temp_line = customer_scan.nextLine();
                    tempStr = temp_line.split("/", 5);
                    String temp_username2 = tempStr[0];

                    if(temp_username2.equals(user)){
                        temp_username += tempStr[0];
                        temp_username += "/" + tempStr[1];
                        temp_username += "/" + String.valueOf(total) + "\n";
                        
                    } else{
                        temp_username += tempStr[0];
                        temp_username += "/" + tempStr[1];
                        temp_username += "/" + tempStr[2] + "\n";
                    }
                }
                
                temp_username = temp_username.replaceFirst("[\n\r]+$", "");
                file_writer.write(temp_username);
                file_writer.close();
                customer_scan.close();
                
                if(!tempCustomer_file.renameTo(customer_file)){
                    Path source = tempCustomer_file.toPath();
                    Path newdir = customer_file.toPath();
                    Files.move(source, newdir, REPLACE_EXISTING);
                }
                
            }

        }catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
     }

    
}
