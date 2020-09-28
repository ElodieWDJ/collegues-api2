package dev.colleguesapi2.controlleur;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.colleguesapi2.dto.RequestDtoCollegue;
import dev.colleguesapi2.dto.ResponseDtoCollegue;
import dev.colleguesapi2.entite.Collegue;
import dev.colleguesapi2.service.ColleguesService;

@Controller
@RequestMapping("/collegue")
public class ColleguesCtrl {
	private ColleguesService colleguesService;

	public ColleguesCtrl(ColleguesService colleguesService) {
		this.colleguesService = colleguesService;
	}

	// Inserer collegue POST Collegue
	// BindingResult =
	@PostMapping("/creerCollegue")
	public ResponseEntity<?> creerCollegue(@RequestBody RequestDtoCollegue requestDtoCollegue,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("Erreur lors de la création");
		} else {
			Collegue collegue = new Collegue(requestDtoCollegue.getMatricule(), requestDtoCollegue.getNom(),
					requestDtoCollegue.getPrenoms(), requestDtoCollegue.getEmail(),
					requestDtoCollegue.getDateDeNaissance(), requestDtoCollegue.getPhotoUrl());
			this.colleguesService.creerCollegue(collegue);
			return ResponseEntity.ok().body(requestDtoCollegue);
		}

	}

	@GetMapping("/matricule/{matricule}")
	public ResponseEntity<?> findCollegueByMatricule(@PathVariable String matricule) {
		Optional<Collegue> collegue = this.colleguesService.findByMatricule(matricule);

		if (!collegue.isPresent()) {
			return ResponseEntity.badRequest().body("Il n'y a pas de collegue qui correspond à ce matricule");
		} else {
			ResponseDtoCollegue responseDtoCollegue = new ResponseDtoCollegue(collegue.get().getMatricule(),
					collegue.get().getNom(), collegue.get().getPrenoms(), collegue.get().getEmail(),
					collegue.get().getDateDeNaissance(), collegue.get().getPhotoUrl());
			this.colleguesService.findByMatricule(matricule);
			return ResponseEntity.ok().body(responseDtoCollegue);
		}

	}

}
