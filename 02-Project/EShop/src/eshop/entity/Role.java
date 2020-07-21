package eshop.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Roles")
public class Role {
	@Id
	String id;
	String name;
	
	@OneToMany(mappedBy="role")
	Collection<MasterRole> masterRoles;
	
	@OneToMany(mappedBy="role")
	Collection<RoleAction> roleActions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<MasterRole> getMasterRoles() {
		return masterRoles;
	}

	public void setMasterRoles(Collection<MasterRole> masterRoles) {
		this.masterRoles = masterRoles;
	}

	public Collection<RoleAction> getRoleActions() {
		return roleActions;
	}

	public void setRoleActions(Collection<RoleAction> roleActions) {
		this.roleActions = roleActions;
	}
	
}
