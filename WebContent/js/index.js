window.onload=function(){
	//上传文件,提交到/MyNetdisk/upload
	var btn_upload=document.myform.myfile;
	btn_upload.onchange=function(){
		document.myform.submit();
	}
	
	//注销方法
	var logout=document.getElementById("logout");
	logout.onclick=function(){
		window.location.href="/MyNetdisk/sessionServlet";
	};
	
	//对所有图片对象设置样式
	var imgLinks=document.getElementById("show").getElementsByClassName("container")[0].getElementsByTagName('a');
	for(var i=0;i<imgLinks.length;i++){
		imgLinks[i].onclick=function(){
			this.style.background="#33b7b22e";
		};
		imgLinks[i].onblur=function(){
			this.style.background="#FFF";
		};
	}
	
	
}