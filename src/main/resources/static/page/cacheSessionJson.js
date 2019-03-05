// 基于准备好的dom，初始化echarts实例
// var myChart1 = echarts.init(document.getElementById('jsonmodel1'));

// 指定图表的配置项和数据

$.ajax({
  type: "GET",
  url: "../cachesession/jsontime",
  data: {},
  dataType: "json",
  success: function(data){
    for(var i = 0;i<data.length;i++){
      var option = {};
      option = data[i];
      // 使用刚指定的配置项和数据显示图表。
      var number = parseInt(i)+1;
      var idStr = 'jsonmodel'+number;
      var mychart = echarts.init(document.getElementById(idStr));
      mychart.setOption(option);
    }
    $.ajax({
      type: "GET",
      url: "../cachesession/redistime",
      data: {},
      dataType: "json",
      success: function(data){
        for(var i = 0;i<data.length;i++){
          var option = {};
          option = data[i];
          // 使用刚指定的配置项和数据显示图表。
          var number = parseInt(i)+1;
          var idStr = 'redismodel'+number;
          var mychart = echarts.init(document.getElementById(idStr));
          mychart.setOption(option);
        }
      }
    });
  }
});

