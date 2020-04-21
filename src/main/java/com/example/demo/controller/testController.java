package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.demo.model.User;
import com.example.demo.utils.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author Administrator
 * @create 2019/12/17
 * @Description:
 * @since 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("/demo")
public class testController {

    @PostMapping("/test")
    public String test(String emsid,String url,String appkey,String appsecret,String authorization,String method){
        if("create".equals(method)){
            log.info(">>>>>>查询物流轨迹开始");
            String result = Order.putOrder(url,appkey,appsecret,authorization);
            log.info("调总部接口的result为："+result);
            return result;
        }else if("query".equals(method)){
            if(StrUtil.isBlank(emsid)){
                return "{\"code\":1,\"msg\":\"请输入正确的邮件号\"}";
            }
            log.info(">>>>>>查询物流轨迹开始");
            String result = Order.getOrder(emsid,url,appkey,appsecret,authorization);
            log.info("调总部接口的result为："+result);
            return result;
        }else{
            return "参数错误";
        }
    }

    @RequestMapping("/getUser")
    @Cacheable(value="user-key",key = "#tagId+'_'+#zz")
    public User getUser(Long tagId,String zz) {
        User user=new User("aa@126.com", "aa");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

    public static void main(String[] args) {
        User user = new User("2","1");
        List<User> users = Arrays.asList(user);
//        System.out.println(users);
        Map<String,Object> map = new HashMap<>();
        map.put("a","aa");
        map.put("b","bb");
        map.put("c","cc");
        Map<String,Object> map2 = new HashMap<>();
        map2.put("a","11");
        map2.put("b","22");
        map2.put("c","22");
        List<Map<String,Object>> maps = new ArrayList<>();
        maps.add(map);
        maps.add(map2);
        System.out.println(maps);
        List<List<Object>> rows = new ArrayList<>();
        for (Map<String,Object> a: maps) {
            System.out.println(a);
//            List<Object> llist = Collections.singletonList(a.values());
//            List<Object> llist =new ArrayList(a.values());
//            System.out.println(llist);
            List<Collection<Object>> collections = Arrays.asList(a.values());
            Collection<Object> values = a.values();
            ArrayList arrayList = new ArrayList(a.values());
            List<Object> row = Collections.singletonList(a.values());
            System.out.println(Collections.singletonList(a.values()));
            System.out.println(Arrays.asList(a.values()));
            System.out.println(new ArrayList(a.values()));
//            row.addAll(llist);
            rows.add(row);
        }
        System.out.println(rows);
    }
}
