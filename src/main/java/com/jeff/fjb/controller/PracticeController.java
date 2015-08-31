package com.jeff.fjb.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.jeff.fjb.dal.entity.PracticeEntity;
import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.PracticeService;
import com.jeff.fjb.dal.service.UserService;
import com.jeff.fjb.util.MagicNum;

@Controller
public class PracticeController {

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
					result.add(new JsonPrimitive(id));
					ids.set(index, ids.get(ids.size() - no - 1));
				}
			} else
				return null;
		}
		return result;
	}

	@RequestMapping(value = "/roundPractice")
	public ModelAndView roundPractice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			JsonArray questionArray = genPractice();
			session.setAttribute("questionArray", questionArray.toString());
			ModelAndView mv = new ModelAndView();
			mv.setViewName("roundPractice");
			return mv;
		} else
			return preCheckResult;
	}

	@RequestMapping(value = "/wholePractice")
	public ModelAndView wholePractice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("wholePractice");
			return mv;
		} else
			return preCheckResult;
	}
	
	@RequestMapping(value = "/getQuestionPhoto")
	@ResponseBody
	public void showImage(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/gif");
		int id = Integer.valueOf(request.getParameter("id"));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			byte[] photo = practiceService.getPhoto(id).getPhoto();
			os.write(photo);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	// @RequestMapping(value = "/getQuestion", produces = "plain/text;
	// charset=UTF-8")
	@RequestMapping(value = "/getQuestion")
	@ResponseBody
	public void getQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView preCheckResult = preCheck(request.getSession());
		HttpSession session = request.getSession();
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (preCheckResult == null) {
			if (request.getParameter("no") != null) {
				try {
					int no = Integer.valueOf(request.getParameter("no"));
					if (no > 0 && no <= MagicNum.QUESTION_NUM) {
						if (session.getAttribute("questionArray") != null) {
							JsonArray questionArray = new JsonParser()
									.parse(session.getAttribute("questionArray").toString()).getAsJsonArray();
							int id = questionArray.get(no - 1).getAsInt();
							PracticeEntity practiceEntity = practiceService.getPracticeEntity(id);
							if (practiceEntity != null) {
								if (!practiceEntity.getHasPhoto().equals("false"))
									practiceEntity.setPhoto_url("getQuestionPhoto?id=" + id);
								else 
									practiceEntity.setPhoto_url(null);
								practiceEntity.setNo(no);
								out.write(new Gson().toJson(practiceEntity));
								return;
							} else {
								JsonObject object = new JsonObject();
								object.addProperty("type", "0");
								object.addProperty("message", "该题不存在");
								object.addProperty("view", "login");
								out.write(object.toString());
								return;
							}
						} else {
							JsonObject object = new JsonObject();
							object.addProperty("type", "0");
							object.addProperty("message", "请点击开始练习");
							object.addProperty("view", "login");
							out.write(object.toString());
							return;
						}
					} else {
						JsonObject object = new JsonObject();
						object.addProperty("type", "0");
						object.addProperty("message", "该题号不存在");
						object.addProperty("view", "login");
						out.write(object.toString());
						return;
					}
				} catch (Exception e) {
					JsonObject object = new JsonObject();
					object.addProperty("type", "0");
					object.addProperty("message", "输入题号不存在");
					object.addProperty("view", "login");
					out.write(object.toString());
					return;
				}
			} else if (request.getParameter("id") != null) {
				try {
					int id = Integer.valueOf(request.getParameter("id"));
					System.out.println("id:" + id);
					PracticeEntity practiceEntity = practiceService.getPracticeEntity(id);
					if (practiceEntity != null) {
						if (!practiceEntity.getHasPhoto().equals("false"))
							practiceEntity.setPhoto_url("getQuestionPhoto?id=" + id);
						else 
							practiceEntity.setPhoto_url(null);
						out.write(new Gson().toJson(practiceEntity));
						return;
					} else {
						JsonObject object = new JsonObject();
						object.addProperty("type", "0");
						object.addProperty("message", "该题不存在");
						object.addProperty("view", "login");
						out.write(object.toString());
						return;
					}
				} catch (Exception e) {
					JsonObject object = new JsonObject();
					object.addProperty("type", "0");
					object.addProperty("message", "id不对");
					object.addProperty("view", "login");
					out.write(object.toString());
					return;
				}
			} else {
				JsonObject object = new JsonObject();
				object.addProperty("type", "0");
				object.addProperty("message", "请输入题号");
				object.addProperty("view", "login");
				out.write(object.toString());
				return;
			}
		} else {
			JsonObject object = new JsonObject();
			object.addProperty("type", "0");
			object.addProperty("message", preCheckResult.getModel().get("message").toString());
			object.addProperty("view", preCheckResult.getViewName());
			out.write(object.toString());
			return;
		}
	}
}
