package com.meng.algo.demo.algotest;

import com.alibaba.fastjson.JSON;
import com.meng.algo.demo.algotest.pojo.CacheSession;
import com.meng.algo.demo.algotest.service.CacheSessionService;
import com.meng.algo.demo.algotest.service.impl.CacheSessionServiceImpl;
import com.meng.algo.demo.algotest.util.CacheSessionStr;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.msgpack.MessagePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgotestApplicationTests {

  @Autowired
  CacheSessionService cacheSessionService;
  @Autowired
  MessagePack messagePack;
  @Test
  public void contextLoads() throws IOException {
    MessagePack messagePack = new MessagePack();
    CacheSession cacheSession = JSON.parseObject(CacheSessionStr.cacheSession, CacheSession.class);
    CacheSession cacheSession1 = JSON.parseObject(CacheSessionStr.cacheSession, CacheSession.class);
    for(int i=1;i<=1200;i++){
      if(i%100 == 0){
        CacheSession cacheSessionByLoNum = cacheSessionService
            .getCacheSessionByLoNum(cacheSession, cacheSession1, i);
        String s = JSON.toJSONString(cacheSessionByLoNum);
        int i1 = s.getBytes().length / 1024;
        byte[] write = messagePack.write(cacheSessionByLoNum);
        int i2 = write.length / 1024;
        System.out.println("json:"+i1+"||"+"mpage:"+i2);
      }
    }
    CacheSessionServiceImpl cacheSessionService = new CacheSessionServiceImpl();
//    CacheSession cacheSessionByLoNum = cacheSessionService.getCacheSessionByLoNum(10);
  }

}

