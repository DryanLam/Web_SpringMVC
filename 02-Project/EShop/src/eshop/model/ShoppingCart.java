package eshop.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import eshop.entity.Product;

@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
@Component("cart")
public class ShoppingCart {
	@Autowired
	SessionFactory factory;
	
	Map<Integer, Product> map = new HashMap<Integer, Product>();
	
	public void add(Integer id) {
		Product p = map.get(id);
		if(p != null){
			p.setQuantity(p.getQuantity() + 1);
		}
		else{
			Session session = factory.openSession();
			p = (Product) session.get(Product.class, id);
			p.setQuantity(1);
			map.put(id, p);
			session.close();
		}
	}
	
	public void remove(Integer id) {
		map.remove(id);
	}
	
	public void clear() {
		map.clear();
	}
	
	public void update(Integer id, Integer newQty) {
		Product p = map.get(id);
		p.setQuantity(newQty);
	}
	
	public double getAmount() {
		double amount = 0;
		for(Product p : map.values()){
			amount += p.getUnitPrice() * p.getQuantity();
		}
		return amount;
	}
	
	public int getCount() {
		int count = 0;
		for(Product p : map.values()){
			count += p.getQuantity();
		}
		return count;
	}
	
	public Collection<Product> getItems() {
		return map.values();
	}
}
