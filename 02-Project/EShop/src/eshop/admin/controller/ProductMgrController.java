package eshop.admin.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

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
import org.springframework.web.multipart.MultipartFile;

import eshop.entity.Category;
import eshop.entity.Product;
import eshop.entity.Supplier;

@Transactional
@Controller
@RequestMapping("/admin/product")
public class ProductMgrController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext app;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		model.addAttribute("product", new Product());
		model.addAttribute("items", getProducts());
		return "admin/product";
	}
	
	List<Product> getProducts(){
		String hql = "FROM Product";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		return list;
	}

	@RequestMapping("/insert")
	public String insert(ModelMap model,
						 @ModelAttribute("product") Product product,
						 @RequestParam("fileImage") MultipartFile file) {
		
		try {
			product.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/images/products/" + product.getImage());
			file.transferTo(new File(path));
		} 
		catch (Exception e) {
			product.setImage("Product.png");
		}

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			product.setViews(0);
			session.save(product);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công.");
		} 
		catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		}
		session.close();
		
		model.addAttribute("items", getProducts());
		return "admin/product";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap model,
					   @PathVariable("id") Integer id) {
		
		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		
		model.addAttribute("product", product);
		model.addAttribute("items", getProducts());
		return "admin/product";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap model,
						 @RequestParam("id") Integer id) {
		
		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		session.delete(product);
		
		return "redirect:/admin/product/index.htm";
	}
	
	@RequestMapping("/update")
	public String update(ModelMap model,
						 @ModelAttribute("product") Product product,
						 @RequestParam("fileImage") MultipartFile file) {
		
		try {
			String path = app.getRealPath("/images/products/" + file.getOriginalFilename());
			file.transferTo(new File(path));
			product.setImage(file.getOriginalFilename());
		} 
		catch (Exception e) {}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(product);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công.");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại!");
		}
		session.close();
		
		model.addAttribute("items", getProducts());
		return "admin/product";
	}
	
	/**
	 * Inject data to Combo-box of Category 
	 */
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		String hql = "FROM Category";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}
	
	/**
	 * Inject data to Combo-box of Category Supplier 
	 */
	@ModelAttribute("suppliers")
	public List<Supplier> getSuppliers(){
		String hql = "FROM Supplier";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Supplier> list = query.list();
		return list;
	}
	
	@RequestMapping("/loadpage")
	public String loadPage(ModelMap model,
						   @RequestParam(value="pageNo", defaultValue="0") Integer pageNo,
						   @RequestParam(value="pageSize", defaultValue="10") Integer pageSize){
		
		String hql = "FROM Product";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(pageNo*pageSize);
		query.setMaxResults(pageSize);
		List<Product> list = query.list();
		
		model.addAttribute("items", list);
		return "ajax/gridview";
	}
	
	@ModelAttribute("pageCount")
	public Long getPageCount(){
		String hql = "SELECT COUNT(p) FROM Product p";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		Long rowCount = (Long) query.uniqueResult();
		Long pageCount = (long) Math.ceil(rowCount/10.0);
		return pageCount;
	}
}
