package eshop.controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import eshop.entity.Product;
import eshop.entity.Supplier;

@Transactional
@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		
		// For Special
		String hql = "FROM Product WHERE special=true";
		Query query = session.createQuery(hql);
		List<Product> prods = query.list();
		
		model.addAttribute("prods", prods);
		
		// For Slideshow
		hql = "FROM Supplier s " +
			  "WHERE SIZE(s.products) >= 3 " +
			  "ORDER BY NewID()";
		query = session.createQuery(hql);
		query.setMaxResults(4);
		List<Supplier> sups = query.list();
		for(Supplier s : sups){
			Hibernate.initialize(s.getProducts());
		}
		model.addAttribute("sups", sups);
		
		return "site/home/index";
	}
	
	@ResponseBody
	@RequestMapping("/set-lang")
	public String setLanguage(
			@RequestParam("lang") String lang) {
		return "";
	}
}
