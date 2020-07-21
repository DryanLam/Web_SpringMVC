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
import eshop.entity.Master;

@Transactional
@Controller
@RequestMapping("/admin/master")
public class MasterMgrController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		model.addAttribute("master", new Master());
		model.addAttribute("items", getMasters());
		return "admin/master";
	}
	
	List<Master> getMasters(){
		String hql = "FROM Master";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Master> list = query.list();
		return list;
	}

	@RequestMapping("/insert")
	public String insert(ModelMap model,
						 @ModelAttribute("master") Master master) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(master);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công.");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		}
		session.close();
		
		model.addAttribute("items", getMasters());
		return "admin/master";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap model,
					   @PathVariable("id") String id) {
		
		Session session = factory.getCurrentSession();
		Master master = (Master) session.get(Master.class, id);
		
		model.addAttribute("master", master);
		model.addAttribute("items", getMasters());
		return "admin/master";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap model,
						 @RequestParam("id") String id) {
		
		Session session = factory.getCurrentSession();
		Master master = (Master) session.get(Master.class, id);
		session.delete(master);
		
		return "redirect:/admin/master/index.htm";
	}
	
	@RequestMapping("/update")
	public String update(ModelMap model,
						 @ModelAttribute("master") Master master) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(master);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công.");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại!");
		}
		session.close();
		
		model.addAttribute("items", getMasters());
		return "admin/master";
	}
}
