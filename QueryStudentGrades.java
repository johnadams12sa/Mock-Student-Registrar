import java.sql.*;
public class QueryStudentGrades {
	public static void main(String args[]) {
		try {
			//load the driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//create a connection to database
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","123");
			
			//create a statement object to interact with database
			Statement stmt = con.createStatement();
			
			//store result of query in an object
			ResultSet rs = stmt.executeQuery("select student.student_id, student.first_name, student.last_name," +
			"grades.course_name, grades.semester, grades.grade " +
			"from student " +
			"inner join grades on student.student_id = grades.student_id " +
			"where student.student_id IN '12345'");
			
			System.out.printf("%25s %25s %25s %25s %25s %25s\n", "Student ID", "First Name", "Last Name", "Course Name",
			"Semester", "Grade");
			while(rs.next()) {
				System.out.printf("%25s %25s %25s %25s %25s %25s\n", rs.getString("student_id"), rs.getString("first_name"),
					rs.getString("last_name"), rs.getString("course_name"), rs.getString("semester"), rs.getInt("grade"));
			}
			
			rs = stmt.executeQuery("select student.student_id, student.first_name, student.last_name, avg(grades.grade) as gpa " +
					"from grades " +
					"inner join student on student.student_id = grades.student_id " +
					"where grades.student_id IN '12345'" + 
					"group by student.student_id, student.first_name, student.last_name");			
			System.out.println("");
			System.out.printf("%25s %25s %25s %25s\n", "Student ID", "First Name", "Last Name", "GPA");
			while(rs.next()) 
				System.out.printf("%25s %25s %25s %25s\n", rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("gpa"));
				
			//close the connection object  
		    con.close();  
			
		}
		catch (Exception e){
			System.out.println("Encountered an error " + e.getMessage());
		}
	}
	
}
