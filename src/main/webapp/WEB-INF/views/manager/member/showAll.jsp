<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="../../util/header.jsp" %>
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
                <th>회원 번호</th>
                <th>회원 이름</th>
                <th>회원 등급</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${memberList}" var="memberDTO">
                <tr>
                    <td>${memberDTO.id}</td>
                    <td>${memberDTO.name}</td>

                    <c:if test="${memberDTO.role eq 1}">
                        <td>일반고객</td>
                    </c:if>

                    <c:if test="${memberDTO.role eq 2}">
                        <td>판매자</td>
                    </c:if>

                    <c:if test="${memberDTO.role eq 3}">
                        <td>관리자</td>
                    </c:if>
                    <td>
                        <a href="/manager/member/showOne/${memberDTO.id}" class="btn btn-outline-primary">상세 보기</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/manager/showAll">관리 목록</a>
    </div>
</div>
</body>
</html>