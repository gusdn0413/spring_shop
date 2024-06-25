<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>카테고리 별 아이템 목록</title>
</head>
<body>
<div class="container">
    <h1 class="header-title text-center">카테고리 별 아이템 목록</h1>
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <th>상품 이름</th>
                <th>상품 가격</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemList}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>
                        <a href="/item/showOne/${item.id}" class="btn btn-outline-primary">상품 보기</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:if test="${login.role eq 2}">
        <div class="text-center mt-4">
            <a href="/item/insert/${categoryId}" class="btn btn-primary">상품 등록</a>
        </div>
    </c:if>
</div>
</body>
</html>