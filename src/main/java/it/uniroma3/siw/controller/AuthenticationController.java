package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.controller.session.SessionData;
import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.OperaService;
import it.uniroma3.siw.validator.ArtistaValidator;
import it.uniroma3.siw.validator.CollezioneValidator;
import it.uniroma3.siw.validator.CredentialsValidator;

@Controller
public class AuthenticationController {

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private CredentialsValidator credentialsValidator;

	@Autowired
	SessionData sessionData;

	@Autowired
	private ArtistaValidator artistaValidator;

	@Autowired
	private CollezioneValidator collezioneValidator;
	
	@Autowired
	private CollezioneService collezioneService;

	@Autowired
	private OperaService operaService;

	/*@Autowired
	private Object userValidator;*/


	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginForm";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "index";
		}
		return "index";
	}

	@RequestMapping(value = "/admin/artisti", method = RequestMethod.GET)
	public String getArtistiAdmin(Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("artisti", this.artistaService.findAll());
			return "admin/artistiAdmin";
		}
		return "index";
	}

	@RequestMapping(value = "/admin/artistaForm", method = RequestMethod.GET)
	public String adminAggiungeArtista(Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("artista", new Artista());

			return "admin/artistaForm";
		}
		return "artisti";
	}
	
	@RequestMapping(value = { "/admin/artistaForm" }, method = RequestMethod.POST)
	public String registerArtista(@Validated @ModelAttribute("artista") Artista artista,
			BindingResult bindingResult,Model model) throws Exception{

		this.artistaValidator.validate(artista, bindingResult);

		if(!bindingResult.hasErrors()) {
			artistaService.save(artista);
			model.addAttribute("artista", this.collezioneService.findAll());
			return "artisti";
		}
		return "admin/artistaForm";
	}
	

	@RequestMapping(value = "/admin/collezioneForm", method = RequestMethod.GET)
	public String adminAggiungeCollezione(Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("collezione", new Collezione());

			return "admin/collezioneForm";
		}
		return "collezioni";
	}

	@RequestMapping(value = { "/admin/collezioneForm" }, method = RequestMethod.POST)
	public String registerCollezione(@Validated @ModelAttribute("collezione") Collezione collezione,
			BindingResult bindingResult,Model model) throws Exception{

		this.collezioneValidator.validate(collezione, bindingResult);

		if(!bindingResult.hasErrors()) {
			collezioneService.save(collezione);
			model.addAttribute("collezioni", this.collezioneService.findAll());
			return "collezioni";
		}
		return "admin/collezioneForm";
	}
	
	@RequestMapping(value = "/admin/aggiungiOperaACollezione", method = RequestMethod.GET)
	public String adminAggiungeOperaACollezione(@ModelAttribute("collezione") Collezione collezione,Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("collezione", collezione);
			model.addAttribute("opere", this.operaService.findAll());

			return "admin/aggiungiOperaACollezione";
		}
		return "collezioni";
	}

	@RequestMapping(value = { "/admin/aggiungiOperaACollezione" }, method = RequestMethod.POST)
	public String registerOperaACollezione(@Validated @ModelAttribute("collezione") Collezione collezione,
			@ModelAttribute("opera") Opera opera,Model model) throws Exception{
		
		if(!collezione.getOpere().contains(opera)) {
			Object opere =  this.operaService.findAll();
			collezione.addOpera(opera);	
			collezioneService.save(collezione);
			model.addAttribute("collezione", collezione);
			model.addAttribute("opere", opere);
		}
		collezione.addOpera(opera);
		collezioneService.save(collezione);
		return "/admin/aggiungiOperaACollezione";
		
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "registerUser";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,
			BindingResult userBindingResult,
			@ModelAttribute("credentials") Credentials credentials,
			BindingResult credentialsBindingResult,
			Model model) {

		// validate user and credentials fields
		//this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);

		// if neither of them had invalid contents, store the User and the Credentials into the DB
		if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
			// set the user and store the credentials;
			// this also stores the User, thanks to Cascade.ALL policy
			credentials.setUser(user);
			credentialsService.saveCredentials(credentials);
			return "registrationSuccessful";
		}
		return "registerUser";
	}
}
