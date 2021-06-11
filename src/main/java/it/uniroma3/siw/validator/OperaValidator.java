package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;

@Component

public class OperaValidator implements Validator{

	@Autowired
	private OperaService operaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo","required");
		
		if(this.operaService.alreadyExists((Opera)target)){
			errors.reject("duplicato");
		}
		
	}

}
