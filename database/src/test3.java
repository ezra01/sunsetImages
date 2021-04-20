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
	    String sqlDatabase = "DROP TABLE if exists posts,follower, comments, likes, contain, tag, image, person; ";
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
	    		"poster VARCHAR(100) NOT NULL," +
	    		"created TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + 
	    		"PRIMARY KEY(imgId)," +
	    		"FOREIGN KEY(poster) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE " +
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
	    		"likeDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + 
	    		"PRIMARY KEY(email, imgId)," + 
	    		"FOREIGN KEY(email) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE, " + 
	    		"FOREIGN KEY(imgId) REFERENCES image(imgId)ON DELETE CASCADE ON UPDATE CASCADE" + 
	    		");";
/*	Foreign key constraint in Image    String sqlCreate6 ="CREATE TABLE posts(" +  
	    		"email VARCHAR(100) NOT NULL," + 
	    		"imgId INT  NOT NULL," + 
	    		"PRIMARY KEY(email, imgId)," + 
	    		"FOREIGN KEY(email) REFERENCES person(email) ON DELETE CASCADE ON UPDATE CASCADE, " + 
	    		"FOREIGN KEY(imgId) REFERENCES image(imgId) ON DELETE CASCADE ON UPDATE CASCADE" + 
	    		");";
*/	    String sqlCreate7 ="CREATE TABLE comments(" + 
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
	    String sqlCreate9 = "CREATE TRIGGER dailyPosts BEFORE INSERT ON image " +
	    		"FOR EACH ROW " +
	    		"BEGIN " +
		    		"IF 5 <= (SELECT COUNT(*) " +
			    		"FROM image " +
			    		"WHERE DATE(created) = DATE(NEW.created) AND " +
			    		"poster = NEW.poster) THEN " +
		    		"SIGNAL SQLSTATE '45000' " +
		    		"SET MESSAGE_TEXT = 'Can’t post more than 5 images per day'; " +
		    		"END IF; " +
	    		"END;";
	    String sqlCreate10 = "CREATE TRIGGER dailyLikes BEFORE INSERT ON likes " +
	    		"FOR EACH ROW " +
	    		"BEGIN " +
		    		"IF 3 <= (SELECT COUNT(*) " +
			    		"FROM image " +
			    		"WHERE DATE(likeDate) = DATE(NEW.likeDate) AND " +
			    		"email = NEW.email) THEN " +
		    		"SIGNAL SQLSTATE '45000' " +
		    		"SET MESSAGE_TEXT = 'Can’t like more than 3 images per day'; " +
		    		"END IF; " +
	    		"END;";
	    String sqlInsert1 = "insert into person(email, passw, fName, lName, gender, birthday) values (?, ?, ?, ?, ?, ?)";
	      //System.out.println("Select a table and then print out its content.");
	      // This will load the MySQL driver, each DB has its own driver
	      // Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	    if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }}
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost:3306/sunsetdb?"
	              + "allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");

	        

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();

	      statement.executeUpdate(sqlDatabase); // table drop
	      statement.executeUpdate(sqlCreate1); // person table
	      statement.executeUpdate(sqlCreate2); // image table
	      statement.executeUpdate(sqlCreate3); // tag table
	      statement.executeUpdate(sqlCreate4); // contain table
	      statement.executeUpdate(sqlCreate5); // likes table
