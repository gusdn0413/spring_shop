<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../util/header.jsp" %>
<html>
<head>
    <title>아이템 목록</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<div class="container">
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <th>상품 번호</th>
                <th>상품 이름</th>
                <th>상품 가격</th>
                <th>상품 판매자 번호</th>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemList}" var="itemDTO">
                <tr>
                    <td>${itemDTO.id}</td>
                    <td>${itemDTO.name}</td>
                    <td>${itemDTO.price}</td>
                    <td>${itemDTO.memberSellerId}</td>
                    <td>
                        <button class="btn btn-outline-danger" onclick="deleteItem(${itemDTO.id})">삭제하기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row justify-content-center">
        <div class="col-12 text-center">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link" href="/item/showAll/1"> << </a>
                </li>
                <c:if test="${curPage > 5}">
                    <li class="page-item">
                        <a href="/item/showAll/${curPage - 5}" class="page-link"> < </a>
                    </li>
                </c:if>
                <c:forEach var="page" begin="${startPage}" end="${endPage}">
                    <c:choose>
                        <c:when test="${page eq curPage}">
                            <li class="page-item active">
                                <span class="page-link">${page}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a href="/item/showAll/${page}" class="page-link">${page}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${curPage < maxPage - 5}">
                    <li class="page-item">
                        <a href="/item/showAll/${curPage + 5}" class="page-link"> > </a>
                    </li>
                </c:if>
                <li class="page-item">
                    <a href="/item/showAll/${maxPage}" class="page-link"> >> </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<script>
    function deleteItem(id) {
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
                    location.href = '/manager/item/delete/' + id;
                })
            }
        });
    }
</script>
</body>
</html>