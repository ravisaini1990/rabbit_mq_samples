package com.ravi.queue.exchange.headers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


public class HeadersPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        for (int i=1 ; i<=5; i++) {
            String message = "HeadersPublisher message: "+ (i);

            Map<String, Object> mobileMap = new HashMap<>();
            mobileMap.put("isAc", true);
            mobileMap.put("isMobile", true);
            //mobileMap.put("x-match", "any");

            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder().headers(mobileMap).build();
            channel.basicPublish(
                    "Headers-Exchange",
                    "",
                    basicProperties, message.getBytes()
            );
        }

        System.out.println("Waiting for message - Exit");
        channel.close();
        connection.close();

    }
}
