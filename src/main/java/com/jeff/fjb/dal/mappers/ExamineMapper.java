package com.jeff.fjb.dal.mappers;

import java.util.List;

import com.jeff.fjb.dal.entity.ExamineEntity;

public interface ExamineMapper {
	public List<ExamineEntity> getAllExamine();
	public void addExamine(ExamineEntity examineEntity);
}
