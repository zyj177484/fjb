package com.jeff.fjb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jeff.fjb.dal.entity.OrderEntity;
import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.OrderService;
import com.jeff.fjb.dal.service.UserService;
import com.jeff.fjb.util.CheckOrder;
import com.jeff.fjb.util.MagicNum;

@Controller
public class PayController {
	private UserService userService = new UserService();
	private OrderService orderService = new OrderService();
	
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
	
	@RequestMapping(value = "/user/pay")
	public ModelAndView pay(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String type = request.getParameter("type");
			if (type != null) {
				if (type.equals("practice")) {
					long time = System.currentTimeMillis()/1000;
					String orderId = session.getAttribute("id").toString() + time;
					
					JsonObject contentObject = new JsonObject();
					contentObject.addProperty("type", "practice");
					contentObject.addProperty("charge", MagicNum.PRACTICE_CHARGE);
					contentObject.addProperty("userId", session.getAttribute("id").toString());
					orderService.addOrder(new OrderEntity(orderId, contentObject.toString(), "false", MagicNum.PRACTICE_CHARGE, time, "open"));
					CheckOrder checkOrder = new CheckOrder(orderId);
					checkOrder.start();
					mv.addObject("orderId", orderId);
					mv.addObject("charge", MagicNum.PRACTICE_CHARGE);
					mv.addObject("attention", "确认付费" + MagicNum.PRACTICE_CHARGE + "元进行练习。<br>订单" + MagicNum.ORDER_WAIT_TIME/60/1000 + "分钟后将失效。");
					mv.setViewName("user/pay");
				}
				return mv;
			} else {
				mv.addObject("message", "请输入付款内容");
				mv.setViewName("redirect:/login");
				return mv;				
			}
			
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("redirect:/index");
			return mv;
		}
	}
	
	@RequestMapping(value = "/payCheck", method = RequestMethod.POST)
	public ModelAndView payCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String orderId = request.getParameter("orderId");
			if (orderId != null && orderId.length()!=0) {
				OrderEntity orderEntity = orderService.getOrder(orderId);
				if (orderEntity != null) {
					orderService.payOrder(orderId, "close", "true");
					session.setAttribute("practice", 1);
					JsonObject orderContent = new JsonParser().parse(orderEntity.getContent()).getAsJsonObject();
					UserService userService = new UserService();
					String userId = orderContent.get("userId").getAsString();
					int practice = 1;
					userService.updateUserPractice(userId, practice);
					mv.addObject("message", "练习题付款成功");
					mv.setViewName("redirect:/login");
					return mv;
				} else {
					mv.addObject("message", "无效订单号");
					mv.setViewName("redirect:/login");
					return mv;
				}
			} else {
				mv.addObject("message", "无订单号");
				mv.setViewName("redirect:/login");
				return mv;
			}
		} else {
			mv.addObject("message", preCheckResult);
			mv.setViewName("redirect:/index");
			return mv;
		}
	}
}
