package com.jeff.fjb.dal.mappers;

import java.util.List;

import com.jeff.fjb.dal.entity.ExamineDistinctEntity;
import com.jeff.fjb.dal.entity.ExamineRoomEntity;

public interface ExamineRoomMapper {
	public void insertExamineDistinct(ExamineDistinctEntity entity);
	public void insertExamineRoom(ExamineRoomEntity entity);
	public List<ExamineDistinctEntity> getAllExamineDistinct();
}
