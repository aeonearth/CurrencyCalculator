import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrencyCalculatorJUNIT {

	private String FromCurrencyName;
	private String ToCurrencyName;
	private Double FromCurrencyValue;
	private Double ToCurrencyValue;
	
	@BeforeEach
	void setUp() throws Exception {
		FromCurrencyName = "TestSGD";
		ToCurrencyName = "TestUSD";
		FromCurrencyValue = 1.0;
		ToCurrencyValue = 0.8;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddCurrencyRates() {
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/currencyrates", "root", "password");
		PreparedStatement ps = con.prepareStatement("INSERT INTO rates (from_currency_name, from_currency_rates, to_currency_name, to_currency_rates) values (?,?,?,?)");
		ps.setString(1, FromCurrencyName);
		ps.setDouble(2, FromCurrencyValue);
		ps.setString(3, ToCurrencyName);
		ps.setDouble(4, ToCurrencyValue);
				
		int i = ps.executeUpdate();
		
		if (i > 0){
			assertTrue(true);
			}
		}
		catch (Exception exception) {
			assertTrue(false);
		}

	}

}
