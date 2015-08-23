package com.jeff.fjb.dal.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.ExamineDistinctEntity;
import com.jeff.fjb.dal.entity.ExamineRoomEntity;
import com.jeff.fjb.dal.mappers.ExamineRoomMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class ExamineRoomService {
	public void insertExamineDistinct(ExamineDistinctEntity entity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineRoomMapper examineRoomMapper = sqlSession.getMapper(ExamineRoomMapper.class);
			examineRoomMapper.insertExamineDistinct(entity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	public void insertExamineRoom(ExamineRoomEntity entity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineRoomMapper examineRoomMapper = sqlSession.getMapper(ExamineRoomMapper.class);
			examineRoomMapper.insertExamineRoom(entity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	public List<ExamineDistinctEntity> getAllExamineDistinct() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineRoomMapper examineRoomMapper = sqlSession.getMapper(ExamineRoomMapper.class);
			return examineRoomMapper.getAllExamineDistinct();
		} finally {
			sqlSession.close();
		}
	}

}
