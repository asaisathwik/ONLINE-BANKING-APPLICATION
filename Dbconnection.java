
import java.sql.*;
public class Dbconnection {
static Connection con=null;
static
{
	try
	{
Class.forName("com.mysql.cj.jdbc.Driver");	
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Reddy1199**");
	}
	catch(Exception e)
	{
	 e.printStackTrace();
	}

}
 static Connection getcon()
{
	return con;
}
}
