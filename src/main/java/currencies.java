import java.sql.Date;


public class currencies {
	//declare attributes
	protected Integer ID;
	protected String from_currency_name;
	protected Double from_currency_rates;
	protected String to_currency_name;
	protected Double to_currency_rates;
	protected Date last_modified_date;
	
	public currencies(Integer iD, String from_currency_name, Double from_currency_rates, String to_currency_name,
			Double to_currency_rates, Date last_modified_date) {
		super();
		ID = iD;
		this.from_currency_name = from_currency_name;
		this.from_currency_rates = from_currency_rates;
		this.to_currency_name = to_currency_name;
		this.to_currency_rates = to_currency_rates;
		this.last_modified_date = last_modified_date;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getFrom_currency_name() {
		return from_currency_name;
	}

	public void setFrom_currency_name(String from_currency_name) {
		this.from_currency_name = from_currency_name;
	}

	public Double getFrom_currency_rates() {
		return from_currency_rates;
	}

	public void setFrom_currency_rates(Double from_currency_rates) {
		this.from_currency_rates = from_currency_rates;
	}

	public String getTo_currency_name() {
		return to_currency_name;
	}

	public void setTo_currency_name(String to_currency_name) {
		this.to_currency_name = to_currency_name;
	}

	public Double getTo_currency_rates() {
		return to_currency_rates;
	}

	public void setTo_currency_rates(Double to_currency_rates) {
		this.to_currency_rates = to_currency_rates;
	}

	public Date getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}



}
