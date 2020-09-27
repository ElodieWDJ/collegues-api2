package dev.colleguesapi2.controlleur;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.colleguesapi2.dto.RequestDtoCollegue;
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

}
