package com.jeff.fjb.dal.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.PracticeEntity;

public interface PracticeMapper {
	public PracticeEntity getPracticeEntity(@Param("id") int id);
	public List<Integer> getIDByType(@Param("type") int type);
	public PracticeEntity getPhoto(@Param("id") int id);
	public void insertPracticeEntity(PracticeEntity practiceEntity);
}
