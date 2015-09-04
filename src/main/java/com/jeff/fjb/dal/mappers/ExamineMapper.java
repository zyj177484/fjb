package com.jeff.fjb.dal.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.ExamineEntity;

public interface ExamineMapper {
	public List<ExamineEntity> getAllExamine();
	public List<ExamineEntity> getUsedExamineRoom(@Param("roomId") long roomId, @Param("startTime") long startTime, @Param("endTime") long endTime);
	public List<ExamineEntity> getToStartExamine(@Param("now") long now);
	public List<ExamineEntity> getToStartExamineBySubject(@Param("now") long now, @Param("subjectId") long subjectId);
	public void addExamine(ExamineEntity examineEntity);
	public void addExamines(@Param("entities") List<ExamineEntity> entities);
}
