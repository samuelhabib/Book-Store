package bookstore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
        


public class LoginScreen extends Application {
    
    // Main
    public static void main(String[] args) {
        launch(args);  
    }
    
    
    // Login buttons
    public Button Login, Logout, logout2, logout3;
    
    // Books buttons
    public Button books_btn, customers_btn, back_btn, add_btn, delete_btn;
    
    // Customer buttons
    public Button back2_btn, add2_btn, delete2_btn;
    
    // Customer Screen buttons
    public Button buy_btn, redeem_btn;
 
    public TextField Usertxt;
    public PasswordField Passtxt;
    
    final Text actiontarget = new Text();
    final Text actiontarget2 = new Text();
    final Text actiontarget3 = new Text();
    final Text actiontarget4 = new Text();
    
    // Stages & Scenes
    Stage window;
    Scene login_screen, admin_screen, book_screen, customer_screen, customer_screen2, payment_screen;
    
    // Table
    TableView<Book> table, table3;
    TableView<Customer> table2;
    
    ObservableList<Book> books;
    
    TextField nameInput, priceInput;
    TextField usernameInput, passwordInput;
    
    // Vbox's
    VBox cus_layout, cus_layout2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        // Window Initializer
        window = primaryStage;
        Usertxt = new TextField();
        Passtxt = new PasswordField();
        Label username = new Label("Username:  ");
        Label password = new Label("Password:  ");
        
        
        // LoginScreen //
        
        
        //Login Button
        Login = new Button("Login");
        Login.setOnAction(Action_Handler_Login);

        GridPane login_scene = new GridPane();
        login_scene.add(actiontarget2, 1, 6);
        login_scene.setAlignment(Pos.CENTER);
        
        GridPane.setConstraints(username, 1, 2);
        GridPane.setConstraints(password, 1, 3); 
        GridPane.setConstraints(Usertxt, 2, 2);
        GridPane.setConstraints(Passtxt, 2, 3);
        GridPane.setConstraints(Login, 3, 4);
 
        login_scene.setVgap(10);
       
        login_scene.getChildren().addAll(Usertxt, Passtxt, username, password, Login);
        
        login_screen = new Scene(login_scene, 400, 300);
        // End of LoginScreen //
        
        
        
        // Logout Button
        Logout = new Button("Logout");
        Logout.setOnAction(Action_Handler_Login);
        login_scene.getChildren().add(Logout);
        //
       
        
        // Admin Scene
        books_btn = new Button("Books");
        books_btn.setOnAction(Action_Handler_AdminFunc);
        
        customers_btn = new Button("Customers");
        customers_btn.setOnAction(Action_Handler_AdminFunc);
        
        books_btn.setStyle("-fx-font-size: 2em;");
        customers_btn.setStyle("-fx-font-size: 2em;");
        Logout.setStyle("-fx-font-size: 2em;");
        
        books_btn.setMinWidth(200);
        customers_btn.setMinWidth(200);
        Logout.setMinWidth(200);
        
        GridPane admin_scene = new GridPane();
        admin_scene.setAlignment(Pos.CENTER);
        admin_scene.setVgap(20);
        
        GridPane.setConstraints(books_btn, 1, 1);
        GridPane.setConstraints(customers_btn, 1, 3);
        GridPane.setConstraints(Logout, 1, 5);
        
        admin_scene.getChildren().addAll(books_btn, customers_btn, Logout);
        
        admin_screen = new Scene(admin_scene, 500, 500);
        // End of Admin Scene

        
        
        // Books Scene
        TableColumn<Book, String> bookColumn = new TableColumn<>("Book Name");
        bookColumn.setMinWidth(299);
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Book Price (CAD)");
        priceColumn.setMinWidth(199);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        back_btn = new Button("Back");
        back_btn.setOnAction(Action_Handler_AdminFunc);
        
        nameInput = new TextField();
        nameInput.setPromptText("Book");
        nameInput.setMinWidth(100);
        
        priceInput = new TextField();
        priceInput.setPromptText("Price");
        priceInput.setMinWidth(100);
        
        table = new TableView<>();
        table.setItems(getBook());
        table.getColumns().addAll(bookColumn, priceColumn);
        table.setStyle("-fx-selection-bar: lightblue; -fx-selection-bar-non-focused: lightblue;");

        
        add_btn = new Button("Add");
        add_btn.setOnAction(Action_Handler_Books);
        
