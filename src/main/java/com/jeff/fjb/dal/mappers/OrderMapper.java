package com.jeff.fjb.dal.mappers;

import org.apache.ibatis.annotations.Param;

import com.jeff.fjb.dal.entity.OrderEntity;

public interface OrderMapper {
	public void addOrder(OrderEntity orderEntity);
	public OrderEntity getOrder(@Param("orderId") String orderId);
	public void updateOrderStatus(@Param("orderId") String orderId, @Param("status") String status);
	public void payOrder(@Param("orderId") String orderId, @Param("status") String status, @Param("payed")String payed);
}
