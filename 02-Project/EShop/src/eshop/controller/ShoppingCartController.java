package eshop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import eshop.entity.Product;
import eshop.model.ShoppingCart;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	ShoppingCart cart;
	
	@ResponseBody
	@RequestMapping("/add")
	public String add(@RequestParam("id") Integer id) {
		cart.add(id);
		
		return getJson();
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") Integer id) {
		cart.remove(id);
		
		return getJson();
	}
	
	@RequestMapping("/clear")
	public String clear() {
		cart.clear();
		return "site/cart/index";
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request) {
		for(Product p : cart.getItems()){
			String name = p.getId().toString();
			String value = request.getParameter(name);
			int newQuantity = Integer.parseInt(value);
			cart.update(p.getId(), newQuantity);
		}
		return "site/cart/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "site/cart/index";
	}
	
	String getJson(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", cart.getCount());
		map.put("amount", cart.getAmount());
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String text = mapper.writeValueAsString(map);
			return text;	
		} 
		catch (Exception e) {
			return "Lỗi json";
		}
	}
}
