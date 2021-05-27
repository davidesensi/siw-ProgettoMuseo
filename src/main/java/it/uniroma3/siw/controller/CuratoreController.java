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

import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.service.CuratoreService;
import it.uniroma3.siw.validator.CuratoreValidator;

@Controller
public class CuratoreController {
	@Autowired
	private CuratoreValidator curatoreValidator;
	
	@Autowired
	private CuratoreService curatoreService;
	
	
	@RequestMapping(value="/addCuratore", method = RequestMethod.GET)
	public String newCuratore(@Validated @ModelAttribute("artista") Curatore curatore,Model model, BindingResult bindingResult) {
		this.curatoreValidator.validate(curatore, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.curatoreService.save(curatore);
			model.addAttribute("artisti",this.curatoreService.findAll());
			return "curatori.html";
		}
		else {
			return "curatoreFrom.html";
		}
	}
	
	@RequestMapping(value="/curatore/{id}", method = RequestMethod.GET)
	public String getCuratore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.curatoreService.findById(id));
		return "curatore.html";
	}
}
