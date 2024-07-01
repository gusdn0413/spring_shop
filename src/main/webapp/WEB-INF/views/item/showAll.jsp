<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>아이템 목록</title>
</head>
<body>
<div class="container">
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <th>상품 이름</th>
                <th>상품 가격</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemList}" var="itemDTO">
                <tr>
                    <td>${itemDTO.name}</td>
                    <td>${itemDTO.price}</td>
                    <td>
                        <a href="/item/showOne/${itemDTO.id}" class="btn btn-outline-primary">상품 보기</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>