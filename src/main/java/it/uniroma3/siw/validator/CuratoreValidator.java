package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.service.CuratoreService;

@Component
public class CuratoreValidator implements Validator{
	@Autowired
	private CuratoreService curatoreService;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Curatore.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome","required");
		if(this.curatoreService.alreadyExists((Curatore)target)){
			errors.reject("duplicato");
		}
		
	}
}
