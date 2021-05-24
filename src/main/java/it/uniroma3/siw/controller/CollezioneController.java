package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.validator.CollezioneValidator;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneValidator collezioneValidator;
	
	@Autowired
	private CollezioneService collezioneService;
	
	
	@RequestMapping(value="/addCollezione", method = RequestMethod.GET)
	public String newCollezione(@Validated @ModelAttribute("collezione") Collezione collezione,Model model, BindingResult bindingResult) {
		this.collezioneValidator.validate(collezione, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.collezioneService.save(collezione);
			model.addAttribute("collezione",this.collezioneService.findAll());
			return "collezioni.html";
		}
		else {
			return "collezioniFrom.html";
		}
	}
	
	@RequestMapping(value="/artista/{id}", method = RequestMethod.GET)
	public String getCollezione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collezione", this.collezioneService.findById(id));
		return "collezioni.html";
	}

}
