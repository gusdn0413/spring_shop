<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-6 ">
            <table class="table table-striped">
                <tr>
                    <th>회원 번호</th>
                    <th>${memberDTO.id}</th>
                </tr>
                <tr>
                    <th>email</th>
                    <th>${memberDTO.email}</th>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <th>${memberDTO.nickname}</th>
                </tr>

                <tr>
                    <th colspan="2" class="text-center">주소</th>
                </tr>
                <tr>
                    <th colspan="2" class="text-center">${memberDTO.address}</th>
                </tr>
                <tr>
                    <th colspan="2" class="text-center">전화번호</th>
                </tr>
                <tr>
                    <th colspan="2" class="text-center">${memberDTO.phoneNumber}</th>
                </tr>

                <c:if test="${memberDTO.role eq 1 }">
                    <tr class="text-center">
                        <td class="text-center" colspan="3">
                            <a class="btn btn-outline-primary" href="/cart/showAll/${memberDTO.id}">장바구니</a>
                            <a class="btn btn-outline-primary" href="/order/showAll/${memberDTO.id}">주문목록</a>
                        </td>
                    </tr>
                </c:if>

                <c:if test="${memberDTO.role eq 2 }">
                    <tr class="text-center">
                        <td class="text-center" colspan="3">
                            <a class="btn btn-outline-primary" href="/item/seller/showAll/${memberDTO.id}">등록 상품 목록</a>
                            <a class="btn btn-outline-primary" href="/order/seller/showAll/${memberDTO.id}">주문받은 상품
                                목록</a>
                        </td>
                    </tr>
                </c:if>

                <c:if test="${login.role ne 3}">
                    <tr class="text-center">
                        <td class="text-center" colspan="3">
                            <a class="btn btn-outline-success" href="/member/update/${memberDTO.id}">수정하기</a>
                            <button class="btn btn-outline-danger" onclick="deleteMember(${memberDTO.id})">삭제하기</button>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="3" class="text-center">
                        <a class="btn btn-outline-secondary" href="/shop/showAll">목록으로</a>
                    </td>
                </tr>
                <c:if test="${memberDTO.role eq 3 }">
                    <tr class="text-center">
                        <td class="text-center" colspan="3">
                            <a class="btn btn-outline-primary" href="/manager/showAll">통합 관리</a>
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>
<script>
    function deleteMember(id) {
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
                    location.href = '/member/delete/' + id;
                })
            }
        });
    }
</script>
</body>
</html>