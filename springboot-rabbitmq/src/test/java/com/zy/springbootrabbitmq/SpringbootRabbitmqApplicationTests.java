package com.zy.springbootrabbitmq;

import com.rabbitmq.client.*;
import com.zy.springbootrabbitmq.common.RabbitMQUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private RabbitMQUtil rabbitMQUtil;


    //交换器名称
    private static final String EXCHANGE_NAME = "LEVMIN_EXCHANGE";

    //队列名称
    private static final String QUEUE_NAME = "LEVMIN_QUEUE";

    //路由键
    private static final String ROUTING_KEY = "LEVMIN_ROUTING_KEY";


    @Test
    void contextLoads() {
    }

    /**
     * 消息发送
     */
    @Test
    void test001() {
        try {
//            RabbitMQUtil rabbitMQUtil = new RabbitMQUtil();
            Connection conn = rabbitMQUtil.getConn();

            Channel channel = conn.createChannel();

            //创建一个direct类型，持久化，不会自动删除的交换器
            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
            //创建一个持久化，非排他性，非自动删除的队列

            //设置消息的过期时间，在队列设置
            final Map<String, Object> map = new HashMap<>();
            map.put("x-message-ttl", 6000);


            channel.queueDeclare(QUEUE_NAME, true, false, false, map);
            //将交换器和队列通过路由键进行绑定
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

            //添加监听器
            channel.addReturnListener(new ReturnListener() {

                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("replyCode=" + replyCode +
                            " ; replyText=" + replyText +
                            " ; exchange=" + exchange +
                            " ; routingKey=" + routingKey
                    );
                    System.out.println("Basic.Return 返回未路由的消息：" + new String(body));
                }
            });
            final AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties().builder();
            builder.deliveryMode(2); // 持久化消息
            builder.expiration("60000");  //设置消息的 ttl
            // 发送的时候，没有指定 ROUTING_KEY
            channel.basicPublish(EXCHANGE_NAME,
                    "",
                    true,
                    //设置消息过期时间
                    builder.build(),
                    "mandatory test".getBytes()
            );
            TimeUnit.SECONDS.sleep(10);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void test002() throws IOException {
        Connection conn = rabbitMQUtil.getConn();
        //创建通道
        Channel channel = conn.createChannel();
        //定义一个死信交换器
        channel.exchangeDeclare("exchange.dlx", "direct",true,false,null);
        //定义一个死信队列
        channel.queueDeclare("queue.dlx", true, false, false, null);
        //将死信队列和死信交换器绑定
        channel.queueBind("queue.dlx", "exchange.dlx", "routingKey-dlx");


        //定义正常的交换器

        channel.exchangeDeclare("exchange.normal", "direct",true,false,null);
        final Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl",6000);
        //定义交换器的属性  使用参数”x-dead-letter-exchange“ 定义死信交换器
        map.put("x-dead-letter-exchange","exchange.dlx");
        //重新定义路由键，否则使用原来的路由键
        map.put("x-dead-letter-routing-key","routingKey-dlx");
        // 定义队列支持的最大级别
        map.put("x-max-priority",10);

        channel.queueDeclare("queue.normal", true, false, false, map);

        channel.queueBind("queue.normal", "exchange.normal", "");

        //发送消息的时候设置消息消费的优先级
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties().builder();
        //持久化消息
        builder.deliveryMode(2);
        //设置消息的优先级
        builder.priority(6);
        channel.basicPublish("exchange.normal","",builder.build(),"test".getBytes());
    }

}
