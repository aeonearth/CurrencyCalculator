
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class currenciessservlet
 */
@WebServlet("/currenciessservlet")
public class currenciessservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections private
	String jdbcURL = "jdbc:mysql://localhost:3306/currencyrates";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_CURRENCY_SQL = "INSERT INTO rates (from_currency_name, from_currency_rates, to_currency_name, to_currency_rates) values (?,?,?,?)";
	private static final String SELECT_CURRENCY_BY_ID = "select ID, from_currency_name, from_currency_rates, to_currency_name, to_currency_rates, last_modified_date from rates where ID = ?";
	private static final String SELECT_ALL_CURRENCIES = "select * from rates";
	private static final String DELETE_CURRENCY_SQL = "delete from rates where ID = ?";
	private static final String UPDATE_CURRENCY_SQL = "update rates set from_currency_name = ?, from_currency_rates= ?, to_currency_name =?, to_currency_rates =? where ID = ?";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public currenciessservlet() {
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
		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/currenciessservlet/delete":
				deleteCurrency(request, response);
				break;
			case "/currenciessservlet/edit":
				showEditForm(request, response);
				break;
			case "/currenciessservlet/update":
				updateCurrency(request, response);
				break;
			case "/currenciessservlet/dashboard":
				listCurrency(request, response);
				break;
			case "/currenciessservlet/calculator":
				calculator(request, response);
				break;
			case "/currenciessservlet/calculate":
				caculate(request, response);
				break;
			case "/currenciessservlet/insert":
				// addCurrency(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Step 5: listUsers function to connect to the database and retrieve all users
	// records
	private void listCurrency(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<currencies> currencies = new ArrayList<>();

		try (Connection connection = getConnection(); // Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENCIES);) { // Step
																												// 5.2:
			// Execute the
			// query or
			// update query
			ResultSet rs = preparedStatement.executeQuery(); // Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				// String ID = rs.getString("ID");
				Integer ID = rs.getInt("ID");
				String from_currency_name = rs.getString("from_currency_name");
				Double from_currency_rates = rs.getDouble("from_currency_rates");
				String to_currency_name = rs.getString("to_currency_name");
				Double to_currency_rates = rs.getDouble("to_currency_rates");
				Date last_modified_date = rs.getDate("last_modified_date");

				// String last_modified_date = rs.getString("last_modified_date");
				currencies.add(new currencies(ID, from_currency_name, from_currency_rates, to_currency_name,
						to_currency_rates, last_modified_date));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5.4: Set the users list into the listUsers attribute to be pass to the
			// userManagement.jsp

		request.setAttribute("listCurrencies", currencies);
		request.getRequestDispatcher("/displayrates.jsp").forward(request, response);
	}

	// method to delete user
	private void deleteCurrency(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String ID = request.getParameter("ID"); // Step 2: Attempt connection with database and execute delete user
												// SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CURRENCY_SQL);) {
			statement.setString(1, ID);
			int i = statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:8080/CurrencyCalculator/currenciessservlet/dashboard");
	}

	private void calculator(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String getID = request.getParameter("ID");
		// User existingUser = new User("", "", "", "");
		currencies currencies = null;
		// List<currencies> currencies = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CURRENCY_BY_ID);) {
			preparedStatement.setString(1, getID); // Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery(); // Step 4: Process the ResultSet object
			while (rs.next()) {
				Integer ID = rs.getInt("ID");
				String from_currency_name = rs.getString("from_currency_name");
				Double from_currency_rates = rs.getDouble("from_currency_rates");
				String to_currency_name = rs.getString("to_currency_name");
				Double to_currency_rates = rs.getDouble("to_currency_rates");
				Date last_modified_date = rs.getDate("last_modified_date");

				currencies = new currencies(ID, from_currency_name, from_currency_rates, to_currency_name,
						to_currency_rates, last_modified_date);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("currencies", currencies);
		request.getRequestDispatcher("/calculate.jsp").forward(request, response);
	}

	private void caculate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		String ID = request.getParameter("ID");
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
		writer.println("<h1>" + "Convert " + String.valueOf(calculateAmount) + " " + from_currency_name + " to " + to_currency_name + " (rate of " + cc + ")"
				+ ".<br/>Converted Amount: " + String.valueOf(calculatedAmount) + "</h1>"
				+ "<br/><a href=\"http://localhost:8080/CurrencyCalculator/currenciessservlet/dashboard\">Go back to dashboard</a>");
		writer.close();
		
		//response.sendRedirect("http://localhost:8080/CurrencyCalculator/currenciessservlet/dashboard");
	}
	
	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String getID = request.getParameter("ID");
		// User existingUser = new User("", "", "", "");
		currencies currencies = null;
		// List<currencies> currencies = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CURRENCY_BY_ID);) {
			preparedStatement.setString(1, getID); // Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery(); // Step 4: Process the ResultSet object
			while (rs.next()) {
				Integer ID = rs.getInt("ID");
				String from_currency_name = rs.getString("from_currency_name");
				Double from_currency_rates = rs.getDouble("from_currency_rates");
				String to_currency_name = rs.getString("to_currency_name");
				Double to_currency_rates = rs.getDouble("to_currency_rates");
				Date last_modified_date = rs.getDate("last_modified_date");

				currencies = new currencies(ID, from_currency_name, from_currency_rates, to_currency_name,
						to_currency_rates, last_modified_date);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("currencies", currencies);
		request.getRequestDispatcher("/currencyEdit.jsp").forward(request, response);
	}

	// method to update the user table base on the form data
	private void updateCurrency(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String ID = request.getParameter("ID");
		String from_currency_name = request.getParameter("from_currency_name");
		String from_currency_rates = request.getParameter("from_currency_rates");
		String to_currency_name = request.getParameter("to_currency_name");
		String to_currency_rates = request.getParameter("to_currency_rates");
		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CURRENCY_SQL);) {
			// statement.setString(1, name);

			statement.setString(1, from_currency_name);
			statement.setDouble(2, Double.parseDouble(from_currency_rates));
			statement.setString(3, to_currency_name);
			statement.setDouble(4, Double.parseDouble(to_currency_rates));

			statement.setInt(5, Integer.parseInt(ID));

			int i = statement.executeUpdate();
		} // Step 3: redirect back to UserServlet (note: remember to change the url to
			// your project name)
		response.sendRedirect("http://localhost:8080/CurrencyCalculator/currenciessservlet/dashboard");
	}
	/*
	 * // method to trigger RegisterServlet private void
	 * addCurrency(HttpServletRequest request, HttpServletResponse response) throws
	 * SQLException, IOException, ServletException { RequestDispatcher rd = null; rd
	 * = getServletContext().getRequestDispatcher("/RegisterServlet");
	 * rd.include(request, response);
	 * 
	 * response.sendRedirect(
	 * "http://localhost:8090/HelloWorldJavaEE/UserServlet/dashboard"); }
	 */
}
