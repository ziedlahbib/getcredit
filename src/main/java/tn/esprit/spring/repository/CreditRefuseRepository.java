package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Creditrefuse;

@Repository
public interface CreditRefuseRepository  extends JpaRepository<Creditrefuse, Long> {

}
