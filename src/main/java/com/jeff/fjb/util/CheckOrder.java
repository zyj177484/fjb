package com.jeff.fjb.util;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jeff.fjb.dal.entity.OrderEntity;
import com.jeff.fjb.dal.service.OrderService;
import com.jeff.fjb.dal.service.UserService;

public class CheckOrder extends Thread {
	private static Logger logger = Logger.getLogger(CheckOrder.class);
	private String orderId;
	public CheckOrder(String orderId) {
		this.orderId = orderId;
	}
	
	@Override
    public void run() {
		logger.info("等待"+MagicNum.ORDER_WAIT_TIME/1000/60+"分钟，然后检查订单" + orderId);
		try {
			Thread.sleep(MagicNum.ORDER_WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("开始检查订单" + orderId);
		OrderService orderService = new OrderService();
		OrderEntity orderEntity = orderService.getOrder(orderId);
		if (orderEntity != null) {
			JsonObject orderContent = new JsonParser().parse(orderEntity.getContent()).getAsJsonObject();
			if (orderContent.get("type").getAsString().equals("practice")) {
				if (orderEntity.getStatus().equals("open")) {
					if (orderEntity.getPayed().equals("true")) {
						UserService userService = new UserService();
						String userId = orderContent.get("userId").getAsString();
						int practice = 1;
						userService.updateUserPractice(userId, practice);
						logger.info("给用户" + userId + "练习权限");
					}
					orderService.updateOrderStatus(orderId, "close");
					logger.info("关闭点单" + orderId);
				}
			} else if (orderContent.get("type").getAsString().equals("examine")) {
				
			} else {
				System.out.println("未知订单类型" + orderContent.get("type").getAsString());
			}
		} else {
			logger.warn("订单" + orderId + "不存在");
		}
		
	}
}
