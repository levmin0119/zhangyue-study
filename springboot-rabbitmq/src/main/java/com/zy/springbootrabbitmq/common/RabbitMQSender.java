package com.zy.springbootrabbitmq.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSender {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.92.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        //获取连接
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello-RabbitMQ", false, false, false, null);
        String msg = "Hello RabbitMQ!";
        channel.basicPublish("", "hello-RabbitMQ", null, msg.getBytes());
        System.out.println("[x] sent '" + msg + "'");
    }
}
