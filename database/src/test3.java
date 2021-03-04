/*
 *
 *
 *
 *
 *
1: Based on previously created database on your local server, you need to create a table that contains some categories of information through java programming. 
Use the code in the file that you can find at the bottom and modify it to your own settings. You need to modify the name of the database, user name and password, which are marked YYYY, XXX, and XXXX, respectively.

 

 

Try all the SQL statements in Chapter 2's slides by modifying test3.java and running it against your own local MySQL database server. Executing the SQL statements using MySQL Workbench is inadequate.?

Explain line by line how test3.java works, and record a youtube using Apowersoft: https://www.apowersoft.com/free-online-screen-recorder (Links to an external site.)Links to an external site.

While test2.java contains the code snippet to execute an SQL INSERT statement, the following code snippets show how to execute SQL UPDATE and DELETE statements in test3.java.

 

 

"

// update
String query1 = "update Student set Address = ? where Name = ?";
preparedStatement = conn.prepareStatement(query1);
preparedStatement.setString(1, "4500 Cass Ave");
preparedStatement.setString(2, "Shiyong Lu");
preparedStatement.executeUpdate();
resultSet = statement
.executeQuery("select * from Student");
writeResultSet(resultSet);

// Delete
String query2 = "DELETE FROM Student WHERE Name = ?";
preparedStatement = conn.prepareStatement(query2);
preparedStatement.setString(1, "Shiyong Lu");
preparedStatement.executeUpdate();
resultSet = statement
.executeQuery("select * from Student");

writeResultSet(resultSet);

"

 

2: Compile the test3.java by using the command. Notice you may need to change the name regarding the version you are using for the jar file. 
Use the jar file you downloaded from previous exercises.

 

 

 javac -cp .:mysql-connector-java-5.1.32-bin.jar test3.java  on Mac, version is only an example here;

 

or

 

 javac -cp .;mysql-connector-java-8.0.13.jar test3.java on Windows 10, version is only an example here.

 

6: Run the java program by using the command

 

 java -cp .:mysql-connector-java-5.1.32-bin.jar test3  on Mac, version is only an example here;

 

or

 

"java -cp .;mysql-connector-java-8.0.13.jar test3  on Windows 10, version is only an example here.

 

 




      preparedStatement = connect

          .prepareStatement("insert into  member values (?, ?, ?)");

      preparedStatement.setString(1, "Your name");

      preparedStatement.setString(2, "Your email");

      preparedStatement.setString(3, "Your country");

      preparedStatement.executeUpdate();

     

      resultSet = statement

          .executeQuery("select * from member");

 

      writeResultSet(resultSet);


Plus, if you have a warning at the beginning that claims Loading class "com.mysql.jdbc.Driver". 
This is deprecated. The new driver class is "com.mysql.cj.jdbc.Driver". The driver is automatically 
registered via the SPI and manual loading of the driver class is generally unnecessary., 
just simply comment the following line in the code as well, 8.0.x version jar file already contains that driver for you to compile the code:


// Class.forName("com.mysql.jdbc.Driver");

 *
 *
 *
 * */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test3  {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;



 public static void main(String[] args) {


    //String sql3 = "insert into  Student(Name, Address, Status) values (?, ?, ?)";
    //String sql4 = "insert into Student(Name, Address, Status) values (\"John Liu\", \"345 Database Road, Troy, MI 48083\", \"Junior\")";
    //String sql5 = "UPDATE Student set Address=\"123 New Main Street, Troy, MI 48083\" WHERE Name=\"Shiyong Lu\"";
    //String sql6 = "DELETE FROM Student WHERE Name=\"Shiyong Lu\"";

 }
 public static void run() {
	    try { 
	    String sqlDatabase = "DROP TABLE if exists follower, comments, posts, likes, contain, tag, image, person; ";
	    String sqlCreate1 = "CREATE TABLE person(" + 
	    		"email VARCHAR(100) NOT NULL," + 
	    		"passw VARCHAR(40) NOT NULL," + 
	    		"fName VARCHAR(32) NOT NULL," + 
	    		"lName VARCHAR(32) NOT NULL," + 
	    		"gender varchar(25)," + 
	    		"birthday DATE NOT NULL," + 
	    		"PRIMARY KEY(email)" + 
	    		");";
	    String sqlCreate2 ="CREATE TABLE image(" + 
	    		"imgId INT auto_increment NOT NULL," + 
	    		"url VARCHAR(500) NOT NULL," + 
	    		"details VARCHAR(500)," + 
	    		"created TIMESTAMP," + 
	    		"PRIMARY KEY(imgId)" + 
	    		");";
	    String sqlCreate3="CREATE TABLE tag(" + 
	    		"tagId INT auto_increment NOT NULL," + 
	    		"word VARCHAR(25)," + 
	    		"primary key(tagID)," + 
	    		"UNIQUE(word) " + 
	    		");";
	    String sqlCreate4 ="CREATE TABLE contain(" + 
	    		"imgId INT  NOT NULL," + 
	    		"tagId INT  NOT NULL," + 
	    		"PRIMARY KEY(imgId, TagId)," + 
	    		"FOREIGN KEY (imgId) REFERENCES image(imgId) ON DELETE CASCADE ON UPDATE CASCADE," + 
	    		"FOREIGN KEY (tagId) REFERENCES tag(tagId) ON DELETE CASCADE ON UPDATE CASCADE" + 
	    		");";
	    String sqlCreate5 ="CREATE TABLE likes(" + 
	    		"email VARCHAR(100) NOT NULL," + 
	    		"imgId INT  NOT NULL," + 
	    		"PRIMARY KEY(email, imgId)," + 
	    		"FOREIGN KEY(email) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE, " + 
	    		"FOREIGN KEY(imgId) REFERENCES image(imgId)ON DELETE CASCADE ON UPDATE CASCADE" + 
	    		");";
	    String sqlCreate6 ="CREATE TABLE posts(" + 
	    		"email VARCHAR(100) NOT NULL," + 
	    		"imgId INT  NOT NULL," + 
	    		"PRIMARY KEY(email, imgId)," + 
	    		"FOREIGN KEY(email) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE, " + 
	    		"FOREIGN KEY(imgId) REFERENCES image(imgId) ON DELETE CASCADE ON UPDATE CASCADE" + 
	    		");";
	    String sqlCreate7 ="CREATE TABLE comments(" + 
	    		"email VARCHAR(100) NOT NULL," + 
	    		"imgId INT  NOT NULL," + 
	    		"detail VARCHAR(500) NOT NULL," + 
	    		"PRIMARY KEY(email, imgId)," + 
	    		"FOREIGN KEY(email) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE, " + 
	    		"FOREIGN KEY(imgId) REFERENCES image(imgId)ON DELETE CASCADE ON UPDATE CASCADE" + 
	    		");";
	    String sqlCreate8 ="CREATE TABLE follower(" + 
	    		"idol VARCHAR(100) NOT NULL," + 
	    		"fan VARCHAR(100) NOT NULL," + 
	    		"FOREIGN KEY(idol) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE," + 
	    		"FOREIGN KEY(fan) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE" + 
	    		");";
	    String sqlInsert1 = "insert into  person(email, passw, fName, lName, gender, birthday) values (?, ?, ?, ?, ?, ?)";
	      //System.out.println("Select a table and then print out its content.");
	      // This will load the MySQL driver, each DB has its own driver
	      // Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	    if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }}
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost:3306/sunsetdb?"
	              + "useSSL=false&user=john&password=pass1234");

	        

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();

	      statement.executeUpdate(sqlDatabase); // table drop
	      statement.executeUpdate(sqlCreate1); // person table
	      statement.executeUpdate(sqlCreate2); // image table
	      statement.executeUpdate(sqlCreate3); // tag table
	      statement.executeUpdate(sqlCreate4); // contain table
	      statement.executeUpdate(sqlCreate5); // likes table
	      statement.executeUpdate(sqlCreate6); // posts table
	      statement.executeUpdate(sqlCreate7); // comments table
	      statement.executeUpdate(sqlCreate8); // follower table

	      Person person;
	      List<Person> personList= new ArrayList<Person>();
	      person= new Person();
	      personList.add(new Person("root", "pass1234", "samplefname", "samplelname", "Male", "2020-02-20"));
	      personList.add(new Person("tNelms@gmail.com", "pass1", "Tom", "Nelms", "Male", "1986-12-11"));
	      personList.add(new Person("rHou@gmail.com", "pass2", "Roxie", "Hou", "Female", "1990-09-09"));
	      personList.add(new Person("eProchaska@gmail.com", "pass3", "Ela", "Prochaska", "Female", "1991-06-20"));
	      personList.add(new Person("rLeis@gmail.com", "pass4", "Rickie", "Leis", "Male", "1991-09-27"));
	      personList.add(new Person("eCrawford@gmail.com", "pass5", "Evelyne", "Crawford", "Female", "1992-09-05"));
	      personList.add(new Person("bIwamoto@gmail.com", "pass6", "Bradly", "Iwamoto", "Male", "1995-03-05"));
	      personList.add(new Person("lKirker@gmail.com", "pass7", "Lucio", "Kirker", "Male", "1996-02-03"));
	      personList.add(new Person("cLockwood@gmail.com", "pass8", "Carli", "Lockwood", "Female", "1996-12-05"));
	      personList.add(new Person("iTheobald@gmail.com", "pass9", "Ignacio", "Theobald", "Male", "1998-12-03"));
	      personList.add(new Person("pRisser@gmail.com", "pass10", "Phyllis", "Risser", "Female", "2000-05-18"));
	      	preparedStatement = connect.prepareStatement(sqlInsert1);
	      	for(Person x: personList) {
	      		preparedStatement.setString(1, x.getEmail());
	      		preparedStatement.setString(2, x.getPassw());
	      		preparedStatement.setString(3, x.getfName());
	      		preparedStatement.setString(4, x.getlName());
	      		preparedStatement.setString(5, x.getGender());
	      		preparedStatement.setString(6, x.getBirthday());
	      	preparedStatement.addBatch();
	      	}
	      	preparedStatement.executeBatch();
	      	
	      	
	      	
	    } catch (Exception e) {
	         System.out.println(e);
	    } finally {
	      close();
	    }

	  }
  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }
  
  // You need to close the resultSet
  private static void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }
      if (preparedStatement != null) {
    	  preparedStatement.close();
        }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
} 
