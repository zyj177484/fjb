package com.jeff.fjb.dal.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

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
}
