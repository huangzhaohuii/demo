package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.R;
import com.example.demo.model.Address;
import com.example.demo.model.EmsInfo;
import com.example.demo.model.OrderNormal;
import com.example.demo.utils.BeanValidatorUtil;
import com.example.demo.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @create 2020/2/21
 * @Description:
 * @since 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("/orderApi")
public class EmsController {

    @PostMapping("/createOrder")
    public R create(@RequestBody JSONObject json){
        String mailNo = "";
        EmsInfo emsInfo = JSON.toJavaObject(json, EmsInfo.class);
        //判断属性是否为空
        BeanValidatorUtil.validate(emsInfo);
        Address sender = new Address(emsInfo.getSenderName(),emsInfo.getSenderPostCode(),emsInfo.getSenderTelephone(),emsInfo.getSenderMobile(),emsInfo.getSenderProvince(),emsInfo.getSenderCity(),emsInfo.getSenderCountry(),emsInfo.getSenderAddress());
        Address receiver = new Address(emsInfo.getReceiverName(),emsInfo.getReceiverPostCode(),emsInfo.getReceiverTelephone(),emsInfo.getReceiverMobile(),emsInfo.getReceiverProvince(),emsInfo.getReceiverCity(),emsInfo.getReceiverCountry(),emsInfo.getReceiverAddress());
        OrderNormal order = new OrderNormal(emsInfo.getOrderId(),"1",sender,receiver);
        JSONArray jsonArray =new JSONArray();
        jsonArray.add(order);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNormals",jsonArray);
        System.out.println(jsonObject);
        String s = OrderUtil.putOrder(jsonObject);
        JSONObject result = JSONObject.parseObject(s);
        if(StrUtil.equals("T",result.getString("success"))){
            JSONArray responseOrders = result.getJSONArray("responseOrders");
            mailNo = responseOrders.getJSONObject(0).getString("mailNo");
        }
        return R.ok("success").put("mailNo",mailNo);
    }

    @PostMapping("/queryTrace")
    public R query(@RequestParam String mailNo){
        JSONArray traces = new JSONArray();
        String result = OrderUtil.getOrder(mailNo);
        JSONObject json = JSONObject.parseObject(result);
        if(StrUtil.equals("T",json.getString("success"))){
            traces = json.getJSONArray("traces");
        }
        return R.ok("success").put("traces",traces);
    }
}
