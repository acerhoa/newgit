package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class addController {
    @FXML
    private TextField facultyNameField;

    @FXML
    private ComboBox departmentBox;

    @FXML
    private TextField staffNameField;

    @FXML
    private ComboBox accessLevelBox;

    @FXML
    private Button play;

    @FXML
    private ComboBox comboBox;

    private ObservableList<String> cbChoices;
    private ObservableList<String> levelChoices;
    private ObservableList<String> departmentChoices;


    @FXML
    void initialize() {
        cbChoices = FXCollections.observableArrayList("Staff","Faculty");
        comboBox.setItems(cbChoices);
        levelChoices = FXCollections.observableArrayList("1","2","3","4","5");
        accessLevelBox.setItems(levelChoices);
        departmentChoices = FXCollections.observableArrayList("Math","History","Art","English","Science");
        departmentBox.setItems(departmentChoices);


        comboBox.getSelectionModel().selectedItemProperty().addListener((cbChoices, oldvalue, newvalue) -> {
            if (newvalue.equals("Staff")) {
                facultyNameField.setDisable(true);
                departmentBox.setDisable(true);
                staffNameField.setDisable(false);
                accessLevelBox.setDisable(false);
                comboBox.getSelectionModel().select("Staff");
            } else {
                staffNameField.setDisable(true);
                accessLevelBox.setDisable(true);
                facultyNameField.setDisable(false);
                departmentBox.setDisable(false);
                comboBox.getSelectionModel().select("Faculty");
            }
        });


        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Controller controller = new Controller();
                if (comboBox.getSelectionModel().getSelectedItem().equals("Faculty")){
                    Faculty newFaculty = new Faculty();
                    newFaculty.id = 1 + controller.getEmployeeList().size();
                    newFaculty.name = facultyNameField.getText();
                    newFaculty.department = departmentBox.getSelectionModel().getSelectedItem().toString();
                    newFaculty.hire();
                    controller.addFacultyList(newFaculty);
                }
                else{
                    Staff newStaff = new Staff();
                    newStaff.id = 1 + controller.getEmployeeList().size();
                    newStaff.name = staffNameField.getText();
                    newStaff.accessLevel = Integer.parseInt(accessLevelBox.getSelectionModel().getSelectedItem().toString());
                    newStaff.hire();
                    controller.addStaffList(newStaff);
                }
                play.getScene().getWindow().hide();
            }

        });
    }
}
