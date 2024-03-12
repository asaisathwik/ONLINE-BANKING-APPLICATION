

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class atmservlet
 */
@WebServlet("/atmservlet")
public class atmservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public atmservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection con=Dbconnection.getcon();
			PrintWriter pw=response.getWriter();
			PreparedStatement ps=con.prepareStatement("select * from candidates where Cid=? and pin=?");
			int Cid1=Integer.parseInt(request.getParameter("Id"));
			int pinNo=Integer.parseInt(request.getParameter("pin"));
			ps.setInt(1, Cid1);
			ps.setInt(2, pinNo);
			ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						ServletContext sct=request.getServletContext();
						sct.setAttribute("Cid2",Cid1);
						
						RequestDispatcher rm=request.getRequestDispatcher("transcation.html");
						rm.forward(request, response);
					}
					else
					{
					pw.println("candidate is not registered");
					
					
					}
						
					
		}
		catch(Exception e)
		{
			
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
