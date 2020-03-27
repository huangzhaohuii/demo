package com.example.demo.rabbitmq;

import com.example.demo.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @create 2020/3/27
 * @Description: 消费者
 * @since 1.0.0
 */
@Component
public class HelloReceiver {

//    @RabbitListener(queues = "hello")
//    public void process(String hello){
//        System.out.println("receiver1:"+hello);
//    }


    @RabbitListener(queues = "hello")
    public void process2(User user){
        System.out.println("receiver3 = "+user.toString());
    }
}
