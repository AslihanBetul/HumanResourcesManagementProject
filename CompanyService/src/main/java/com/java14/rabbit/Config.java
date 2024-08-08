//package com.java14.rabbit;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Config {
//    private final String directExchange = "directExchange";
//    private final String queueSaveCompanyId = "queueSaveCompanyId";
//    private final String keySaveCompanyId = "keySaveCompanyId";
//
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange(directExchange);
//    }
//
//    @Bean
//    public Queue queueManagerMail() {
//        return new Queue(queueManagerMail);
//    }
//    @Bean
//    public Queue queueEmployeeMail() {
//        return new Queue(queueEmployeeMail);
//    }
//
//
//
//    @Bean
//    public Binding bindingManagerMail() {
//        return BindingBuilder.bind(queueManagerMail()).to(directExchange()).with(keyManagerMail);
//    }
//    @Bean
//    public Binding bindingEmployeeMail() {
//        return BindingBuilder.bind(queueEmployeeMail()).to(directExchange()).with(keyEmployeeMail);
//    }
//
//    @Bean
//    MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//
//    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter());
//        return rabbitTemplate;
//    }
//}
