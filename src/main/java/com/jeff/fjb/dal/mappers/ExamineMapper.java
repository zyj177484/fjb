package com.jeff.fjb.dal.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.ExamineEntity;

public interface ExamineMapper {
	public List<ExamineEntity> getAllExamine();
	public List<ExamineEntity> getExamine(ExamineEntity examineEntity);
	public void addExamine(ExamineEntity examineEntity);
	public void addExamines(@Param("entities") List<ExamineEntity> entities);
	
}
