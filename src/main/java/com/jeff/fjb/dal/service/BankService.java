package com.jeff.fjb.dal.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jeff.fjb.dal.entity.BankEntity;
import com.jeff.fjb.dal.mappers.BankMapper;
import com.jeff.fjb.dal.util.MyBatisSqlSessionFactory;

public class BankService {
	
	public List<BankEntity> getAllZonghang() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			BankMapper bankMapper = sqlSession.getMapper(BankMapper.class);
			return bankMapper.getAllZonghang();
		} finally {
			sqlSession.close();
		}
	}

	public List<BankEntity> getAllFenhang(String zonghangid) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			BankMapper bankMapper = sqlSession.getMapper(BankMapper.class);
			return bankMapper.getAllFenhang(zonghangid);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<BankEntity> getAllZhihang(String fenhangid) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			BankMapper bankMapper = sqlSession.getMapper(BankMapper.class);
			return bankMapper.getAllZhihang(fenhangid);
		} finally {
			sqlSession.close();
		}
	}
	
	public void addZonghang(BankEntity bankEntity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			BankMapper bankMapper = sqlSession.getMapper(BankMapper.class);
			bankMapper.addZonghang(bankEntity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public void addFenhang(BankEntity bankEntity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			BankMapper bankMapper = sqlSession.getMapper(BankMapper.class);
			bankMapper.addFenhang(bankEntity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public void addZhihang(BankEntity bankEntity) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			BankMapper bankMapper = sqlSession.getMapper(BankMapper.class);
			bankMapper.addZhihang(bankEntity);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	public static void main(String[] args) {
		BankService service = new BankService();
		BankEntity entity = new BankEntity();
		entity.setId("B0003H111000001");
		entity.setName("中国银行股份有限公司2");
		try {
			service.addZonghang(entity);
		} catch (Exception e) {
			System.out.println("here");
			e.printStackTrace();
		}
		System.out.println("there");
	}
}
