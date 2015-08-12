package com.jeff.fjb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	private UserService userService = new UserService();

	public String preCheck(HttpSession session) {
		if (session.getAttribute("id") != null && session.getAttribute("password") != null
				&& session.getAttribute("role") != null) {
			String id = session.getAttribute("id").toString();
			String password = session.getAttribute("password").toString();
			String role = session.getAttribute("role").toString();
			if (role.equals("admin")) {
				UserEntity userEntity = userService.getCheckInfo(id);
				if (userEntity.getPassword().equals(password)) {
					if (userEntity.getSessionId().equals(session.getId()))
						return null;
					else
						return "用户从其他处登录";
				} else
					return "密码错误";
			} else {
				return "非管理员，无法操作";
			}
		} else
			return "请登录或者注册";
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String id = session.getAttribute("id").toString();
			UserEntity userEntity = userService.getUserEntity(id);
			mv.addObject("message", "欢迎登录:" + userEntity.getUsername());
			mv.addObject("user", userEntity);
			mv.setViewName("admin/dashboard");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("index");
		}
		return mv;
	}
	
	@RequestMapping(value = "/addBank")
	public ModelAndView addBank(HttpServletRequest request) {
		
		return null;
	}

}
