import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */

public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonDAO personDAO;
 
    public void init() {
    	personDAO = new PersonDAO(); 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Cookie ck[] = request.getCookies();
    	String user = ck[0].getValue();
    	
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/toRegistration":
            	showRegistration(request,response);
            	break;
            case "/toLogin":
            	showLogin(request,response);
            	break;
            case "/signUp":
            	insertPerson(request,response);
            	break;
            case "/login":
            	verifyAccount(request,response);
            	break;
            case "/feed":
            	showFeed(request,response);
            	break;
            case "/toPost":
            	System.out.println("ucu");
            	showPost(request, response);
            	System.out.println("cuc");
            	break;
            case "/post":
            	post(request, response);
            	break;
            case "/follow":
            	follow(request, response);
            	break;
            case "/profile":
            	showProfile(request,response);
            	break; 
            case "/community":
            	showCommunity(request,response);
            	break;
            case "/resetDatabase":
            	// userShould be restricted or verified
        		System.out.println("started");
            	if(user.equals("root")) {
            		System.out.println("verified");
            		test3.run(); //Initialize database
            	}
            	System.out.println("finished");
            	
            	response.sendRedirect("feed");
            	break;
            default:
            	System.out.println("Error?");
            	showLogin(request,response);
            	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    // navigate to LOGIN page
    private void showLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userLogin.jsp");
        dispatcher.forward(request, response);
    } 
    
    // navigate to COMMUNITY page
    private void showCommunity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    		// get user information
    				Cookie ck[] = request.getCookies();
    				PersonDAO persondao = new PersonDAO();
	    	// get All people - me and root
			ArrayList<Person> personList = null;
			personList = persondao.getCommunityPeople(ck[0].getValue());
			request.setAttribute("personList",personList );
			// Get matching following info too
			ArrayList<Follower> followList = null;
			followList = persondao.getCommunityFollowings(ck[0].getValue());
			request.setAttribute("followList",followList );	
        RequestDispatcher dispatcher = request.getRequestDispatcher("communityPage.jsp");
        dispatcher.forward(request, response);
    }
 // navigate to REGISTRATION page
    private void showRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistration.jsp");
        dispatcher.forward(request, response);
    }
    // navigate to FEED page
    private void showFeed(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	// get user information
			Cookie ck[] = request.getCookies();
			PersonDAO persondao= new PersonDAO();
		// get related user posts
			ArrayList<Image> imageList = null;
			imageList = persondao.getAllImages(ck[0].getValue());
			request.setAttribute("imageList",imageList );
		//get Likes
    		ArrayList<LikeInfo> likeList = null;
    		likeList = persondao.getAllLikes(ck[0].getValue());
    		request.setAttribute("likeList",likeList );
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedPage.jsp");
        dispatcher.forward(request, response);
    }
    
    // navigate to new POST page
    private void showPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("postPage.jsp");
        dispatcher.forward(request, response);
    }
    // navigate to PROFILE page
    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	// get user information
    		Cookie ck[] = request.getCookies();
    		PersonDAO persondao= new PersonDAO();
    		Person person = new Person();
    	// email
    		String email = request.getParameter("username");
    		
    		person = persondao.getPerson(email);
    		request.setAttribute("person",person );
    	// get user posts
    		ArrayList<Image> imageList = null;
    		imageList = persondao.getMyImages(email);
    		request.setAttribute("imageList",imageList );
    	//get Likes
    		ArrayList<LikeInfo> likeList = null;
    		likeList = persondao.getMyLikes(email);
    		request.setAttribute("likeList",likeList );
		// get my fans / followers
			ArrayList<Person> fanList = null;
			fanList = persondao.getFans(email);
			request.setAttribute("fanList",fanList );
		// get count of fans
			Long fanCount = null;
			fanCount = persondao.getFanCount(email);
			request.setAttribute("fanCount",fanCount );
		// get my idols / followings
			ArrayList<Person> idolList = null;
			idolList = persondao.getIdols(email);
			request.setAttribute("idolList",idolList );
		// get count of idols
			Long idolCount = null;
			idolCount = persondao.getIdolCount(email);
			request.setAttribute("idolCount",idolCount );
			
    		
        RequestDispatcher dispatcher = request.getRequestDispatcher("profilePage.jsp");
        dispatcher.forward(request, response);
    }
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    private void insertPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("dob");
        Person newPeople = new Person(email, pw, fname, lname, gender, birthday);
        if(	!ifExists(email)	) {
        	personDAO.insert(newPeople);
        	response.sendRedirect("feed");
        }
        else {
        	String msg= "UserName already exists.";
        	request.setAttribute("message",msg);
        	try {
        	showRegistration(request,response); // by using foward, we do not ping the servlet!
        	}catch(Exception e) {System.out.println("foward Exception--InsertPerson function");}
        	}
       // The sendRedirect() method works at client side and sends a new request
    }
    private void verifyAccount(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        PersonDAO personDAO = new PersonDAO();
        Person p1 = new Person();
        p1=personDAO.getPerson(email);
        
       
        if(p1.getEmail()!=null && p1.getPassw().equals(pw)) {
        	//Password is same
        	response.addCookie(new Cookie("user", email)); // Persistent User
        	
    		response.sendRedirect("feed");
        }
        else {
        	//password is different
        	response.sendRedirect("toLogin");
    	}
    }
    
  //checks if username exists, returns boolean values
    private boolean ifExists(String email)
            throws SQLException, IOException {
    PersonDAO personDAO = new PersonDAO();
    Person p1 = new Person();
    p1=personDAO.getPerson(email);
    if(p1.getEmail()!=null) {	return true;    }
    else {	return false;	}
    }
    
    private void post(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException  {
    	Cookie ck[] = request.getCookies();
    	
    	String url = request.getParameter("url");
    	String details = request.getParameter("details");
    	String poster = ck[0].getValue(); //name:user
    	Image newImage = new Image(url, details, poster);
    	
    	personDAO.post(newImage);
    	
    }
    
    private void follow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException  {
    	Cookie ck[] = request.getCookies();
    	
    	String idol = request.getParameter("idol");
    	String fan = ck[0].getValue(); //name:user
    	Follower newFollower = new Follower(idol, fan);
    	
    	personDAO.follow(newFollower);
    }
   

}