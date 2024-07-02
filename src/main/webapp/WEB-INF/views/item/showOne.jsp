<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>${itemDTO.name} 상품</title>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-6 ">
            <table class="table table-striped">
                <tr>
                    <th>상품 이름</th>
                    <th>${itemDTO.name}</th>
                </tr>
                <tr>
                    <th>상품 수량</th>
                    <th>${itemDTO.quantity}</th>
                </tr>
                <tr>
                    <th>가격</th>
                    <th>${itemDTO.price}</th>
                </tr>
                <tr>
                    <th>판매자 전화번호</th>
                    <th>${itemDTO.phoneNumber}</th>
                </tr>
                <tr>
                    <th>상품 내용</th>
                    <th>${itemDTO.content}</th>
                </tr>
                <c:if test="${itemDTO.memberSellerId ne login.id}">
                    <tr class="text-center">
                        <td class="text-center" colspan="3">
                            <a class="btn btn-outline-primary" href="/cart/insert/${itemDTO.id}">장바구니</a>
                            <a class="btn btn-outline-primary" href="/order/insert/${itemDTO.id}">바로구매</a>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${itemDTO.memberSellerId eq login.id}">
                    <tr class="text-center">
                        <td class="text-center" colspan="3">
                            <a class="btn btn-outline-success" href="/item/seller/update/${itemDTO.id}">수정하기</a>
                            <button class="btn btn-outline-danger" onclick="deleteItem(${itemDTO.id})">삭제하기</button>
                        </td>
                    </tr>
                </c:if>

                <tr>
                    <td colspan="3" class="text-center">
                        <a class="btn btn-outline-secondary" href="/item/showAllByCategory/${itemDTO.categoryId}">카테고리 별
                            목록</a>
                        <a class="btn btn-outline-secondary" href="/item/showAll">전체 상품 목록</a>
                        <c:if test="${itemDTO.memberSellerId eq login.id}">
                            <a class="btn btn-outline-primary"
                               href="/item/seller/showAll/${login.id}">등록 상품 목록</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
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
                    location.href = '/item/seller/delete/' + id;
                })
            }
        });
    }
</script>
</html>