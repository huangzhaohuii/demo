package com.example.demo.rabbitmq;

import cn.hutool.core.date.DateUtil;
import com.example.demo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Administrator
 * @create 2020/3/27
 * @Description: 生产者
 * @since 1.0.0
 */
@RestController
@RequestMapping("/te")
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("/testtest")
    public void send(){
        String content = "hello +" + new Date();
        System.out.println("sender:"+ content);
        this.rabbitTemplate.convertAndSend("hello",content);
    }

    public void send2(){
        String content = "hello +" + DateUtil.now();
        System.out.println("sender:"+ content);
        for (int i = 0; i < 10 ; i++) {
            this.rabbitTemplate.convertAndSend("hello",content);
        }
    }

    public void send3(User user) {
        System.out.println("Sender object: " + user.toString());
        this.rabbitTemplate.convertAndSend("hello", user);
    }
}
