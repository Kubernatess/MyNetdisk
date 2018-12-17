<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 引入自己的EL函数 -->
<%@ taglib prefix="lumlum" uri="http://www.lumlum.com/mynetdisk/EL"%>

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
<link rel="stylesheet" href="css/bootstrap-index.css" />
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


<section id="guide">
<div class="container">
<div class="row">
<span class="col-md-2">
	<form method="post" name="myform" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
	<label for="upload" class="btn btn-primary btn-lg">上传</label>
	<input type="file" name="myfile" id="upload" multiple style="display:none" />
	</form>
</span>
<span class="col-md-2"><button class="btn btn-info btn-lg">下载</button></span>
<span class="col-md-4 navbar-right"><form class="form-inline">
<div class="input-group">
<input type="text" class="form-control input-lg" style="width:300px;">
<div class="btn btn-default input-group-addon">Search</div>
</div>
</form></span>
</div>
</div>
</section>


<section id="show">
<div class="container">
<a class="col-md-2" href="#"><img src="images/folder.png" class="img-responsive"><p>Hadoop</p></a>
<a class="col-md-2" href="#"><img src="images/folder.png" class="img-responsive"><p>Hadoop</p></a>
<a class="col-md-2" href="#"><img src="images/folder.png" class="img-responsive"><p>Hadoop</p></a>
<a class="col-md-2" href="#"><img src="images/folder.png" class="img-responsive"><p>Hadoop</p></a>
<a class="col-md-2" href="#"><img src="images/folder.png" class="img-responsive"><p>Hadoop</p></a>
<a class="col-md-2" href="#"><img src="images/folder.png" class="img-responsive"><p>Hadoop</p></a>
</div>
</section>

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>