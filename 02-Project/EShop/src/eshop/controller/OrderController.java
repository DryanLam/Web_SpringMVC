package eshop.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
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

import eshop.entity.Customer;
import eshop.entity.Order;
import eshop.entity.OrderDetail;
import eshop.entity.Product;
import eshop.model.ShoppingCart;

@Transactional
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ShoppingCart cart;
	
	@RequestMapping("/checkout")
	public String checkout(ModelMap model, 
						   HttpSession httpSession) {
		
		Customer user = (Customer) httpSession.getAttribute("user");
		
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setRequireDate(new Date());
		order.setCustomer(user);
		order.setAmount(cart.getAmount());
		order.setReceiver(user.getFullname());
		
		model.addAttribute("order", order);
		return "site/order/checkout";
	}

	@RequestMapping("/purchase")
	public String purchase(ModelMap model, 
						   @ModelAttribute("order") Order order,
			               HttpSession httpSession) {
		
		Customer user = (Customer) httpSession.getAttribute("user");
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(order);
			for(Product p: cart.getItems()){
				OrderDetail d = new OrderDetail();
				d.setDiscount(p.getDiscount());
				d.setOrder(order);
				d.setProduct(p);
				d.setQuantity(p.getQuantity());
				d.setUnitPrice(p.getUnitPrice());
				
				session.save(d);
			}
			t.commit();
			model.addAttribute("message", "Đặt hàng thành công.");
			cart.clear();
			return "redirect:/order/list.htm";
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Đặt hàng thất bại!");
			return "site/order/checkout";
		}
		finally{
			session.close();
		}
	}

	@RequestMapping("/list")
	public String list(ModelMap model,
					   HttpSession httpSession) {
		
		Customer user = (Customer) httpSession.getAttribute("user");
		
		String hql = "FROM Order WHERE customer.id=:cid ORDER BY orderDate DESC";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("cid", user.getId());
		List<Order> list = query.list();
		
		model.addAttribute("orders", list);
		return "site/order/list";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(ModelMap model,
						 @PathVariable("id") Integer id) {
		
		Session session = factory.getCurrentSession();
		
		Order order = (Order) session.get(Order.class, id);
		Hibernate.initialize(order.getOrderDetails());
		
		model.addAttribute("order", order);
		return "site/order/detail";
	}
	
	@RequestMapping("/products")
	public String products(ModelMap model,
						   HttpSession httpSession) {
		
		Customer user = (Customer) httpSession.getAttribute("user");
		
		String hql = "SELECT DISTINCT d.product" +
					 "FROM OrderDetail d" +
					 "WHERE d.order.customer.id=:cid";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("cid", user.getId());
		List<Product> list = query.list();
		
		model.addAttribute("prods", list);
		return "site/product/list";
	}
}
