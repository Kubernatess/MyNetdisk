<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="defined" uri="http://www.lumlum.cn/lumlum" %>
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

<c:choose>
	<c:when test="${ !empty param.fileAbsolutePath }">
		<!-- 自定义标签:遍历根目录下所有文件 -->
		<defined:fetch currentDirectoryPath="${ param.fileAbsolutePath }" />
	</c:when>
	<c:otherwise>
		<defined:fetch />
	</c:otherwise>
</c:choose>

</div>
</section>

</body>
</html>