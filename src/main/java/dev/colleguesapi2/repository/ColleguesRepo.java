package dev.colleguesapi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.colleguesapi2.entite.Collegue;

public interface ColleguesRepo extends JpaRepository<Collegue, Integer> {

}
