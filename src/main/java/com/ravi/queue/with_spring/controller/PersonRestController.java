package com.ravi.queue.with_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravi.queue.with_spring.enitity.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonRestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{name}")
    String getPersonName(@PathVariable String name) throws JsonProcessingException {
        Person person = new Person(name);
        //rabbitTemplate.convertAndSend("Person", person);

        //send it to other exchange too - for testing
        //rabbitTemplate.convertAndSend("Direct-Exchange", "person", person);
        //rabbitTemplate.convertAndSend("Fanout-Exchnage", "", person);
        //rabbitTemplate.convertAndSend("Topic-Exchange", "ac.person.mobile", person);

        //to header exchange - convert object to byte array
        Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(person))
                //.setHeader("x-match", "any")
                .setHeader("isPerson", true)
                .build();
        rabbitTemplate.send("Headers-Exchange", "", message);

        return "Welcome ! "+name.toUpperCase();
    }
}
