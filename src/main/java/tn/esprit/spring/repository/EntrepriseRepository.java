package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Entreprise;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
