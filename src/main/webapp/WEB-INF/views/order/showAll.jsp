<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>주문 목록</title>
</head>
<body>
<div class="container">
    <h1 class="header-title text-center">주문 목록</h1>
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <th>상품 이름</th>
                <th>구매 날짜</th>
                <th>상품 수량</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderList}" var="orderDTO">
                <tr>
                    <td>${orderDTO.name}</td>
                    <td><fmt:formatDate value="${orderDTO.entryDate}" pattern="yy년 MM월 dd일"/></td>
                    <td>
                        <a href="/order/showOne/${orderDTO.id}" class="btn btn-outline-primary">상세 보기</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>