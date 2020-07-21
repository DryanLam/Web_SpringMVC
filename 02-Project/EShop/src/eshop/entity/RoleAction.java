package eshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RoleActions")
public class RoleAction {
	@Id
	@GeneratedValue
	Integer id;
	//String roleId;
	//Integer webActionId;
	
	@ManyToOne
	@JoinColumn(name="roleId")
	Role role;
	
	@ManyToOne
	@JoinColumn(name="webActionId")
	WebAction webAction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public WebAction getWebAction() {
		return webAction;
	}

	public void setWebAction(WebAction webAction) {
		this.webAction = webAction;
	}
	
}
