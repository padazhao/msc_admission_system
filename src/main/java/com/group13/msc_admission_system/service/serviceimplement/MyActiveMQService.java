package com.group13.msc_admission_system.service.serviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.*;

@Service
public class MyActiveMQService {

    private final JmsTemplate jmsTemplate;
    private final Topic statusUpdateTopic;
    private Connection connection;
    private Session session;
    private TopicSubscriber topicSubscriber;
    private final Map<String, List<String>> id_NotificationMap = new HashMap<>();



    @Autowired
    public MyActiveMQService(JmsTemplate jmsTemplate, Topic statusUpdateTopic) {
        this.jmsTemplate = jmsTemplate;
        this.statusUpdateTopic = statusUpdateTopic;
    }

    public void publishNotification(String applicantId, String statusMessageUpdate) {
        jmsTemplate.send(statusUpdateTopic, session -> {
            //CREATE A TEXT MESSAGE
            Message messageToSend = session.createTextMessage(statusMessageUpdate);
            //CREATE SELECTOR USING ID
            messageToSend.setStringProperty("id", applicantId);
            return messageToSend;
        });
        System.out.println("Status Update Message:" + statusMessageUpdate + " has been successfully sent to Topic");
    }

    public void consumeNotification(String applicantId) {
        try {
            connection = jmsTemplate.getConnectionFactory().createConnection();
            connection.setClientID(applicantId);
            session =connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            startSubscriber(applicantId);
            try {
                Thread.sleep(1000); // Wait for 5 seconds
            } catch (InterruptedException e) {
                // Handle exception
            }
            //closeSubscriber();
            this.topicSubscriber.close();
            System.out.println("Closing subscriber");
            session.close();
            connection.stop();
            connection.close();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private class ApplicantMessageListener implements MessageListener{

        private final List<String> messageList = new ArrayList<>();

        @Override
        public void onMessage(Message message) {
            String id = null;

            if (message instanceof TextMessage textMessage) {
                System.out.println("Got receiveMessage published by Publisher : " + textMessage);
                try {

                    messageList.add(textMessage.getText());
                    id= message.getStringProperty("id");
                    message.acknowledge();
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("text receiveMessage only");
            }
            if(id_NotificationMap.get(id)!=null){
                id_NotificationMap.get(id).addAll(messageList);
            }
            else {
                id_NotificationMap.put(id,messageList);
            }

            //id_NotificationMap.put(id,messageList);
        }
    }
    public void startSubscriber(String applicantId){
        System.out.println("Starting subscriber");
        try {
            connection.stop();
            String selector = "id='" + applicantId + "'";
            topicSubscriber = session.createDurableSubscriber(statusUpdateTopic,applicantId,selector,true);
            topicSubscriber.setMessageListener(new ApplicantMessageListener());
            connection.start();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }
    public void closeSubscriber() {
            System.out.println("Closing subscriber");
        try {
            topicSubscriber.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getNotification(String id){
        return id_NotificationMap.get(id);
    }


}
