package com.jeff.fjb.dal.service;

import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.UserEntity;
import com.jeff.fjb.dal.mappers.UserMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class UserService {

	public UserEntity getUserEntity(String id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getUserEntity(id);
		} finally {
			sqlSession.close();
		}
	}

	public UserEntity getCheckInfo(String id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getCheckInfo(id);
		} finally {
			sqlSession.close();
		}
	}

	public UserEntity getPhoto(String id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getPhoto(id);
		} finally {
			sqlSession.close();
		}
	}
	
	public void updateUserSession(String id, String sessionId) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.updateSessionId(id, sessionId);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public void insertUser(UserEntity userEntity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertUser(userEntity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public void insertUserPhoto(String id, byte[] photo) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertUserPhoto(id, photo);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public void updateUserPractice(String id, int practice) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.updateUserPractice(id, practice);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		
	}
	
	public  static void main(String[] args){
		UserService service = new UserService();
		if (service.getPhoto("123") != null)
			System.out.println("yes");
		else 
			System.out.println("no");
	}
}
