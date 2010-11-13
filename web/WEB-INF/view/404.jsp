<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="icon" type="image/x-icon" href="/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/images/error/favicon.ico"/>" />
<meta name="robots" content="noindex,nofollow" />
<title>Nothing found for 404</title>

<style>
body {
	background: #f9fee8;
	margin: 0;
	padding: 20px;
	text-align: center;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #666666;
}

.error_page {
	width: 600px;
	padding: 50px;
	margin: auto;
}

.error_page h1 {
	margin: 20px 0 0;
}

.error_page p {
	margin: 10px 0;
	padding: 0;
}

a {
	color: #9caa6d;
	text-decoration: none;
}

a:hover {
	color: #9caa6d;
	text-decoration: underline;
}
</style>

</head>

<body class="login">
<div class="error_page"><img alt="Kidmondo_face_sad"
	src="<c:url value="/images/error/kidmondo_face_sad.gif"/>" />
<h1>We're sorry...</h1>
<p>The page or journal you are looking for cannot be found.</p>
<p><a href="<c:url value="/"/>">Return to the homepage</a></p>
</div>
</body>
</html>
<!-- Page not cached by WP Super Cache. 404. -->