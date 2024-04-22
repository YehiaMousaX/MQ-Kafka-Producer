package MQExample.Springboot.service;

import MQExample.Springboot.dbo.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
public class publisherService {
    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("Topic1", message);
        future.whenComplete((result, ex) ->
        {
            if (ex == null) {
                System.out.println("Sent message=[" + message + "]with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message =[" +
                        message + "] due to :" + ex.getMessage());
            }
        });
    }

    public void sendObjectToTopic(customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("Topic1", customer);
            future.whenComplete((result, ex) ->
            {


                if (ex == null) {
                    System.out.println("Sent message=[" + customer + "]with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message =[" +
                            customer + "] due to :" + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println("ERROR" + ex.getMessage());
        }
    }
}
