package com.jeff.fjb.dal.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.ExamineEntity;
import com.jeff.fjb.dal.entity.ExamineSubjectEntity;
import com.jeff.fjb.dal.mappers.ExamineSubjectMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class ExamineSubjectService {
	public void addSubject(ExamineSubjectEntity entity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineSubjectMapper mapper = sqlSession.getMapper(ExamineSubjectMapper.class);
			mapper.addSubject(entity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public List<ExamineSubjectEntity> getSubjects() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineSubjectMapper mapper = sqlSession.getMapper(ExamineSubjectMapper.class);
			return mapper.getSubjects();
		} finally {
			sqlSession.close();
		}
	}
	
	public ExamineSubjectEntity getSubject(String subject) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineSubjectMapper mapper = sqlSession.getMapper(ExamineSubjectMapper.class);
			return mapper.getSubject(subject);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<ExamineSubjectEntity> getRegSubjects(long now) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineSubjectMapper mapper = sqlSession.getMapper(ExamineSubjectMapper.class);
			return mapper.getRegSubjects(now);
		} finally {
			sqlSession.close();
		}
	}
	
	public ExamineSubjectEntity getSubjectById(long id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineSubjectMapper mapper = sqlSession.getMapper(ExamineSubjectMapper.class);
			return mapper.getSubjectById(id);
		} finally {
			sqlSession.close();
		}
	}
	
	public static void main(String[] args) {
//		ExamineService service = new ExamineService();
		long now =System.currentTimeMillis()/1000;
//		List<ExamineEntity> examineEntities = service.getToStartExamine(now);
//		for (ExamineEntity entity : examineEntities){
//			System.out.println(entity.getExamineId() + ":" + entity.getExamineDistinct() + " " +entity.getRoom() + " " + entity.getSubject());
//		}
		long subjectId = 1;
		ExamineService service = new ExamineService();
		List<ExamineEntity> examineEntities = service.getToStartExamineBySubject(now, subjectId);
		for (ExamineEntity entity : examineEntities)
			System.out.println(entity.getExamineId());
	}
}
