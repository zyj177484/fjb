package com.jeff.fjb.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	private static Logger logger = Logger.getLogger(LoginAndRegisterController.class);

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
	
	@RequestMapping(value = "/uploadPhoto")
	public ModelAndView uploadPhoto(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			mv.setViewName("user/uploadPhoto");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("redirect:/index");
		}
		return mv;
	}

	@RequestMapping(value = "/uploadPhotoCheck", method = RequestMethod.POST)
	public ModelAndView uploadPhotoCheck(@RequestParam(value = "photo", required = true) MultipartFile file,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		String id = session.getAttribute("id").toString();
		if (preCheckResult == null) {
			if (file!=null && file.getSize() < 310 * 1024) {
				try {
					BufferedImage image = ImageIO.read(file.getInputStream());
					if (image == null) {
						mv.addObject("message", "上传的不是图片");
						mv.setViewName("redirect:/user/uploadPhoto");
					} else {
						if (image.getWidth() != 114 && image.getHeight() != 156) {
							mv.addObject("message", "照片长宽不正确。当前照片宽：" + image.getWidth() + "长：" + image.getHeight());
							mv.setViewName("redirect:/user/uploadPhoto");
						} else {
							userService.insertUserPhoto(id, file.getBytes());
							mv.setViewName("redirect:/login");
						}
					}
				} catch (IOException e) {
					mv.addObject("message", "上传的不是图片");
					mv.setViewName("redirect:/user/uploadPhoto");
					return mv;
				}
			} else {
				mv.addObject("message", "文件超过300KB");
				mv.setViewName("redirect:/user/uploadPhoto");
			}
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("redirect:/index");
		}
		return mv;
	}

	@RequestMapping(value = "/registerCheck", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request) {
		//TODO logic check
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String id = request.getParameter("id");
		UserEntity userEntity = userService.getCheckInfo(id);
		if (userEntity == null) {
			String password = request.getParameter("password");
			String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			String mobile = request.getParameter("mobile");
			String zonghang = request.getParameter("zonghang");
			String fenhang = request.getParameter("fenhang");
			String zhihang = request.getParameter("zhihang");
			String fenlichu = request.getParameter("fenlichu");
			String mail = request.getParameter("mail");
			String role = "user";
			userEntity = new UserEntity(id, username, password, session.getId(), role, zonghang, fenhang, zhihang,
					fenlichu, sex, mobile, mail);
			userService.insertUser(userEntity);
			session.setAttribute("id", userEntity.getId());
			session.setAttribute("username", userEntity.getUsername());
			session.setAttribute("password", userEntity.getPassword());
			mv.setViewName("redirect:/uploadPhoto");
		} else {
			mv.addObject("message", "该用户已经存在");
			mv.setViewName("redirect:/register");
		}
		return mv;
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
			mv.setViewName("user/dashboard");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("redirect:/index");
		}
		return mv;
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public ModelAndView loginCheck(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if (id != null && password != null && id.length() != 0 && password.length() != 0) {
			UserEntity userEntity = userService.getCheckInfo(id);
			if (userEntity != null && userEntity.getPassword().equals(password)) {
				userService.updateUserSession(userEntity.getId(), session.getId());
				session.setAttribute("id", userEntity.getId());
				session.setAttribute("username", userEntity.getUsername());
				session.setAttribute("password", userEntity.getPassword());
				session.setAttribute("practice", userEntity.getPractice());
				session.setAttribute("role", userEntity.getRole());
				logger.info(userEntity.getRole() + "用户" + userEntity.getUsername() + "于" + new Date() + "登录");
				if (userEntity.getRole().equals("admin"))
					mv.setViewName("redirect:/admin/login");
				else {
					UserEntity photoEntity = userService.getPhoto(id);
					if (photoEntity==null || photoEntity.getPhoto() == null) 
						mv.setViewName("redirect:/uploadPhoto");
					else 
						mv.setViewName("redirect:/login");
				}
			} else {
				mv.addObject("message", "账号或者密码错误");
				mv.setViewName("redirect:/index");
			}
		} else {
			mv.addObject("message", "未输入账号或者密码");
			mv.setViewName("redirect:/index");
		}
		return mv;
	}

}
