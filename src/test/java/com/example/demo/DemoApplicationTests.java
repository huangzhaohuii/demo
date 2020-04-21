package com.example.demo;

//import org.junit.jupiter.api.Test;
import com.example.demo.model.User;
import com.example.demo.rabbitmq.HelloSender;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Autowired
//    private HashOperations<String, String, Object> hashOperations;



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

    @Test
    public void test4() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }

    @Test
    public void test6(){
        User user = new User("2","1");
        List<User> users = Arrays.asList(user);
        System.out.println(users);
    }
}
