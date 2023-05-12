
package com.Project.Patient.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="Patientt")
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data

public class Patient  implements UserDetails{


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name", nullable =false)
	
	private String name;
	
	
	@Column(name="email",nullable=false, unique=true)
	private String email;
	

	@Column(name="password", nullable =false)
	private String password;
	
	@Column(name="address", nullable =false)
	private String address;
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name="patient_role",
//	joinColumns=@JoinColumn(name="patient",referencedColumnName="id"),
//	inverseJoinColumns=@JoinColumn(name="role",referencedColumnName="id"))
//	
//	private Set<Role> roles=new HashSet<>();
	
	//for appointment thingy
	
	@javax.persistence.Transient
	private List<Appointment> appointments=new ArrayList<>();

	@javax.persistence.Transient
	private List<Doctor> doctors=new ArrayList<>();
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//
//
//		List<SimpleGrantedAuthority> authorities=this.roles.stream()
//				.map((role) ->new SimpleGrantedAuthority(role.getName()) ).collect(Collectors.toList());
//		return authorities;
//	}
//	
	
	
	

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


  
	
	

}