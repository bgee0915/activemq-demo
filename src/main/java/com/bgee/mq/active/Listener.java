package com.bgee.mq.active;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * 消息监听
 * @author lx
 *
 */
public class Listener implements MessageListener{
	public void onMessage(Message message) {
		try {
			System.out.println("接收到的消息：   " + ((TextMessage)message).getText());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