        delete_btn = new Button("Delete");
        delete_btn.setOnAction(Action_Handler_Books);
        
        back_btn = new Button("Back");
        back_btn.setOnAction(Action_Handler_AdminFunc);
        
       
        // Button Styling
       delete_btn.setStyle("-fx-font-size: 1em; ");
       back_btn.setStyle("-fx-font-size: 1em; ");
       add_btn.setStyle("-fx-font-size: 1em; ");
       delete_btn.setMinWidth(160);
       back_btn.setMinWidth(162);
       add_btn.setMinWidth(138);
        
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, priceInput, add_btn);
        
        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(back_btn, delete_btn);
        
        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(0,10,10,10));
        hBox3.setSpacing(10);
        hBox3.getChildren().addAll(actiontarget);
        
        
        VBox book_scene = new VBox();
        book_scene.getChildren().addAll(table, hBox, hBox2, hBox3);
        
        book_screen = new Scene(book_scene, 500, 500);
        // End of Books Scene
        
        
        
        
        
        // Customers Scene
        TableColumn<Customer, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(200);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        TableColumn<Customer, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(200);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        TableColumn<Customer, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setMinWidth(99);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        
        back2_btn = new Button("Back");
        back2_btn.setOnAction(Action_Handler_AdminFunc);
        
        usernameInput = new TextField();
        usernameInput.setPromptText("Username");
        usernameInput.setMinWidth(100);
        
        passwordInput = new TextField();
        passwordInput.setPromptText("Password");
        passwordInput.setMinWidth(100);
        
        table2 = new TableView<>();
        table2.setItems(getCustomers());
        table2.getColumns().addAll(usernameColumn, passwordColumn, pointsColumn);
        table2.setStyle("-fx-selection-bar: lightblue; -fx-selection-bar-non-focused: lightblue;");

        
        add2_btn = new Button("Add");
        add2_btn.setOnAction(Action_Handler_Customer);
        
        delete2_btn = new Button("Delete");
        delete2_btn.setOnAction(Action_Handler_Customer);
        
        back2_btn = new Button("Back");
        back2_btn.setOnAction(Action_Handler_AdminFunc);
        
        
       // Button Styling
       delete_btn.setStyle("-fx-font-size: 1em; ");
       back_btn.setStyle("-fx-font-size: 1em; ");
       add_btn.setStyle("-fx-font-size: 1em; ");
       delete2_btn.setMinWidth(160);
       back2_btn.setMinWidth(162);
       add2_btn.setMinWidth(138);
        
        HBox cBox = new HBox();
        cBox.setPadding(new Insets(10,10,10,10));
        cBox.setSpacing(10);
        cBox.getChildren().addAll(usernameInput, passwordInput, add2_btn);
        
        HBox cBox2 = new HBox();
        cBox2.setPadding(new Insets(10,10,10,10));
        cBox2.setSpacing(10);
        cBox2.getChildren().addAll(back2_btn, delete2_btn);
        
        HBox cBox3 = new HBox();
        cBox3.setPadding(new Insets(0,10,10,10));
        cBox3.setSpacing(10);
        cBox3.getChildren().addAll(actiontarget3);
        
        
        VBox customer_scene = new VBox();
        customer_scene.getChildren().addAll(table2, cBox, cBox2, cBox3);
        
        customer_screen = new Scene(customer_scene, 500, 500);
        // End of Customers Scene
        
        

        // Customer Screen Scene
        
        HBox intro_msg = new HBox();
        intro_msg.setPadding(new Insets(10,10,10,10));
        intro_msg.setSpacing(10);
        intro_msg.getChildren().addAll(new Text(""));
        
        
        TableColumn<Book, String> bookColumn2 = new TableColumn<>("Book Name");
        bookColumn2.setMinWidth(199);
        bookColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Book, Double> priceColumn2 = new TableColumn<>("Book Price (CAD)");
        priceColumn2.setMinWidth(199);
        priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Book, CheckBox> selectColumn = new TableColumn<>("Select");
        selectColumn.setMinWidth(99);
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));

        table3 = new TableView<>();
        table3.setItems(getBook());
        table3.getColumns().addAll(bookColumn2, priceColumn2, selectColumn);
        table3.setStyle("-fx-selection-bar: lightblue; -fx-selection-bar-non-focused: lightblue;");
        
        buy_btn = new Button("Buy");
        buy_btn.setOnAction(Action_Handler_CustomerScreen);
        
        redeem_btn = new Button("Redeem and Buy");
        redeem_btn.setOnAction(Action_Handler_CustomerScreen);
        
        logout2 = new Button("Logout");
        logout2.setOnAction(Action_Handler_Login);

        
        // Button Styling
        buy_btn.setStyle("-fx-font-size: 1em; ");
        redeem_btn.setStyle("-fx-font-size: 1em; ");
        logout2.setStyle("-fx-font-size: 1em; ");
        buy_btn.setMinWidth(150);
        redeem_btn.setMinWidth(150);
        logout2.setMinWidth(150);
       
        HBox btns = new HBox();
        btns.setPadding(new Insets(10,10,0,10));
        btns.setSpacing(10);
        btns.getChildren().addAll(buy_btn, redeem_btn, logout2);
        
        HBox errorMsg = new HBox();
        errorMsg.setPadding(new Insets(0,10,0,10));
        errorMsg.setSpacing(10);
        errorMsg.getChildren().addAll(actiontarget4);

        cus_layout = new VBox(10);
        cus_layout.setPadding(new Insets(10,0,10,0));
        cus_layout.getChildren().addAll(intro_msg, table3, btns, errorMsg);

        customer_screen2 = new Scene(cus_layout, 500, 500);
        // End of Customer Screen Scene
        
        
        
        
        
        
        
        // Buy/Redeem Scene
        HBox totalCost_msg = new HBox();
        totalCost_msg.setPadding(new Insets(10,10,10,10));
        totalCost_msg.setSpacing(10);
        Text totalCost_text = new Text("");
        totalCost_msg.getChildren().addAll(totalCost_text);
        
        HBox status_msg = new HBox();
        status_msg.setPadding(new Insets(10,10,10,10));
        status_msg.setSpacing(10);
        Text status_text = new Text("");
        status_msg.getChildren().addAll(status_text);
        
        logout3 = new Button("Logout");
        logout3.setMinWidth(150);
        logout3.setStyle("-fx-font-size: 2em;");
        logout3.setOnAction(Action_Handler_Login);
        
        HBox logout_box = new HBox();
        logout_box.setSpacing(10);
        logout_box.setPadding(new Insets(0, 0, 0, 30));
        logout_box.getChildren().addAll(logout3);

        cus_layout2 = new VBox(40);
        cus_layout2.setPadding(new Insets(130, 0, 0, 125));
        cus_layout2.getChildren().addAll(totalCost_msg, status_msg, logout_box);

        payment_screen = new Scene(cus_layout2, 500, 500);
        // End of Buy/Scene Scene
        
        
        
        

        
        
        
        // Scene setter //
        primaryStage.setScene(login_screen);
        primaryStage.show();
        primaryStage.setTitle("Login Screen");
        primaryStage.setAlwaysOnTop(true);
        // End of Scene Setter //
    }
    
    public ObservableList<Book> getBook(){
        books = FXCollections.observableArrayList();
        ArrayList<String> book_list;
        book_list = Book_Loading.getBooks();
        String[] tempStr;
        
        for (String temp : book_list){
             tempStr = temp.split("/", 5);
             String temp_bookname = tempStr[0];
             double temp_bookprice = Double.parseDouble(tempStr[1]);
             books.add(new Book(temp_bookname, temp_bookprice));
        }
        
        return books;
    }
    
    public ObservableList<Customer> getCustomers(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ArrayList<String> customer_list;
        customer_list = Customer_Loading.getCustomers();
        String[] tempStr;

        for (String temp : customer_list){
             tempStr = temp.split("/", 5);
             String temp_username = tempStr[0];
             String temp_password = tempStr[1];
             int temp_points = Integer.parseInt(tempStr[2]);
             customers.add(new Customer(temp_username, temp_password, temp_points));
        }
        
        return customers;
    }
    
    
    
       
    final EventHandler<ActionEvent> Action_Handler_Login = new EventHandler<ActionEvent>(){
    
        @Override
        public void handle(ActionEvent event) {

            if(event.getSource() == Logout || event.getSource() == logout2 || event.getSource() == logout3){
                window.setScene(login_screen);
                Usertxt.clear();
                Passtxt.clear();
                actiontarget2.setText("");
                actiontarget4.setText("");
                window.setTitle("Login Screen");

            } else if(event.getSource() == Login){

                String temp_user = Usertxt.getText();
                String temp_pass = Passtxt.getText();

                switch (Authentication.Auth(temp_user, temp_pass)) {

                    case "Admin":
                        window.setScene(admin_screen);
                        window.setTitle("Owner Start Screen");
                        break;

                    case "Customer":
                        window.setScene(customer_screen2);
                        window.setTitle("Customer Start Screen");
                        table3.setItems(getBook());
                        
                        String temp_username = Usertxt.getText();
                        String[] temp_userr = Customer.init_customer(temp_username);
                        String msg = "Welcome " + temp_userr[0] + ". You have " + temp_userr[2] + " points. " + "Your status is S";
                        cus_layout.getChildren().set(0, new Text(msg));
                        break;

                    default:
                        actiontarget2.setFill(Color.FIREBRICK);
                        actiontarget2.setText("Wrong details");
                        break;
                }

            }

        }
            
    };
    
  
  
    EventHandler<ActionEvent> Action_Handler_AdminFunc = new EventHandler<ActionEvent>(){
        
       @Override
       public void handle(ActionEvent event) {
       
            if(event.getSource() == books_btn){

                window.setScene(book_screen);
                window.setTitle("Book Screen");

            }else if(event.getSource() == customers_btn){

                window.setScene(customer_screen);
                window.setTitle("Customer Screen");

            }else if(event.getSource() == back_btn || event.getSource() == back2_btn){
                nameInput.clear();
                priceInput.clear();
                usernameInput.clear();
                passwordInput.clear();
                actiontarget.setText("");
                actiontarget2.setText("");
                actiontarget3.setText("");
                actiontarget4.setText("");
                
                window.setScene(admin_screen);
                window.setTitle("Owner Start Screen");
            }
               
        }
       
    };
    
    
    EventHandler<ActionEvent> Action_Handler_Books = new EventHandler<ActionEvent>(){
        
       @Override
       public void handle(ActionEvent event) {
       
            if(event.getSource() == add_btn){
                boolean isNum;
                double temp2_num = 0;
                String temp1 = nameInput.getText().trim();
                String temp2 = priceInput.getText().trim();
                
                try {
                    temp2_num = Double.parseDouble(temp2);
                    isNum = true;
                    
                } catch (NumberFormatException e) {
                    isNum = false;
                }
                

                if(temp1.isEmpty() == false && (isNum == true) && temp2_num > 0){
                    Book book = new Book("", 0.0);
                    book.setName(nameInput.getText().trim());
                    book.setPrice(Double.parseDouble(priceInput.getText()));
                    table.getItems().add(book);
                    Book_Loading.addBooks(temp1, temp2_num);
                    nameInput.clear();
                    priceInput.clear();
                    actiontarget.setText("");
                    
                } else{
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Error, please try again");
                }
 
                
            }else if(event.getSource() == delete_btn){
                try{
                ObservableList<Book> bookSelected, allBooks;
                allBooks = table.getItems();
                bookSelected = table.getSelectionModel().getSelectedItems();
                String book_to_delete = bookSelected.get(0).getName();
                
                Book_Loading.deleteBook(book_to_delete);
                bookSelected.forEach(allBooks::remove);
                
                } catch (NullPointerException e){
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Error, must select a book before using delete");
                }
            }
        }
       
    };
    
    
    
    EventHandler<ActionEvent> Action_Handler_Customer = new EventHandler<ActionEvent>(){
        
       @Override
       public void handle(ActionEvent event) {
       
            if(event.getSource() == add2_btn){
                String temp1 = usernameInput.getText().trim();
                String temp2 = passwordInput.getText().trim();
                

                if(temp1.isEmpty() == false && temp2.isEmpty() == false){
                    Customer customer = new Customer("", "", 0);
                    customer.setUsername(usernameInput.getText().trim());
                    customer.setPassword(passwordInput.getText().trim());
                    table2.getItems().add(customer);
                    Customer_Loading.addCustomer(temp1, temp2);
                    usernameInput.clear();
                    passwordInput.clear();
                    actiontarget3.setText("");
                    
                } else{
                    actiontarget3.setFill(Color.FIREBRICK);
                    actiontarget3.setText("Error, please try again");
                }
 
            }else if(event.getSource() == delete2_btn){
                try{
                ObservableList<Customer> CustomerSelected, allCustomers;
                allCustomers = table2.getItems();
                CustomerSelected = table2.getSelectionModel().getSelectedItems();
                String customer_to_delete = CustomerSelected.get(0).getUsername();
                
                Customer_Loading.deleteCustomer(customer_to_delete);
                CustomerSelected.forEach(allCustomers::remove);
                
                } catch (NullPointerException e){
                    actiontarget3.setFill(Color.FIREBRICK);
                    actiontarget3.setText("Error, must select a user before using delete");
                }
            }
        }
       
    };
    
    
    
    EventHandler<ActionEvent> Action_Handler_CustomerScreen = new EventHandler<ActionEvent>(){
        
       @Override
       public void handle(ActionEvent event) {
       
            if(event.getSource() == buy_btn){
                boolean bookExists = false;
                int total_cost = 0;
                
                ArrayList<Book> books_to_delete = new ArrayList<>();
                
                for(Book temp: books){
                    
                    if(temp.getSelect().isSelected()){
                        books_to_delete.add(temp);
                        total_cost += temp.price;
                        bookExists = true;
                    }
                }
                
                books_to_delete.forEach((temp) -> {
                    Book_Loading.deleteBook(temp.name);
                });
                
                
                if(bookExists){
                    actiontarget4.setText("");
                    window.setScene(payment_screen);
                    window.setTitle("Customer Cost Screen");
                    
                    String temp_username = Usertxt.getText();
                    String[] temp_userr = Customer.init_customer(temp_username);
                    Customer temp = new Customer(temp_userr[0], temp_userr[1], 0);

                    int current_points = Integer.parseInt(temp_userr[2]); 
                    int cost_points = temp.updatePoints(total_cost);
                    int final_total = current_points + cost_points;

                    Customer_Loading.UpdateCustomer(temp_userr[0], final_total);

                    table3.setItems(getBook());
                    table.setItems(getBook());
                    String msg1 = "Total Cost: " + "$" + total_cost + " CAD";
                    String msg2 = "Points: " + final_total + ", Status S";

                    Text tmp1 = new Text(msg1);
                    Text tmp2 = new Text(msg2);
                    tmp1.setStroke(Color.ORANGE);
                    tmp2.setStroke(Color.ORANGE);
                    tmp1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    tmp2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

                    cus_layout2.getChildren().set(0, tmp1);
                    cus_layout2.getChildren().set(1, tmp2);
                    table2.setItems(getCustomers());
                    
                } else{
                    actiontarget4.setFill(Color.FIREBRICK);
                    actiontarget4.setText("Please select a book");
                }

            } else if(event.getSource() == redeem_btn){
                
                boolean bookExists = false;
                int total_cost = 0;
                ArrayList<Book> books_to_delete = new ArrayList<>();
                
                for(Book temp: books){
                    
                    if(temp.getSelect().isSelected()){
                        total_cost += temp.price;
                        books_to_delete.add(temp);
                        bookExists = true;
                        actiontarget4.setText("");
                    }
                }
                
                books_to_delete.forEach((temp) -> {
                Book_Loading.deleteBook(temp.name);
                });
                
                if(bookExists){
                    window.setScene(payment_screen);
                    window.setTitle("Customer Cost Screen");
                                    
                    String temp_username = Usertxt.getText();
                    String[] temp_userr = Customer.init_customer(temp_username);
                    int current_points = Integer.parseInt(temp_userr[2]);
                    Customer temp = new Customer(temp_userr[0], temp_userr[1], current_points);
                    
                    int newCost = temp.redeemPoints(total_cost);
                    
                    int newPoints;
                    if(newCost > 0){
                        newPoints = temp.updatePoints(newCost);
                    } else{
                        newPoints = temp.getPoints();
                    }

                    Customer_Loading.UpdateCustomer(temp_userr[0], newPoints);

                    table.setItems(getBook());
                    table3.setItems(getBook());
                    String msg1 = "Total Cost: " + "$" + newCost + " CAD";
                    String msg2 = "Points: " + newPoints + ", Status S";

                    Text tmp1 = new Text(msg1);
                    Text tmp2 = new Text(msg2);
                    tmp1.setStroke(Color.ORANGE);
                    tmp2.setStroke(Color.ORANGE);
                    tmp1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    tmp2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

                    cus_layout2.getChildren().set(0, tmp1);
                    cus_layout2.getChildren().set(1, tmp2);
                    table2.setItems(getCustomers());
                    
                } else{
                    actiontarget4.setFill(Color.FIREBRICK);
                    actiontarget4.setText("Please select a book");
                }

            }
            
            
            
        }
       
    };
    

  
    
}
