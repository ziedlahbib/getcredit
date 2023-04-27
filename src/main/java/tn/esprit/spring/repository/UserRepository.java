package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
	Optional<User> findByResetToken(String resetToken);
	Optional<User> findByEmail(String email);
	@Query("Select u FROM User u join u.magasin m where m.magasinId = :magasinId")
	List<User> userparmagasin(@Param("magasinId") Long magasinId);

}
