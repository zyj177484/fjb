package com.jeff.fjb.dal.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.ExamineDistinctEntity;
import com.jeff.fjb.dal.entity.ExamineRoomEntity;

public interface ExamineRoomMapper {
	public void insertExamineDistinct(ExamineDistinctEntity entity);
	public void insertExamineRoom(ExamineRoomEntity entity);
	public List<ExamineDistinctEntity> getAllExamineDistinct();
	public ExamineDistinctEntity getExamineDistinct(@Param("distinct")String distinct);
	public ExamineRoomEntity getExamineRoom(@Param("distinct")String distinct, @Param("room")String room);
	public List<ExamineRoomEntity> getExamineRooms(@Param("distinct")String distinct);
}
