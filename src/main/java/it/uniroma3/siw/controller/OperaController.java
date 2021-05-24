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

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;
import it.uniroma3.siw.validator.OperaValidator;


@Controller
public class OperaController {

	@Autowired
	private OperaValidator operaValidator;
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value="/addOpera", method = RequestMethod.GET)
	public String newOpera(@Validated @ModelAttribute("opera") Opera opera,Model model, BindingResult bindingResult) {
		this.operaValidator.validate(opera, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.operaService.save(opera);
			model.addAttribute("opera",this.operaService.findAll());
			return "opere.html";
		}
		else {
			return "opereFrom.html";
		}
	}
	
	@RequestMapping(value="/opera/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", this.operaService.findById(id));

		return "opera.html";

	}
}
