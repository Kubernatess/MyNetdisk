<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
#input-verify{
	outline: #999 solid thick;
	height: 25px;
	margin-bottom: 10px;
}
#verification{
	display: inline;
	height: 50px;
	width: auto;
}
font{
	display:none;
	color:red;
}
</style>
<script>
//切换验证码图片
function changeVerification(){
	var verification=document.getElementById('verification');
	verification.onclick=function(){
		verification.src="/MyNetdisk/VerificationGraphics?time="+new Date().getTime();
	};
}

window.onload=function(){	
	//获取账号、密码、登陆按钮对象
	var oBtn=document.getElementById('login');
	var oUsername=document.getElementById('username');
	var oPassword=document.getElementById('pass');
	var oVerification=document.getElementById('input-verify');
	var arrFont=document.getElementsByTagName('font');
	//获取对象的值
	var username;
	var password;
	var verification;
	
	oUsername.onblur=oPassword.onblur=oVerification.onblur=function(){
		//获取对象的值
		username=oUsername.value;
		password=oPassword.value;
		verification=oVerification.value;
		if(username!="")
			arrFont[0].style.display="none";
		if(password!="")
			arrFont[2].style.display="none";
		if(verification!="")
			arrFont[4].style.display="none";
	};
	
	//点击提交按钮后
	oBtn.onclick=function(){
		//获取对象的值
		username=oUsername.value;
		password=oPassword.value;
		verification=oVerification.value;
		//如果用户名为空,显示font标签 否则不显示
		alert(arrFont[1].style.display=="none");
		if((username=="")&&(arrFont[1].style.display=="none")){
			arrFont[0].style.display="block";
			return;
		}
		
		//如果密码为空,显示font标签 否则不显示
		if((password=="")&&(arrFont[3].style.display=="none")){
			arrFont[2].style.display="block";
			return;
		}	
		alert(arrFont[5].style.display);
		//如果验证码为空,显示font标签 否则不显示
		if((verification=="")&&(arrFont[5].style.display=="none")){
			arrFont[4].style.display="block";
			alert('d');
			return;
		}	
		
	};
	
};
</script>
</head>
<body>
<body>
<div class="dowebok">
	<div class="container-login100">
		<div class="wrap-login100">
			<div class="login100-pic js-tilt" data-tilt>
				<img src="images/img-01.png" alt="IMG">
			</div>

			<form class="login100-form validate-form">
				<span class="login100-form-title">
					会员登陆
				</span>

				<div class="wrap-input100 validate-input">
					<input class="input100" type="text" name="username" id="username" placeholder="用户名">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-user-circle" aria-hidden="true"></i>
					</span>
				</div>
				
				<font>用户名不能为空</font>
				<font>当前用户不存在</font>

				<div class="wrap-input100 validate-input">
					<input class="input100" type="password" name="pass" id="pass" placeholder="密码">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-lock" aria-hidden="true"></i>
					</span>
				</div>
				<font>密码不能为空</font>
				<font>密码错误</font>
				
				<input type="text" id="input-verify" placeholder="输入验证码" >
                
                <font>验证码不能为空</font>
                <font>验证码有误</font>
                
                <img src="/MyNetdisk/VerificationGraphics" width=100 id="verification" onclick="changeVerification();">
				
				<div class="container-login100-form-btn">
					<input type="button" name="login" id="login" value="登陆" class="login100-form-btn">
				</div>

				<div class="text-center p-t-12">
					<a class="txt2" href="javascript:">
						忘记密码？
					</a>
				</div>

				<div class="text-center p-t-130">
					<a class="txt2" href="/MyNetdisk/register.html" target="_blank">
							还没有账号？立即注册
						<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
					</a>
				</div>
			</form>
		</div>
	</div>
</div>

</body>
</html>