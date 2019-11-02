package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<UHEmployee> employeeListView;

    private static ObservableList<UHEmployee> employeeList;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        employeeList = FXCollections.observableArrayList();
        generateEmployees();
        employeeListView.setItems(employeeList);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/addView.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage =  new Stage();
                stage.setTitle("Add Screen");
                stage.setScene(new Scene(root));
                stage.show();
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int ep = employeeListView.getSelectionModel().getSelectedIndex();
                if (ep>=0){
                    employeeListView.getItems().remove(ep);
                }
            }
        });

    }

    private void generateEmployees()
    {
        for(int i = 0; i < 10; i++)
        {
            if(i%2==0) //staff
            {
                Staff newStaff = new Staff();
                newStaff.id = i;
                newStaff.name = "staffName : " + i;
                newStaff.hire();
                employeeList.add(newStaff);
            }
            else //faculty
            {
                Faculty newFaculty = new Faculty();
                newFaculty.id = i;
                newFaculty.name = "facultyName : " + i;
                newFaculty.hire();
                employeeList.add(newFaculty);
            }
        }
    }

    public ObservableList<UHEmployee> getEmployeeList(){
        return  employeeList;
    }

    public void addStaffList(Staff staff ){
        employeeList.add(staff);
    }

    public void addFacultyList(Faculty faculty){
        employeeList.add(faculty);
    }

}
