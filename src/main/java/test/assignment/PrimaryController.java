package test.assignment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable{

    
    
      
    @FXML
    private TextField tID;
    @FXML
    private TextField tName;
    @FXML
    private TextField tPhone;
    @FXML
    private Button Save;
    @FXML
    private TextArea tAddress;

    
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    String userName="java_test";
    String password="dontknow";
    String dbMachine="localhost";
    String dbName="your_first_name";
    String url="jdbc:mysql://" +dbMachine +  "/" + dbName;
    
    
    @FXML
    private Label status;
    @FXML
    private Button Update;
    @FXML
    private Button Delete;
    @FXML
    private Button Reset;
    @FXML
    private ComboBox<String> comboBox;
    
    
   
    
    ///ObservableList<ProductSearchModel>List = FXCollections.observableArrayList();
    
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb)
    {       
        comboBox.setItems(FXCollections.observableArrayList("Accounts", "HR", "IT"));
        
    } 
    

    @FXML
    private void doSave(ActionEvent event) 
    {
            
     
        String name = tName.getText();    
        String phone = tPhone.getText();
        int id=Integer.parseInt(tID.getText());
        
        String address=tAddress.getText();

        String dept = comboBox.getSelectionModel().getSelectedItem().toString();
       
       String sql = "insert into employee value(" +id +", '" +name +"' , '" +address +"' , '" +phone+"' , '"+dept +"' )";
       
       
       
       try{
            
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(url, userName, password);
             st = con.createStatement();
             
            st.executeUpdate(sql);
            
            
            status.setText("Successfully saved ...");
        }catch(Exception e)
        {
            
             showInformationAlert();        
        }
            
    }
    
  

    @FXML
    private void doUpdate(ActionEvent event)
    {
    
        String name = tName.getText();
        String phone = tPhone.getText();
        int id=Integer.parseInt(tID.getText());
        
        String address=tAddress.getText();

        String dept = comboBox.getSelectionModel().getSelectedItem().toString();
       
       String sql = "update employee set phone = '" +phone +"' , name ='" +name +"' , address = ' " +address +"' , dept = ' " +dept +" ' where id = " +id ; 
       
       try{
            
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(url, userName, password);
             st = con.createStatement();
            
            st.executeUpdate(sql);
            status.setText("Successfully updated ...");
        }catch(Exception e)
        {
            status.setText("" +e);
        }
            
    }
 
    
    @FXML
    private void doDelete(ActionEvent event) 
    {
    
        int id=Integer.parseInt(tID.getText());
       
       String sql = "delete from employee where id = " +id ;
     
       
       try{
            
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(url, userName, password);
             st = con.createStatement();
            
            st.executeUpdate(sql);
           
            status.setText("Successfully deleted ...");
        }catch(Exception e)
        {
            status.setText("" +e);
       
        }
           
    }

    @FXML
    private void doReset(ActionEvent event)
    {
        tName.setText("");
        tID.setText("");
        tPhone.setText("");
        tAddress.setText("");
        status.setText("");
        comboBox.setValue("");
        
    }
    
    
     private void showInformationAlert()
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("ID already exist ...");
            alert.show();
        }
             
    }
    

  

