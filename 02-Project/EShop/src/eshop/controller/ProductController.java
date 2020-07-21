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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import eshop.entity.Product;

@Transactional
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("detail/{id}")
	public String detail(ModelMap model,
						 @PathVariable("id") Integer id) {
		
		Session session = factory.getCurrentSession();
		Product p = (Product) session.get(Product.class, id);
		
		// Nạp kèm các sản phẩm cùng loại, cùng hãng
		Hibernate.initialize(p.getCategory().getProducts());
		Hibernate.initialize(p.getSupplier().getProducts());
		
		model.addAttribute("prod", p);
		
		// Tăng số lượt xem
		p.setViews(p.getViews() + 1);
		
		return "site/product/detail";
	}

	@RequestMapping("/list-by-cate/{id}")
	public String listByCategory(ModelMap model,
			   					 @PathVariable("id") Integer cateId) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.category.id=:cId";
		Query query = session.createQuery(hql);
		query.setParameter("cId", cateId);
		List<Product> list = query.list();

		model.addAttribute("prods", list);

		return "site/product/list";
	}

	@RequestMapping("/list-by-supp/{id}")
	public String listBySupplier(ModelMap model,
								 @PathVariable("id") String suppId) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.supplier.id=:sId";
		Query query = session.createQuery(hql);
		query.setParameter("sId", suppId);
		List<Product> list = query.list();

		model.addAttribute("prods", list);

		return "site/product/list";
	}

	@RequestMapping("/list-by-spec/{id}")
	public String listBySupplier(ModelMap model,
								 @PathVariable("id") Integer specId) {
		
		Session session = factory.getCurrentSession();

		String hql = "FROM Product p";
		Query query = session.createQuery(hql);
		switch (specId) {
		case 0:
			hql = "FROM Product p ORDER BY SIZE(p.orderDetails) DESC";
			query = session.createQuery(hql);
			query.setMaxResults(9);
			break;
		case 1:
			hql = "FROM Product p WHERE p.latest=true";
			query = session.createQuery(hql);
			break;
		case 2:
			hql = "FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC";
			query = session.createQuery(hql);
			query.setMaxResults(9);
			break;
		case 3:
			hql = "FROM Product p WHERE p.special=true";
			query = session.createQuery(hql);
			break;
		case 4:
			hql = "FROM Product p WHERE p.views > 0 ORDER BY p.views DESC";
			query = session.createQuery(hql);
			query.setMaxResults(9);
			break;
		default:
			break;
		}

		List<Product> list = query.list();

		model.addAttribute("prods", list);

		return "site/product/list";
	}
	
	@RequestMapping("/list-by-keywords")
	public String listByKeywords(ModelMap model,
								 @RequestParam("keywords") String keywords) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.name LIKE :keywords";
		Query query = session.createQuery(hql);
		query.setParameter("keywords", "%" + keywords + "%");
		List<Product> list = query.list();

		model.addAttribute("prods", list);

		return "site/product/list";
	}
}
