package com.ravi.queue.with_spring;

import com.ravi.queue.with_spring.enitity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PersonConsumer {

    private static  final Logger LOGGER = LoggerFactory.getLogger(PersonConsumer.class);

    @RabbitListener(queues = "Person", messageConverter = "converter")
    public void getPerson(Person person) {
        LOGGER.info("Person Consumer- {}", person);
    }
}

