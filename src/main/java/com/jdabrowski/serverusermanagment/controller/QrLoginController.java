package com.jdabrowski.serverusermanagment.controller;

import com.jdabrowski.serverusermanagment.model.OutputMessage;
import com.jdabrowski.serverusermanagment.model.User;
import com.jdabrowski.serverusermanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class QrLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;


    ExecutorService executorService = Executors.newSingleThreadExecutor();


    @PostMapping("/api/userqrlogin/{token}")
    public ResponseEntity<?> qrLoginWithToken(User user, @PathVariable("token") String token) {
        User user1 = userService.findUserByIdAndUsername(user);
        if (user1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final OutputMessage message = new OutputMessage();
        executorService.submit(() -> {
            message.setToken(token);
            message.setUser(user1);
            jobEnd(token, message);
        });
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/api/user/clientLogin")
    public ResponseEntity<?> qrLoginClientAuthentication(@RequestBody User user) {
        User user1 = userService.findUserByIdAndUsername(user);
        if (user1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private void jobEnd(String token, OutputMessage outputMessage) {
        simpMessageSendingOperations.convertAndSend("/queue/message-" + token, outputMessage);
    }


}
