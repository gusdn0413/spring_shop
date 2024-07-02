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
                <th>상품 이름</th>
                <th>주문자 번호</th>
                <th>구매 날짜</th>
                <th>상품 수량</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderList}" var="orderDTO">
                <tr>
                    <td>${orderDTO.name}</td>
                    <td>${orderDTO.memberCustomerId}</td>
                    <td><fmt:formatDate value="${orderDTO.entryDate}" pattern="yy년 MM월 dd일"/></td>
                    <td>
                        <button class="btn btn-outline-danger" onclick="deleteOrder(${orderDTO.id})">삭제하기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/manager/showAll">관리 목록</a>
    </div>
</div>
<script>
    function deleteOrder(id) {
        console.log(id);
        Swal.fire({
            title: '정말 삭제하시겠습니까?',
            showCancelButton: true,
            confirmButtonText: '삭제하기',
            cancelButtonText: '취소',
            icon: 'warning'
        }).then((result) => {
            console.log(result);
            if (result.isConfirmed) {
                Swal.fire({
                    title: '삭제되었습니다.'
                }).then((result) => {
                    location.href = '/manager/order/delete/' + id;
                })
            }
        });
    }
</script>
</body>
</html>