package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Banned;
import tn.esprit.spring.entity.Creditrefuse;
@Repository
public interface BannedRepository  extends JpaRepository<Banned, Long> {

}
