<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>아이템 목록</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous">
    </script>
    <style>
        .goodCursor:hover > td {
            cursor: pointer;
            color: blue;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <div class="row justify-content-center">
            <div class="col-8 text-center">
                <table class="table table-striped">
                    <tr>
                        <th>상품 이름</th>
                        <th>상품 가격</th>
                    </tr>
                    <c:forEach items="${itemList}" var="itemDTO">
                        <tr class="goodCursor" onclick="javascript:location.href='/item/showOne/${itemDTO.id}'">
                            <td>${itemDTO.name}</td>
                            <td>${itemDTO.price}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6" class="text-center">
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
                                            <li class="page-item">
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
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row justify-content-end">
            <div class="col-3">
                <c:if test="${categoryId != null}">
                    <a class="btn btn-outline-success" href="/item/seller/insert/${categoryId}">상품 등록</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>