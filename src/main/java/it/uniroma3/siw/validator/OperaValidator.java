package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
<<<<<<< HEAD
import org.springframework.validation.Validator;

=======

import it.uniroma3.siw.model.Collezione;
>>>>>>> 8c1e45b365edba4254077d3c3d8121829760f808
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;

@Component
<<<<<<< HEAD
public class OperaValidator implements Validator{

	@Autowired
	private OperaService operaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome","required");
=======
public class OperaValidator {
	@Autowired
	private OperaService operaService;
	
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anno","required");
>>>>>>> 8c1e45b365edba4254077d3c3d8121829760f808
		if(this.operaService.alreadyExists((Opera)target)){
			errors.reject("duplicato");
		}
		
	}
<<<<<<< HEAD
=======

>>>>>>> 8c1e45b365edba4254077d3c3d8121829760f808
}
