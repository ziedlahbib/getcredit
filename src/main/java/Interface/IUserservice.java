package Interface;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import request.ChangePasswordRequest;
import request.SignupRequest;
import tn.esprit.spring.entity.User;
@Service
public interface IUserservice {
	public User ajoutclient(User user);
	public String updatepassword(ChangePasswordRequest request ,Long idUser);
	public User resetpassword(User user );
	public User updateUser(SignupRequest signUpRequest, Long idUser);
	public User updateclient(User user, Long idUser);
	public void deleteUser(Long idUser);
	public User affichDetailUser(Long idUser);
	public List<User> affichUser();
	public Optional<User> findbyusername(String username);
	public User affcterfileauuser(Long iduser,Long idfile);
	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByResetToken(String resetToken);
	public User affecteruseraumagasin(Long iduser,Long idmag);
	public User affecteragentauentrepreneur(Long idagent,Long ident);
	public List<User> getusersbyEntrepreneur(Long idEnt);
	public List<User> getusersbyagent(Long idagent);
	public List<User> getusersbymagasin(Long idmagasin);
	public User activer(Long iduser);
	public User desactiver(Long iduser);
}
