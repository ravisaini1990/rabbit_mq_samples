package com.ravi.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        for (int i=0 ; i<5; i++) {
            String message = "First message from Rabbit MQ with Pos: "+ i;
            channel.basicPublish(
                    "",
                    "Queue-1",
                    null,
                    message.getBytes()
            );
        }

        System.out.println("Waiting for Message to Exit &&&&&&&&&");
        channel.close();
        connection.close();

    }
}
