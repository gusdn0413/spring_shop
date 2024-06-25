<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>상품 목록</title>
</head>
<body>
<div class="container mt-4">
    <c:forEach items="${categoryList}" var="category">
        <div class="row mb-3">
            <div class="col text-center">
                <a href="/item/showAllByCategory/${category.id}" class="btn btn-outline-primary">${category.name} 상품 보기</a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>