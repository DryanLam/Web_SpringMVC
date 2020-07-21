package eshop.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import eshop.entity.Category;
import eshop.entity.Customer;
import eshop.entity.Supplier;

@Transactional
public class AuthorizeInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	SessionFactory factory;
	@Override
	public boolean preHandle(HttpServletRequest request,
					     	 HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		if(user == null){
			String url = request.getRequestURI();
			session.setAttribute("ReturnUrl", url);
			response.sendRedirect("/EShop/account/login.htm");
			return false;
		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
		if(modelAndView != null){
			Session session = factory.getCurrentSession();
			String hql = "FROM Category";
			Query query = session.createQuery(hql);
			List<Category> list = query.list();
			
			String hql2 = "FROM Supplier";
			Query query2 = session.createQuery(hql2);
			List<Supplier> list2 = query2.list();
			
			modelAndView.addObject("cates", list);
			modelAndView.addObject("supps", list2);
		}
	}
}
