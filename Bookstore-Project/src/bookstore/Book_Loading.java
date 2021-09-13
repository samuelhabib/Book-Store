package bookstore;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;


public class Book_Loading {
    String book;
    private static final Book_Loading user_file = new Book_Loading("books.txt");
    
    public Book_Loading(String book_init){
        this.book = book_init;
    }
    
    public static Book_Loading getInstance(){
        return user_file;
    }
    
    
    public static ArrayList<String> getBooks(){
        try {
            File book_file = new File("books.txt");
            
            ArrayList<String> book_list;
            try (Scanner book_scan = new Scanner(book_file)) {
                book_list = new ArrayList<>();
                while(book_scan.hasNextLine() == true){
                    String temp_book = book_scan.nextLine();
                    book_list.add(temp_book);
                }
            }
            return book_list;
            

        } catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        
        return null;
    }
    
    
    public static void addBooks(String bookname, Double bookprice){
        try {
            File book_file = new File("books.txt");
            String text;
            bookname = bookname.trim();
            
            try (FileWriter file_writer = new FileWriter(book_file, true)){
                if(book_file.length() == 0){
                    text = bookname + "/" + String.valueOf(bookprice);
                } else{
                    text = "\n" + bookname + "/" + String.valueOf(bookprice);
                }
                
                file_writer.write(text);
                file_writer.close();
            }
            
        }catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        
    }
    
    
    
    public static void deleteBook(String lineToRemove){
        try {
            File book_file = new File("books.txt");
            File tempBook_file = new File("tempBooks.txt");
            Scanner book_scan = new Scanner(book_file);
            String temp_bookname = "";
            
            try (FileWriter file_writer = new FileWriter(tempBook_file, true)){
                String[] tempStr;
                
                while(book_scan.hasNextLine() == true){
                    String temp_line = book_scan.nextLine();
                    tempStr = temp_line.split("/", 5);
                    String temp_bookname2 = tempStr[0];
                    
                    if(temp_bookname2.equals(lineToRemove)) continue;
                    
                    temp_bookname += tempStr[0];
                    temp_bookname += "/" + tempStr[1] + "\n";
                }
                
                temp_bookname = temp_bookname.replaceFirst("[\n\r]+$", "");
                file_writer.write(temp_bookname);
                file_writer.close();
                book_scan.close();
                
                if(!tempBook_file.renameTo(book_file)){
                    Path source = tempBook_file.toPath();
                    Path newdir = book_file.toPath();
                    Files.move(source, newdir, REPLACE_EXISTING);
                }

            }

        }catch (IOException e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        
    }
    

    
}
