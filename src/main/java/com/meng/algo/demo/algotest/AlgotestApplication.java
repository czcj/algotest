package com.meng.algo.demo.algotest;

import org.msgpack.MessagePack;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class AlgotestApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(AlgotestApplication.class, args);
  }

  @Bean
  public MessagePack getMessagePack(){
    return new MessagePack();
  }

//  @Bean
//  public ServerEndpointExporter serverEndpointExporter(){
//    return new ServerEndpointExporter();
//  }
}

