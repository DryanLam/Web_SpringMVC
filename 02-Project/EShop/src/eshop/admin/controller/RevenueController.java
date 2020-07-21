package eshop.admin.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@RequestMapping("/admin/revenue")
@Controller
public class RevenueController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/by-product")
	public String byProduct(ModelMap model,
						    @RequestParam(value="min", defaultValue="01/01/1900") Date min,
						    @RequestParam(value="max", defaultValue="31/12/2099") Date max) {
		
		String hql = "SELECT d.product.name, " +
				     "SUM(d.unitPrice*d.quantity*(1-d.discount)), " +
				     "SUM(d.quantity), " +
				     "MIN(d.unitPrice), " +
				     "MAX(d.unitPrice), " +
				     "AVG(d.unitPrice) " +
				     "FROM OrderDetail d " +
				     "WHERE d.order.orderDate BETWEEN :min AND :max " +
				     "GROUP BY d.product.name";
		this.addToModel(model, hql, min, max);
		return "admin/revenue";
	}
	
	@RequestMapping("/by-category")
	public String byCategory(ModelMap model,
							 @RequestParam(value="min", defaultValue="01/01/1900") Date min,
							 @RequestParam(value="max", defaultValue="31/12/2099") Date max) {
		
		String hql = "SELECT d.product.category.nameVN, " +
					 "SUM(d.unitPrice*d.quantity*(1-d.discount)), " +
					 "SUM(d.quantity), " +
					 "MIN(d.unitPrice), " +
					 "MAX(d.unitPrice), " +
					 "AVG(d.unitPrice) " +
					 "FROM OrderDetail d " +
					 "WHERE d.order.orderDate BETWEEN :min AND :max " +
					 "GROUP BY d.product.category.nameVN";
		this.addToModel(model, hql, min, max);
		return "admin/revenue";
	}
	
	@RequestMapping("/by-supplier")
	public String bySupplier(ModelMap model,
							 @RequestParam(value="min", defaultValue="01/01/1900") Date min,
							 @RequestParam(value="max", defaultValue="31/12/2099") Date max) {
		
		String hql = "SELECT d.product.supplier.name, " +
					 "SUM(d.unitPrice*d.quantity*(1-d.discount)), " +
					 "SUM(d.quantity), " +
					 "MIN(d.unitPrice), " +
					 "MAX(d.unitPrice), " +
					 "AVG(d.unitPrice) " +
					 "FROM OrderDetail d " +
					 "WHERE d.order.orderDate BETWEEN :min AND :max " +
					 "GROUP BY d.product.supplier.name";
		this.addToModel(model, hql, min, max);
		return "admin/revenue";
	}
	
	@RequestMapping("/by-customer")
	public String byCustomer(ModelMap model,
							 @RequestParam(value="min", defaultValue="01/01/1900") Date min,
							 @RequestParam(value="max", defaultValue="31/12/2099") Date max) {
		
		String hql = "SELECT d.order.customer.id, " +
					 "SUM(d.unitPrice*d.quantity*(1-d.discount)) AS Reve, " +
					 "SUM(d.quantity), " +
					 "MIN(d.unitPrice), " +
					 "MAX(d.unitPrice), " +
					 "AVG(d.unitPrice) " +
					 "FROM OrderDetail d " +
					 "WHERE d.order.orderDate BETWEEN :min AND :max " +
					 "GROUP BY d.order.customer.id " +
					 "ORDER BY Reve DESC";
		this.addToModel(model, hql, min, max);
		return "admin/revenue";
	}
	
	@RequestMapping("/by-year")
	public String byYear(ModelMap model,
						 @RequestParam(value="min", defaultValue="01/01/1900") Date min,
						 @RequestParam(value="max", defaultValue="31/12/2099") Date max) {
		
		String hql = "SELECT Year(d.order.orderDate) AS Year, " +
					 "SUM(d.unitPrice*d.quantity*(1-d.discount)), " +
					 "SUM(d.quantity), " +
					 "MIN(d.unitPrice) ," +
					 "MAX(d.unitPrice), " +
					 "AVG(d.unitPrice) " +
					 "FROM OrderDetail d " +
					 "WHERE d.order.orderDate BETWEEN :min AND :max " +
					 "GROUP BY Year(d.order.orderDate) " +
					 "ORDER BY Year DESC";
		this.addToModel(model, hql, min, max);
		return "admin/revenue";
	}
	
	@RequestMapping("/by-month")
	public String byMonth(ModelMap model,
						  @RequestParam(value="min", defaultValue="01/01/1900") Date min,
						  @RequestParam(value="max", defaultValue="31/12/2099") Date max) {
		
		String hql = "SELECT Month(d.order.orderDate) AS Month, " +
					 "SUM(d.unitPrice*d.quantity*(1-d.discount)), " +
					 "SUM(d.quantity), " +
					 "MIN(d.unitPrice), " +
					 "MAX(d.unitPrice), " +
					 "AVG(d.unitPrice) " +
					 "FROM OrderDetail d " +
					 "WHERE d.order.orderDate BETWEEN :min AND :max " +
					 "GROUP BY Month(d.order.orderDate)" +
					 "ORDER BY Month";
		this.addToModel(model, hql, min, max);
		return "admin/revenue";
	}
	
	@RequestMapping("/by-quarter")
	public String byQuarter(ModelMap model,
						 	@RequestParam(value="min", defaultValue="01/01/1900") Date min,
						 	@RequestParam(value="max", defaultValue="31/12/2099") Date max) {
		
		String hql = "SELECT ceiling(Month(d.order.orderDate)/3.0) AS Quarter, " +
					 "SUM(d.unitPrice*d.quantity*(1-d.discount)), " +
					 "SUM(d.quantity), " +
					 "MIN(d.unitPrice), " +
					 "MAX(d.unitPrice), " +
					 "AVG(d.unitPrice) " +
					 "FROM OrderDetail d " +
					 "WHERE d.order.orderDate BETWEEN :min AND :max " +
					 "GROUP BY ceiling(Month(d.order.orderDate)/3.0) " +
					 "ORDER BY Quarter";
		this.addToModel(model, hql, min, max);
		return "admin/revenue";
	}
	
	void addToModel(ModelMap model, String hql, Date min, Date max){
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("min", min);
		query.setParameter("max", max);
		List<Object[]> list = query.list();
		model.addAttribute("items", list);
	}
}
