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
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import eshop.entity.Master;
import eshop.entity.Role;
import eshop.entity.RoleAction;

@Transactional
public class AdminInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	SessionFactory factory;
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Master master = (Master) session.getAttribute("master");
		
		String url = request.getRequestURI();
		session.setAttribute("ReturnUrl", url);
		if(master == null){
			response.sendRedirect("/EShop/admin/home/login.htm");
			return false;
		}
		
		int i = url.indexOf("/admin/") + 7;
		int j = url.lastIndexOf(".");
		String action = url.substring(i, j);
		if(action.indexOf("/") < action.lastIndexOf("/")){
			j = action.lastIndexOf("/");
			action = action.substring(0, j);
		}
		
		Session hsession = factory.getCurrentSession();
		String hql = "SELECT COUNT(a) FROM WebAction a WHERE a.name=:aname";
		Query query = hsession.createQuery(hql);
		query.setParameter("aname", action);
		Long count = (Long) query.uniqueResult();
		if(count > 0){
			hql = "SELECT mr.role FROM MasterRole mr WHERE mr.master.id=:mid";
			query = hsession.createQuery(hql);
			query.setParameter("mid", master.getId());
			List<Role> roles = query.list();
			for(Role r : roles){
				for(RoleAction ra : r.getRoleActions()){
					if(ra.getWebAction().getName().equals(action)){
						return true;
					}
				}
			}
			response.sendRedirect("/EShop/admin/home/login.htm");
			return false;
		}
		
		return true;
	}
}
