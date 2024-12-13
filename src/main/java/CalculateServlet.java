
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
 * Servlet implementation class CalculateServlet
 */
@WebServlet("/CalculateServlet")
public class CalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();

		// ===
		String from_currency_name = request.getParameter("from_currency_name");
		String pp = request.getParameter("from_currency_rates");
		double from_currency_rates = Double.parseDouble(pp);

		String to_currency_name = request.getParameter("to_currency_name");
		String cc = request.getParameter("to_currency_rates");
		double to_currency_rates = Double.parseDouble(cc);

		String aa = request.getParameter("calculateAmount");
		double calculateAmount = Double.parseDouble(aa);
		// ===
		double calculatedAmount = calculateAmount * to_currency_rates;

		PrintWriter writer = response.getWriter();
		writer.println("<h1>" + "Convert " + String.valueOf(calculateAmount) + " to " + to_currency_name
				+ ".<br/>Converted Amount: " + String.valueOf(calculatedAmount) + "</h1>"
				+ "<br/><a href=\"http://localhost:8080/CurrencyCalculator/currenciessservlet/dashboard\">Go back to dashboard</a>");
		writer.close();
		out.close();

		doGet(request, response);
	}

}
