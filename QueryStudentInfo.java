import java.sql.*;
public class QueryStudentInfo {
	public static void main(String args[]) {
		try {
			//load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//create  the connection object  
	        Connection con = DriverManager.getConnection(  
	        "jdbc:oracle:thin:@localhost:1521:xe","system","123");
			
			//create a statement object
			Statement stmt = con.createStatement();
			
			//store results in a variable 
			
			ResultSet rs = stmt.executeQuery("select student.student_id, student.first_name, student.last_name, student.current_year, contact_info.home_address, " +
					"contact_info.email, contact_info.phone " +
					"from student " +
					"inner join contact_info on student.student_id = contact_info.student_id " +
					"where student.student_id in '12345'");
			
			System.out.printf("%25s %25s %25s %25s %25s %25s %25s\n ",
					"Student ID", "First Name", "Last Name", "Current Year", "Home Address", "Email", "Phone");
			while(rs.next()) {
				System.out.printf("%25s %25s %25s %25s %25s %25s %25s\n", rs.getString("student_id"), rs.getString("first_name"), 
				rs.getString("last_name"), rs.getString("current_year"), rs.getString("home_address"), rs.getString("email"), rs.getString("phone"));
			}
			//close the connection object  
		    con.close();  
		}
		catch(Exception e) {
			System.out.println("Error encountered: " + e.getMessage());
		}
	}
}
