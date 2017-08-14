package com.bgee.mq.active2;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * 订阅者1 - 消息监听
 * @author lx
 *
 */
public class Listener implements MessageListener{
	public void onMessage(Message message) {
		try {
			System.out.println("订阅者1 - 接收到的消息：   " + ((TextMessage)message).getText());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
