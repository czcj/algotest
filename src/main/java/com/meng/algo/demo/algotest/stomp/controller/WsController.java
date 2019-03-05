package com.meng.algo.demo.algotest.stomp.controller;

import com.meng.algo.demo.algotest.stomp.listener.WiselyMessage;
import com.meng.algo.demo.algotest.stomp.sender.WiselyResponse;
import com.meng.algo.demo.algotest.util.WebSocketData;
import java.util.concurrent.TimeUnit;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WsController {
  @MessageMapping("/welcome")
  @SendTo("/topic/getResponse")
  public WiselyResponse say(WiselyMessage message){
    while(WebSocketData.data == null){
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return new WiselyResponse("welcome,"+WebSocketData.data+"!");
//    return new WiselyResponse("welcome,"+message.getName()+"!");
  }

  @RequestMapping("/welcome")
  public String welcome(){
    return "/page/ws";
  }
}
