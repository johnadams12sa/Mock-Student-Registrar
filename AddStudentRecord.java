import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
public class AddStudentRecord {
	public static void main(String args[]) throws IOException{
		try {
			//load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//create a connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
			
			//create a statement object to interact with the db
			Statement stmt = con.createStatement();
			
			//initialization of variables 
			String student_id = null;
			String first_name = null;
			String last_name = null;
			String current_year = null;
			String dept_no = null;
			Integer InChar = null;
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("This module is for adding new records into the DB");
			System.out.println("Press 1 to Continue");
			System.out.println("Press 0 to Quit");
			try {
				InChar = scanner.nextInt();
				if (InChar == 1) {
					scanner.nextLine();
					System.out.println("Enter student's ID number");
					student_id = scanner.next();
					
					System.out.println("Enter student's first name");
					first_name = scanner.next();
					
					System.out.println("Enter student's last name");
					last_name = scanner.next();
					
					System.out.println("Enter student's current year");
					current_year = scanner.next();
					
					System.out.println("Enter student's department. If not applicable, leave blank");
					dept_no = scanner.next();
					
				}
				else if (InChar == 0) {
					System.out.println("Closing Application");
					con.close();
					scanner.close();
					System.exit(1);
				}
				else {
					throw new Exception("Invalid Input Found");
				}
			}
			catch (IOException io) {
				System.out.println("IO Exception encountered " + io.getMessage());
			}
			//store results of SQL command to a variable
			int rs = stmt.executeUpdate("insert into student values ('" + student_id + "','" + first_name + "','" + last_name +
					"','" + current_year + "','" + dept_no + "')");
			System.out.println(rs + " row(s) added to the table");
			
			scanner.close();
			//close the connection object  
	        con.close();  

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
