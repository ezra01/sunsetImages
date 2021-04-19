

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet(
		name="AjaxServlet",
		urlPatterns= {"/ajax","/ajax*","/AjaxServlet","/AjaxServlet*"},
		asyncSupported = true
)
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		private PersonDAO personDAO;
	    public void init() {
	    	personDAO = new PersonDAO(); 
	    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// Action
		String param1 = request.getParameter("action");
		//System.out.println(param1);
		//response.getWriter().print("Served at: "+param1+"baksljdfls");
		try {
            switch (param1) {
            case "Like":
            	//System.out.println("Like Case!");
            	Like(request,response);
            	break;
            case "Unlike":
            	//System.out.println("UNlike Case!");
            	Unlike(request,response);
            	break;
            case "Follow":
            	//System.out.println("Follow or Unfollow Case!");
            	Follow(request,response);
            	break;
            default:
            	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void Like(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
		 PersonDAO personDAO = new PersonDAO();
		// Parameters
			// User
		Cookie ck[] = request.getCookies();
    	String user = ck[0].getValue();
    	int param2 = Integer.parseInt(request.getParameter("id"));
    	boolean isSucess =personDAO.InsertLike(new Likes(user,param2));
    	response.getWriter().print(personDAO.getLike(param2));
	}
	private void Unlike(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
	 PersonDAO personDAO = new PersonDAO();
	// Parameters
		// User
	Cookie ck[] = request.getCookies();
	String user = ck[0].getValue();
	int param2 = Integer.parseInt(request.getParameter("id"));
	boolean isSucess =personDAO.DeleteLike(new Likes(user,param2));
	response.getWriter().print(personDAO.getLike(param2));
}
	
	private void Follow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
	 PersonDAO personDAO = new PersonDAO();
	// Parameters
		// User
	Cookie ck[] = request.getCookies();
	String user = ck[0].getValue();
	String param2 = request.getParameter("id");
	
	//Method
	boolean isSucess =personDAO.follow(new Follower(param2,user));
	
	// Response
	//response.getWriter().print(personDAO.getLike(param2));
}

}
