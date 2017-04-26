package ScheduleFinal.Schedule;

import ScheduleFinal.EmployeeDatabase.EmployeeDatabaseHandler;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ScheduleMainController implements Initializable{
    ObservableList<Employee> list = FXCollections.observableArrayList();

    @FXML private BorderPane rootPane;

    @FXML private TableView<Employee> employeeTableView;
    @FXML private TableColumn<Employee, Integer> idColumn;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;

    @FXML private Label idLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label salaryLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label dateOfHireLabel;

    @FXML private Button hireButton;
    @FXML private Button editButton;
    @FXML private Button fireButton;
    @FXML private Button calculateScheduleButton;

    @FXML private TextField weekBudgetTextField;

    @FXML private ToggleGroup TruckShipment;
    @FXML private RadioButton truckShipmentOne;
    @FXML private RadioButton truckShipmentTwo;
    @FXML private RadioButton truckShipmentThree;
    @FXML private RadioButton truckShipmentFour;
    @FXML private RadioButton truckShipmentFive;

    @FXML private StackedBarChart<?, ?> scheduleGraph;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }

    private void initCol(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
    }

    private void loadData(){
        EmployeeDatabaseHandler handler = EmployeeDatabaseHandler.getInstance();
        String qu = "SELECT ID, FIRST_NAME, LAST_NAME FROM EMPLOYEE";
        ResultSet rs = handler.execQuery(qu);

        try{
            while(rs.next()){
                Integer id = rs.getInt("ID");
                String firstName = rs.getString("FIRST_NAME");
                String lastName = rs.getString("LAST_NAME");

                list.add(new Employee(id, firstName, lastName));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        employeeTableView.getItems().setAll(list);
    }

    @FXML void hireEmployee(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/ScheduleFinal/AddEmployees/AddEmployeesUI.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);

            stage.setTitle("Hire New Employee");
            stage.setScene(new Scene((parent)));

            stage.setResizable(false);

            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML void editEmployee(ActionEvent event) {

    }

    @FXML void fireEmployee(ActionEvent event) {

    }

    @FXML void calculateSchedule(ActionEvent event) {

    }

    // Not used for hire employee to setResizable to false
    void loadWindow(String location, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);

            stage.setTitle(title);
            stage.setScene(new Scene((parent)));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static class Employee{
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private SimpleDoubleProperty salary;
        private SimpleStringProperty email;
        private SimpleStringProperty phone;
        private SimpleObjectProperty dateOfHire;

        Employee(int id, String firstName, String lastName){//, Double salary, String email, String phone, Date dateOfHire){
            this.id = new SimpleIntegerProperty(id);
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            //this.salary = new SimpleDoubleProperty(salary);
            //this.email = new SimpleStringProperty(email);
            //this.phone = new SimpleStringProperty(phone);
            //this.dateOfHire = new SimpleObjectProperty(dateOfHire);
        }

        public int getId() {
            return id.get();
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public String getFirstName() {
            return firstName.get();
        }

        public SimpleStringProperty firstNameProperty() {
            return firstName;
        }

        public String getLastName() {
            return lastName.get();
        }

        public SimpleStringProperty lastNameProperty() {
            return lastName;
        }

        public double getSalary() {
            return salary.get();
        }

        public SimpleDoubleProperty salaryProperty() {
            return salary;
        }

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public String getPhone() {
            return phone.get();
        }

        public SimpleStringProperty phoneProperty() {
            return phone;
        }

        public Object getDateOfHire() {
            return dateOfHire.get();
        }

        public SimpleObjectProperty dateOfHireProperty() {
            return dateOfHire;
        }
    }
}
