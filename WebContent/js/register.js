// JavaScript Document
window.onload=function(){
	//更改皮肤事件
	var favcolor=document.getElementById('favcolor');
	favcolor.onchange=function(){
		document.body.style.backgroundColor=favcolor.value;
	};
	
	
	//获取所有对象
	var oUsername=document.getElementById('username');
	var oPassword=document.getElementById('password');	
	var oComfirm=document.getElementById('comfirm');	
	var oBtn=document.getElementById('submission');
	var username;
	var password;
	var comfirm;
	//获取font标签数组
	var arrFont=document.getElementsByTagName("font");
	
	
	//对用户输入框、密码框、确认密码框设置失去焦点事件
	oUsername.onblur=oPassword.onblur=oComfirm.onblur=function(){
		//获取所有对象的值
		username=oUsername.value;
		password=oPassword.value;
		comfirm=oComfirm.value;
		//若用户名不为空,隐藏font标签
		if(username!=""){
			arrFont[0].style.display="none";
		}
		//若密码不为空,隐藏font标签
		if(password!=""){
			arrFont[2].style.display="none";
		}
		//若确认密码不为空,隐藏font标签
		if(comfirm!=""){
			arrFont[4].style.display="none";
		}
		
		//判断用户名是否存在
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				if(eval(xhr.responseText)==0){
					arrFont[1].style.display="none";
				}
				else {
					arrFont[1].style.display="block";
				}
			}
		};
		xhr.open("get","/MyNetdisk/registerServlet2?username="+username);
		xhr.send(null);
		
		//密码至少6位
		if((password.length<6)&&(password!="")){
			arrFont[3].style.display="block";
		}
		else arrFont[3].style.display="none";
		
		//输入密码与确认密码必须一致
		if(comfirm!=password&&password!=""&&comfirm!=""){
			arrFont[5].style.display="block";
		}
		else arrFont[5].style.display="none";
		
	};

	
	//提交按钮前先判断是否还有未完成的表单项
	oBtn.onclick=function(){
		//获取所有对象的值
		username=oUsername.value;
		password=oPassword.value;
		comfirm=oComfirm.value;
		if(username==""&&(arrFont[1].style.display=="none"))
			arrFont[0].style.display="block";
		else 
			arrFont[0].style.display="none";
		
		if(password==""&&(arrFont[3].style.display=="none"))
			arrFont[2].style.display="block";
		else 
			arrFont[2].style.display="none";
		
		if(comfirm==""&&(arrFont[5].style.display=="none"))
			arrFont[4].style.display="block";
		else
			arrFont[4].style.display="none";
		
		
		//提交表单前先判断是否还有"红字提示"
		var i;
		for(i=0;i<arrFont.length;i++){
			if(arrFont[i].style.display=="block"){
				break;
			}
		}
		//如果i==6表明所有验证通过
		if((i==6)&&(username!="")&&(password!="")&&(comfirm!="")){
			var choose=confirm("确定注册新用户?");
			if(choose){
				document.registerForm.submit();
			}
		}
		
		
	};
}