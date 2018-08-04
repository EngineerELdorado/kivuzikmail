//package com.api.KivuzikMail.configs;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
//import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
//
//@Configuration
//public class RabbitMqConfiguration implements RabbitListenerConfigurer {
//
//    public static final String KIVUZIK_EMAIL="KIVUZIK_EMAIL";
//
//    private final Environment environment;
//
//    @Autowired
//    public RabbitMqConfiguration(Environment environment) {
//        this.environment = environment;
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(environment.getRequiredProperty("rabbit.host"));
//        connectionFactory.setUsername(environment.getRequiredProperty("rabbit.username"));
//        connectionFactory.setPassword(environment.getRequiredProperty("rabbit.password"));
//        return connectionFactory;
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        template.setMessageConverter(jsonMessageConverter());
//        return template;
//    }
//
//    @Override
//    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
//        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
//    }
//
//    @Bean
//    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
//        return new MappingJackson2MessageConverter();
//    }
//
//    @Bean
//    MessageHandlerMethodFactory messageHandlerMethodFactory() {
//        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
//        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
//        return messageHandlerMethodFactory;
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setConcurrentConsumers(3);
//        factory.setDefaultRequeueRejected(false);
//        factory.setMaxConcurrentConsumers(10);
//        return factory;
//    }
//}
