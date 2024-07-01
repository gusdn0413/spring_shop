<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>장바구니 ${cartDTO.name}</title>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-6 ">
            <table class="table table-striped">
                <tr>
                    <th>상품 이름</th>
                    <th>${cartDTO.name}</th>
                </tr>
                <tr>
                    <th>상품 수량</th>
                    <th>${cartDTO.quantity}</th>
                </tr>
                <tr>
                    <th>총가격</th>
                    <th>${cartDTO.price}</th>
                </tr>
                <tr>
                    <td><fmt:formatDate value="${cartDTO.entryDate}" pattern="yy년 MM월 dd일"/></td>
                </tr>
                <tr>
                    <th>판매자 번호</th>
                    <th>${cartDTO.memberSellerId}</th>
                </tr>

                    <tr class="text-center">
                        <td class="text-center" colspan="3">
                            <a class="btn btn-outline-primary" href="/cart/updateToOrder/${cartDTO.id}">주문</a>
                            <a class="btn btn-outline-primary" href="/cart/update/${cartDTO.id}">장바구니 수정</a>
                            <button class="btn btn-outline-danger" onclick="deleteCart(${cartDTO.id})">장바구니 삭제</button>
                        </td>
                    </tr>
                <tr>
                    <td colspan="3" class="text-center">
                        <a class="btn btn-outline-secondary"
                           href="/cart/showAll/${login.id}">목록으로</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script>
    function deleteCart(id) {
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
                    location.href = '/cart/delete/' + id;
                })
            }
        });
    }
</script>