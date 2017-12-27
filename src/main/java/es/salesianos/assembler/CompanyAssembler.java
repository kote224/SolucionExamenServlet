package es.salesianos.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Empresa;
import es.salesianos.model.Company;
import es.salesianos.model.Consola;

public class CompanyAssembler {

	public Company createUserFromRequest(HttpServletRequest request) {

		Company company = new Company();
		
		company.setId(request.getParameter("id"));
		company.setName(request.getParameter("nombre"));
		company.setCompanyDate(request.getParameter("dateEmpr"));
		return company;
	}

}
