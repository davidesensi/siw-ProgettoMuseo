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
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Artista.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoN","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nazionalita","required");
		if(this.artistaService.alreadyExists((Artista)target)){
			errors.reject("duplicato");
		}
		
	}
}

