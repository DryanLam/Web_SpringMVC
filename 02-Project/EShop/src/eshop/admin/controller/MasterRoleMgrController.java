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

@Transactional
@RequestMapping("/admin/master-role")
@Controller
public class MasterRoleMgrController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		return "admin/master-role";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String index(ModelMap model, 
					 	@RequestParam("masterId") String masterId,
					 	@RequestParam("roleId") String[] roleId) {
		
		Session session = factory.getCurrentSession();
		
		// Clear all role of master acc
		String hql = "DELETE MasterRole mr WHERE mr.master.id=:mid";
		Query query = session.createQuery(hql);
		query.setParameter("mid", masterId);
		query.executeUpdate();
		
		// Modify role of master acc
		Master master = (Master) session.get(Master.class, masterId);
		for(String rId : roleId){
			Role role = (Role) session.get(Role.class, rId);
			
			MasterRole mr = new MasterRole();
			mr.setMaster(master);
			mr.setRole(role);
			
			session.save(mr);
		}
		return "admin/master-role";
	}
	
	@ResponseBody
	@RequestMapping("/get-roles")
	public String getRoles(@RequestParam("masterId") String masterId) {
		
		Session session = factory.getCurrentSession();
		String hql = "SELECT mr.role.id " +
					 "FROM MasterRole mr " +
					 "WHERE mr.master.id=:mid";
		Query query = session.createQuery(hql);
		query.setParameter("mid", masterId);
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
	
	@ModelAttribute("masters")
	public List<Master> getMasters(){
		String hql = "FROM Master";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Master> list = query.list();
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
