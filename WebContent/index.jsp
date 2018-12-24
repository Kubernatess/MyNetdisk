<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="defined" uri="http://www.lumlum.cn/lumlum" %>


<!-- 
	给登陆用户赋予通行证
	若用户没有登陆而直接访问index.jsp,那么跳转到login.html
 -->
<c:if test="${ empty sessionScope.username }">
	<script>window.location.href="login.html";</script>
</c:if>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>index</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/index.css" />
<script src="js/index.js"></script>
</head>
<body>
<!--导航-->
<nav class="navbar navbar-default navbar-fixed-top">
<div class="container">

<!--小屏幕导航按和Logo-->
<div class="navbar-header">
<!--Logo-->
<p class="navbar-brand">LumLum</p>
<!--小屏幕导航按-->
<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
</div>

<!--导航-->
<div class="navbar-collapse collapse">
<ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          welcome to <span class="dropdown-toggle" data-toggle="dropdown"> ${ sessionScope.username }<span class="caret"></span></span>
          <ul class="dropdown-menu">
            <li><a href="#">Account</a></li>
            <li><a href="#">capacity</a></li>
            <li><a href="#">feedback</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">settig</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#" id="logout">logout</a></li>
          </ul>
        </li>
</ul>
</div>
</div>
</nav>


<div id="optionGroup" class="container">
<div class="row">
<span class="col-md-2">
	<form method="post" name="myform" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
	<label for="upload" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-arrow-up"></span>上传</label>
	<input type="file" name="myfile" id="upload" multiple style="display:none" />
	</form>
</span>
<span class="col-md-2"><button class="btn btn-info btn-lg" id="download"><span class="glyphicon glyphicon-download-alt"></span>下载</button></span>
<span class="col-md-4 navbar-right"><form class="form-inline">
<div class="input-group">
<input type="text" class="form-control input-lg" style="width:300px;">
<div class="btn btn-default input-group-addon">Search</div>
</div>
</form></span>
</div>
</div>


<div class="container">
<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span>新建文件夹</button>
<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span>删除文件</button>
<span class="dropdown">
<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">排序方式<span class="caret"></span></button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">名称</a></li>
    <li><a href="#">大小</a></li>
    <li><a href="#">文件类型</a></li>
    <li><a href="#">修改日期</a></li>
  </ul>
</span>
</div>


<div id="fileLocation" class="container">
<button type="button" id="back">返回</button>
<c:choose>
	<c:when test="${ !empty param.subDirectoryPath }">
		<!-- 如果index.jsp有传递subDirectoryPath参数,遍历子目录 -->
		<defined:list subDirectoryPath="${ param.subDirectoryPath }" />
	</c:when>
	<c:otherwise>
		<!-- 如果index.jsp没有传递subDirectoryPath参数,遍历根目录 -->
		<defined:list />
	</c:otherwise>
</c:choose>
</div>


<section id="display">
<div class="container">
<c:choose>
	<c:when test="${ !empty param.subDirectoryPath }">
		<defined:fetch subDirectoryPath="${ param.subDirectoryPath }" />
	</c:when>
	<c:otherwise>
		<defined:fetch />
	</c:otherwise>
</c:choose>
</div>
</section>


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>