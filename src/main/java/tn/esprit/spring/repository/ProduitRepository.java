package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Produit;



@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{
	//boolean existsByGeneratedString(String generatedString);
	Produit findByReference(String reference);

}
