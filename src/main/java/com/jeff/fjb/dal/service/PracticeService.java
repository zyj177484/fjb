package com.jeff.fjb.dal.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.PracticeEntity;
import com.jeff.fjb.dal.mappers.PracticeMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class PracticeService {
	public PracticeEntity getPracticeEntity(int id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PracticeMapper practiceMapper = sqlSession.getMapper(PracticeMapper.class);
			return practiceMapper.getPracticeEntity(id);
		} finally {
			sqlSession.close();
		}
	}

	public PracticeEntity getPhoto(int id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PracticeMapper practiceMapper = sqlSession.getMapper(PracticeMapper.class);
			return practiceMapper.getPhoto(id);
		} finally {
			sqlSession.close();
		}
	}

	public List<Integer> getIDByType(int type) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PracticeMapper practiceMapper = sqlSession.getMapper(PracticeMapper.class);
			return practiceMapper.getIDByType(type);
		} finally {
			sqlSession.close();
		}
	}

	public void insertPracticeEntity(PracticeEntity practiceEntity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PracticeMapper practiceMapper = sqlSession.getMapper(PracticeMapper.class);
			practiceMapper.insertPracticeEntity(practiceEntity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
