package Interface;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.User;
@Service
public interface IUserservice {
	public User ajoutuser(User user);
	public User updatepassword(User user ,Long idUser);
	public User updateUser(User user, Long idUser);
	public void deleteUser(Long idUser);
	public User affichDetailUser(Long idUser);
	public List<User> affichUser();
	public User findbyusername(String username);
	public User affcterfileauuser(Long iduser,Long idfile);
	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByResetToken(String resetToken);

}
