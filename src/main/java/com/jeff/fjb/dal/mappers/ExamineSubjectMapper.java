package com.jeff.fjb.dal.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.ExamineSubjectEntity;

public interface ExamineSubjectMapper {
	public void addSubject(ExamineSubjectEntity entity);
	public List<ExamineSubjectEntity> getSubjects();
	public ExamineSubjectEntity getSubject(@Param("subject") String subject);
	public List<ExamineSubjectEntity> getRegSubjects(@Param("now") long now);
	public ExamineSubjectEntity getSubjectById(@Param("id") long id);
}
