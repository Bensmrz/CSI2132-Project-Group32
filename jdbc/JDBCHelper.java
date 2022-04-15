import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

public class JDBCHelper {

    public ResultSet listDentists(Connection conn) throws SQLException{
        String sqlQ = "SELECT userid FROM employee E WHERE E.employeetype = \"Dentist\" GROUP BY E.branch_id"; 
        PreparedStatement myStatement = conn.prepareStatement(sqlQ);
        return myStatement.executeQuery();
    }

    public ResultSet addPatient(Connection conn, String insurance, String email, String record_id, String user_id) throws SQLException{
        String sqlQ = "INSERT INTO patient(insurance, email, record_id, user_id) VALUES (\"" + insurance +"\",\""+ email +"\",\""+ record_id + "\",\""+user_id +"\")";
        PreparedStatement myStatement = conn.prepareStatement(sqlQ);
        return myStatement.executeQuery();
    }

    //needs to be updated after dentist column is added to appointment table
    public ResultSet checkAppointment(Connection conn) throws SQLException{
        String sqlQ = "SELECT appointment_id FROM appointment A";
        PreparedStatement myStatement = conn.prepareStatement(sqlQ);
        return myStatement.executeQuery();
    }

    //
    public ResultSet setAppointment(Connection conn, String appointment_id, String a_date, String start_time, String end_time, String appointment_type, String status, int fee_id, int treatment_code) throws SQLException{
        String sqlQ = "INSERT INTO appointment(appointment_id, a_date, start_time, end_time, appointment_type, status, fee_id, treatment_code)";
        sqlQ = sqlQ + "VALUES (\""+ appointment_id +"\",\""+ a_date +"\",\""+ start_time +" \",\""+ end_time +"\",\""+ appointment_type+"\",\""+ status +"\",\""+ fee_id +"\",\""+treatment_code+"\")";
        PreparedStatement myStatement = conn.prepareStatement(sqlQ);
        return myStatement.executeQuery();
    }

    public ResultSet addEmployee(Connection conn, String employeerole, String employeetype, int salary, int user_id, int branch_id) throws SQLException{
        String sqlQ = "INSERT INTO employee(employeerole, employeetype, salary, user_id, branch_id)";
        sqlQ = sqlQ + "VALUES (\""+ employeerole +"\",\""+ employeetype +"\",\""+ salary +"\",\""+ user_id +"\",\""+ branch_id +"\");";
        PreparedStatement myStatement = conn.prepareStatement(sqlQ);
        return myStatement.executeQuery();
    }

    public ResultSet checkProcedures(Connection conn) throws SQLException{
        String sqlQ = "SELECT procedure_type FROM appointment_procedure AP";
        PreparedStatement myStatement = conn.prepareStatement(sqlQ);
        return myStatement.executeQuery();
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("postgres://srsqrkamklhsii:f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47@ec2-18-215-96-22.compute-1.amazonaws.com:5432/daek7g9mrllna4"));
    
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
    
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void main(String[] args)throws URISyntaxException, SQLException{

        Connection conn = getConnection();

        // auto close connection and preparedStatement
        //Connection connect = DriverManager.getConnection("jdbc:postgresql://srsqrkamklhsii:f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47@ec2-18-215-96-22.compute-1.amazonaws.com:5432/daek7g9mrllna4?user=srsqrkamklhsii&password=f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47");

        //try (Connection connect = DriverManager.getConnection("jdbc:postgresql://srsqrkamklhsii:f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47@ec2-18-215-96-22.compute-1.amazonaws.com:5432/daek7g9mrllna4?user=srsqrkamklhsii&password=f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47"));


        // try (Connection conn = DriverManager.getConnection("postgres://srsqrkamklhsii:f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47@ec2-18-215-96-22.compute-1.amazonaws.com:5432/daek7g9mrllna4?user=srsqrkamklhsii&password=f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47")){
        //     //Connection conn = DriverManager.getConnection(
        // //         "jdbc:postgresql://group32-csiproject.herokuapp.com/", 
        // //         "srsqrkamklhsii", 
        // //         "f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47")) {
        // //             "srsqrkamklhsii:f01068b4fd889f7cd0d23b191f5eeb9729674ee40dce0a5b7063342aacd94a47@ec2-18-215-96-22.compute-1.amazonaws.com:5432/daek7g9mrllna4"

        //     if (conn != null) {
        //         System.out.println("Connected to the database!");
        //     } else {
        //         System.out.println("Failed to make connection!");
        //     }

        // } catch (SQLException e) {

        //     System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}