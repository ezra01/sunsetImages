import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/PersonDAO")
public class PersonDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public PersonDAO() {

    }
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://localhost:3306/sunsetdb?"
  			          + "allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insert(Person person) throws SQLException {
    	connect_func();         
		String sql = "insert into  person(email, passw, fName, lName, gender, birthday) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, person.email);
		preparedStatement.setString(2, person.passw);
		preparedStatement.setString(3, person.fName);
		preparedStatement.setString(4, person.lName);
		preparedStatement.setString(5, person.gender);
		preparedStatement.setString(6, person.birthday);	
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowInserted;
    }
    
    public Person getPerson(String email) throws SQLException {
    	Person person = null;
        String sql = "SELECT * FROM person WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        person = new Person();
        if (resultSet.next()) {
        	String name = resultSet.getString("email");
            String pw = resultSet.getString("passw");
            String fname = resultSet.getString("fName");
            String lname = resultSet.getString("lName");
            String gender = resultSet.getString("gender");
            Date dob = resultSet.getDate("birthday");
            person = new Person(name,pw,fname,lname,gender,dob.toString());
        }
         System.out.println(person.email);
        resultSet.close();
        preparedStatement.close();
         
        return person;
    }
    
    // No tags yet
    public boolean post(Image image) throws SQLException{
    	String sql1 = "INSERT INTO image (url, detail, poster) VALUES (?, ?, ?);";
    	
    	preparedStatement = connect.prepareStatement(sql1);
    	preparedStatement.setString(1, image.url);
    	preparedStatement.setString(2, image.details);
    	preparedStatement.setString(3, image.poster);
    	    	
    	boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowInserted;
    }
    
    public boolean follow(Follower follower) throws SQLException{
    	String sql1 = "SELECT idol FROM follower WHERE idol=" + follower.idol; //can you concatenate db query?
    	String sql2 = "INSERT INTO follower (idol, fan) VALUES (?, ?)";
    	String sql3 = "DELETE FROM follower WHERE idol=" + follower.idol;
    	
    	preparedStatement = connect.prepareStatement(sql1);
    	//preparedStatement.setString(1, follower.idol);
    	resultSet = preparedStatement.executeQuery();
    	
    	// If row !exists, add it
    	if(!resultSet.next()) {
    		preparedStatement = connect.prepareStatement(sql2);
        	preparedStatement.setString(1, follower.idol);
        	preparedStatement.setString(2, follower.fan);
    	}
    	// If row exists, delete it
    	else
    		preparedStatement = connect.prepareStatement(sql3);
    	
    	boolean rowInserted = preparedStatement.executeUpdate() > 0;
    	resultSet.close();
        preparedStatement.close();
        return rowInserted;
    }
    
   
}
