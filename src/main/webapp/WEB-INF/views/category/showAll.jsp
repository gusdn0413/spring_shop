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
                <c:if test="${login.role eq 3}">
                    <a href="/category/manager/update/${category.id}" class="btn btn-outline-secondary">카테고리 수정</a>
                    <button class="btn btn-outline-danger" onclick="deleteCategory(${category.id})">삭제하기</button>
                </c:if>
            </div>
        </div>
    </c:forEach>
    <a class="btn btn-outline-secondary" href="/shop/showAll">홈으로</a>
</div>
</body>
<script>
    function deleteCategory(id) {
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
                    location.href = '/category/manager/delete/' + id;
                })
            }
        });
    }
</script>
</html>