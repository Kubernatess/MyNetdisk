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
					<input class="input100" type="text" name="email" placeholder="用户名">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-user-circle" aria-hidden="true"></i>
					</span>
				</div>
				
				<font>当前用户不存在</font><br/>

				<div class="wrap-input100 validate-input">
					<input class="input100" type="password" name="pass" placeholder="密码">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-lock" aria-hidden="true"></i>
					</span>
				</div>
				<font>密码错误</font><br/>
				
				<input type="text" id="input-verify" placeholder="输入验证码" >
                
                <font>验证码有误</font>
                
                <img src="/MyNetdisk/Verification" width=100 id="verification" onclick="changeVerification();">
				
				<div class="container-login100-form-btn">
					<button class="login100-form-btn">
						登陆
					</button>
				</div>

				<div class="text-center p-t-12">
					<a class="txt2" href="javascript:">
						忘记密码？
					</a>
				</div>

				<div class="text-center p-t-100">
					<a class="txt2" href="/MyNetdisk/register.html" target="_blank">
							还没有账号？立即注册
						<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
					</a>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
function changeVerification(){
	var verification=document.getElementById('verification');
	verification.src="/MyNetdisk/Verification?time="+new Date().getTime();
}
</script>
</body>
</html>