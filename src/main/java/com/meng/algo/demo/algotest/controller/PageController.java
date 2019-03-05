package com.meng.algo.demo.algotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

//  @RequestMapping("/test")
//  public String test(){
//    return "/page/test";
//  }
//
//  @RequestMapping("/loMap")
//  public String loMap(){
//    return "page/loMap";
//  }
//  @RequestMapping("redisTest")
//  public String redisTest(){
//    return "page/redisTest";
//  }
//  @RequestMapping("redisGetValue")
//  public String redisGetValue(){
//    return "page/redisGetValue";
//  }
  @RequestMapping("{path}.html")
  public String path(@PathVariable(name = "path") String path){
    String returnStr = "page/"+path;
    return returnStr;
  }
}
