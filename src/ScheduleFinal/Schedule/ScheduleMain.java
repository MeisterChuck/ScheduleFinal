package ScheduleFinal.Schedule;

import ScheduleFinal.EmployeeDatabase.EmployeeDatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScheduleMain extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ScheduleMainUI.FXML"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        EmployeeDatabaseHandler.getInstance();
    }

    public static void main(String[] args){
        launch(args);
    }
}
