package com.ravi.queue.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        for (int i = 1; i <= 5; i++) {
            String message = "TopicPublisher message: " + (i);
            channel.basicPublish(
                    "Topic-Exchange", "tv.mobile.ac",
                    null, message.getBytes()
            );
        }

        System.out.println("Waiting for message - Exit");
        channel.close();
        connection.close();

    }
}
