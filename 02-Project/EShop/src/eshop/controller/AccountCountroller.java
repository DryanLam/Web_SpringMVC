package eshop.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eshop.entity.Customer;

@Transactional
@Controller
@RequestMapping("/account")
public class AccountCountroller {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext application;
	
	@Autowired
	JavaMailSender mailSender;
	
	@RequestMapping("/login")
	public String login() {
		return "site/account/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(ModelMap model,
						@RequestParam("id") String id,
						@RequestParam("password") String pw,
						@RequestParam(value="remember", defaultValue="false") Boolean rm,
						HttpServletResponse response,
						HttpSession httpSession) {
		
		Session session = factory.getCurrentSession();
		try {
			Customer user = (Customer) session.get(Customer.class, id);
			if(user.getActivated() == false){
				model.addAttribute("message", "Tài khoản chưa được kích hoạt!");
			}
			else if(user.getPassword().equals(pw)){
				model.addAttribute("message", "Đăng nhập thành công!");
				
				// Set user-session
				httpSession.setAttribute("user", user);
				
				// Remember me?
				Cookie ckId = new Cookie("uid", id);
				Cookie ckPw = new Cookie("upw", pw);
				
				int expiry = 0;
				if(rm == true){
					expiry = 30*24*60*60;
				}
				ckId.setMaxAge(expiry);
				ckPw.setMaxAge(expiry);
				
				response.addCookie(ckId);
				response.addCookie(ckPw);
				
				// Return previous URL(if having)
				String url = (String) httpSession.getAttribute("ReturnUrl");
				if(url != null){
					response.sendRedirect(url);
				}
			}
			else{
				model.addAttribute("message", "Sai mật khẩu!");
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Sai tên đăng nhập!");
		}
		return "site/account/login";
	}

	@RequestMapping("/logoff")
	public String logoff(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/home/index.htm";
	}

	@RequestMapping("/register")
	public String register(ModelMap model) {
		
		Customer user = new Customer();
		user.setActivated(false);
		model.addAttribute("user", user);
		return "site/account/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(ModelMap model,
						   @ModelAttribute("user") Customer customer,
						   @RequestParam("filePhoto") MultipartFile file,
						   HttpServletRequest request) {

		String path = application.getRealPath("/images/customers/" + file.getOriginalFilename());
		try {
			file.transferTo(new File(path));	
			customer.setPhoto(file.getOriginalFilename());
		} 
		catch (Exception e) {
			customer.setPhoto("user.png");
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(customer);
			t.commit();
			model.addAttribute("message", "Đăng ký thành công!");
			
			try {
				MimeMessage mail = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail);
				String from = "songlong2k@gmail.com";
				String fullname = "Shopping Online Viet Nam";
				String to = customer.getEmail();
				String subject = "Welcome mail";
				String host = request.getServerName()+":"+request.getServerPort();
				String text = "Click <a href='http://"+host+"/EShop/account/activate/"+customer.getId()+".htm'>Activate</a> để kích hoạt tài khoản";
				helper.setFrom(from, fullname);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(text, true);
				helper.setReplyTo(from);
				mailSender.send(mail);
				model.addAttribute("message", "Vui lòng check email để kích hoạt tài khoản!");
			} 
			catch (Exception e) {
				model.addAttribute("message", "Không gửi được email!");
			}
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Đăng ký thất bại!");
		}
		
		return "site/account/register";
	}

	@RequestMapping("/activate/{id}")
	public String activate(@PathVariable("id") String id) {
		
		Session session = factory.getCurrentSession();
		Customer user = (Customer) session.get(Customer.class, id);
		user.setActivated(true);
		return "redirect:/account/login.htm";
	}

	@RequestMapping("/forgot")
	public String forgot() {
		return "site/account/forgot";
	}
	
	@RequestMapping(value="/forgot", method=RequestMethod.POST)
	public String forgot(ModelMap model,
						 @RequestParam("id") String id,
						 @RequestParam("email") String email) {
			
		Session session = factory.getCurrentSession();
		try {
			Customer user = (Customer) session.get(Customer.class, id);
			if(user.getEmail().equals(email)){
				try {
					MimeMessage mail = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(mail);
					String from = "songlong2k@gmail.com";
					String fullname = "E-Shop Viet Nam";
					String to = user.getEmail();
					String subject = "Forgot password";
					String text = "Mat khau cua ban la: " + user.getPassword();
					helper.setFrom(from, fullname);
					helper.setTo(to);
					helper.setSubject(subject);
					helper.setText(text, true);
					helper.setReplyTo(from);
					mailSender.send(mail);
					model.addAttribute("message", "Vui lòng kiểm tra email để lấy lại mật khẩu!");
				} 
				catch (Exception e) {
					model.addAttribute("message", "Không gửi được email!");
				}
			}
			else{
				model.addAttribute("message", "Địa chỉ email không đúng!");
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Tên tài khoản không đúng!");
		}
		return "site/account/forgot";
	}

	@RequestMapping("/change")
	public String change() {
		return "site/account/change";
	}
	
	@RequestMapping(value="/change", method=RequestMethod.POST)
	public String change(ModelMap model,
						 @RequestParam("id") String id,
						 @RequestParam("password") String pw,
						 @RequestParam("password1") String pw1,
						 @RequestParam("password2") String pw2) {
		
		if(pw1.equals(pw2)){
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				Customer user = (Customer) session.get(Customer.class, id);
				if(user.getPassword().equals(pw)){
					user.setPassword(pw1);
					session.update(user);
					model.addAttribute("message", "Đã đổi mật khẩu thành công!");
				}
				else{
					model.addAttribute("message", "Sai mật khẩu cũ!");
				}
				t.commit();
			} 
			catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Tên đăng nhập không đúng!");
			}	
		}
		else {
			model.addAttribute("message", "Xác nhận mật khẩu mới không đúng!");
		}
		return "site/account/change";
	}

	@RequestMapping("/edit")
	public String edit(ModelMap model, 
					   HttpSession httpSession) {
		
		Customer user = (Customer) httpSession.getAttribute("user");
		model.addAttribute("user", user);
		return "site/account/edit";
	}
	
	@RequestMapping(value="/update")
	public String update(ModelMap model,
						 @ModelAttribute("user") Customer customer,
						 @RequestParam("filePhoto") MultipartFile file,
						 HttpServletRequest request) {
		
		String path = application.getRealPath("/images/customers/" + file.getOriginalFilename());
		try {
			file.transferTo(new File(path));	
			customer.setPhoto(file.getOriginalFilename());
		} 
		catch (Exception e) {}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(customer);
			request.getSession().setAttribute("user", customer);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công!");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại!");
		}
		return "site/account/edit";
	}
}
