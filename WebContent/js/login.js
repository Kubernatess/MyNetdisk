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
	var oPassword=document.getElementById('password');
	var oVerification=document.getElementById('input-verify');
	var arrFont=document.getElementsByTagName('font');
	//获取对象的值
	var username=oUsername.value;
	var password=oPassword.value;
	var verification=oVerification.value;
	
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
		
		arrFont[1].style.display="none";
		arrFont[3].style.display="none";
		arrFont[5].style.display="none";
		
	};
	
	//点击提交按钮后
	oBtn.onclick=function(){
		//获取对象的值
		username=oUsername.value;
		password=oPassword.value;
		verification=oVerification.value;
		//如果用户名为空,显示font标签 否则不显示
		if((username=="")&&(arrFont[1].style.display=="none")){
			arrFont[0].style.display="block";
			return;
		}
		
		//如果密码为空,显示font标签 否则不显示
		if((password=="")&&(arrFont[3].style.display=="none")){
			arrFont[2].style.display="block";
			return;
		}	
		//如果验证码为空,显示font标签 否则不显示
		if((verification=="")&&(arrFont[5].style.display=="none")){
			arrFont[4].style.display="block";
			return;
		}			
	
		//Ajax判断账号名是否存在
		if(username!=""){
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if((eval(xhr.responseText)==0)&&(arrFont[0].style.display=="none")){
						//如果用户名不存在,显示font标签
						arrFont[1].style.display="block";
						return;
					}
					else{
						//用户名存在,隐藏font标签
						arrFont[1].style.display="none";
					}
				}
			};
			xhr.open("get","/MyNetdisk/UsernameServlet?username="+username);
			xhr.send(null);
		}
		
		
		//Ajax判断密码是否正确
		if(password!=""){
			var xhr2=new XMLHttpRequest();
			xhr2.onreadystatechange=function(){
				if(xhr2.readyState==4){
					if((eval(xhr2.responseText)==0)&&(arrFont[2].style.display=="none")){
						//如果密码不正确,显示font标签
						arrFont[3].style.display="block";
						return;
					}
					else{
						//密码正确,隐藏font标签
						arrFont[3].style.display="none";
					}
				}
			};
			xhr2.open("get","/MyNetdisk/confirmServlet?username="+username+"&password="+password);
			xhr2.send(null);
		}
		
		
		if(verification!=""){
			//Ajax判断验证码是否正确
			var xhr3=new XMLHttpRequest();
			xhr3.onreadystatechange=function(){
				if(xhr3.readyState==4){
					if((eval(xhr3.responseText)==0)&&(arrFont[4].style.display=="none")){
						//如果验证码输入不正确,显示font标签
						arrFont[5].style.display="block";
						return;
					}
					else{
						//如果验证码正确,隐藏font标签
						arrFont[5].style.display="none";
					}
				}
			};
			xhr3.open("get","/MyNetdisk/VerificationServlet?verification="+verification);
			xhr3.send(null);
		}
		
		//提交表单前先判断是否还有"红字提示"
		var i;
		for(i=0;i<arrFont.length;i++){
			if(arrFont[i].style.display=="block"){
				break;
			}
		}
		//如果i==6表明所有验证通过
		if((i==6)&&(username!="")&&(password!="")&&(verification!="")){
			window.location.href="index.jsp";
		}
	};
	
};