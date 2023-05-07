package com.group13.msc_admission_system.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

@Configuration
@EnableJms
public class MyJmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String BROKER_URL;
    @Value("${spring.activemq.user}")
    private String BROKER_USERNAME;
    @Value("${spring.activemq.password}")
    private String BROKER_PASSWORD;
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        activeMQConnectionFactory.setBrokerURL(BROKER_URL);
        activeMQConnectionFactory.setPassword(BROKER_PASSWORD);
        activeMQConnectionFactory.setUserName(BROKER_USERNAME);
        return activeMQConnectionFactory;
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("notifation-topic");
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(true);
        template.setDeliveryMode(DeliveryMode.PERSISTENT);
        template.setDeliveryPersistent(true);
        template.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        template.setExplicitQosEnabled(true);
        template.setTimeToLive(60000);
        template.setPubSubNoLocal(true);
        return template;
    }

//    @Bean
//    public DefaultMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setClientId("default");
//        container.setConnectionFactory(connectionFactory);
//        container.setDestination(topic());
//
//        container.setPubSubDomain(true);
//        return container;
//    }


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory container = new DefaultJmsListenerContainerFactory();
        container.setClientId("default");
        container.setConnectionFactory(connectionFactory);
        container.setPubSubDomain(true);
        return container;
    }

    //    @Bean
    //    public Connection jmsConnection() throws JMSException {
    //        Connection connection = connectionFactory().createConnection();
    //        //connection.start();
    //        return connection;
    //    }
//    @Bean
//    public DefaultMessageListenerContainer jmsContainer(ConnectionFactory connectionFactory) {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setDestinationName("status-update");
//        //container.setMessageListener(listenerAdapter);
//        container.setSessionTransacted(true);
//        container.setSubscriptionDurable(true);
//        container.setClientId("myClientId");
//        container.setSubscriptionShared(true);
//        container.setConcurrentConsumers(1);
//        container.setCacheLevel(DefaultMessageListenerContainer.CACHE_NONE);
//        container.setPubSubDomain(true);
//        return container;
//    }

}
