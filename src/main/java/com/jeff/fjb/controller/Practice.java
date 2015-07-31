package com.jeff.fjb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.UserService;

@Controller
public class Practice {

	private UserService userService = new UserService();

	public ModelAndView preCheck(HttpSession session) {
		if (session.getAttribute("id") != null && session.getAttribute("password") != null
				&& session.getAttribute("practice") != null) {
			String id = session.getAttribute("id").toString();
			String password = session.getAttribute("password").toString();
			String practice = session.getAttribute("practice").toString();
			if (practice.equals("1")) {
				UserEntity userEntity = userService.getCheckInfo(id);
				if (userEntity.getPassword().equals(password)) {
					if (userEntity.getSessionId().equals(session.getId()))
						return null;
					else {
						ModelAndView mv = new ModelAndView();
						mv.addObject("message", "用户从其他处登录");
						mv.setViewName("index");
						return mv;
					}
				} else{
					ModelAndView mv = new ModelAndView();
					mv.addObject("message", "密码错误");
					mv.setViewName("index");
					return mv;
				}
			} else {
				ModelAndView mv = new ModelAndView();
				mv.addObject("message", "无权限进行练习，请进行付费。");
				mv.setViewName("user/dashboard");
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("message", "请登录或者注册");
			mv.setViewName("index");
			return mv;
		}
	}
	
	@RequestMapping(value = "/practice")
	public ModelAndView login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("test/practice");
			return mv;
		} else 
			return preCheckResult;
	}
}
