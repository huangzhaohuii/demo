package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.demo.utils.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
