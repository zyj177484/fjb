package com.jeff.fjb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.UserService;

@Controller
public class LoginAndRegisterController {

	private UserService userService = new UserService();
	
	public String preCheck(HttpSession session) {
		if (session.getAttribute("id") != null && session.getAttribute("password") != null) {
			String id = session.getAttribute("id").toString();
			String password = session.getAttribute("password").toString();
			UserEntity userEntity = userService.getCheckInfo(id);
			if (userEntity.getPassword().equals(password)) {
				if (userEntity.getSessionId().equals(session.getId()))
					return null;
				else
					return "用户从其他处登录";
			} else
				return "密码错误";
		} else
			return "请登录或者注册";
	}
	
	@RequestMapping(value = "/upload.do")
	public ModelAndView register(@RequestParam(value = "photo", required = false) MultipartFile photo) {
		
		return null;
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
			mv.setViewName("/user/hello");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("dashboard");
		}
		return mv;
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public ModelAndView loginCheck(@RequestParam(value="id",required=true) String id, 
			@RequestParam(value="password", required=true) String password, 
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if (id != null && password != null && id.length() != 0 && password.length() != 0) {
			UserEntity userEntity = userService.getUserEntity(id);
			if (userEntity != null && userEntity.getPassword().equals(password)) {
				userService.updateUserSession(userEntity.getId(), session.getId());
				session.setAttribute("id", userEntity.getId());
				session.setAttribute("username", userEntity.getUsername());
				session.setAttribute("password", userEntity.getPassword());
				mv.setViewName("redirect:/login");
			} else {
				mv.addObject("message", "账号或者密码错误");
				mv.setViewName("index");
			}
		} else {
			mv.addObject("message", "未输入账号或者密码");
			mv.setViewName("index");
		}
		return mv;
	}

}
