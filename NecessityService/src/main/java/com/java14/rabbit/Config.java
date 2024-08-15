package com.java14.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final String directExchange = "directExchange";

    private final String queueEmployeeMailLeave = "queueEmployeeMailLeave";
    private final String keyEmployeeMailleave = "keyEmployeeMailleave";

    private final String queueApproveLeave = "queueApproveLeave";
    private final String keyApproveMailleave = "keyApproveMailleave";

    private final String queueDissapproveLeave = "queueDissapproveLeave";
    private final String keyDissapproveMailleave = "keyDissapproveMailleave";


    @Bean
    public Queue queueApproveLeaveMail() {
        return new Queue(queueApproveLeave);
    }
    @Bean
    public Queue queueDissapproveLeaveMail() {
        return new Queue(queueDissapproveLeave);
    }
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public Queue queueLeaveMail() {
        return new Queue(queueEmployeeMailLeave);
    }




    @Bean
    public Binding bindingLeaveMail() {
        return BindingBuilder.bind(queueLeaveMail()).to(directExchange()).with(keyEmployeeMailleave);
    }

    @Bean
    public Binding bindingApproveLeaveMail() {
        return BindingBuilder.bind(queueApproveLeaveMail()).to(directExchange()).with(keyApproveMailleave);
    }
    @Bean
    public Binding bindingDissapproveLeaveMail() {
        return BindingBuilder.bind(queueDissapproveLeaveMail()).to(directExchange()).with(keyDissapproveMailleave);
    }


    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
