package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Credit;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

}
