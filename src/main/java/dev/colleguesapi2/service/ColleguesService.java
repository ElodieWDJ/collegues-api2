package dev.colleguesapi2.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.colleguesapi2.entite.Collegue;
import dev.colleguesapi2.repository.ColleguesRepo;

@Service
public class ColleguesService {
	private ColleguesRepo colleguesRepo;

	public ColleguesService(ColleguesRepo colleguesRepo) {
		this.colleguesRepo = colleguesRepo;
	}

	public Collegue creerCollegue(Collegue collegue) {
		return this.colleguesRepo.save(collegue);

	}

	public Optional<Collegue> findByMatricule(String matricule) {

		return this.colleguesRepo.findByMatricule(matricule);
	}
}
