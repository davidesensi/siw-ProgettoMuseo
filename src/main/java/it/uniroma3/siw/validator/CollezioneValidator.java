package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;

@Component
public class CollezioneValidator {

	@Autowired
	private CollezioneService collezioneService;
	
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome","required");
		if(this.collezioneService.alreadyExists((Collezione)target)){
			errors.reject("duplicato");
		}
		
	}
}
