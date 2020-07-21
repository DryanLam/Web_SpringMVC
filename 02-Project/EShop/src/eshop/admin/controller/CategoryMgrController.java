package eshop.admin.controller;

import java.util.List;
import javax.validation.Valid;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eshop.entity.Category;

@Transactional
@Controller
@RequestMapping("/admin/category")
public class CategoryMgrController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		model.addAttribute("category", new Category());
		model.addAttribute("items", getCategories());
		return "admin/category";
	}
	
	List<Category> getCategories(){
		String hql = "FROM Category";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}

	@RequestMapping("/insert")
	public String insert(ModelMap model,
						 @ModelAttribute("category") @Valid Category category,
						 BindingResult errors) {
		
		if(errors.hasErrors()){
			model.addAttribute("message", "Vui lòng sửa các lỗi sau:");
		}
		else{
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(category);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công.");
			} 
			catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại!");
			}
			session.close();
			
			model.addAttribute("items", getCategories());	
		}
		return "admin/category";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap model,
					   @PathVariable("id") Integer id) {
		
		Session session = factory.getCurrentSession();
		Category category = (Category) session.get(Category.class, id);
		
		model.addAttribute("category", category);
		model.addAttribute("items", getCategories());
		return "admin/category";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap model,
						 @RequestParam("id") Integer id) {
		
		Session session = factory.getCurrentSession();
		Category category = (Category) session.get(Category.class, id);
		session.delete(category);
		
		return "redirect:/admin/category/index.htm";
	}
	
	@RequestMapping("/update")
	public String update(ModelMap model,
						 @ModelAttribute("category") Category category) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(category);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công.");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại!");
		}
		session.close();
		
		model.addAttribute("items", getCategories());
		return "admin/category";
	}
}