//	      statement.executeUpdate(sqlCreate6); // posts table
	      statement.executeUpdate(sqlCreate7); // comments table
	      statement.executeUpdate(sqlCreate8); // follower table
	      statement.executeUpdate(sqlCreate9); // dailyPosts trigger
	      statement.executeUpdate(sqlCreate10); // dailyLikes trigger
	      
	    //person
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
	      
	     //images
	      List<String> imageList = new ArrayList<String>();
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1611095560396-89216a352761?ixid=MXwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80\",\"DocuSign from Unsplash.com\", \"tNelms@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1614862053138-10ca7a58866e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1525&q=80\",\"Antelope Canyon (IG: @clay.banks)\", \"rHou@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1593642634402-b0eb5e2eebc9?ixid=MXwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80\",\"Dell XPS Designed to be the Best\", \"eProchaska@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1614640005509-2b10151cf3b1?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80\",\"Kyoto,Japan Photo Courtesy of Riccardo Mancino\", \"rLeis@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1614687150515-f82d2a1ce260?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80\",\"Photo Courtesy of Mitchel Luo\", \"eCrawford@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1614620304143-4b762783a93d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80\",\"Photo Photo Courtesy of Alex Quezada\", \"bIwamoto@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1614815099662-55eb56815d4d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80\",\"Photo Courtesy of Marco De Hevia\", \"lKirker@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1610346915620-6819c46e0544?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80\",\"Photo Courtesy of Jack Irwin\", \"cLockwood@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1614680889612-d82e69f49ea2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80\",\"Photo Courtesy of Liza Azorina\", \"iTheobald@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values (\"https://images.unsplash.com/photo-1614694871663-f80b4e1decb6?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80\",\"Photo Courtesy of Ingmar\", \"pRisser@gmail.com\")");
	      imageList.add(" insert into image(url,details, poster) values(\"https://images.unsplash.com/photo-1612832164065-fc35ded2a291?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80\",\"Surface Stock\",\"bIwamoto@gmail.com\");");
	      for(String x: imageList) {
	    	  statement.addBatch(x);
	      }
	      statement.executeBatch();
	      
	      	// tag List
	      	List<String> tagList = new ArrayList<String>();
	      	 tagList.add("insert into tag(word) values (\"landscape\"),(\"animals\"),(\"beautiful\"),(\"cool\"),(\"creativity\"),(\"social\"),(\"bright\"),(\"vision\"),(\"passion\"),(\"morning\")");
	      	for(String x: tagList) {
		    	  statement.addBatch(x);
		      }
		    statement.executeBatch();
		    // Container List
		      List<String> containList = new ArrayList<String>();
		      	 containList.add("insert into contain(imgId,tagId) values (1,6),(2,4),(3,8),(5,9),(6,10),(7,2),(8,5),(9,7),(4,3),(10,1)");
		      	for(String x: containList) {
			    	  statement.addBatch(x);
		      		}
		      statement.executeBatch();
		      // Likes List
			  List<String> likesList = new ArrayList<String>();
			  likesList.add("insert into likes(email,imgId) values (\"tNelms@gmail.com\",6),(\"bIwamoto@gmail.com\",4),(\"bIwamoto@gmail.com\",6),(\"lKirker@gmail.com\",9),(\"iTheobald@gmail.com\",10),(\"pRisser@gmail.com\",2),(\"rHou@gmail.com\",5),(\"cLockwood@gmail.com\",7),(\"iTheobald@gmail.com\",3),(\"iTheobald@gmail.com\",1)");
			     for(String x: likesList) {
				    statement.addBatch(x);
				 	}
			  statement.executeBatch();
			  // Posts List--Must be after image and person
/*			  List<String> postsList = new ArrayList<String>();
			  postsList.add("insert into posts(email,imgId) values (\"tNelms@gmail.com\",1),(\"rHou@gmail.com\",2),(\"eProchaska@gmail.com\",3),(\"rLeis@gmail.com\",4),(\"eCrawford@gmail.com\",5),(\"bIwamoto@gmail.com\",6),(\"lKirker@gmail.com\",7),(\"cLockwood@gmail.com\",8),(\"iTheobald@gmail.com\",9),(\"pRisser@gmail.com\",10)");
			     for(String x: postsList) {
				    statement.addBatch(x);
				 	}
			  statement.executeBatch();
*/			  
			// Posts List--Must be after image and person
			  List<String> commentsList = new ArrayList<String>();
			  commentsList.add("insert into comments(email,imgId,detail) values "+
					  			"(\"tNelms@gmail.com\",1, \"Wow! Take a good look at this photo friends, friends.\" ),"+
					  			"(\"rHou@gmail.com\",1,\"Impressive photo!\" ),"+
					  			"(\"eProchaska@gmail.com\",3,\"I don't Like this at all.. D:\" ),"+
					  			"(\"eCrawford@gmail.com\",3,\"You deserve more praise. Keep on working hard.\" ),"+
					  			"(\"eProchaska@gmail.com\",5,\"You're photos are bad. You need to take a class or soemthing.\" ),"+
					  			"(\"lKirker@gmail.com\",6,\"Hang in there!\" ),"+
					  			"(\"eProchaska@gmail.com\",7,\"Good weork kiddo. Back in my day we had to have over 300 confirmed shots before even training in black room development.\" ),"+
					  			"(\"eCrawford@gmail.com\",7,\"This is a great work. You must be related to Van Gogh!\" ),"+
					  			"(\"lKirker@gmail.com\",9,\"I like this!\" ),"+
					  			"(\"lKirker@gmail.com\",1,\"I like this!\" )"
					  			);
			     for(String x: commentsList) {
				    statement.addBatch(x);
				 	}
			  statement.executeBatch();
			// Posts List--Must be after image and person
			  List<String> followerList = new ArrayList<String>();
			  followerList.add("insert into follower(idol,fan) values(\"tNelms@gmail.com\",\"rHou@gmail.com\"),( \"rHou@gmail.com\",\"eProchaska@gmail.com\" ),(\"rLeis@gmail.com\" , \"bIwamoto@gmail.com\" ),( \"lKirker@gmail.com\", \"cLockwood@gmail.com\"),( \"cLockwood@gmail.com\", \"lKirker@gmail.com\" ),( \"cLockwood@gmail.com\",\"iTheobald@gmail.com\" ),( \"cLockwood@gmail.com\",\"rHou@gmail.com\" ),(\"iTheobald@gmail.com\" ,\"rHou@gmail.com\" ),(\"rHou@gmail.com\" ,\"pRisser@gmail.com\" ),( \"pRisser@gmail.com\", \"eProchaska@gmail.com\"),(\"iTheobald@gmail.com\",\"bIwamoto@gmail.com\") ");
			     for(String x: followerList) {
				    statement.addBatch(x);
				 	}
			  statement.executeBatch();	
	      	
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
