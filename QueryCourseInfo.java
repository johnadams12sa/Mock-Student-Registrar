import java.sql.*;
public class QueryCourseInfo {
	public static void main(String args[]) {
		try{  
	        //load the driver class  
	        Class.forName("oracle.jdbc.driver.OracleDriver");  
	        
	        //create  the connection object  
	        Connection con = DriverManager.getConnection(  
	        "jdbc:oracle:thin:@localhost:1521:xe","system","123");
	        
	        //create statement object to interact with db
	        Statement stmt = con.createStatement();
	        
	        //store the results in a ResultSet object
	        ResultSet rs = stmt.executeQuery("select * from course_info");
	        
        	System.out.printf("%25s %25s %25s\n", "Course Number", "Course Name", "Instructor");
	        //print out the results
	        while(rs.next()) 
	        	System.out.printf("%25s %25s %25s\n", rs.getString("course_no"), rs.getString("course_name"), rs.getString("instructor"));
	        
	        //close the connection object  
	        con.close();  
	        
		}
		catch(Exception e) {
			System.out.println("Encountered an error " + e.getMessage());
		}
	}
}
