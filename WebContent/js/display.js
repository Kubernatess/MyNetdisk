var fileAbsolutePath;

//对所有图片对象设置样式
window.onload=function(){
	var imgLinks=document.getElementById("display").getElementsByClassName("container")[0].getElementsByTagName('a');
	for(var i=0;i<imgLinks.length;i++){
		imgLinks[i].onfocus=function(){
			this.style.background="#33b7b22e";
			fileAbsolutePath=this.getElementsByTagName('input')[0].name;
			//设置localStorage
			window.localStorage.setItem('fileAbsolutePath',fileAbsolutePath);
		};
		imgLinks[i].onblur=function(){
			this.style.background="#FFF";
		};
		imgLinks[i].ondblclick=function(){
			//对文件磁盘路径名进行编码
			fileAbsolutePath=encodeURIComponent(fileAbsolutePath);
			window.location.href="display2.jsp?fileAbsolutePath="+fileAbsolutePath;
		};
	}
	

};
	