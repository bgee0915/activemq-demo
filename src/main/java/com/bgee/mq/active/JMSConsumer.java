package com.bgee.mq.active;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息消费者
 * @author lx
 *
 */
public class JMSConsumer {
	
//	默认用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	
//	默认密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
//	默认连接地址
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	
	public static void main(String[] args) {
		
//		连接工厂
		ConnectionFactory connectionFactory = null;
		
//		连接
		Connection connection = null;
		
//		会话
		Session session = null;
		
//		消息目的地
		Destination destination = null;
		
//		消息消费者
		MessageConsumer messageConsumer = null;
		
		try {
			
//			建立连接工厂
			connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
			
//			获取连接
			connection = connectionFactory.createConnection(USERNAME, PASSWORD);
		
//			打开连接
			connection.start();
			
//			创建会话
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			
//			创建消息队列
			destination = session.createQueue("FirstQueue"); 
			
			messageConsumer = session.createConsumer(destination);
			
			while(true) {
				TextMessage textMessage = (TextMessage)messageConsumer.receive(100000); // 毫秒
				if(null != textMessage) {
					System.out.println("接收到的消息哦：    " + textMessage.getText());
				}else {
					break;
				}
			}
		}catch(JMSException e) {
			e.printStackTrace();
		}
		
	}
}
