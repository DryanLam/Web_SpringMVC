package eshop.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Masters")
public class Master {
	@Id
	String id;
	String password;
	String fullName;
	String email;
	String mobile;
	
	@OneToMany(mappedBy="master")
	Collection<MasterRole> masterRoles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Collection<MasterRole> getMasterRoles() {
		return masterRoles;
	}

	public void setMasterRoles(Collection<MasterRole> masterRoles) {
		this.masterRoles = masterRoles;
	}
	
}
