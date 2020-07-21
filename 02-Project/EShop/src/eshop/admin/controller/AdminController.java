package eshop.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import eshop.entity.Master;

@Transactional
@Controller
@RequestMapping("/admin/home")
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index() {
		return "admin/home/index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "admin/home/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(ModelMap model,
						@RequestParam("id") String id,
						@RequestParam("password") String pw,
						HttpSession httpSession,
						HttpServletResponse response) {
		
		Session session = factory.getCurrentSession();
		try {
			Master master = (Master) session.get(Master.class, id);
			if(master.getPassword().equals(pw)){
				model.addAttribute("message", "Đăng nhập thành công !");
				httpSession.setAttribute("master", master);
				
				String url = (String) httpSession.getAttribute("ReturnUrl");
				if(url != null){
					response.sendRedirect(url);
				}
			}
			else{
				model.addAttribute("message", "Sai mật khẩu !");
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Sai tên đăng nhập !");
		}
		return "admin/home/login";
	}
	
	@RequestMapping("/logoff")
	public String logoff(HttpSession httpSession) {
		httpSession.removeAttribute("master");
		return "redirect:/admin/home/login.htm";
	}
}
