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
    
    // insert person     
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
        disconnect();
        return rowInserted;
    }
    
    // read person
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
        resultSet.close();
        preparedStatement.close();
        disconnect();
         
        return person;
    }
    // return list of people
    public ArrayList<Person> getAllPeople() throws SQLException {
        String sql = "select * from person;";
        Person person = null;
        connect_func();
    	ArrayList<Person> listPeople = new ArrayList<Person>();            
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	String name = resultSet.getString("email");
            String pw = resultSet.getString("passw");
            String fname = resultSet.getString("fName");
            String lname = resultSet.getString("lName");
            String gender = resultSet.getString("gender");
            Date dob = resultSet.getDate("birthday");
            person = new Person(name,pw,fname,lname,gender,dob.toString());
            listPeople.add(person);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listPeople;
    }
       
    // read Images related to user
    // 		-posts by user
    //		-posts by those following
    public ArrayList<Image> getAllImages(String email) throws SQLException {
        String sql = "SELECT * from image left join follower on image.poster = follower.idol where follower.fan = ? or image.poster=? order by created;";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        Image image = null;
        ArrayList<Image> imageList = new ArrayList<Image>();
        while (resultSet.next()) {
        	int imgId = resultSet.getInt("imgId");
        	String url = resultSet.getString("url");
            String details = resultSet.getString("details");
            String c = resultSet.getString("created");
            String poster = resultSet.getString("poster");
           
            image = new Image(imgId,url,details,poster,c);
            imageList.add(image);
        }
        
        resultSet.close();
        preparedStatement.close();
        disconnect();
         
        return imageList;
    }
    public  ArrayList<LikeInfo>getAllLikes(String email) throws SQLException {
        String sql = "select imgid,likeCount,boolResult from " + 
        		"(select * from image left join follower on image.poster = follower.idol where follower.fan = ? or image.poster=? order by created) " + 
        		"as a " + 
        		"left join" + 
        		"(select imgId as id,(select count(*) from likes where imgId=id) as likeCount, imgId in (select imgId from likes where email =? group by imgId)as boolResult from likes group by imgId) " + 
        		"as b " + 
        		"on a.imgId = b.id order by created;";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, email);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<LikeInfo> likeInfoList = new ArrayList<LikeInfo>();
        while (resultSet.next()) {
        	int imgId = resultSet.getInt("imgId");
        	int likeCount = resultSet.getInt("likeCount");
            boolean boolResult = resultSet.getBoolean("boolResult");
            //System.out.println(imgId+" "+likeCount+" "+boolResult);
            likeInfoList.add( new LikeInfo(imgId,likeCount,boolResult));
        }
        
        resultSet.close();
        preparedStatement.close();
        disconnect();
         
        return likeInfoList;
    }
    // read Images ONLY posted by user
    public ArrayList<Image> getMyImages(String email) throws SQLException {
        String sql = "SELECT * FROM image where poster = ? order by created;";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        Image image = null;
        ArrayList<Image> imageList = new ArrayList<Image>();
        while (resultSet.next()) {
        	int imgId = resultSet.getInt("imgId");
        	String url = resultSet.getString("url");
            String details = resultSet.getString("details");
            String poster = resultSet.getString("poster");
            String created = resultSet.getDate("created").toString();
            image = new Image(imgId,url,details,poster,created);
            imageList.add(image);
        }
        
        resultSet.close();
        preparedStatement.close();
        disconnect();
         
        return imageList;
    }
    public  ArrayList<LikeInfo>getMyLikes(String email) throws SQLException {
        String sql = "select imgid,likeCount,boolResult from " + 
        		"(SELECT * FROM image where poster = ? order by created) " + 
        		"as a " + 
        		"left join " + 
        		"(select imgId as id,(select count(*) from likes where imgId=id) as likeCount, imgId in (select imgId from likes where email =? group by imgId)as boolResult from likes group by imgId) " + 
        		"as b " + 
        		"on a.imgId = b.id order by created;";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, email);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<LikeInfo> likeInfoList = new ArrayList<LikeInfo>();
        while (resultSet.next()) {
        	int imgId = resultSet.getInt("imgId");
        	int likeCount = resultSet.getInt("likeCount");
            boolean boolResult = resultSet.getBoolean("boolResult");
                  
            likeInfoList.add( new LikeInfo(imgId,likeCount,boolResult));
        }
        
        resultSet.close();
        preparedStatement.close();
        disconnect();
         
        return likeInfoList;
    }
    
    public long getLike(int imgId) throws SQLException {
        String sql = "select count(*) from likes where imgId=?";
        connect_func();
        long count = 0;
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, imgId);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        	count = resultSet.getLong("count(*)");
        }
        resultSet.close();
        preparedStatement.close();
        disconnect();
         
        return count;
    }
    
    // No tags yet
    
    // insert post / image
    public boolean post(Image image) throws SQLException{
    	String sql1 = "INSERT INTO image(url, details, poster) VALUES (?, ?, ?);";
    	connect_func();
    	preparedStatement = (PreparedStatement)connect.prepareStatement(sql1);
    	preparedStatement.setString(1, image.url);
    	preparedStatement.setString(2, image.details);
    	preparedStatement.setString(3, image.poster);
    	    	
    	boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowInserted;
    }
    
    // insert follow
    public boolean follow(Follower follower) throws SQLException{
    	String sql1 = "SELECT idol FROM follower WHERE idol='" + follower.idol +"'"; //can you concatenate db query?
    	String sql2 = "INSERT INTO follower (idol, fan) VALUES (?, ?)";
    	String sql3 = "DELETE FROM follower WHERE idol='" + follower.idol +"'";
    	
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
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
        disconnect();
        return rowInserted;
    }
    
 // insert Like
    public boolean InsertLike(Likes x) throws SQLException{
    	String sql = "INSERT INTO likes (email, imgId) VALUES (?, ?)";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    		preparedStatement = connect.prepareStatement(sql);
        	preparedStatement.setString(1, x.email);
        	preparedStatement.setInt(2, x.imgId);
	    boolean rowInserted = preparedStatement.executeUpdate() > 0;
	    preparedStatement.close();
	    disconnect();
	    return rowInserted;
    }
    public boolean DeleteLike(Likes x) throws SQLException{
    	String sql = "DELETE from likes where email= ? AND imgId = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    		preparedStatement = connect.prepareStatement(sql);
        	preparedStatement.setString(1, x.email);
        	preparedStatement.setInt(2, x.imgId);
	    boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	    preparedStatement.close();
	    disconnect();
	    return rowDeleted;
    }
   
}