package com.jeff.fjb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jeff.fjb.dal.entity.BankEntity;
import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.BankService;
import com.jeff.fjb.dal.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	private UserService userService = new UserService();
	private BankService bankService = new BankService();

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
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String id = session.getAttribute("id").toString();
			UserEntity userEntity = userService.getUserEntity(id);
			mv.addObject("user", userEntity);
			mv.setViewName("admin/addBank");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("index");
		}
		return mv;
	}
	@RequestMapping(value="/addBankCheck/{bankType}",method = RequestMethod.POST)
	public ModelAndView addBankCheck(HttpServletRequest request, @PathVariable("bankType") String bankType) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			if (id != null && id.length() != 0 &&  name != null && name.length() != 0) {
				String address = request.getParameter("address");
				String contactPeople = request.getParameter("contactPeople");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String bossid = null;
				if (bankType.equals("fenhang")) {
					bossid = request.getParameter("zonghangid");
					BankEntity bankEntity = new BankEntity(id, bossid, name, address, contactPeople, phone, email);
					try {
						bankService.addFenhang(bankEntity);
						mv.addObject("message", "插入分行成功");
					} catch (Exception e) {
						mv.addObject("message", "插入分行失败，该总行编码:"+bossid+"不存在或者分行编码:"+id+"已经存在");
					}
				}
				else if (bankType.equals("zhihang")) {
					bossid = request.getParameter("fenhangid");
					BankEntity bankEntity = new BankEntity(id, bossid, name, address, contactPeople, phone, email);
					try {
						bankService.addZhihang(bankEntity);
						mv.addObject("message", "插入支行成功");
					} catch (Exception e) {
						mv.addObject("message", "插入支行失败，该分行编码:"+bossid+"不存在或者支行编码:"+id+"已经存在");
					}
				} else if (bankType.equals("zonghang")){
					BankEntity bankEntity = new BankEntity(id, bossid, name, address, contactPeople, phone, email);
					try {
						bankService.addZonghang(bankEntity);
						mv.addObject("message", "插入总行成功");
					} catch (Exception e) {
						mv.addObject("message", "插入总行失败，该总行编码"+id+"已经存在");
					}
				} else {
					mv.addObject("message", "addBank中不存在 "+bankType+" 命令");
				}
				mv.setViewName("admin/addBank");
			} else {
				mv.addObject("message", "银行编码或者银行名称未填");
				mv.setViewName("admin/addBank");
			}
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("index");
		}
		return mv;
	}
}
