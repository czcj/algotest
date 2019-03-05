package com.meng.algo.demo.algotest.controller;

import com.meng.algo.demo.algotest.util.WebSocketData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/python")
public class PythonDataView {
  @RequestMapping("/data")
  public String pythonData(Model model,String data){
    System.out.println(data);
    if(data != null){
      WebSocketData.data = data;
    }
    return "page/python";
  }
}
