package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller  implements Initializable
{

    @FXML
    private ListView filterListview;
    @FXML
    private JFXButton filterAge;
    @FXML
    private JFXButton filterGPA;
    @FXML
    private JFXButton filterCIS;
    @FXML
    private JFXButton createSTabke;
    @FXML
    private JFXButton loadSTable;
    @FXML
    private JFXButton deleteSTable;
    @FXML
    private ListView studentListView;




    final String hostname = "tommy.heliohost.org";
    final String dbName = "ultimazs_mainDB";
    final String port = "3306";
    final String username = "ultimazs";
    final String password = "Ultima123";
    final String HelioHost_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + username + "&password=" +password;



    //Create table method
    private void createDatabase(String url)
    {
        try{

            //Establish database connection and create a table with the following attributes
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            try
            {
                stmt.execute("CREATE TABLE Student (" +
                        "Id INT(36) NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "StudentName CHAR(25), " +
                        "Major CHAR(25), " +
                        "GPA VARCHAR(25), " +
                        "Age VARCHAR (25))");

                System.out.println("TABLE CREATED");
            }
            catch (Exception ex)
            {
                System.out.println("TABLE ALREADY EXISTS/FAILED TO CREATE");
            }


            //Fill the table rows with the below values
            stmt.execute("INSERT INTO Student (StudentName, Major, GPA, Age) VALUES" +
                    "('Tan Pham','CIS', 3.5 , 27)," +
                    "('Tony La','CS', 2.6, 19)," +
                    "('Tommy Lai','Biotech',3.5, 30)," +
                    "('Ronnald Jackson','CIS',3.8, 25)," +
                    "('Amelia Rhode','CIS',3.4, 22)," +
                    "('Omar Userf','CIS',3.0, 24)," +
                    "('Tammy Le','CS',4.0 , 20)," +
                    "('James Gordan','Engineer',3.9, 34)," +
                    "('Jordan Tran','Digital Media', 2.99, 26)," +
                    "('Eddie Alva','CS',3.45, 21)");


            stmt.close();
            conn.close();

            System.out.println("TABLE FILLED");

        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println(msg);
        }
    }

    //Delete table method
    private void deleteTable(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE Student");
            stmt.close();
            conn.close();
            System.out.println("TABLE DROPPED");
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("TABLE NOT DROPPED");
            System.out.println(msg);
        }
    }

    //Load data from the table and display them onto the listview
    private void loadData(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM Student";
            ResultSet result = stmt.executeQuery(sqlStatement);
            ObservableList<Student> studentList = FXCollections.observableArrayList();
            while (result.next())
            {
                Student student = new Student();
                student.id = result.getInt( "Id");
                student.studentName = result.getString("StudentName");
                student.gpa = result.getString("GPA");
                student.age = result.getString("Age");
                student.major = result.getString("Major");

                studentList.add(student);
            }

            studentListView.setItems(studentList);

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
    }

    //Display students who are over 20 years old
    private void filterAge(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM Student WHERE Age >= 20";
            ResultSet result = stmt.executeQuery(sqlStatement);
            ObservableList<Student> studentList = FXCollections.observableArrayList();
            while (result.next())
            {
                Student student = new Student();
                student.id = result.getInt( "Id");
                student.studentName = result.getString("StudentName");
                student.age = result.getString("Age");
                student.major = result.getString("Major");

                studentList.add(student);
            }

            filterListview.setItems(studentList);

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
    }

    //Display students who have 3.0 or higher GPA
    private void filterGPA(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM Student WHERE GPA >= 3.0";
            ResultSet result = stmt.executeQuery(sqlStatement);
            ObservableList<Student> studentList = FXCollections.observableArrayList();
            while (result.next())
            {
                Student student = new Student();
                student.id = result.getInt( "Id");
                student.studentName = result.getString("StudentName");
                student.gpa = result.getString("GPA");
                student.major = result.getString("Major");

                studentList.add(student);
            }

            filterListview.setItems(studentList);

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
    }

    //Display students who are CIS major
    private void filterCIS(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM Student WHERE Major = 'CIS'";
            ResultSet result = stmt.executeQuery(sqlStatement);
            ObservableList<Student> studentList = FXCollections.observableArrayList();
            while (result.next())
            {
                Student student = new Student();
                student.id = result.getInt( "Id");
                student.studentName = result.getString("StudentName");
                student.major = result.getString("Major");

                studentList.add(student);
            }

            filterListview.setItems(studentList);

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        //give action events to each buttons with appropriate functions
        createSTabke.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                createDatabase(HelioHost_URL);
            }
        });
        deleteSTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                deleteTable(HelioHost_URL);
            }
        });
        loadSTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                loadData(HelioHost_URL);
            }
        });
        filterAge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                filterAge(HelioHost_URL);
            }
        });
        filterGPA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                filterGPA(HelioHost_URL);
            }
        });
        filterCIS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                filterCIS(HelioHost_URL);
            }
        });
    }
}
