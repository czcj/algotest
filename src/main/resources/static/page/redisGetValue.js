$("#submit").click(function () {
  var key = $("#key").val();
  $.ajax({
    type: "GET",
    url: "../json/getValueByKey",
    data: {"key":key},
    dataType: "json",
    success: function(data){

      $("#value").html(formatJson(data));
      // $("#value").html("123123123");
    }
  });
});
function formatJson(msg) {
  var rep = "~";
  var jsonStr = JSON.stringify(msg, null, rep)
  var str = "";
  for (var i = 0; i < jsonStr.length; i++) {
    var text2 = jsonStr.charAt(i)
    if (i > 1) {
      var text = jsonStr.charAt(i - 1)
      if (rep != text && rep == text2) {
        str += "<br/>"
      }
    }
    str += text2;
  }
  jsonStr = "";
  for (var i = 0; i < str.length; i++) {
    var text = str.charAt(i);
    if (rep == text)
      jsonStr += "        "
    else {
      jsonStr += text;
    }
    if (i == str.length - 2)
      jsonStr += "<br/>"
  }
  return jsonStr;
}

