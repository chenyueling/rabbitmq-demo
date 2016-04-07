package com.chenyueling.rabbitmq.demo;

/**
 * 　　　　　　　 ┏┓   ┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　  ┃　　　┃
 * 　　　　　　　  ┃　　　┃ + + + +
 * 　　　　　　　  ┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　  ┃　　　┃ + 　　　　神兽保佑,代码无BUG
 * 　　　　　　　  ┃　　　┃
 * 　　　　　　　  ┃　　　┃　　+
 * 　　　　　　　  ┃　 　　┗━━━┓ + +
 * 　　　　　　　  ┃ 　　　　　　　┣┓
 * 　　　　　　　  ┃ 　　　　　　　┏┛
 * 　　　　　　　  ┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　┃┫┫ ┃┫┫
 * 　　　　　　　　　┗┻┛ ┗┻┛+ + + +
 * Created by Paul on 2016/4/7.
 */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        for (int i = 0; i < 10000000; i++) {
            // AMQP的连接其实是对Socket做的封装, 注意以下AMQP协议的版本号，不同版本的协议用法可能不同。

            Connection connection = RabbitFactory.getConnection();
            // 下一步我们创建一个channel, 通过这个channel就可以完成API中的大部分工作了。
            Channel channel = connection.createChannel();

            // 为了发送消息, 我们必须声明一个队列，来表示我们的消息最终要发往的目的地。
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            // 然后我们将一个消息发往这个队列。
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[" + message + "]");

            // 最后，我们关闭channel和连接，释放资源。
            channel.close();
            connection.close();
        }
    }
}