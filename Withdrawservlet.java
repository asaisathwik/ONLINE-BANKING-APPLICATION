

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class Withdrawservlet
 */
@WebServlet("/Withdrawservlet")
public class Withdrawservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdrawservlet() {
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
		int Wamt=Integer.parseInt(request.getParameter("withdraw"));
		if(Wamt%100==0) {
		PreparedStatement ps=con.prepareStatement("select * from candidates where Cid=?");
		ServletContext sct=request.getServletContext();
		Integer Cid2=(Integer)sct.getAttribute("Cid2");
		ps.setInt(1, Cid2);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			if(rs.getInt(4)>Wamt)
			{
			pw.println("Amount is Withdrawn");
			int amt=rs.getInt(4)-Wamt;
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
			else
			{
			pw.println("INSUFFIENT FUNDS\n Go back & plz enter valid Amount ");
			}
		}
		
		}
		else
		{
			pw.println("ENTER VALID AMOUNT");
		}
		
	}
	catch(Exception e)
	{
	}
	}

	

}

