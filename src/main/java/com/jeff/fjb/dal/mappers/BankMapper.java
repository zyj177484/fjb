package com.jeff.fjb.dal.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.BankEntity;

public interface BankMapper {

	public List<BankEntity> getAllZonghang();
	public List<BankEntity> getAllFenhang(@Param("zonghangid") String zonghangid);
	public List<BankEntity> getAllZhihang(@Param("fenhangid") String fenhangid);
}
