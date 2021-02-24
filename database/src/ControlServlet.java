import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            	response.sendRedirect("feed");
            	break;
            case "/login":
            	verifyAccount(request,response);
            	break;
            case "/feed":
            	showFeed(request,response);
            	break;
            case "/resetDatabase":
            	// userShould be restricted or verified
            	test3.run(); //Initialize database
            	response.sendRedirect("feed");
            	break;
            default:          	
            	showLogin(request,response);
            	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    // to show login page
    private void showLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userLogin.jsp");
        dispatcher.forward(request, response);
    }
    
    // to show registration page
    private void showRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistration.jsp");
        dispatcher.forward(request, response);
    }
    private void showFeed(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedPage.jsp");
        dispatcher.forward(request, response);
    }
    
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("dob");
        Person newPeople = new Person(email, pw, fname, lname, gender, birthday);
        personDAO.insert(newPeople);
       // The sendRedirect() method works at client side and sends a new request
    }
    private void verifyAccount(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String email = request.getParameter("email");
    	System.out.println("Email in is:"+email);
        String pw = request.getParameter("pw");
        PersonDAO personDAO = new PersonDAO();
        Person p1 = new Person();
        p1=personDAO.getPerson(email);
        
       
        if(p1.getEmail()!=null && p1.getPassw().equals(pw)) {
        	//Password is same
    		response.sendRedirect("feed");
        }
        else {
        	//password is different
        	response.sendRedirect("toLogin");
    	}
    }
   

}