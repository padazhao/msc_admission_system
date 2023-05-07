package com.group13.msc_admission_system.controller;

//import com.group13.msc_admission_system.config.StudentMessageReceiver;

import com.group13.msc_admission_system.service.serviceimplement.MyActiveMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final MyActiveMQService myActiveMQService;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public TestController(MyActiveMQService myActiveMQService){
        this.myActiveMQService = myActiveMQService;
    }

    // USED SEND A MESSAGE
    @GetMapping("/send_message/{message}/{id}")
    public String sendMessage(@PathVariable("message") String message,@PathVariable("id") String id) {
        myActiveMQService.publishNotification(id,message);
        return message;
    }

    // USED RECEIVE A MESSAGE
    @GetMapping("/receive_message/{id}") // USED SHOW A REGISTRATION FORM
    public List<String> receiveMessage(@PathVariable("id") String id){
        myActiveMQService.consumeNotification(id);
        return myActiveMQService.getNotification(id);
    }
}
