package request;

import java.util.Set;



public class SignupRequest {

  private String username;


  private String email;

  private Set<String> role;

  private String password;
  
	private String nom;
	
	private String prenom;
	
	private String adresse;
	
	private String tel;
	
	private Boolean active;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getAdresse() {
	return adresse;
}

public void setAdresse(String adresse) {
	this.adresse = adresse;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public Boolean getActive() {
	return active;
}

public void setActive(Boolean active) {
	this.active = active;
}
  
}
