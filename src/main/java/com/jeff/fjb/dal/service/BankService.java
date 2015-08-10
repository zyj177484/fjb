package com.jeff.fjb.dal.service;

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
	
	public static void main(String[] args) {
		BankService service = new BankService();
		List<BankEntity> bankEntities = service.getAllZonghang();
		System.out.println(bankEntities.get(0).getId() + ":" + bankEntities.get(0).getName());
		bankEntities = service.getAllFenhang(bankEntities.get(0).getId());
		System.out.println(bankEntities.get(0).getId() + ":" + bankEntities.get(0).getName());
		bankEntities = service.getAllZhihang(bankEntities.get(0).getId());
		System.out.println(bankEntities.get(0).getId() + ":" + bankEntities.get(0).getName());
	}
}
