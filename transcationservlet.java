

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class transcationservlet
 */
@WebServlet("/transcationservlet")
public class transcationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transcationservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw=response.getWriter();
			Connection con=Dbconnection.getcon();
		String td=request.getParameter("test");
		if("deposit".equalsIgnoreCase(td))
		{
		RequestDispatcher rd=request.getRequestDispatcher("Deposit.html");
		rd.forward(request, response);
		}
		else if("withdraw".equalsIgnoreCase(td))
		{
			RequestDispatcher rd=request.getRequestDispatcher("Withdarw.html");
			rd.forward(request, response);
		}
		else if("balance enquiry".equalsIgnoreCase(td))
		{
			
			
			PreparedStatement ps=con.prepareStatement("select * from candidates where Cid=?");
			ServletContext sct=request.getServletContext();
			Integer Cid2=(Integer)sct.getAttribute("Cid2");
			ps.setInt(1, Cid2);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				pw.println(rs.getString(3)+" Balance is "+rs.getInt(4));
			}
			}
		
	}
		catch(Exception e)
		{
			
		}
	}

	
	
}
