package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Value;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;




@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String nom;
	
	private String prenom;
	
	private String adresse;
	
	private String tel;
	
	private String email;
	
	private String password;
	
	private String resetToken;
	
	private Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	  @JoinTable(  name = "user_roles", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Role roles ;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<FileDB> files;
	
	
	
	@OneToMany(mappedBy="entrepreneur")
	@JsonIgnore
	private List<User> agents;
	
	@ManyToOne
	@JsonIgnore
	private User entrepreneur;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Entreprise> entreprise;
	
	
	@ManyToOne
	private Magasin magasin;
	
	
	@OneToMany(mappedBy = "user")
	private List<Credit> credits;
	@OneToMany(mappedBy = "agent")
	private List<Credit> credit;
	
	@ManyToMany(mappedBy = "client")
	private List<Creditrefuse> creditsref;
	@ManyToMany(mappedBy = "agentr")
	private List<Creditrefuse> creditref;
	  public User(String username, String email, String password) {
		    this.username = username;
		    this.email = email;
		    this.password = password;
		  }
}
