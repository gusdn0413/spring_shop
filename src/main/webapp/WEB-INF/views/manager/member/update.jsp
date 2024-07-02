<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../util/header.jsp" %>
<html>
<head>
    <title>회원 정보 수정</title>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <form action="/manager/member/update/${memberDTO.id}" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="nickname">닉네임</label>
                    <input type="text" name="nickname" id="nickname" class="form-control" value="${memberDTO.nickname}">

                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="name">이름</label>
                    <input type="text" name="name" id="name" class="form-control" value="${memberDTO.name}">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="role">1. 일반 고객 2. 판매자</label>
                    <input type="number" name="role" id="role" class="form-control" value="${memberDTO.role}">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4 text-center">
                    <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="정보 수정">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>