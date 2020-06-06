$(function () {


  $("#p1").click(function () {
    var id = $("#id").val();
    var pwd = $("#pwd").val();
    var kind = $(".check");

    //利用循环获取checked
    for(var i = 0;i <$(".check").length;i++){
      if($(".check")[i].checked){
        var kinds = kind[i].value;
      }
    }

    if(id === "" || id == null){
      alert("用户名不能为空！！！");
    }
    else if(id !== "" && pwd === ""){
      alert("密码为空！！！")
    }
    if(id !== "" && pwd !== ""){
      if(kinds === "学生"){
        $.get(
        	"loginServlet",
        	{id:id,pwd:pwd,kinds:kinds},
        	function(response,status) {
				if(response.state == "SUC"){
					location.href = "Stumsg.html?tid="+encodeURI(id)+"";
				}
				else{
					alert(response.msg);
				}
			}
        );
      }
      else if(kinds === "老师"){
    	  $.get(
    	        	"loginServlet",
    	        	{id:id,pwd:pwd,kinds:kinds},
    	        	function(response,status) {
    					if(response.state == "SUC"){
    						location.href = "Teamsg.html?tid="+encodeURI(id)+"";
    					}
    					else{
    						alert(response.msg);
    					}
    				}
    	        );
      }
      else if(kinds === "管理员"){
    	  $.get(
    	        	"loginServlet",
    	        	{id:id,pwd:pwd,kinds:kinds},
    	        	function(response,status) {
    					if(response.state == "SUC"){
    						location.href = "adminmsg.html?tid="+encodeURI(id)+"";
    					}
    					else{
    						alert(response.msg);
    					}
    				}
    	        );
      }
    }
  });

})
