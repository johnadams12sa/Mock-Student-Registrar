import java.sql.*;
import java.lang.Class;  
class CreateDB{  
    public static void main(String args[]){  
    	try{  
        //load the driver class  
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        
        //create  the connection object  
        Connection con = DriverManager.getConnection(  
        "jdbc:oracle:thin:@localhost:1521:xe","system","123");  
        
        //drop tables if they exist and re-make
        dropTables(con);
        
        //build the tables
        buildStudentTable(con);
        buildContact_infoTable(con);
        buildFinancesTable(con);
        buildDeptTable(con);
        buildCoursesTable(con);
        buildCourse_infoTable(con);
        buildGradesTable(con);
        
        Statement stmt = con.createStatement();
        //execute query, show what is in each table
        ResultSet rs=stmt.executeQuery("select * from student");  
        System.out.printf("%25s %25s %25s %25s %25s\n", "Student ID", "First Name", "Last Name", "Current_Year", "Department Number");
        while(rs.next())  
        System.out.printf("%25s %25s %25s %25s %25s\n", rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("current_year"), rs.getString("dept_no"));  
        System.out.println("");
        
        ResultSet rs2=stmt.executeQuery("select * from contact_info");
        System.out.printf("%25s %25s %25s %25s\n", "Student ID", "Home Address", "Email", "Phone");
        while(rs2.next())  
        System.out.printf("%25s %25s %25s %25s\n", rs2.getString("student_id"), rs2.getString("home_address"), rs2.getString("email"), rs2.getString("phone"));
        System.out.println("");
        
        ResultSet rs3=stmt.executeQuery("select * from finances"); 
        System.out.printf("%25s %25s\n", "Student ID", "Fees");
        while(rs3.next())  
        System.out.printf("%25s %25s\n", rs3.getString("student_id"), rs3.getInt("fees"));
        System.out.println("");
        
        ResultSet rs4=stmt.executeQuery("select * from dept");
        System.out.printf("%25s %25s\n", "Department Number", "Department Name");
        while(rs4.next())  
        System.out.printf("%25s %25s\n", rs4.getString("dept_no"), rs4.getString("dept_name"));
        System.out.println("");
        
        ResultSet rs5=stmt.executeQuery("select * from courses");  
        System.out.printf("%25s %25s %25s\n", "Course Number", "Course Name", "Department Number");
        while(rs5.next())  
        System.out.printf("%25s %25s %25s\n", rs5.getString("course_no"), rs5.getString("course_name"), rs5.getString("dept_no"));
        System.out.println("");
        
        ResultSet rs6=stmt.executeQuery("select * from course_info");
        System.out.printf("%25s %25s %25s\n", "Course Number", "Course Name", "Instructor");
        while(rs6.next())  
        System.out.printf("%25s %25s %25s\n", rs6.getString("course_no"), rs6.getString("course_name"), rs6.getString("instructor"));
        System.out.println("");
        
        ResultSet rs7=stmt.executeQuery("select * from grades");  
        System.out.printf("%25s %25s %25s %25s %25s\n", "Student ID", "Course Number", "Course Name", "Semester", "Grade");
        while(rs7.next())  
        System.out.printf("%25s %25s %25s %25s %25s\n", rs7.getString("student_id"), rs7.getString("course_no"), rs7.getString("course_name"), rs7.getString("semester"), rs7.getInt("grade"));
        
        
        //close the connection object  
        con.close();  
        }
    	catch(Exception e) {
            System.out.println(e.getMessage());
    	}
    }
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    public static void buildStudentTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
    		
    		//create student table
    		stmt.execute("create table student(" + 
    			"student_id varchar2(5) PRIMARY KEY," + 
    			"first_name varchar2(15) NOT NULL," + 
    			"last_name varchar2(15) NOT NULL," + 
    			"current_year varchar2(2) NOT NULL," + 
   				"dept_no varchar2(5) NOT NULL)");
    		System.out.println("student table created");
    		//insert values into the table
    		stmt.execute("insert into student values ("+
	    		"'12345'," +
	    		"'Aaron'," +
	    		"'Yam'," +
	    		"'Jr'," +
	    		"'00001'" +
	    		")");
    		
