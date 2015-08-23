package com.jeff.fjb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jeff.fjb.dal.entity.BankEntity;
import com.jeff.fjb.dal.entity.ExamineDistinctEntity;
import com.jeff.fjb.dal.entity.ExamineRoomEntity;
import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.BankService;
import com.jeff.fjb.dal.service.ExamineRoomService;
import com.jeff.fjb.dal.service.UserService;

@Controller
public class AdminController {
	private UserService userService = new UserService();
	private BankService bankService = new BankService();
	private ExamineRoomService examineRoomService = new ExamineRoomService();
	
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

	@RequestMapping(value = "/admin/login")
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
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	@RequestMapping(value = "/admin/addExaminationRoom")
	public ModelAndView addExaminationRoom(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			List<ExamineDistinctEntity> entities = examineRoomService.getAllExamineDistinct();
			String message = request.getParameter("message");
			if (message!=null && message.length() !=0)
				mv.addObject("message", message);
			mv.addObject("distinctList", entities);
			mv.setViewName("admin/addExaminationRoom");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	@RequestMapping(value = "/admin/addBank")
	public ModelAndView addBank(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String message = request.getParameter("message");
			if (message!=null && message.length() !=0)
				mv.addObject("message", message);
			mv.setViewName("admin/addBank");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	
	@RequestMapping(value="/admin/addExamineRoomCheck/{roomType}",method = RequestMethod.POST)
	public ModelAndView addExamineRoomCheck(HttpServletRequest request, @PathVariable("roomType") String roomType) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			if (roomType.equals("distinct")) {
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				if (name != null && address != null && name.length() != 0 && address.length() != 0) {
					ExamineDistinctEntity entity = new ExamineDistinctEntity(name, address);
					try {
						examineRoomService.insertExamineDistinct(entity);
						mv.addObject("message", "插入考区:"+name+"成功");
					} catch (Exception e) {
						mv.addObject("message", "插入考区失败，该考区名字:"+name+"已经存在");
					}
				} else 
					mv.addObject("message", "请输入考区和地址");
			} else if (roomType.equals("room")) {
				String distinct = request.getParameter("distinct");
				String name = request.getParameter("name");
				int num = -1;
				try {
					num = Integer.valueOf(request.getParameter("num"));
				} catch (Exception e) {
					mv.addObject("message", "人数请输入大于0的数字");
				}
				if (distinct != null && name != null && distinct.length() != 0 && name.length() != 0 && num > 0) {
					ExamineRoomEntity entity = new ExamineRoomEntity(distinct, name, num);
					try {
						examineRoomService.insertExamineRoom(entity);
						mv.addObject("message", "插入考场:"+name+"成功");
					} catch (Exception e) {
						mv.addObject("message", "插入考区失败，该考区名字:"+distinct+"不存在，或者该考场:" +name + "已经存在");
					}
				} else 
					mv.addObject("message", "请输入考区，考场，且人数大于0");
			} else 
				mv.addObject("message", "addExaminationRoom中不存在 "+roomType+" 命令");
			mv.setView(new RedirectView("/admin/addExaminationRoom", true));
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/addBankCheck/{bankType}",method = RequestMethod.POST)
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
				} else 
					mv.addObject("message", "addBank中不存在 "+bankType+" 命令");
			} else 
				mv.addObject("message", "银行编码或者银行名称未填");
			mv.setView(new RedirectView("/admin/addBank", true));
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
}
