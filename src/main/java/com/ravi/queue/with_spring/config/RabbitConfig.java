package com.ravi.queue.with_spring.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJackson2MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /*@Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }*/

    @Bean
    public RabbitTemplate jsonTemplate(ConnectionFactory connectionFactory, AbstractJackson2MessageConverter jackson2MessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2MessageConverter);
        return rabbitTemplate;
    }

    @Bean(name = "converter")
    AbstractJackson2MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
