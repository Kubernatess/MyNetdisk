<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Queue" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>display here</title>
<link rel="stylesheet" href="css/bootstrap.min.css"/>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/display.css" />
<script src="js/display.js"></script>
</head>
<body>
<section id="display">
<div class="container">
<%
//以OpenStack账号为例
		//先获取登陆账号名OpenStack
		String username=(String) request.getSession().getAttribute("username");
		//获取/directory的磁盘路径
		String rootAbsolutePath=getServletContext().getRealPath("/directory");
		//拼接 ,此时得到OpenStack的磁盘路径
		rootAbsolutePath=rootAbsolutePath+"\\"+username;
		//以OpenStack文件夹为根节点
		File root=new File(rootAbsolutePath);
		//获取file文件夹下的所有子节点
		File files[]=root.listFiles();
		//循环遍历
		for(File f:files){
			//拼接路径名
			String fileAbsolutePath=rootAbsolutePath+"\\"+f.getName();
			//System.out.println(fileAbsolutePath);
				
			//截取文件名
			StringBuilder filename=new StringBuilder(f.getName());
			if(filename.length()>=10){
				//文件名如果长度超过10,则后面使用省略号
				int start=filename.lastIndexOf(".");
				int end=filename.length();
				filename.replace(start, end, "...");
			}
			//拿到每一个file对象,判断file是文件还是文件夹
			String src=null;
			if(f.isFile())
				src="images/file.png";
			else
				src="images/folder.png";
			
			//输出文件或文件夹
			out.print("<a class='col-md-2' href='#'>");
			out.print("<input type='hidden' name=\'"+fileAbsolutePath+"\' value=\'"+fileAbsolutePath+"\'>");
			out.print("<img src=\'"+src+"\' title=\'"+f.getName()+"\' class='img-responsive'>");
			out.println("<p>"+filename+"</p></a>");
		}
		
%>
</div>
</section>

</body>
</html>