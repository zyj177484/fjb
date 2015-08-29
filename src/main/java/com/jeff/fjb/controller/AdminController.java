package com.jeff.fjb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
import com.jeff.fjb.dal.entity.ExamineEntity;
import com.jeff.fjb.dal.entity.ExamineRoomEntity;
import com.jeff.fjb.dal.entity.ExamineSubjectEntity;
import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.service.BankService;
import com.jeff.fjb.dal.service.ExamineRoomService;
import com.jeff.fjb.dal.service.ExamineService;
import com.jeff.fjb.dal.service.ExamineSubjectService;
import com.jeff.fjb.dal.service.UserService;

@Controller
public class AdminController {
	private UserService userService = new UserService();
	private BankService bankService = new BankService();
	private ExamineRoomService examineRoomService = new ExamineRoomService();
	private ExamineSubjectService examineSubjectService = new ExamineSubjectService();
	private ExamineService examineService = new ExamineService();
	private static SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	
	public AdminController(){
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	}
	
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
	
	@RequestMapping(value = "/admin/manageExamine")
	public ModelAndView manageExamine(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String message = request.getParameter("message");
			if (message!=null && message.length() !=0)
				mv.addObject("message", message);
			mv.addObject("examineList", examineService.getToStartExamine(new Date().getTime()/1000));
			mv.addObject("subjectList", examineSubjectService.getSubjects());
			mv.addObject("distinctList", examineRoomService.getAllExamineDistinct());
			mv.setViewName("admin/manageExamine");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	@RequestMapping(value = "/admin/addExamineCheck")
	public ModelAndView addExamineCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult != null) {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
			return mv;
		}
		String[] rooms = request.getParameterValues("room");
		String subject = request.getParameter("subject");
		String distinct = request.getParameter("distinct");
		String date = request.getParameter("date").trim();
		String startTime = request.getParameter("startTime").trim();
		String endTime = request.getParameter("endTime").trim();
		if (rooms.length != 0 && subject != null && distinct != null && date != null 
				&& startTime != null && endTime != null) {
			ExamineSubjectEntity examineSubjectEntity = examineSubjectService.getSubject(subject);
			if (examineSubjectEntity == null) {
		    	mv.addObject("message", "科目:" + subject + " 不存在");
				mv.setView(new RedirectView("/admin/manageExamine", true));
				return mv;
		    }
			
			long startTimeStamp = 0;
			long endTimeStamp = 0;
			try {
				startTimeStamp = sdf.parse(date + " " + startTime).getTime()/1000;
				endTimeStamp = sdf.parse(date + " " + endTime).getTime()/1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				mv.addObject("message", "日期或者时间格式不对");
				mv.setView(new RedirectView("/admin/manageExamine", true));
				return mv;
			}
			
			if (startTimeStamp >= endTimeStamp ) {
				mv.addObject("message", "考试开始时间: " + startTime + " 大于考试结束时间: " + endTime);
				mv.setView(new RedirectView("/admin/manageExamine", true));
				return mv;
			}
			
			if (startTimeStamp >= examineSubjectEntity.getResultTime() 
					|| endTimeStamp >= examineSubjectEntity.getResultTime()) {
				mv.addObject("message", "考试时间在考试公布结果之后");
				mv.setView(new RedirectView("/admin/manageExamine", true));
				return mv;
			}
			
			ExamineDistinctEntity examineDistinctEntity = examineRoomService.getExamineDistinct(distinct);
			if (examineDistinctEntity == null) {
		    	mv.addObject("message", "考区:" + distinct + " 不存在");
				mv.setView(new RedirectView("/admin/manageExamine", true));
				return mv;
		    }

			StringBuffer message = new StringBuffer();
			for (String room : rooms) {
				ExamineRoomEntity examineRoomEntity = examineRoomService.getExamineRoom(distinct, room);
				if (examineRoomEntity == null) {
					mv.addObject("message", "考场:" + room + " 不存在");
					mv.setView(new RedirectView("/admin/manageExamine", true));
					return mv;
				}
				System.out.println("1." + subject + " " + distinct + " " + room + " " + startTimeStamp + " " + endTimeStamp);
				ExamineEntity examineEntity = new ExamineEntity(subject, distinct, 
						room, startTimeStamp, endTimeStamp, 0, examineRoomEntity.getNum());
				List<ExamineEntity> examineEntities = examineService.getUsedExamineRoom(examineEntity);
				for (ExamineEntity entity : examineEntities ){
					System.out.println("2." + entity.getSubject() + ":" +entity.getExamineDistinct() + ":" + entity.getRoom() + ":" +entity.getStartTime()+":"+entity.getEndTime());
				}
				if (examineEntities == null || examineEntities.size() == 0) {
					try {
						examineService.addExamine(examineEntity);
						System.out.println("3." + examineEntity.getSubject() + ":" +examineEntity.getExamineDistinct() + ":" + examineEntity.getRoom() + ":" +examineEntity.getStartTime()+":"+examineEntity.getEndTime());
						message.append("成功，考场:" + room + " 时间:"+date+ " " + startTime + " ~ " + endTime + "<br/>");
					} catch (Exception e) {
						System.out.println("4");
						message.append("失败，考场:" + room + " 时间:"+date+ " " + startTime + " ~ " + endTime + "<br/>");
					}
				} else {
					message.append("失败，考场:" + room + " 时间:" + date + " " + startTime + " ~ " + endTime + "<br/>");
				}
			}
			mv.addObject("message", message.toString());
			mv.setView(new RedirectView("/admin/manageExamine", true));
			return mv;
		} else {
			mv.addObject("message", "参数未填写完全");
			mv.setView(new RedirectView("/admin/manageExamine", true));
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/manageExamineSubject")
	public ModelAndView addExaminationSubject(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String message = request.getParameter("message");
			if (message!=null && message.length() !=0)
				mv.addObject("message", message);
			mv.setViewName("admin/manageExamineSubject");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	@RequestMapping(value = "/admin/addExamineSubjectCheck")
	public ModelAndView addExamineSubjectCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String message = request.getParameter("message");
			if (message!=null && message.length() !=0)
				mv.addObject("message", message);
			String subject = request.getParameter("subject");
			String charge = request.getParameter("charge");
			String regTime = request.getParameter("regTime");
			String resultTime = request.getParameter("resultTime");
			String note = request.getParameter("note");
			
			if (subject == null || subject.length() == 0) {
				mv.addObject("message", "请输入科目");
				mv.setView(new RedirectView("/admin/manageExamineSubject", true));
				return mv;
			}
			if (charge == null || charge.length() == 0) {
				mv.addObject("message", "请输入费用");
				mv.setView(new RedirectView("/admin/manageExamineSubject", true));
				return mv;
			}
			if (regTime == null || regTime.length() == 0) {
				mv.addObject("message", "报考截止日期");
				mv.setView(new RedirectView("/admin/manageExamineSubject", true));
				return mv;
			}
			if (resultTime == null || resultTime.length() == 0) {
				mv.addObject("message", "请输入考试结果公布日期");
				mv.setViewName("admin/manageExamineSubject");
				return mv;
			}
			long chargeNum;
			try {
				chargeNum = Long.valueOf(charge);
			} catch (Exception e) {
				mv.addObject("message", "请输入整数费用");
				mv.setViewName("admin/manageExamineSubject");
				return mv;
			}
			
			long regTimeStamp;
			try {
				Date date = sdf.parse(regTime + " 23:59:59" );
				regTimeStamp = date.getTime() / 1000;
			} catch (ParseException e) {
				mv.addObject("message", "报名截止日期格式错误");
				mv.setViewName("admin/manageExamineSubject");
				return mv;
			}
			
			long resultTimeStamp;
			try {
				Date date = sdf.parse(resultTime + " 23:59:59" );
				resultTimeStamp = date.getTime() / 1000;
			} catch (ParseException e) {
				mv.addObject("message", "考试结果公布日期格式错误");
				mv.setViewName("admin/manageExamineSubject");
				return mv;
			}
			
			examineSubjectService.addSubject(new ExamineSubjectEntity(subject, note, chargeNum, regTimeStamp, resultTimeStamp));
			mv.addObject("message", "添加科目："+subject+"成功");
			mv.setViewName("admin/manageExamineSubject");			
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	@RequestMapping(value = "/admin/manageExamineRoom")
	public ModelAndView addExaminationRoom(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			List<ExamineDistinctEntity> entities = examineRoomService.getAllExamineDistinct();
			mv.addObject("distinctList", entities);
			String message = request.getParameter("message");
			if (message!=null && message.length() !=0)
				mv.addObject("message", message);
			mv.setViewName("admin/manageExamineRoom");
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
	
	@RequestMapping(value = "/admin/manageBank")
	public ModelAndView addBank(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String preCheckResult = preCheck(session);
		if (preCheckResult == null) {
			String message = request.getParameter("message");
			if (message!=null && message.length() !=0)
				mv.addObject("message", message);
			mv.setViewName("admin/manageBank");
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
				mv.addObject("message", "addExaminationRoomCheck中不存在 "+roomType+" 命令");
			mv.setView(new RedirectView("/admin/manageExamineRoom", true));
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
					mv.addObject("message", "manageBank中不存在 "+bankType+" 命令");
			} else 
				mv.addObject("message", "银行编码或者银行名称未填");
			mv.setView(new RedirectView("/admin/manageBank", true));
		} else {
			mv.addObject("message", preCheckResult);
			mv.setView(new RedirectView("/index", true));
		}
		return mv;
	}
}
