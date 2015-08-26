package com.jeff.fjb.dal.service;

import java.util.List;

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
}
