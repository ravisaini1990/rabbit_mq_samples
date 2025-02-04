package com.ravi.queue.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

///  Key is not required , as everu msg will deliver to every queue
public class FanOutPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        for (int i=1 ; i<=25; i++) {
            String message = "FanOutPublisher message - Mobile: "+ (i*100);
            channel.basicPublish(
                    "Fanout-Exchnage",
                    "",
                    null,
                    message.getBytes()
            );
        }

        System.out.println("Waiting for Direct message - Mobile to Exit");
        channel.close();
        connection.close();

    }
}
