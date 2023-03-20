package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Magasin;


@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long> {

}
