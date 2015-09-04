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

	public ExamineDistinctEntity getExamineDistinct(String distinct) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineRoomMapper examineRoomMapper = sqlSession.getMapper(ExamineRoomMapper.class);
			return examineRoomMapper.getExamineDistinct(distinct);
		} finally {
			sqlSession.close();
		}
	}
	
	public ExamineRoomEntity getExamineRoom(String distinct, String room) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineRoomMapper examineRoomMapper = sqlSession.getMapper(ExamineRoomMapper.class);
			return examineRoomMapper.getExamineRoom(distinct, room);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<ExamineRoomEntity> getExamineRoomByDistinct(String distinct) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineRoomMapper examineRoomMapper = sqlSession.getMapper(ExamineRoomMapper.class);
			return examineRoomMapper.getExamineRoomByDistinct(distinct);
		} finally {
			sqlSession.close();
		}
	}
	
	public ExamineRoomEntity getExamineRoomById(long id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ExamineRoomMapper examineRoomMapper = sqlSession.getMapper(ExamineRoomMapper.class);
			return examineRoomMapper.getExamineRoomById(id);
		} finally {
			sqlSession.close();
		}
	}
}
