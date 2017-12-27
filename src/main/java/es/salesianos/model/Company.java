package es.salesianos.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Company {

	String company;
	Date dateComp;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public String getId() {
		return company;
	}

	public void setId(String id) {
		this.company = id;
	}
	
	public String getName() {
		return company;
	}

	public void setName(String Name) {
		this.company = Name;
	}
	
	
	public Date getCompanyDate() {
		return dateComp;
	}
	
	public void setCompanyDate(Date dateComp) {
		this.dateComp = dateComp;
	}
	
	public void setCompanyDate(String dateComp) {
		try {
			System.out.print(dateComp);
			this.dateComp = sdf.parse(dateComp);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}


}
