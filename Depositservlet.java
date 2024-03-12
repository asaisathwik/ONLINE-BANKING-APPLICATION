
import java.sql.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Depositservlet
 */
@WebServlet("/Depositservlet")
public class Depositservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Depositservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			PrintWriter pw=response.getWriter();
		Connection con=Dbconnection.getcon();
		int Damt=Integer.parseInt(request.getParameter("deposit"));
		PreparedStatement ps=con.prepareStatement("select * from candidates where Cid=?");
		ServletContext sct=request.getServletContext();
		Integer Cid2=(Integer)sct.getAttribute("Cid2");
		ps.setInt(1, Cid2);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			pw.println("amount is Deposited");
			int amt=rs.getInt(4)+Damt;
			PreparedStatement px=con.prepareStatement("UPDATE `candidates` SET `Balance` = ? WHERE (`Cid` = ?)");
			px.setInt(1, amt);
			px.setInt(2,Cid2);
		    px.executeUpdate();
		    PreparedStatement pm=con.prepareStatement("select * from candidates where Cid=?");
		    pm.setInt(1, Cid2);
		    ResultSet rx=pm.executeQuery();
		    if(rx.next())
		    {
		   pw.println(rx.getString(3)+" Your balance is "+rx.getInt(4));
		    }
		}
		
	}
	catch(Exception e)
	{
	}
	}

}
