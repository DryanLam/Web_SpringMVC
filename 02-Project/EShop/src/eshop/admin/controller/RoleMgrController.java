package eshop.admin.controller;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import eshop.entity.Role;

@Transactional
@Controller
@RequestMapping("/admin/role")
public class RoleMgrController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		model.addAttribute("role", new Role());
		model.addAttribute("items", getRoles());
		return "admin/role";
	}
	
	List<Role> getRoles(){
		String hql = "FROM Role";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Role> list = query.list();
		return list;
	}

	@RequestMapping("/insert")
	public String insert(ModelMap model,
						 @ModelAttribute("role") Role role) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(role);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công.");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		}
		session.close();
		
		model.addAttribute("items", getRoles());
		return "admin/role";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap model,
					   @PathVariable("id") String id) {
		
		Session session = factory.getCurrentSession();
		Role role = (Role) session.get(Role.class, id);
		
		model.addAttribute("role", role);
		model.addAttribute("items", getRoles());
		return "admin/role";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap model,
						 @RequestParam("id") String id) {
		
		Session session = factory.getCurrentSession();
		Role role = (Role) session.get(Role.class, id);
		session.delete(role);
		
		return "redirect:/admin/role/index.htm";
	}
	
	@RequestMapping("/update")
	public String update(ModelMap model,
						 @ModelAttribute("role") Role role) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(role);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công.");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại!");
		}
		session.close();
		
		model.addAttribute("items", getRoles());
		return "admin/role";
	}
}
