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
    private final String queueManagerMail = "queueManagerMail";
    private final String keyManagerMail = "keyManagerMail";
    private final String queueEmployeeMail = "queueEmployeeMail";
    private final String keyEmployeeMail = "keyEmployeeMail";


    private final String queueEmployeeMailLeave = "queueEmployeeMailLeave";
    private final String keyEmployeeMailleave = "keyEmployeeMailleave";

    private final String queueApproveLeave = "queueApproveLeave";
    private final String keyApproveMailleave = "keyApproveMailleave";

    private final String queueDissapproveLeave = "queueDissapproveLeave";
    private final String keyDissapproveMailleave = "keyDissapproveMailleave";

    private final String keyForgetPasswordMail= "keyForgetPasswordMail";
    private final String queueForgetPasswordMail= "queueForgetPasswordMail";


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public Queue queueManagerMail() {
        return new Queue(queueManagerMail);
    }
    @Bean
    public Queue queueEmployeeMail() {
        return new Queue(queueEmployeeMail);
    }
    @Bean
    public Queue queueLeaveMail() {
        return new Queue(queueEmployeeMailLeave);
    }
    @Bean
    public Queue queueApproveLeaveMail() {
        return new Queue(queueApproveLeave);
    }
    @Bean
    public Queue queueDissapproveLeaveMail() {
        return new Queue(queueDissapproveLeave);
    }
    @Bean
    public Queue queueForgetPasswordMail() {
        return new Queue(queueForgetPasswordMail);
    }




    @Bean
    public Binding bindingManagerMail() {
        return BindingBuilder.bind(queueManagerMail()).to(directExchange()).with(keyManagerMail);
    }
    @Bean
    public Binding bindingEmployeeMail() {
        return BindingBuilder.bind(queueEmployeeMail()).to(directExchange()).with(keyEmployeeMail);
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
    public Binding bindingForgetPasswordMail() {
        return BindingBuilder.bind(queueForgetPasswordMail()).to(directExchange()).with(keyForgetPasswordMail);
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
