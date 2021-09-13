package bookstore;
import javafx.scene.control.CheckBox;

public class Book {
    
    String name;
    double price;
    private CheckBox select;
    
    public Book(String name, double price){
        this.name = name;
        this.price = price;
        this.select = new CheckBox();
    }

    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public CheckBox getSelect(){
        return select;
    }
    
    public void setName(String book_name){
        this.name = book_name;
    }
    
    public void setPrice(double book_price){
        this.price = book_price;
    }

    public void setSelect(CheckBox select){
        this.select = select;
    }
    
    
    
}
