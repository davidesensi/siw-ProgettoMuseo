package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.OperaService;

@Controller
@SessionAttributes("accountCorrente")
public class OperaController {
	@Autowired
	private CredentialsService credentialsService;
	

    @Autowired
    private OperaService operaService;
    
    @Autowired
	private CollezioneService collezioneService;

	@RequestMapping(value = "opera/{id}/admin/removeOpera", method = RequestMethod.GET)
    public String removeOpera(@PathVariable("id") Long id, Model model) {    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			
			model.addAttribute("opera", this.operaService.findById(id));
			return "admin/removeOpera";
		}
		
		return "opere";
    		
    }
    
    @RequestMapping(value = "opera/{id}/admin/removeOpera", method = RequestMethod.POST)
    public String registerRemoveOpera(@PathVariable("id") Long id, Model model) {
    	Opera opera = operaService.findById(id);
    		/* Imposta a NULL tutte le collezioni delle opere (così si "liberano") */
    		/*operaService.removeOperaIdFromCollezioneId( opera.getId(),opera.getCollezione().getId());
    		operaService.removeArtistaIdByOperaId( opera.getId(),opera.getArtista().getId());*/
    		/* Cancella la collezione dal db */
            this.operaService.rimuovi(opera.getId());
        	/* Se l'inserimento dei dati nella form è corretto, viene mostrata la lista delle collezioni aggiornata */
            return "redirect:/opere";
        
    }
    
    
    
    @RequestMapping(value = "/opera/{id}/admin/aggiungiCollezioneAOpera", method = RequestMethod.GET)
	public String adminAggiungeCollezioneAOpera(@PathVariable("id") Long id,Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("accountCorrente", credentials);
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("collezioni", this.collezioneService.findAll());
			model.addAttribute("opera", this.operaService.findById(id));

			return "admin/aggiungiCollezioneAOpera";
		}
		return "opere";
	}

	@RequestMapping(value = { "/opera/{id}/admin/aggiungiCollezioneAOpera" }, method = RequestMethod.POST)
	public String registerCollezioneAOpera(@PathVariable("id") Long idOpera,
				@RequestParam("collezione") Collezione collezione ,Model model) throws Exception{
				Opera opera = operaService.findById(idOpera);
				if(!collezione.getOpere().contains(opera)) {
					collezione.addOpera(opera);
					operaService.setCollezione(collezione, opera.getId());
					operaService.save(opera);
					return "redirect:/opera/{id}";
			}
			
		return "admin/aggiungiCollezioneAOpera";
		
	}
    
    
}
