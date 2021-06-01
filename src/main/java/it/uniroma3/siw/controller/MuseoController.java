package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.service.ArtistaService;

@Controller
public class MuseoController {
	
	@Autowired
	private ArtistaService artistaService;

	@RequestMapping(value="/artisti" , method= RequestMethod.GET)
	public String getArtisti(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.findAll());
		return "artisti.html";
	}
}
