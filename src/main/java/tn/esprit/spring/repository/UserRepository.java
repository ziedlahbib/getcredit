package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	Optional<User> findByResetToken(String resetToken);
	Optional<User> findByEmail(String email);

}
