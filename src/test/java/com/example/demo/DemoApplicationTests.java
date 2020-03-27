package com.example.demo;

//import org.junit.jupiter.api.Test;
import com.example.demo.model.User;
import com.example.demo.rabbitmq.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private HelloSender helloSender;


    @Test
    public void hello(){
        helloSender.send();
    }

    @Test
    public void oneToMany(){
        helloSender.send2();
    }

    @Test
    public void test3(){
        helloSender.send3(new User("huang","20"));
    }
}
