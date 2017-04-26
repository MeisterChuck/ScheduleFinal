package ScheduleFinal.AddEmployees;

import ScheduleFinal.EmployeeDatabase.EmployeeDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddEmployeesController implements Initializable{
    @FXML private BorderPane rootPane;

    @FXML private TextField idTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField salaryTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private DatePicker dateOfHireField;
    @FXML private ToggleGroup Managment;
    @FXML private ToggleGroup Cashier;
    @FXML private ToggleGroup Cutting;
    @FXML private ToggleGroup Framing;
    @FXML private ToggleGroup Zoning;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;

    EmployeeDatabaseHandler employeeDatabaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        employeeDatabaseHandler = EmployeeDatabaseHandler.getInstance();

        checkData();
    }

    @FXML void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setResizable(false);
        stage.close();
    }

    @FXML void save(ActionEvent event) {
        if(idTextField.getText() == "" || idTextField.getText().isEmpty() ||
                firstNameTextField.getText() == "" || firstNameTextField.getText().isEmpty() ||
                lastNameTextField.getText() == "" || lastNameTextField.getText().isEmpty() ||
                salaryTextField.getText() == "" || salaryTextField.getText().isEmpty() ||
                emailTextField.getText() == "" || emailTextField.getText().isEmpty() ||
                phoneTextField.getText() == "" || phoneTextField.getText().isEmpty() ||
                dateOfHireField.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter all Fields");
            alert.showAndWait();
            return;
        }

        int id = Integer.parseInt(idTextField.getText());
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        BigDecimal salary = new BigDecimal(salaryTextField.getText().replaceAll(",", ""));
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        LocalDate date = dateOfHireField.getValue();
        Date dateOfHire = Date.valueOf(date);

        String qu = "INSERT INTO EMPLOYEE VALUES (" +
                "" + id + "," +
                "'" + firstName + "'," +
                "'" + lastName + "'," +
                "" + salary + "," +
                "'" + email + "'," +
                "'" + phone + "'," +
                "'" + dateOfHire + "'" +
                ")";
        System.out.println(qu);

        if(employeeDatabaseHandler.execAction(qu)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    private void checkData(){
        String qu = "SELECT * FROM EMPLOYEE";
        ResultSet rs = employeeDatabaseHandler.execQuery(qu);

        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRST_NAME");
                String lastName = rs.getString("LAST_NAME");
                BigDecimal salary = rs.getBigDecimal("SALARY");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                Date dateOfHire = rs.getDate("DATE_OF_HIRE");

                System.out.println(id + ", "
                        + firstName + ", "
                        + lastName + ", "
                        + salary + ", "
                        + email + ", "
                        + phone + ", "
                        + dateOfHire);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
