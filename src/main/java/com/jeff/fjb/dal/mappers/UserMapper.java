package com.jeff.fjb.dal.mappers;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.UserEntity;

public interface UserMapper {
	public UserEntity getUserEntity(@Param("id") String id);
	public UserEntity getCheckInfo(@Param("id") String id);
	public void updateSessionId(@Param("id") String id, @Param("sessionId") String sessionId);
	public void insertUser(UserEntity userEntity);
	public void insertUserPhoto(@Param("id") String id, @Param("photo") byte[] photo);
	public java.sql.Blob getPhoto(@Param("id") String id);
}
