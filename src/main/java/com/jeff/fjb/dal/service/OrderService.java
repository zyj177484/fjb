package com.jeff.fjb.dal.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.OrderEntity;
import com.jeff.fjb.dal.mappers.OrderMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class OrderService {
	public void addOrder(OrderEntity orderEntity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			orderMapper.addOrder(orderEntity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public OrderEntity getOrder(String orderId) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			return orderMapper.getOrder(orderId);
		} finally {
			sqlSession.close();
		}
	}
	
	public void updateOrderStatus(String orderId, String status) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			orderMapper.updateOrderStatus(orderId, status);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public void payOrder(String orderId, String status, String payed){
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			orderMapper.payOrder(orderId, status, payed);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
