package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.OperaService;
//import it.uniroma3.siw.validator.ArtistaValidator;
//import it.uniroma3.siw.validator.ArtistaValidator;

@Controller
public class MuseoController {
	
	@Autowired
	private ArtistaService artistaService;
	
	//@Autowired
	//private ArtistaValidator artistaValidator;

	@Autowired
	private CollezioneService collezioneService;

	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value="/artistaForm", method = RequestMethod.GET)
	public String newArtista(@ModelAttribute("artista") Artista artista,Model model, BindingResult bindingResult) {
		//this.artistaValidator.validate(artista, bindingResult);
		//if(!bindingResult.hasErrors()) {
			this.artistaService.save(artista);
			model.addAttribute("artisti",this.artistaService.findAll());
			return "artistaForm.html";
	}
		/*}
		else {
			return "index.html";
		}*/

	@RequestMapping(value="/artisti" , method= RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.findAll());
		return "artisti";
	}
	
	@RequestMapping(value="/artista/{id}", method = RequestMethod.GET)
	public String getArtista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.findById(id));
		return "artista";
	}
	
	@RequestMapping(value="/collezioni" , method= RequestMethod.GET)
	public String getCollezioni(Model model) {
			model.addAttribute("collezioni", this.collezioneService.findAll());
			return "collezioni";
	
		
	}
	
	@RequestMapping(value="/collezione/{id}", method = RequestMethod.GET)
	public String getCollezione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collezione", this.collezioneService.findById(id));
		return "collezione";
	}
	
	@RequestMapping(value="/opere" , method= RequestMethod.GET)
	public String getOpere(Model model) {
		model.addAttribute("opere", this.operaService.findAll());
		return "opere";
	}
	
	@RequestMapping(value="/opera/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", this.operaService.findById(id));
		return "opera";
	}
	
	
	
}
