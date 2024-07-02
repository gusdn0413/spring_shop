<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../util/header.jsp" %>

<html>
<head>
    <title>통합 관리</title>
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col text-center">
            <a href="/manager/member/showAll" class="btn btn-primary btn-lg">회원 목록 보기</a>
        </div>
        <div class="col text-center">
            <a href="/manager/item/showAll" class="btn btn-primary btn-lg">상품 목록 보기</a>
        </div>
        <div class="col text-center">
            <a href="/manager/order/showAll" class="btn btn-primary btn-lg">주문 목록 보기</a>
        </div>
    </div>
</div>
<hr/>

<c:if test="${login.role eq 3}">
    <div class="row">
        <div class="col text-center">
            <a class="btn btn-outline-success" href="/category/insert">카테고리 추가</a>
        </div>
    </div>
</c:if>
</div>
</body>
</html>