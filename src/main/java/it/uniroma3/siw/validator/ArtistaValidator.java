package it.uniroma3.siw.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.service.ArtistaService;


@Component
public class ArtistaValidator implements Validator{

	
	@Autowired
	private ArtistaService artistaService;
	
	/*public boolean validate(HttpServletRequest request) {
		String nome = request.getParameter("nome");
	  	String cognome = request.getParameter("cognome");
	  	Map<String,String> messaggiErrori = new HashMap<String,String>();
	  	
	  	
	  	if(!nome.equals("") && !cognome.equals("")) {
	  		return true;
	  	}
	  	else {
	  		if(nome.equals("")) {
	  			messaggiErrori.put("nome","Il nome è un campo obbligatorio");
	  			request.setAttribute("nome", nome);
	  		}
	  		if(cognome.equals("")) {
	  			messaggiErrori.put("cognome","Il cognome è un campo obbligatorio");
	  			request.setAttribute("cognome", cognome);
	  		}
	  		request.setAttribute("errori", messaggiErrori);
	  		return false;
	  	}
	}*/

	@Override
	public boolean supports(Class<?> clazz) {
		return Artista.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome","required");
		if(this.artistaService.alreadyExists((Artista)target)){
			errors.reject("duplicato");
		}
		
	}
}

