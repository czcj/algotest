package com.meng.algo.demo.algotest.controller;

import com.meng.algo.demo.algotest.service.RedisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetRedisValueController {

  @Autowired
  private RedisTestService redisTestService;

  @RequestMapping("/json/getValueByKey")
  public String getValue(String key){
    return redisTestService.getValueByKey(key);
  }
}
