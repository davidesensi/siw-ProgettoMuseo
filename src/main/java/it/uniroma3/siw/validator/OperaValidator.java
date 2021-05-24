package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;

@Component
public class OperaValidator {
	@Autowired
	private OperaService operaService;
	
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anno","required");
		if(this.operaService.alreadyExists((Opera)target)){
			errors.reject("duplicato");
		}
		
	}

}
