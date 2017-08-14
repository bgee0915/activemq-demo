package com.bgee.mq.active;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection; 
import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * 消息生产者
 * @author lx
 *
 */
public class JMSProducer {
	
//	默认用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	
//	默认密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
//	默认连接地址
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
//	发送消息数量
	private static final int sendNum = 10;
	
	public static void main(String[] args)  {
//		连接工厂
		ConnectionFactory connectionFactory = null;
		
//		连接
		Connection connection = null;
		
//		会话
		Session session = null;
		
//		消息目的地
		Destination destination = null;
		
//		消息生产者
		MessageProducer messageProducer = null;
		try {
			
		
//			建立连接工厂
			connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
		
//			获取连接
			connection = connectionFactory.createConnection(USERNAME, PASSWORD);
		
//			打开连接
			connection.start();
		
//			创建会话
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		
//			创建消息队列
			destination = session.createQueue("FirstQueue"); 
		
//			创建消息生产者
			messageProducer = session.createProducer(destination);
		
//			发送消息
			sendMessage(session,messageProducer);
		
//			提交事务
			session.commit();
			}catch(JMSException e) {
				e.printStackTrace();
			}finally{
				if(connection != null) {
					try {
						connection.close();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
	}
	/**
	 * 发送消息
	 * @param session
	 * @param messageProducer
	 */
	public static void sendMessage(Session session, MessageProducer messageProducer)throws JMSException {
		for(int i=0; i<JMSProducer.sendNum; i++) {
			TextMessage message = session.createTextMessage("activeMQ 发送的消息  : " + i + i);
			System.out.println("发送消息：  ActiveMQ 发送的消息- " + i + i);
			messageProducer.send(message);
		}
	}
}