    		stmt.execute("insert into student values (" +
	    		"'23456'," +
	    		"'Erwin'," +
	    		"'Smith'," +
	    		"'Sr'," +
	    		"'00002'" +
	    		")");
    		}
    	catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void buildContact_infoTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
    		
    		//create contact_info table
    		stmt.execute("create table contact_info(" + 
    			"student_id varchar2(5) PRIMARY KEY, " + 
    			"home_address varchar2(25), " + 
    			"email varchar2(25), " + 
    			"phone varchar2(10), " + 
   				"constraint fk_contact_info " + 
   				"foreign key (student_id) references student (student_id))");
    		System.out.println("contact_info table created");
    		//insert values into table
    		stmt.execute("insert into contact_info values( " +
    			"'12345'," +
    			"'50 China Road'," +
    			"'notreal@email.com'," +
    			"'7187187181')");
    		stmt.execute("insert into contact_info values( " +
        		"'23456'," +
        		"'Red Lobster Lane'," +
        		"'lol@email.com'," +
       			"'7181234581')");
    	}
    	catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void buildFinancesTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
    		
    		//create finances tables
    		stmt.execute("create table finances(" + 
    			"student_id varchar2(5) NOT NULL, " + 
    			"fees number(10) DEFAULT 0, " + 
    			"constraint fk_finances " + 
    			"foreign key (student_id) references student (student_id))");
    		System.out.println("finances table created");
    		//insert values into table
    		stmt.execute("insert into finances values(" +
    			"'12345'," +
    			"0)");
    		stmt.execute("insert into finances values(" +
        		"'23456'," +
        		"100)");
    	}
    	catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void buildDeptTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
    		
    		//create tables
    		stmt.execute("create table dept(" + 
    			"dept_no varchar2(5) PRIMARY KEY, " + 
    			"dept_name varchar2(20) NOT NULL)");
    		System.out.println("dept table created");
    		//insert values into table
    		stmt.execute("insert into dept values(" +
    			"'00001'," +
    			"'Computer Science')");
    		stmt.execute("insert into dept values(" +
        		"'00002'," +
        		"'Political Science')");
    	}
    	catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void buildCoursesTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
    		
    		//create tables
    		stmt.execute("create table courses(" + 
    			"course_no varchar2(5) PRIMARY KEY, " + 
    			"course_name varchar2(25) NOT NULL, " + 
    			"dept_no varchar2(5) NOT NULL)");
    		System.out.println("courses table created");
    		//insert values into table
    		stmt.execute("insert into courses values(" +
    			"'11111'," +
    			"'Intro to Logic'," +
    			"'00001')"
    				);
    		stmt.execute("insert into courses values(" +
        		"'55555'," +
        		"'Advanced Logic'," +
        		"'00001')"
        				);
    		stmt.execute("insert into courses values(" +
        		"'53534'," +
        		"'Policies in the World'," +
        		"'00002')");
    	}
    	catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void buildCourse_infoTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
    		
    		//create tables
    		stmt.execute("create table course_info(" + 
    			"course_no varchar2(5) PRIMARY KEY, " + 
    			"course_name varchar2(25) NOT NULL, " + 
    			"instructor varchar2(15) DEFAULT 'Staff', " + 
    			"constraint fk_course_info " + 
    			"foreign key (course_no) references courses (course_no))");
    		System.out.println("course_info table created");
    		//insert values into table
    		stmt.execute("insert into course_info values(" +
    			"'11111'," +
    			"'Intro to Logic'," +
    			"'Mathma')");
    		stmt.execute("insert into course_info values(" +
        		"'55555'," +
        		"'Advanced Logic'," +
        		"'Rilacola')");
    		stmt.execute("insert into course_info values(" +
        		"'53534'," +
        		"'Policies in the World'," +
        		"'Reeee')");
  
    		}
    	catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void buildGradesTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
    		
    		//create tables
    		stmt.execute("create table grades(" + 
    			"student_id varchar2(5) NOT NULL, " + 
    			"course_no varchar2(5) NOT NULL, " + 
    			"course_name varchar2(25) NOT NULL, " + 
    			"semester varchar2(11) NOT NULL, " + 
   				"grade number(3), " + 
    			"constraint fk_grades " + 
    			"foreign key (student_id) references student (student_id))");
    		System.out.println("grades table created");
    		//insert values into table
    		stmt.execute("insert into grades values(" +
    			"'12345'," +
    			"'11111'," +
    			"'Intro to Logic'," +
    			"'Spring 2016'," +
    			"95)");
    		stmt.execute("insert into grades values(" +
        		"'23456'," +
        		"'11111'," +
        		"'Intro to Logic'," +
        		"'Spring 2016'," +
        		"88)");
    		stmt.execute("insert into grades values(" +
        		"'12345'," +
        		"'55555'," +
        		"'Advanced Logic'," +
       			"'Fall 2016'," +
       			"50)");
    		stmt.execute("insert into grades values(" +
        		"'23456'," +
        		"'55555'," +
        		"'Advanced Logic'," +
       			"'Fall 2016'," +
       			"70)");
    		stmt.execute("insert into grades values(" +
        		"'12345'," +
        		"'53534'," +
        		"'Policies in the World'," +
       			"'Spring 2017'," +
       			"86)");
    		stmt.execute("insert into grades values(" +
        		"'23456'," +
        		"'53534'," +
        		"'Policies in the World'," +
       			"'Spring 2017'," +
       			"99)");
    		}
    	catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    public static void dropTables(Connection con) {
    	System.out.println("If tables already exist, dropping from database");
    	try {
	    	//create the statement object  
	    	Statement stmt = con.createStatement();
	    	
	        //drop contact_info table
	        try {
	        	stmt.execute("drop table contact_info");
	        	System.out.println("contact_info table dropped");
	        }
	        catch(SQLException e) {
	        	System.out.println("There was a problem dropping the contact_info table");
	        }
	        
	        //drop finances table
	        try {
	        	stmt.execute("drop table finances");
	        	System.out.println("finances table dropped");
	        }
	        catch(SQLException e) {
	        	System.out.println("There was a problem dropping the finances table");
	        }
	        
	        //drop dept table
	        try {
	        	stmt.execute("drop table dept");
	        	System.out.println("dept table dropped");
	        }
	        catch(SQLException e) {
	        	System.out.println("There was a problem dropping the dept table");
	        }
	        
	        //drop course_info table
	        try {
	        	stmt.execute("drop table course_info");
	        	System.out.println("course_info table dropped");
	        }
	        catch(SQLException e) {
	        	System.out.println("There was a problem dropping the course_info table");
	        }
	        
	        //drop courses table
	        try {
	        	stmt.execute("drop table courses");
	        	System.out.println("courses table dropped");
	        }
	        catch(SQLException e) {
	        	System.out.println("There was a problem dropping the courses table");
	        }
	        
	        
	        //drop grades table
	        try {
	        	stmt.execute("drop table grades");
	        	System.out.println("grades table dropped");
	        }
	        catch(SQLException e) {
	        	System.out.println("There was a problem dropping the grades table");
	        }
	        
	        //drop student table
	        try {
	        	stmt.execute("drop table student");
	        	System.out.println("student table dropped");
	        	System.out.println("");
	        	System.out.println("");
	        }
	        catch(SQLException e) {
	        	System.out.println("There was a problem dropping the student table");
	        }
    	}
    	catch(Exception e) {
            System.out.println(e);
        }
    }
}  