package eshop.admin.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("/admin/inventory")
public class InventoryController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/by-category")
	public String byCategory(ModelMap model) {
		
		Session session = factory.getCurrentSession();
		String hql = "SELECT p.category.nameVN, " +
				     "SUM(p.unitPrice*p.quantity), " +
				     "SUM(p.quantity), " +
				     "MIN(p.unitPrice), " +
				     "MAX(p.unitPrice), " +
				     "AVG(p.unitPrice) " +
				     "FROM Product p " +
				     "GROUP BY p.category.nameVN ";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("title", "INVENTORY BY CATEGORY");
		model.addAttribute("items", list);
		return "admin/inventory";
	}
	
	@RequestMapping("/by-supplier")
	public String bySupplier(ModelMap model) {
		
		Session session = factory.getCurrentSession();
		String hql = "SELECT p.supplier.name, " +
				     "SUM(p.unitPrice*p.quantity), " +
				     "SUM(p.quantity), " +
				     "MIN(p.unitPrice), " +
				     "MAX(p.unitPrice), " +
				     "AVG(p.unitPrice) " +
				     "FROM Product p " +
				     "GROUP BY p.supplier.name";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		model.addAttribute("title", "INVENTORY BY SUPPLIER");
		model.addAttribute("items", list);
		return "admin/inventory";
	}
}
