package com.example.demo.mybatisPlus.controller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * @description:类描述：
 * @author:trd
 * @date:2022-04-25 13:31
 */

public class activeMq {
    public static void main(String[] args) {
        try {
            send("hello,world ,activeMq test");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void send(String messageStr)
            throws JMSException {
        // 这里为简便，依赖ActiveMQ的JMS实现。现实程序中可以通过jndi查找方式查找factory，这样彻底避免关联特定JMS实现者。并拥有最大的可移植性。
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(
                false,
                Session.AUTO_ACKNOWLEDGE);
        Message message = session.createTextMessage(
                messageStr);
        // 同上，这里依赖了ActiveMQ的JMS实现，现实中可以采用jndi查找方式获得Queue。
        Queue queue = new ActiveMQQueue("queue.somebody");
        MessageProducer producer = session.createProducer(
                queue);
        producer.send(
                message);

        session.close();
        connection.close();
    }

}

