package com.ravi.queue.direct_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        for (int i=1 ; i<=25; i++) {
            String message = "Direct message - Mobile: "+ (i*100);
            channel.basicPublish(
                    "Direct-Exchange",
                    "mobile",
                    null,
                    message.getBytes()
            );
        }

        System.out.println("Waiting for Direct message - Mobile to Exit");
        channel.close();
        connection.close();

    }
}
