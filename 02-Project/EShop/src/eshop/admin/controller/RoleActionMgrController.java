package eshop.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import eshop.entity.Master;
import eshop.entity.MasterRole;
import eshop.entity.Role;
import eshop.entity.RoleAction;
import eshop.entity.WebAction;

@Transactional
@RequestMapping("/admin/role-action")
@Controller
public class RoleActionMgrController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		return "admin/role-action";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String index(ModelMap model, 
					 	@RequestParam("roleId") String roleId,
					 	@RequestParam("actionId") Integer[] actionId) {
		
		Session session = factory.getCurrentSession();
		
		// Remove all action of role
		String hql = "DELETE RoleAction ra WHERE ra.role.id=:rid";
		Query query = session.createQuery(hql);
		query.setParameter("rid", roleId);
		query.executeUpdate();
		
		// Modify actions of role
		Role role = (Role) session.get(Role.class, roleId);
		for(Integer aId : actionId){
			WebAction action = (WebAction) session.get(WebAction.class, aId);
			
			RoleAction ra = new RoleAction();
			ra.setRole(role);
			ra.setWebAction(action);
			
			session.save(ra);
		}
		return "admin/role-action";
	}
	
	@ResponseBody
	@RequestMapping("/get-actions")
	public String getRoles(@RequestParam("roleId") String roleId) {
		
		Session session = factory.getCurrentSession();
		String hql = "SELECT ra.webAction.id " +
					 "FROM RoleAction ra " +
					 "WHERE ra.role.id=:rid ";
		Query query = session.createQuery(hql);
		query.setParameter("rid", roleId);
		List<String> list = query.list();
		
		return getJson(list);
	}
	
	@ModelAttribute("roles")
	public List<Role> getRoles(){
		String hql = "FROM Role";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Role> list = query.list();
		return list;
	}
	
	@ModelAttribute("actions")
	public List<WebAction> getActions(){
		String hql = "FROM WebAction";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<WebAction> list = query.list();
		return list;
	}

	String getJson(List<String> list){
		try {
			ObjectMapper mapper = new ObjectMapper();
			String text = mapper.writeValueAsString(list);
			return text;	
		} 
		catch (Exception e) {
			return "Lỗi JSON";
		}
	}
}
