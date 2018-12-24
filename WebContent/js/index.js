var fileRelativePath;

window.onload=function(){
	//第一次访问index.jsp,需要记录下根目录
	var subDirectoryPath=window.localStorage.getItem('subDirectoryPath');
	if(subDirectoryPath==""){
		window.localStorage.setItem('subDirectoryPath',"");
	}
	
	
	//上传文件,提交到/MyNetdisk/upload
	var btn_upload=document.getElementById("upload");
	btn_upload.onchange=function(){
		
		//改用Ajax提交表单
		document.myform.submit();
	}
	
	//注销方法
	var logout=document.getElementById("logout");
	logout.onclick=function(){
		localStorage.clear();
		window.location.href="/MyNetdisk/loginServlet";
	};
	
	
	//下载操作
	var download=document.getElementById("download");
	download.onclick=function(){
		//fileRelativePath在文件被选中时已经被记录
		var fileRelativePath=window.localStorage.getItem('fileRelativePath');
		var encodingFileRelativePath=encodeURIComponent(fileRelativePath);
		//请求下载
		window.location.href="/MyNetdisk/DownloadServlet?fileRelativePath="+encodingFileRelativePath;
	};
	
	
	//获取display区域里所有的a标签
	var imageHyperlink=document.getElementById("display").getElementsByClassName("container")[0].getElementsByTagName('a');
	for(var i=0;i<imageHyperlink.length;i++){
		//对所有图片超链接设置样式
		imageHyperlink[i].onfocus=function(){
			this.style.background="#33b7b22e";
			//获取该文件的路径位置
			fileRelativePath=this.getElementsByTagName('input')[0].name;
			//记录该文件的路径位置
			window.localStorage.setItem('fileRelativePath',fileRelativePath);
		};
		imageHyperlink[i].onblur=function(){
			this.style.background="#FFF";
		};
		
		//双击文件夹进入下一子目录
		imageHyperlink[i].ondblclick=function(){
			//fileRelativePath在单击事件的时候就已经捕获到了
			var index=fileRelativePath.lastIndexOf(".");
			var encodingFileRelativePath=encodeURIComponent(fileRelativePath);
			//index返回-1说明是文件夹
			if(index==-1){
				//记录当前文件下一子目录路径
				window.localStorage.setItem('subDirectoryPath',fileRelativePath);	
				
				//刷新页面
				window.location.href="index.jsp?subDirectoryPath="+encodingFileRelativePath;
			}
			//如果是文件,请求下载
			else{
				window.location.href="/MyNetdisk/DownloadServlet?fileRelativePath="+encodingFileRelativePath;
			}
			
		};
	}
	
	var back=document.getElementById("back");
	back.onclick=function(){
		var subDirectoryPath=window.localStorage.getItem("subDirectoryPath");
		var index=subDirectoryPath.lastIndexOf("\\");		
		subDirectoryPath=subDirectoryPath.substring(0,index);
		window.localStorage.setItem('subDirectoryPath',subDirectoryPath);	
		if(subDirectoryPath=="\\"){
			subDirectoryPath="";
		}
		var encodingSubDirectoryPath=encodeURIComponent(subDirectoryPath);
		window.location.href="index.jsp?subDirectoryPath="+encodingSubDirectoryPath;
	};
	
}