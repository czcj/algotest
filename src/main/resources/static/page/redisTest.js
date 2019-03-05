// 基于准备好的dom，初始化echarts实例
var writeChart = echarts.init(document.getElementById('writeDiv'));
var updateChart = echarts.init(document.getElementById('updateDiv'));
var readChart = echarts.init(document.getElementById('readDiv'));
var writeArrChart = echarts.init(document.getElementById('writeArrDiv'));
var updateArrChart = echarts.init(document.getElementById('updateArrDiv'));
var readArrChart = echarts.init(document.getElementById('readArrDiv'));

// 指定图表的配置项和数据
var writeoption = {};
var updateoption = {};
var readoption = {};
var writearroption = {};
var updatearroption = {};
var readarroption = {};
$("#sure1").click(function(){
  var size = $("#size").val();
  $.ajax({
    type: "GET",
    url: "../json/redisWriteTest?size="+size,
    data: {},
    dataType: "json",
    success: function(data){
      writeoption = data;
      // console.log(option)
// 使用刚指定的配置项和数据显示图表。
      writeChart.setOption(writeoption);
      $.ajax({
        type: "GET",
        url: "../json/redisUpdateTest?size="+size,
        data: {},
        dataType: "json",
        success: function(data){
          updateoption = data;
          // console.log(option)
// 使用刚指定的配置项和数据显示图表。
          updateChart.setOption(updateoption);
          $.ajax({
            type: "GET",
            url: "../json/redisReadTest?size="+size,
            data: {},
            dataType: "json",
            success: function(data){
              readoption = data;
              // console.log(option)
// 使用刚指定的配置项和数据显示图表。
              readChart.setOption(readoption);
            }
          });
        }
      });
    }
  });
});
$("#sure2").click(function(){
  var size = $("#size").val();
  $.ajax({
    type: "GET",
    url: "../json/redisArrWriteTest?size="+size,
    data: {},
    dataType: "json",
    success: function(data){
      writearroption = data;
      // console.log(option)
// 使用刚指定的配置项和数据显示图表。
      writeArrChart.setOption(writearroption);
      $.ajax({
        type: "GET",
        url: "../json/redisArrUpdateTest?size="+size,
        data: {},
        dataType: "json",
        success: function(data){
          updatearroption = data;
          // console.log(option)
// 使用刚指定的配置项和数据显示图表。
          updateArrChart.setOption(updatearroption);
          $.ajax({
            type: "GET",
            url: "../json/redisArrReadTest?size="+size,
            data: {},
            dataType: "json",
            success: function(data){
              readarroption = data;
              // console.log(option)
// 使用刚指定的配置项和数据显示图表。
              readArrChart.setOption(readarroption);
            }
          });
        }
      });
    }
  });
});

