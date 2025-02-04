package com.ravi.queue.exchange.headers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class HeadersConsumer2 {

    private static  final Logger LOGGER = LoggerFactory.getLogger(HeadersConsumer2.class);

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody());

            LOGGER.info("Header Ac Consumer {}", receivedMessage);

        };
        channel.basicConsume(
                "Ac",
                true,
                deliverCallback, consumerTag -> {
                    LOGGER.info("Message Tag {}", consumerTag);
                }

        );
    }
}
