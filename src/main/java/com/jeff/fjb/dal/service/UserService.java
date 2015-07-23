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
}
