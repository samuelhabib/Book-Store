
package bookstore;

import javafx.scene.control.CheckBox;

public class Select {
    
    String name;
    double price;
    CheckBox select;
    
    public Select(String name, double price,CheckBox select){
        this.name = name;
        this.price = price;
        this.select = select;
    }

    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setName(String book_name){
        this.name = book_name;
    }
    
    public void setPrice(double book_price){
        this.price = book_price;
    }
    
     public CheckBox getSelect(){
        return select;
    }
    
    public void setSelect(CheckBox select_in){
        this.select = select_in;
    }
    
}
