package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.OperaService;

@Controller
public class CollezioneController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private CollezioneService collezioneService;
    
    /* Il service di Opera ci serve per la constatazione dell'ID della prima opera, in quanto una collezione DEVE AVERE ALMENO UN'OPERA */
    @Autowired
    private OperaService operaService;
    
    @RequestMapping(value = "collezione/{id}/admin/removeCollezione", method = RequestMethod.GET)
    public String removeCollezione(@PathVariable("id") Long id, Model model) {    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			
			model.addAttribute("collezione", this.collezioneService.findById(id));
			return "admin/removeCollezione";
		}
		
		return "collezioni";
    		
    }
    
    @RequestMapping(value = "collezione/{id}/admin/removeCollezione", method = RequestMethod.POST)
    public String registerRemoveCollezione(@PathVariable("id") Long id, Model model) {
    	Collezione collezione = collezioneService.findById(id);
    		/* Imposta a NULL tutte le collezioni delle opere (così si "liberano") */
    		this.rimuoviCollezioneDaListaOpere(collezione);
    		/* Cancella la collezione dal db */
            this.collezioneService.rimuovi(collezione);
        	/* Se l'inserimento dei dati nella form è corretto, viene mostrata la lista delle collezioni aggiornata */
            return "redirect:/collezioni";
        
    }
    
    /** Imposta a NULL tutte le collezioni delle opere appartenenti ad una collezione.
     * Restituisce il numero di opere modificate.  **/
    private void rimuoviCollezioneDaListaOpere(Collezione collezione) {
    	List<Opera> opereCollezioneDaCancellare = collezione.getOpere();
		
		for(Opera opera : opereCollezioneDaCancellare) {
			operaService.removeCollezioneIdByOperaId(opera.getId());
		}
    }

}
