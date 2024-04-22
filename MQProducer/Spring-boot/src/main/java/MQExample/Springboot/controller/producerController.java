package MQExample.Springboot.controller;

import MQExample.Springboot.dbo.customer;
import MQExample.Springboot.service.publisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class producerController {

    @Autowired
    private publisherService publisher;
    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try {
            for(int i=0 ; i<10000;i++) {
                publisher.sendMessageToTopic(message+":"+i);
            }
            return ResponseEntity.ok("message published successfully..");
        }catch(Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
