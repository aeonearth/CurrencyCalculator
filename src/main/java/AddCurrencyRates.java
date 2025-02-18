

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AddCurrencyRates
 */
@WebServlet("/AddCurrencyRates")
public class AddCurrencyRates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCurrencyRates() {
        super();
        // TODO Auto-generated constructor stub
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
		response.setContentType("text/html"); //Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter(); //Step 2: retrieve the four parameters from the request from the web form
		String n = request.getParameter("fromCurrencyName");
		String pp = request.getParameter("fromCurrencyRates");
		double p = Double.parseDouble(pp); 
		
		String e = request.getParameter("toCurrencyName");
		String cc = request.getParameter("toCurrencyRates");
		double c = Double.parseDouble(cc); 
		
		//Step 3: attempt connection to database using JDBC
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/currencyrates", "root", "password");
		//Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
		PreparedStatement ps = con.prepareStatement("INSERT INTO rates (from_currency_name, from_currency_rates, to_currency_name, to_currency_rates) values (?,?,?,?)");
		//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
		ps.setString(1, n);
		ps.setDouble(2, p);
		ps.setString(3, e);
		ps.setDouble(4, c);
		
		
		//Step 6: perform the query on the database using the prepared statement
		int i = ps.executeUpdate();
		
		//Step 7: check if the query had been successfully executed
		if (i > 0){
			PrintWriter writer = response.getWriter();
			writer.println("<h1>" + "You have successfully added a new currency rates!" + "</h1>" + 
			"<br/><a href=\"http://localhost:8080/CurrencyCalculator/currenciessservlet/dashboard\">Go back to dashboard</a>");
			writer.close();
			}
			} //Step 8: catch and print out any exception
			catch (Exception exception) {
			System.out.println(exception);
			out.close();
			}
			doGet(request, response);
		}

}
