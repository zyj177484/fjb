package com.jeff.fjb.dal.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.ExamineEntity;
import com.jeff.fjb.dal.mappers.ExamineMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class ExamineService {
	
	public List<ExamineEntity> getAllExamine() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineMapper examineMapper = sqlSession.getMapper(ExamineMapper.class);
			return examineMapper.getAllExamine();
		} finally {
			sqlSession.close();
		}
	}
	
	public List<ExamineEntity> getToStartExamine(long now) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineMapper examineMapper = sqlSession.getMapper(ExamineMapper.class);
			return examineMapper.getToStartExamine(now);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<ExamineEntity> getToStartExamineBySubject(long now, long subjectId) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineMapper examineMapper = sqlSession.getMapper(ExamineMapper.class);
			return examineMapper.getToStartExamineBySubject(now, subjectId);
		} finally {
			sqlSession.close();
		}
	}
	
	
	public List<ExamineEntity> getUsedExamineRoom(long roomId, long startTime, long endTime) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineMapper examineMapper = sqlSession.getMapper(ExamineMapper.class);
			return examineMapper.getUsedExamineRoom(roomId, startTime, endTime);
		} finally {
			sqlSession.close();
		}
	}
	
	public void addExamine(ExamineEntity entity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineMapper examineMapper = sqlSession.getMapper(ExamineMapper.class);
			examineMapper.addExamine(entity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public void addExamines(List<ExamineEntity> entities) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineMapper examineMapper = sqlSession.getMapper(ExamineMapper.class);
			examineMapper.addExamines(entities);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
