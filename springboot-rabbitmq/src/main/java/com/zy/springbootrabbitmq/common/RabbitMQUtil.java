package com.zy.springbootrabbitmq.common;


import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 连接
 */
@Component
public class RabbitMQUtil {

    @Value("${rabbitmq.host}")
    private  String host;

    @Value("${rabbitmq.port}")
    private  int port;

    @Value("${rabbitmq.virtual-host}")
    private  String virtualHost;

    @Value("${rabbitmq.password}")
    private  String password;

    @Value("${rabbitmq.username}")
    private  String username;

    //交换器名称
    private static final String EXCHANGE_NAME = "LEVMIN_EXCHANGE";

    //队列名称
    private static final String QUEUE_NAME = "LEVMIN_QUEUE";

    //路由键
    private static final String ROUTING_KEY = "LEVMIN_ROUTING_KEY";



//    public static void main(String[] args) {
////        rabbitmqConn();
//
//        try {
//            RabbitMQUtil rabbitMQUtil = new RabbitMQUtil();
//            Connection conn = rabbitMQUtil.getConn();
//
//            Channel channel = conn.createChannel();
//
//            //创建一个direct类型，持久化，不会自动删除的交换器
//            channel.exchangeDeclare(EXCHANGE_NAME, "direct",true,false,null);
//            //创建一个持久化，非排他性，非自动删除的队列
//            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//            //将交换器和队列通过路由键进行绑定
//            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
//
//            //添加监听器
//            channel.addReturnListener(new ReturnListener() {
//
//                @Override
//                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    System.out.println("replyCode=" + replyCode +
//                            " ; replyText=" + replyText +
//                            " ; exchange=" + exchange +
//                            " ; routingKey=" + routingKey
//                    );
//                    System.out.println("Basic.Return 返回未路由的消息：" + new String(body));
//                }
//            });
//
//            // 发送的时候，没有指定 ROUTING_KEY
//            channel.basicPublish(EXCHANGE_NAME,
//                    "",
//                    true,
//                    MessageProperties.PERSISTENT_TEXT_PLAIN,
//                    "mandatory test".getBytes()
//            );
//            TimeUnit.SECONDS.sleep(10);
//
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    public Connection getConn() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
            factory.setVirtualHost(virtualHost);
            factory.setUsername(username);
            factory.setPassword(password);
//            factory.setUsername("guest");
//            factory.setPassword("guest");
//            factory.setHost("192.168.92.128");
//            factory.setPort(5672);
//            factory.setVirtualHost("/");

            Connection connection = factory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String rabbitmqConn() throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("192.168.92.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");

//        factory.setUri("amqp://guest:guest@192.168.92.128:5672/virtualHost");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        boolean open = channel.isOpen();
        //声明交换器
        channel.exchangeDeclare("levmin", "direct", true);
        //声明队列
        String queue = channel.queueDeclare().getQueue();
        //声明一个持久化队列
//        channel.queueDeclare("LEVMIN", true, false, false, null);
        System.out.println(queue);

        //队列绑定
        //在在该 Connection 可用，同一个 Connection 的不同 Channel 可共用，在 Connection 断开时，会自动删除该队列。
        channel.queueBind(queue, "levmin", "aaaa");

        channel.basicPublish("levmin", "aaaa", null, "你好rabbitmq！".getBytes("UTF-8"));

        return "success";
    }
}
