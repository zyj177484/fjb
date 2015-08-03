package com.jeff.fjb.dal.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.PracticeEntity;
import com.jeff.fjb.dal.mappers.PracticeMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class PracticeService {
	public PracticeEntity getPracticeEntity(int id, int type) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PracticeMapper practiceMapper = sqlSession.getMapper(PracticeMapper.class);
			return practiceMapper.getPracticeEntity(id, type);
		} finally {
			sqlSession.close();
		}
	}
	
	public PracticeEntity getPhoto(int id, int type) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PracticeMapper practiceMapper = sqlSession.getMapper(PracticeMapper.class);
			return practiceMapper.getPhoto(id, type);
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
}
