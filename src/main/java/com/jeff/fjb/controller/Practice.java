package com.jeff.fjb.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jeff.fjb.dal.entity.PracticeEntity;
import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.PracticeService;
import com.jeff.fjb.dal.service.UserService;
import com.jeff.fjb.util.MagicNum;

@Controller
public class Practice {

	private UserService userService = new UserService();
	private PracticeService practiceService = new PracticeService();

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
				} else {
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

	public JsonArray genPractice() {
		Random random = new Random();
		JsonArray result = new JsonArray();
		for (int type = 1; type <= 4; type++) {
			List<Integer> ids = practiceService.getIDByType(type);
			if (ids.size() >= MagicNum.TYPE_MAP_QUESTION_NUM[type]) {
				for (int no = 0; no < MagicNum.TYPE_MAP_QUESTION_NUM[type]; no++) {
					int index = random.nextInt(ids.size() - no);
					int id = ids.get(index);
					JsonObject question = new JsonObject();
					question.addProperty("type", type);
					question.addProperty("id", id);
					result.add(question);
					ids.set(index, ids.get(ids.size() - no));
				}
			} else
				return null;
		}
		return result;
	}

	@RequestMapping(value = "/practice")
	public ModelAndView login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			JsonArray questionArray = genPractice();
			session.setAttribute("questionArray", questionArray.toString());
			ModelAndView mv = new ModelAndView();
			mv.setViewName("test/practice");
			return mv;
		} else
			return preCheckResult;
	}
	
	@RequestMapping(value = "/getQuestionPhoto")
	@ResponseBody
	public void showImage(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/gif");
		int id = Integer.valueOf(request.getParameter("id"));
		int type = Integer.valueOf(request.getParameter("type"));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			byte[] photo = practiceService.getPhoto(id, type).getPhoto();
			os.write(photo);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os!=null)
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@RequestMapping(value = "/getQuestion")
	@ResponseBody
	public String getQuestion(HttpServletRequest request) {
		ModelAndView preCheckResult = preCheck(request.getSession());
		HttpSession session = request.getSession();
		if (preCheckResult == null) {
			if (request.getParameter("no") != null) {
				try {
					int no = Integer.valueOf(request.getParameter("no"));
					if (session.getAttribute("questionArray")!=null) {
						JsonArray questionArray = new JsonParser().parse(session.getAttribute("questionArray").toString()).getAsJsonArray();
						int id = questionArray.get(no-1).getAsJsonObject().get("id").getAsInt();
						int type = questionArray.get(no-1).getAsJsonObject().get("type").getAsInt();
						PracticeEntity practiceEntity = practiceService.getPracticeEntity(id, type);
						return new Gson().toJson(practiceEntity);
					} else {
						JsonObject object = new JsonObject();
						object.addProperty("type", "0");
						object.addProperty("message", "请点击开始练习");
						object.addProperty("view", "login");
						return object.toString();
					}
				} catch (Exception e) {
					JsonObject object = new JsonObject();
					object.addProperty("type", "0");
					object.addProperty("message", "输入题号不存在");
					object.addProperty("view", "login");
					return object.toString();
				}
			} else if (request.getParameter("id") != null && request.getParameter("type") != null) {
				try {
					int id = Integer.valueOf(request.getParameter("id"));
					int type = Integer.valueOf(request.getParameter("type"));
					PracticeEntity practiceEntity = practiceService.getPracticeEntity(id, type);
					if (practiceEntity != null)
						return new Gson().toJson(practiceEntity);
					else {
						JsonObject object = new JsonObject();
						object.addProperty("type", "0");
						object.addProperty("message", "该题不存在");
						object.addProperty("view", "login");
						return object.toString();
					}
				} catch (Exception e) {
					JsonObject object = new JsonObject();
					object.addProperty("type", "0");
					object.addProperty("message", "id或者type格式不对");
					object.addProperty("view", "login");
					return object.toString();
				}
			} else {
				JsonObject object = new JsonObject();
				object.addProperty("type", "0");
				object.addProperty("message", "请输入题号");
				object.addProperty("view", "login");
				return object.toString();
			}
		} else {
			JsonObject object = new JsonObject();
			object.addProperty("type", "0");
			object.addProperty("message", preCheckResult.getModel().get("message").toString());
			object.addProperty("view", preCheckResult.getViewName());
			return object.toString();
		}
	}
}
