package com.jeff.fjb.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jeff.fjb.dal.entity.BankEntity;
import com.jeff.fjb.dal.entity.ExamineRoomEntity;
import com.jeff.fjb.dal.service.BankService;
import com.jeff.fjb.dal.service.ExamineRoomService;
import com.jeff.fjb.dal.service.UserService;

@Controller
public class RESTApi {
	
	@RequestMapping(value = "/showPhoto")
	@ResponseBody
	public void showImage(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/gif");
		String id = request.getSession().getAttribute("id").toString();
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			UserService userService = new UserService();
			byte[] photo = userService.getPhoto(id).getPhoto();
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
	
	@RequestMapping(value = "/getBank")
	@ResponseBody
	public void getBank(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (type == null) {
			JsonObject object = new JsonObject();
			object.addProperty("type", "0");
			object.addProperty("message", "请输入银行类型");
			object.addProperty("view", "user/dashboard");
			out.write(object.toString());
		} else {
			BankService service = new BankService();
			List<BankEntity> entities = null;
			if (type.equals("zonghang")) 
				entities = service.getAllZonghang();
			else if (type.equals("fenhang")){
				String zonghangid = request.getParameter("zonghangid");
				if (zonghangid == null) {
					JsonObject object = new JsonObject();
					object.addProperty("type", "0");
					object.addProperty("message", "请输入zonghangid");
					object.addProperty("view", "user/dashboard");
					out.write(object.toString());
				} else {
					entities = service.getAllFenhang(zonghangid);
				}
			} else if (type.equals("zhihang")){
				String fenhangid = request.getParameter("fenhangid");
				if (fenhangid == null) {
					JsonObject object = new JsonObject();
					object.addProperty("type", "0");
					object.addProperty("message", "请输入fenhangid");
					object.addProperty("view", "user/dashboard");
					out.write(object.toString());
				} else {
					entities = service.getAllZhihang(fenhangid);
				}
			}
			
			if (entities != null) {
				JsonArray array = new JsonArray();
				for (BankEntity bankEntity : entities) {
					JsonObject bankObject = new JsonObject();
					bankObject.addProperty("id", bankEntity.getId());
					bankObject.addProperty("name", bankEntity.getName());
					array.add(bankObject);
				}
				out.write(array.toString());
			}
		}
	}
	
	@RequestMapping(value = "/getRooms")
	@ResponseBody
	public void getRoom(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String distinct = request.getParameter("distinct");
		System.out.println(distinct);
		if (distinct!=null) {
			ExamineRoomService service = new ExamineRoomService();
			List<ExamineRoomEntity> entities = service.getExamineRooms(distinct);
			out.write(new Gson().toJson(entities));
		} else {
			out.write("未输入考区");
		}
	}
}
