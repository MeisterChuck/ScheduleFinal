package ScheduleFinal.EmployeeDatabase;

import java.sql.*;

public class EmployeeDatabaseHandler {
    private static EmployeeDatabaseHandler handler = null;

    private static final String DB_URL = "jdbc:h2:file:./DataBase/Data;create=true";
    private static Connection conn = null;
    private Statement stmt = null;

    private EmployeeDatabaseHandler(){
        createConnection();
        setupEmployeeTable();
        setupProficiencyTable();
        setupStartTimeTable();
        setupEndTimeTable();
    }

    public void refresh(){
        setupEmployeeTable();
        setupProficiencyTable();
        setupStartTimeTable();
        setupEndTimeTable();
    }

    // Allows the main class to share with all other classes
    public static EmployeeDatabaseHandler getInstance(){
        if(handler == null){
            handler = new EmployeeDatabaseHandler();
        }

        return handler;
    }

    void createConnection(){
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, "test", "test");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    void setupEmployeeTable(){
        String employeeTableName = "Employee";

        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet employeeTable = dbm.getTables(null, null, employeeTableName.toUpperCase(), null);

            if(employeeTable.next()){
                System.out.println("Tables " + employeeTableName + " already exists.");
            }else{
                stmt.execute("CREATE TABLE " + employeeTableName
                        + "(id INT PRIMARY KEY, " +
                        "first_name VARCHAR(200), " +
                        "last_name VARCHAR(200), " +
                        "salary DECIMAL(20,2), " +
                        "email VARCHAR(200), " +
                        "phone VARCHAR(10), " +
                        "date_of_hire DATE )");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + "--- setupEmployeeTable");
        }finally {

        }
    }

    void setupProficiencyTable(){
        String proficiencyTableName = "Proficiency";

        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet proficiencyTable = dbm.getTables(null, null, proficiencyTableName.toUpperCase(), null);


            if(proficiencyTable.next()){
                System.out.println("Tables " + proficiencyTableName + " already exists.");
            }else{
                stmt.execute("CREATE TABLE " + proficiencyTableName
                        + "(id INT PRIMARY KEY, " +
                        "manager INT, " +
                        "zoning INT, " +
                        "cashier INT, " +
                        "cutting INT, " +
                        "framing INT, " +
                        "stocking INT)");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + "--- setupProficiencyTable");
        }finally {

        }
    }

    void setupStartTimeTable(){
        String startTimeTableName = "Start_Time";

        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet startTimeTable = dbm.getTables(null, null, startTimeTableName.toUpperCase(), null);


            if(startTimeTable.next()){
                System.out.println("Tables " + startTimeTableName + " already exists.");
            }else{
                stmt.execute("CREATE TABLE " + startTimeTableName
                        + "(ID INT PRIMARY KEY, " +
                        "SUNDAY TIME, " +
                        "MONDAY TIME, " +
                        "TUESDAY TIME, " +
                        "WEDNESDAY TIME, " +
                        "THURSDAY TIME, " +
                        "FRIDAY TIME, " +
                        "SATURDAY TIME)");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + "--- setupStartTimeTable");
        }finally {

        }
    }

    void setupEndTimeTable(){
        String endTimeTableName = "End_Time";

        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet endTimeTable = dbm.getTables(null, null, endTimeTableName.toUpperCase(), null);


            if(endTimeTable.next()){
                System.out.println("Tables " + endTimeTableName + " already exists.");
            }else{
                stmt.execute("CREATE TABLE " + endTimeTableName
                        + "(id INT PRIMARY KEY, " +
                        "monday TIME, " +
                        "tuesday TIME, " +
                        "wednesday TIME, " +
                        "thursday TIME, " +
                        "friday TIME, " +
                        "saturday TIME)");

            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + "--- setupEndTimeTable");
        }finally {

        }
    }

    public ResultSet execQuery(String query){
        ResultSet result;

        try{
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        }catch(SQLException e){
            System.out.println("Exception at execQuery:employeeDatabaseHandler");
            return null;
        }finally {

        }

        return result;
    }

    public boolean execAction(String qu){
        try{
            stmt = conn.createStatement();
            stmt.execute(qu);

            return true;
        }catch(SQLException e){
            System.out.println("\nException at execAction:employeeDatabaseHandler" + e.getLocalizedMessage() + "\n");
            return false;
        }finally {

        }
    }
}
